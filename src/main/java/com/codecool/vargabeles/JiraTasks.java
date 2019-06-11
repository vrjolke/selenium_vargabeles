package com.codecool.vargabeles;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class JiraTasks {
    public static WebDriver driver;

    public JiraTasks() {
        System.setProperty("webdriver.chrome.driver", System.getenv("webdriverRoute"));
        driver = new ChromeDriver();
    }

    public boolean login() {
        WebDriverWait wait = new WebDriverWait(JiraTasks.driver, 60);
        JiraTasks.driver.navigate().to("https://jira.codecool.codecanvas.hu/secure/Dashboard.jspa");
        JiraTasks.driver.findElement(By.id("login-form-username")).sendKeys(System.getenv("username"));
        JiraTasks.driver.findElement(By.id("login-form-password")).click();
        JiraTasks.driver.findElement(By.id("login-form-password")).sendKeys(System.getenv("password"));
        JiraTasks.driver.findElement(By.id("login")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("header-details-user-fullname")));
        return JiraTasks.driver.findElement(By.id("header-details-user-fullname")) != null;
    }


    public boolean logout() {
        try {
            driver.findElement(By.id("header-details-user-fullname")).isDisplayed();
        } catch (Exception e) {
            login();
        }
        driver.findElement(By.id("header-details-user-fullname")).click();
        driver.findElement(By.id("log_out")).click();
        WebDriverWait wait = new WebDriverWait(JiraTasks.driver, 60);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"content\"]/div/div/section/div/div/p[2]/a")));
        return driver.findElement(By.xpath("//*[@id=\"content\"]/div/div/section/div/div/p[2]/a")) != null;
    }
}
