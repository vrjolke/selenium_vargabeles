package com.codecool.vargabeles;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class JiraTasks {

    WebDriver driver;
    private WebDriverWait wait;

    WebDriver getDriver() {
        return driver;
    }

    JiraTasks() {
        System.setProperty("webdriver.chrome.driver", System.getenv("webdriverRoute"));
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 5);
    }

    boolean isLoggedIn() {
        try {
            driver.findElement(By.id("header-details-user-fullname")).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }

    public void loginIfNotLoggedIn() {
        try {
            driver.findElement(By.id("header-details-user-fullname")).isDisplayed();
        } catch (Exception e) {
            login();
        }
    }

    boolean login() {
        driver.navigate().to("https://jira.codecool.codecanvas.hu/secure/Dashboard.jspa");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-form-username")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-form-password")));
        driver.findElement(By.id("login-form-username")).sendKeys(System.getenv("username"));
        driver.findElement(By.id("login-form-password")).sendKeys(System.getenv("password"));
        driver.findElement(By.id("login")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("header-details-user-fullname")));
        return isLoggedIn();
    }

    boolean loginWithWrongCredentials(String username, String password) {
        driver.navigate().to("https://jira.codecool.codecanvas.hu/secure/Dashboard.jspa");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-form-username")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-form-password")));
        driver.findElement(By.id("login-form-username")).sendKeys(username);
        driver.findElement(By.id("login-form-password")).sendKeys(password);
        driver.findElement(By.id("login")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"usernameerror\"]/p")));
        return isLoggedIn();
    }

    boolean logout() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("header-details-user-fullname")));
        driver.findElement(By.id("header-details-user-fullname")).click();
        driver.findElement(By.id("log_out")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"content\"]/div/div/section/div/div/p[2]/a")));
        return isLoggedIn();
    }

    public boolean createIssue() {

        WebDriverWait wait = new WebDriverWait(driver, 10);
        driver.navigate().to("https://jira.codecool.codecanvas.hu/secure/Dashboard.jspa");
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
        } catch (NoSuchElementException e) {
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



    public boolean editIssue () {
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
        driver.navigate().to("https://jira.codecool.codecanvas.hu/browse/SAND-25");
        driver.findElement(By.id("type-val")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("issuetype-single-select")));
        driver.findElement(By.id("issuetype-single-select")).click();
        driver.findElement(By.id(issueType)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"issuetype-form\"]/div[2]/button[1]/span")));
        driver.findElement(By.xpath("//*[@id=\"issuetype-form\"]/div[2]/button[1]/span")).click();
        return driver.findElement(By.id("type-val")).isDisplayed();
    }


    boolean issueIsAvailable(String issueName) {
        driver.navigate().to("https://jira.codecool.codecanvas.hu/browse/" + issueName);
        try{
            driver.findElement(By.id("summary-val")).isDisplayed();
        }
        catch (Exception e){
            return false;
        }
        return true;

    }

    public boolean projectIsAvailable(String projectName){
        driver.navigate().to("https://jira.codecool.codecanvas.hu/projects/"+projectName);
        String pageTitle = driver.getTitle();
        return !pageTitle.contains("Project not found");
    }

    boolean projectHasNIssues(String projectname, int issues) {
//        loginIfNotLoggedIn();
        for (int i = 1; i <= issues; i++) {
            boolean issueExists = issueIsAvailable(projectname + "-" + i);
            if (!issueExists) {
                return false;
            }
        }
        return true;
    }
}
