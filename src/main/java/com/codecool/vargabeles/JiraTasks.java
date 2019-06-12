package com.codecool.vargabeles;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class JiraTasks {

    private WebDriver driver;
    private WebDriverWait wait;


    public WebDriver getDriver() {
        return driver;
    }

    public JiraTasks() {
        System.setProperty("webdriver.chrome.driver", System.getenv("webdriverRoute"));
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 60);
    }

    public boolean login() {
        driver.navigate().to("https://jira.codecool.codecanvas.hu/secure/Dashboard.jspa");
        driver.findElement(By.id("login-form-username")).sendKeys(System.getenv("username"));
        driver.findElement(By.id("login-form-password")).click();
        driver.findElement(By.id("login-form-password")).sendKeys(System.getenv("password"));
        driver.findElement(By.id("login")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("header-details-user-fullname")));
        return driver.findElement(By.id("header-details-user-fullname")) != null;
    }


    public boolean logout() {
        try {
            driver.findElement(By.id("header-details-user-fullname")).isDisplayed();
        } catch (Exception e) {
            login();
        }
        driver.findElement(By.id("header-details-user-fullname")).click();
        driver.findElement(By.id("log_out")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"content\"]/div/div/section/div/div/p[2]/a")));
        return driver.findElement(By.xpath("//*[@id=\"content\"]/div/div/section/div/div/p[2]/a")) != null;
    }

    public boolean createNewVersion() throws InterruptedException {
        boolean newVersionIsVisibleInGlass = false;
        try {
            driver.findElement(By.id("header-details-user-fullname")).isDisplayed();
        } catch (Exception e) {
            login();
        }
        driver.navigate().to("https://jira.codecool.codecanvas.hu/projects/PP3/summary");
        driver.findElement(By.xpath("//*[@id=\"content\"]/div[1]/div/div[1]/nav/div/div[2]/ul/li[3]/a")).click();
        try {
            driver.findElement(By.xpath("//*[text()='2.0.1']")).isDisplayed();
            System.out.println("Version 2.0.1 is exists!");
        } catch (Exception e) {
            System.out.println("Version 2.0.1 isn't exists... YET!");
            driver.findElement(By.xpath("//*[@id=\"releases-add__version\"]/div[1]/input")).click();
            driver.findElement(By.xpath("//*[@id=\"releases-add__version\"]/div[1]/input")).sendKeys("2.0.1");
            driver.findElement(By.xpath("//*[@id=\"releases-add__version\"]/div[5]/button")).click();
            System.out.println("Version 2.0.1 is created!");
        }
        driver.findElement(By.xpath("//*[@id=\"content\"]/div[1]/div/div[1]/nav/div/div[2]/ul/li[7]/a")).click();
        driver.findElement(By.id("aui-uid-2")).click();
        try {
            driver.findElement(By.xpath("//*[text()='2.0.1']")).isDisplayed();
            System.out.println("Version 2.0.1 is exists in Glass documentation too");
            newVersionIsVisibleInGlass = true;
        } catch (Exception e) {
            System.out.println("Something wrong... test fail!");
        }
        driver.navigate().to("https://jira.codecool.codecanvas.hu/projects/PP3/summary");
        driver.findElement(By.xpath("//*[@id=\"content\"]/div[1]/div/div[1]/nav/div/div[2]/ul/li[3]/a")).click();
        String link = driver.findElement(By.xpath("//*[text()='2.0.1']")).getAttribute("href");
        String subLink = link.substring(link.length()-5);
        driver.findElement(By.id("version-filter-text")).click();
        driver.findElement(By.id("version-filter-text")).sendKeys("2.0.1");
        Thread.sleep(500);
        driver.findElement(By.xpath("//*[@id=\"versions-table\"]/tbody[2]/tr/td[8]/div/a/span")).click();
        Thread.sleep(500);
        driver.findElement(By.xpath("//*[@id=\"version-actions-"+subLink+"\"]/ul/li/a[4]")).click();
        Thread.sleep(500);
        driver.findElement(By.id("submit")).click();
        System.out.println("Version 2.0.1 is deleted!");
        return newVersionIsVisibleInGlass;
    }
}
