package com.sk.brook.task.weibo.firefox;

import com.sk.brook.constants.CookieConstants;
import com.sk.brook.constants.WebDriverConstants;
import com.sk.brook.dao.domain.CommentInfo;
import com.sk.brook.dao.domain.WebCookie;
import com.sk.brook.dao.domain.WebInfo;
import com.sk.brook.dao.domain.WebTask;
import com.sk.brook.service.holder.ServiceHolder;
import com.sk.brook.service.util.CommentPerNum;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.stream.events.Comment;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by songk on 17/11/22.
 */
public class AutoCommentRunnable implements Runnable {

    private static Logger logger = LoggerFactory.getLogger(AutoCommentRunnable.class);

    private List<WebCookie> cookieList;
    private WebTask webTask;
    private String mainIndex;
    private String meIndex;
    private WebInfo webInfo;
    private WebDriver driver;
    private String comment;
    private ServiceHolder serviceHolder;

    public AutoCommentRunnable(WebInfo webInfo, CommentInfo commentInfo,  WebTask webTask, ServiceHolder serviceHolder) {
        super();
        this.mainIndex = webInfo.getWebMainIndex();
        this.meIndex = webInfo.getWebMeIndex();
        this.comment = commentInfo.getCommentDes();
        this.webInfo = webInfo;
        this.webTask = webTask;
        this.serviceHolder = serviceHolder;
    }


    @Override
    public void run() {
        try {
            if (this.isRunnable()) {
                FirefoxOptions options = new FirefoxOptions();
                options.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT,true);
                FirefoxProfile profile = new FirefoxProfile();
                profile.setPreference("layout.css.devPixelsPerPx","0.7");
                options.setProfile(profile);
                //options.setBinary("D:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
                System.setProperty("webdriver.gecko.driver",
                        WebDriverConstants.fire_fox_0_19_1);
                driver = new FirefoxDriver(options);
                driver.manage().window().setSize(new Dimension(1024 ,1024));

//                driver.manage().window().maximize();
                this.executeStart();
            }
        } catch (Exception e) {
            logger.error("自动微博评论异常，评论内容：" + this.toString(), e);
        } finally {
            if (driver != null) {
                driver.close();
            }
            this.successUnlock();
        }

    }

    private void executeStart() {
        //打开网站主页面
        this.openMainIndex();
        //设置登陆cookie信息
        this.setCookie();
        //打开我的主页面
        boolean meFlag = this.openMeIndex();
        //执行评论
        if(meFlag){
            this.executeComment();
        }
    }

    /**
     * 打开网站主页
     */
    private void openMainIndex() {
        //与浏览器同步非常重要，必须等待浏览器加载完毕
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        logger.info("准备打开weibo主页。。。。。。" + this.mainIndex);
        driver.get(this.mainIndex);
        driver.manage().window().setSize(new Dimension(1920, 1080));
        driver.manage().deleteAllCookies();
        this.sleep3s();
        logger.info("打开微博主页面。。。。。" + driver.getCurrentUrl());
    }


    /**
     * 设置登陆cookie
     */
    private void setCookie() {
        logger.info("设置Cookie开始");
        List<WebCookie> webCookies = this.serviceHolder.getWebCookieService().findWebCookie(webTask.getWebId());
        if(webCookies == null || webCookies.size() == 0){
            return;
        }else{
            this.cookieList = webCookies;
        }
        //设置登陆状态
        if (cookieList != null) {
            for (WebCookie webCookie : cookieList) {
                try {
                    Cookie cookie = new Cookie(webCookie.getCookieName(), webCookie.getCookieValue(), webCookie.getWebDomain(), webCookie.getPath(), webCookie.getExpireDate());
                    driver.manage().addCookie(cookie);
                } catch (Exception e) {
                    logger.error("写入cookie异常", e);
                }
            }
            this.sleep3s();
        }
    }


    /**
     * 打开我的主页面
     */
    private boolean openMeIndex() {
        driver.get(this.meIndex);
        this.sleep(5000);
        logger.info("打开的我的微博页面。。。。。" + driver.getCurrentUrl());
        String url = driver.getCurrentUrl();
        if (webInfo.getWebLogin() != null && ( url.contains(webInfo.getWebLogin()) || url.contains("passport"))) {
            if(this.submitWeb()){
                driver.get(this.meIndex);
                this.sleep3s();
                if(driver.getCurrentUrl().contains("login")){
                    return true;
                }
            }
        }
        return true;
    }

    /**
     * 执行评论
     */
    private void executeComment() {
        /**
         * 获取每一条评论下的底部评论区（收藏、转发、评论、点赞）
         */
        List<WebElement> elements = driver.findElements(By.className("WB_feed_handle"));
        this.sleep3s();
        int successCommentCount = 0;
        for (WebElement webFeedHandle : elements) {
            //获取评论div
            List<WebElement> stxt = webFeedHandle.findElements(By.className("S_txt2"));
            if (stxt == null || stxt.size() == 0 || stxt.size() < 3) {
                return;
            }
            boolean success = false;
            try {
                success = this.handleComment(stxt.get(2));
                //休眠30S~50S
                this.sleep3s();
            } catch (Exception e) {
                logger.error("写入评论异常", e);
            }
            if (success) {
                successCommentCount++;
                this.serviceHolder.getWebTaskMapper().addSuccessNum(this.webInfo.getId());
            }
            if (successCommentCount == CommentPerNum.WEIBO_COMMENT_NUM) {
                break;
            }
        }
    }

    //写入评论内容，提交评论，关闭评论区
    private boolean handleComment(WebElement commentElement) {
        boolean success = false;
        //点击评论
        commentElement.click();
        this.sleep3s();
        //获取评论textarea
        WebElement pub = driver.findElement(By.className("WB_publish"));
        if (pub != null) {
            WebElement des = pub.findElement(By.tagName("textarea"));
            if (des != null) {
                des.sendKeys(this.comment);
                List<WebElement> submit = pub.findElements(By.tagName("a"));
                if (submit != null && submit.size() > 0) {
                    submit.get(0).click();
                    this.sleep3s();
                    //关闭评论区
                    commentElement.click();
                    logger.info("评论成功");
                    success = true;
                }
            }
        }
        return success;
    }


    private void sleep3s() {
        try {
            Thread.sleep(3000l);
        } catch (Exception e) {
            logger.error("休眠3S异常", e);
        }

    }

    private void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (Exception e) {
            logger.error("休眠" + time + "S异常", e);
        }

    }

    @Override
    public String toString() {
        return "AutoCommentRunnable{" +
                "cookieList=" + cookieList +
                ", mainIndex='" + mainIndex + '\'' +
                ", meIndex='" + meIndex + '\'' +
                ", driver=" + driver +
                ", comment='" + comment + '\'' +
                '}';
    }

    /**
     * 用户名，密码，登陆
     * @return
     */
    public boolean submitWeb() {
        //driver.get(webInfo.getWebLogin());
        logger.info("已经打开登陆页面：" + driver.getCurrentUrl());
        this.sleep(10000);
        WebElement loginName = driver.findElement(By.id("loginname"));
        this.sleep(10000);
        loginName.sendKeys(webInfo.getUserName());
        this.sleep(5000);
        WebElement loginPassword = driver.findElement(By.name("password"));
        this.sleep(10000);
        loginPassword.sendKeys(webInfo.getUserPwd());
        this.sleep(5000);
        WebElement submit = driver.findElement(By.cssSelector("a.W_btn_a.btn_32px"));
        System.out.print(submit.getText());
//        WebElement submit = driver.findElement(By.id("loginAction"));
        this.sleep(5000);
        submit.click();
        this.sleep(10000);
        String url = driver.getCurrentUrl();
        if (url.contains(webInfo.getWebLogin())) {
            return false;
        }
        this.getAndSaveCookie();
        return true;
    }

    /**
     * 从浏览器获取cookie，并将cookie信息存储到数据库
     */
    private void getAndSaveCookie(){
        this.serviceHolder.getWebCookieMapper().deleteByWebId(this.webInfo.getId());
        Calendar cl = Calendar.getInstance();
        cl.add(Calendar.YEAR,1);

        for(String cookieName : CookieConstants.WEB_COOKIE_NAME_SET){
            Cookie cookie = driver.manage().getCookieNamed(cookieName);
            WebCookie webCookie = new WebCookie();
            if(cookie != null){
                webCookie.setCookieName(cookie.getName());
                webCookie.setCookieValue(cookie.getValue());
                webCookie.setExpireDate(cl.getTime());
                webCookie.setPath(cookie.getPath());
                String domain = cookie.getDomain();
                if(domain.startsWith(".")){
                    webCookie.setWebDomain(domain.substring(1));
                }else{
                    webCookie.setWebDomain(cookie.getDomain());
                }
                webCookie.setWebId(this.webInfo.getId());
                this.serviceHolder.getWebCookieMapper().insert(webCookie);
            }
        }
    }

    /**
     * 获取指定区间的
     *
     * @param min
     * @param max
     * @return
     */
    private long getRandom(int min, int max) {
        Random random = new Random();
        int s = random.nextInt(max) % (max - min + 1) + min;
        return s;
    }

    private boolean isRunnable() {
        int i = this.serviceHolder.getWebTaskMapper().lockTask(this.webTask.getWebId());
        if (i == 1) {
            return true;
        }
        return false;
    }

    private boolean successUnlock() {
        int i = this.serviceHolder.getWebTaskMapper().unlockTask(this.webTask.getWebId());
        if (i == 1) {
            return true;
        }
        return false;
    }
}
