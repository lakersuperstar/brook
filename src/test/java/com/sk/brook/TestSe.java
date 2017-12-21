package com.sk.brook;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by songk on 17/11/20.
 */
public class TestSe {

    private static String weiboMainIndex = "https://weibo.com";

    private static String weiboMeIndex = "https://weibo.com/lakersuperstar/home?wvr=5";

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
        dcaps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,"/Users/songk/WorkTools/phantomjs-2.1.1-macosx/bin/phantomjs");

//        PhantomJSDriver driver = new PhantomJSDriver(dcaps);

        System.setProperty("webdriver.gecko.driver",
                "/Users/songk/WorkTools/geckodriver");
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        //与浏览器同步非常重要，必须等待浏览器加载完毕
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println("打开weibo主页。。。。。。"+weiboMainIndex);
        driver.get(weiboMainIndex);
        driver.manage().window().setSize(new Dimension(1920, 1080));
        driver.manage().deleteAllCookies();
        Thread.sleep(3000l);
        //设置登陆状态
        driver.manage().addCookie(new Cookie("_T_WM","154c2750c0ef26db99f10be406d43ae2","weibo.com","/",DateTempUtil.parseString("2017-12-15 07:09:57")));
        driver.manage().addCookie(new Cookie("SUHB","00xogan57uOHnI","weibo.com","/",DateTempUtil.parseString("2017-12-15 07:09:57")));
        driver.manage().addCookie(new Cookie("SUB","_2A253FqnODeRhGeRP6FoS8C7JyTmIHXVU-DeGrDV6PUJbktANLRPskW1NHetkT5wkuTD3AoDSCgHCR_6G8T7vfMRO","weibo.com","/",DateTempUtil.parseString("2018-11-20 13:33:18")));
        driver.manage().addCookie(new Cookie("SCF","AvCXSpF3gUCW6NnWRlUbmYFi9vpTaW4pq3oNrH_LgAXKRb9WwzMR6eE2-nkitkXnew5VeyXOezFS1PdOGvnz_4c.","weibo.com","/",DateTempUtil.parseString("2017-12-15 07:09:57")));
        driver.manage().addCookie(new Cookie("SSOLoginState","1511320939","weibo.com","/",DateTempUtil.parseString("2017-11-22 10:10:10")));
        //设置微博的分组
        driver.manage().addCookie(new Cookie("H5_INDEX_TITLE","%E5%90%8D%E4%BA%BA%E6%98%8E%E6%98%9F","weibo.com","/",DateTempUtil.parseString("2017-11-22 10:10:10")));

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
        List<WebElement> elements = driver.findElements(By.className("card-list"));
        for(WebElement e: elements){
            List<WebElement> es = e.findElements(By.ByTagName.tagName("footer"));
            if(es == null || es.size() == 0){
                continue;
            }
            for(WebElement e2 : es){
                List<WebElement> es3 = e2.findElements(By.tagName("a"));
                if(es3 == null || es3.size() == 0){
                    continue;
                }
                System.out.println("微博正文页。。。。。。。"+driver.getCurrentUrl());
                es3.get(1).click();
                Thread.sleep(5000l);
//                System.out.println(driver.getPageSource());
                List<WebElement> es7 = driver.findElements(By.tagName("textarea"));

                if(es7 == null || es7.size() == 0){
                    List<WebElement> es6 = driver.findElements(By.tagName("footer"));
                    System.out.println(es6.size());
                    if(es6 != null || es6.size() != 0){
                        List<WebElement> es8 = es6.get(0).findElements(By.tagName("div"));
                        if(es8 != null && es8.size() > 2){
                            es8.get(1).click();
                        }

                    }
                    Thread.sleep(5000l);
                    System.out.println("===---=++++++++++++++++++");
//                    System.out.println(driver.getPageSource());
                }
                System.out.println("评论页面。。。。。。。"+driver.getCurrentUrl());
                List<WebElement> es4 = driver.findElements(By.tagName("textarea"));
                es4.get(0).sendKeys("ping guo https://item.taobao.com/item.htm?a123123123=b12312123sadfafsvadf3&spm=686.1000925.0.0.28dc0902qygnvq&c12312131232312311212323=d123123123123123&id=560462534538");
                Thread.sleep(5000l);
//                System.out.println(driver.getPageSource());
//                System.out.println(driver.getCurrentUrl());
                break;
            }
//            System.out.println(driver.getCurrentUrl());
            driver.findElement(By.tagName("a")).click();
            Thread.sleep(5000l);
            System.out.println("提交评论后跳转页面。。。。。"+driver.getCurrentUrl());
            Thread.sleep(5000l);
            System.out.println("第"+(i+1)+"次结束。");
            break;

        }
        }

        driver.close();
    }
}
