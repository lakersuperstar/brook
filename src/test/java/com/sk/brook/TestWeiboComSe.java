package com.sk.brook;

import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.awt.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by songk on 17/11/20.
 */
public class TestWeiboComSe {

    private static String weiboMainIndex = "https://weibo.com";

    private static String weiboMeIndex = "https://weibo.com/mygroups?gid=201105160214813419&wvr=6&leftnav=1";

    public static void main(String[] args) throws  Exception{
        //设置必要参数
        DesiredCapabilities dcaps = new DesiredCapabilities();
        //ssl证书支持
//        dcaps.setCapability("acceptSslCerts", true);
//        //截屏支持
//        dcaps.setCapability("takesScreenshot", true);
//        //css搜索支持
//        dcaps.setCapability("cssSelectorsEnabled", true);
        //js支持
        dcaps.setJavascriptEnabled(true);
        //驱动支持
        dcaps.setCapability("webdriver.firefox.bin","D:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");

//        PhantomJSDriver driver = new PhantomJSDriver(dcaps);

        //System.setProperty("webdriver.gecko.driver",
          //      "/Users/songk/WorkTools/geckodriver");


        FirefoxOptions options = new FirefoxOptions();

        options.setBinary("D:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
        System.setProperty("webdriver.gecko.driver",
                "D:\\tools\\geckodriver.exe");
        System.setProperty("webdriver.gecko.driver",
                "D:\\tools\\geckodriver.exe");
        WebDriver  driver = new FirefoxDriver(options);
        driver.manage().window().maximize();
        //与浏览器同步非常重要，必须等待浏览器加载完毕
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println("打开weibo主页。。。。。。"+weiboMainIndex);
        driver.get(weiboMainIndex);
        driver.manage().window().setSize(new Dimension(1920, 1080));
        driver.manage().deleteAllCookies();
        Thread.sleep(3000l);
        //设置登陆状态
        driver.manage().addCookie(new Cookie("ALF","1543287767","weibo.com","/",DateTempUtil.parseString("2017-12-15 07:09:57")));
        driver.manage().addCookie(new Cookie("SUHB","0YhQWQ9QEtye-0","weibo.com","/",DateTempUtil.parseString("2017-12-15 07:09:57")));
        driver.manage().addCookie(new Cookie("SUB","_2A253H_AIDeRhGeRP6FoS8C7JyTmIHXVUbWbArDV8PUNbmtANLUXjkW9NUDujMk3mDOIZHK5bEP7Hz68u0Cp_GP01","weibo.com","/",DateTempUtil.parseString("2018-11-29 13:33:18")));
        driver.manage().addCookie(new Cookie("SSOLoginState","1511751768","weibo.com","/",DateTempUtil.parseString("2017-11-29 10:10:10")));
        driver.manage().addCookie(new Cookie("YF-V5-G0","a2489c19ecf98bbe86a7bf6f0edcb071","weibo.com","/",DateTempUtil.parseString("2017-11-25 10:10:10")));
        driver.manage().addCookie(new Cookie("SUBP","0033WrSXqPxfM725Ws9jqgMF55529P9D9WhvHxVEz44ocgLix__fd-by5JpX5KMhUgL.Fozpe0n0eh5feo-2dJLoI7Ljqg_LMrSXqPzt","weibo.com","/",DateTempUtil.parseString("2017-11-29 10:10:10")));



        //设置微博的分组
//        driver.manage().addCookie(new Cookie("H5_INDEX_TITLE","%E5%90%8D%E4%BA%BA%E6%98%8E%E6%98%9F","weibo.com","/",DateTempUtil.parseString("2017-11-22 10:10:10")));

        //        System.out.println(driver.getPageSource());
        Thread.sleep(3000l);
//        driver.get("https://m.weibo.cn/");
//        Thread.sleep(5000l);
//        System.out.println(driver.getPageSource());
//        System.out.println(driver.manage().getCookies());
//        WebElement loginName = driver.findElement(By.id("loginName"));
//        System.out.println(loginName);
//        loginName.sendKeys("lakersuperstar@163.com");
//        Thread.sleep(10000l);
//        WebElement loginPassword = driver.findElement(By.id("loginPassword"));
//        loginPassword.sendKeys("SongK198996");
//        Thread.sleep(10000l);
//        WebElement submit = driver.findElement(By.id("loginAction"));
//        submit.submit();
//        Thread.sleep(10000l);
//        driver.getCurrentUrl();
//        System.out.println(driver.getCurrentUrl());
//        System.out.println(driver.getPageSource());
//
        for(int i = 0;i<10;i++){
            System.out.println("第"+(i+1)+"次开始。");
            driver.get(weiboMeIndex);
            Thread.sleep(5000l);
            System.out.println("打开我的微博页面。。。。。"+driver.getCurrentUrl());

        List<WebElement> elements = driver.findElements(By.className("WB_feed_handle"));
        for(WebElement e: elements){
            List<WebElement> es = e.findElements(By.className("S_txt2"));
            if(es == null || es.size() == 0 || es.size() < 3){
                continue;
            }
            es.get(2).click();

            Thread.sleep(3000l);

            WebElement pub = driver.findElement(By.className("WB_publish"));
//            driver.
//            driver.switchTo().activeElement().sendKeys("123123");
//            web.sendKeys("123123");
//            WebElement pub = e.findElement(By.xpath("//div[1]/../.."));
//            System.out.println(pub.getText());
            if(pub != null) {
                WebElement des = pub.findElement(By.tagName("textarea"));
                if(des != null){
                    des.sendKeys("123123123");
                    List<WebElement> submit = pub.findElements(By.tagName("a"));
                    if(submit != null && submit.size()>0){
                        submit.get(0).click();
                        Thread.sleep(3000l);
                        es.get(2).click();
                    }
                }
            }



        }
        }

        driver.close();
    }
}
