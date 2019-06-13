package com.codecool.vargabeles;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
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

    public boolean createIssue() {

        WebDriverWait wait = new WebDriverWait(JiraTasks.driver, 10);
        try {
            driver.findElement(By.id("header-details-user-fullname")).isDisplayed();
        } catch (Exception e) {
            login();
        }
        JiraTasks.driver.navigate().to("https://jira.codecool.codecanvas.hu/secure/Dashboard.jspa");
        driver.findElement(By.id("create_link")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#create-issue-dialog > div.jira-dialog-heading > h2")));

        WebElement dropdown = driver.findElement(By.cssSelector("#project-field"));
        dropdown.click();
        dropdown.sendKeys("Sandbox (SAND)");
        dropdown.sendKeys(Keys.RETURN);

        wait.until(ExpectedConditions.elementToBeClickable(By.id("summary")));
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement summary = driver.findElement(By.id("summary"));



        summary.sendKeys("Test summary for create issue");

        WebElement submitButton = driver.findElement(By.id("create-issue-submit"));
        submitButton.click();
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".issue-created-key")));
        } catch (Exception e) {
            return false;
        }

        WebElement popUpLink = driver.findElement(By.cssSelector(".issue-created-key"));
        popUpLink.click();


        driver.findElement(By.id("opsbar-operations_more")).click();
        driver.findElement(By.cssSelector("aui-item-link[title=\"Delete this issue\"]")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("delete-issue-submit")));
        driver.findElement(By.id("delete-issue-submit")).click();

        return true;









    }
}
