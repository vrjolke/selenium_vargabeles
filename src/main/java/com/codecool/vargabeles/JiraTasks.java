package com.codecool.vargabeles;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class JiraTasks {

    private WebDriver driver;
    private WebDriverWait wait;

    public WebDriverWait getWait() {
        return wait;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public JiraTasks() {
        System.setProperty("webdriver.chrome.driver", System.getenv("webdriverRoute"));
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 5);
    }

    public boolean login() {
        driver.navigate().to("https://jira.codecool.codecanvas.hu/secure/Dashboard.jspa");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-form-username")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-form-password")));
        driver.findElement(By.id("login-form-username")).sendKeys(System.getenv("username"));
        driver.findElement(By.id("login-form-password")).sendKeys(System.getenv("password"));
        driver.findElement(By.id("login")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("header-details-user-fullname")));
        return isLoggedIn();
    }

    public boolean loginWithEmptyCredentials() {
        driver.navigate().to("https://jira.codecool.codecanvas.hu/secure/Dashboard.jspa");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-form-username")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-form-password")));
        driver.findElement(By.id("login-form-username")).sendKeys("");
        driver.findElement(By.id("login-form-password")).sendKeys("");
        driver.findElement(By.id("login")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"usernameerror\"]/p")));
        return isLoggedIn();
    }

    public boolean isLoggedIn() {
        try {
            driver.findElement(By.id("header-details-user-fullname")).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }

    public boolean logout() {
        loginIfNotLoggedIn();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("header-details-user-fullname")));
        driver.findElement(By.id("header-details-user-fullname")).click();
        driver.findElement(By.id("log_out")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"content\"]/div/div/section/div/div/p[2]/a")));
        return driver.findElement(By.xpath("//*[@id=\"content\"]/div/div/section/div/div/p[2]/a")) != null;
    }

    public boolean issueIsAvailable(String issueName) {
        loginIfNotLoggedIn();
        driver.navigate().to("https://jira.codecool.codecanvas.hu/browse/" + issueName);
        return driver.findElement(By.id("summary-val")).isDisplayed();
    }

    private void loginIfNotLoggedIn() {
        try {
            driver.findElement(By.id("header-details-user-fullname")).isDisplayed();
        } catch (Exception e) {
            login();
        }
    }

    public boolean projectHasNIssues(String projectname, int issues) {
        loginIfNotLoggedIn();
        for (int i = 1; i <= issues; i++) {
            boolean issueExists = issueIsAvailable(projectname + "-" + i);
            if (!issueExists) {
                return false;
            }
        }
        return true;
    }

    public boolean comparePermissions() {
        checkPermissions();
        checkPermissionsWithGlass();
        return false;
    }

    public List<Boolean> checkPermissions() {
        loginIfNotLoggedIn();
        driver.navigate().to("https://jira.codecool.codecanvas.hu/projects/PP1?selectedItem=com.codecanvas.glass:glass");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//section[@id='content']/div/div/div[2]/a/span")));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(By.xpath("//section[@id='content']/div/div/div[2]/a/span")).click();
        driver.findElement(By.id("view_project_permissions")).click();
        List<Boolean> permissionList = new ArrayList<>();
        if(driver.findElement(By.cssSelector(".permissions-group:nth-child(2) tr:nth-child(4) dd")).getText().equals("Any logged in user")){
            permissionList.add(true);
        } else {
            permissionList.add(false);
        }if(driver.findElement(By.xpath("//*[@id=\"project-config-panel-permissions\"]/jira-permissions-table/div[2]/table/tbody/tr[5]/td[2]/dl/dd")).getText().equals("Any logged in user")){
            permissionList.add(true);
        } else {
            permissionList.add(false);
        }if(driver.findElement(By.cssSelector(".permissions-group:nth-child(2) tr:nth-child(6) dd")).getText().equals("Any logged in user")){
            permissionList.add(true);
        } else {
            permissionList.add(false);
        }
        return permissionList;
    }

    public List<Boolean> checkPermissionsWithGlass() {
        loginIfNotLoggedIn();
        driver.navigate().to("https://jira.codecool.codecanvas.hu/projects/PP1?selectedItem=com.codecanvas.glass:glass");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(By.xpath("//*[@id=\"content\"]/div[1]/div/div[1]/nav/div/div[2]/ul/li[7]/a/span[1]")).click();
        driver.findElement(By.xpath("//*[@id=\"glass-permissions-nav\"]/a")).click();
        List<Boolean> permissionList = new ArrayList<>();
        if(driver.findElement(By.xpath("//*[@id=\"glass-permissions-panel\"]/div/table/tbody/tr[6]/td[3]/div")).getAttribute("class").equals("glass-true-icon")){
            permissionList.add(true);
        } else {
            permissionList.add(false);
        }if(driver.findElement(By.xpath("//*[@id=\"glass-permissions-panel\"]/div/table/tbody/tr[12]/td[3]")).getAttribute("class").equals("glass-true-icon")){
            permissionList.add(true);
        } else {
            permissionList.add(false);
        }if(driver.findElement(By.xpath("//*[@id=\"glass-permissions-panel\"]/div/table/tbody/tr[18]/td[3]/div")).getAttribute("class").equals("glass-true-icon")){
            permissionList.add(true);
        } else {
            permissionList.add(false);
        }
        return  permissionList;
    }
}
