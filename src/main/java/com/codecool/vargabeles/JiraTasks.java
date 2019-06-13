package com.codecool.vargabeles;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class JiraTasks {

    private WebDriver driver;
    private WebDriverWait wait;


    public WebDriver getDriver() {
        return driver;
    }

    public JiraTasks() {
        System.setProperty("webdriver.chrome.driver", System.getenv("webdriverRoute"));
        driver = new ChromeDriver();
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

    public boolean editIssue() {
        login();
        driver.navigate().to("https://jira.codecool.codecanvas.hu/browse/SAND-1");
        driver.findElement(By.id("edit-issue")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("edit-issue-dialog")));
        driver.findElement(By.id("summary")).sendKeys("Test Story");
        driver.findElement(By.id("edit-issue-submit")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("aui-flag-container")));
        return driver.findElement(By.id("summary-val")).getText().equals("Test Story");
    }

    public void restoreEditIssue() {
        driver.findElement(By.id("edit-issue")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("edit-issue-dialog")));
        driver.findElement(By.id("summary")).sendKeys("Story");
        driver.findElement(By.id("edit-issue-submit")).click();
    }

    public boolean editIssueOnTheIssuePage(String issueType) {
        login();
        driver.navigate().to("https://jira.codecool.codecanvas.hu/browse/SAND-25");
        driver.findElement(By.id("type-val")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("issuetype-single-select")));
        driver.findElement(By.id("issuetype-single-select")).click();
        driver.findElement(By.id(issueType)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"issuetype-form\"]/div[2]/button[1]/span")));
        driver.findElement(By.xpath("//*[@id=\"issuetype-form\"]/div[2]/button[1]/span")).click();
        return driver.findElement(By.id("type-val")).isDisplayed();
    }

}
