package com.codecool.vargabeles;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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

    boolean isLoggedIn(){
        try{
            driver.findElement(By.id("header-details-user-fullname")).isDisplayed();
        }catch(NoSuchElementException e){
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

    boolean loginWithWrongCredentials(String username, String password){
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
//        loginIfNotLoggedIn();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("header-details-user-fullname")));
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


    boolean issueIsAvailable(String issueName) {
//        loginIfNotLoggedIn();
        driver.navigate().to("https://jira.codecool.codecanvas.hu/browse/" + issueName);
        return driver.findElement(By.id("summary-val")).isDisplayed();
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

    public int categorizeIssues() throws InterruptedException {
        login();
        driver.navigate().to("https://jira.codecool.codecanvas.hu/browse/PP3");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"content\"]/div[1]/div/div[1]/nav/div/div[2]/ul/li[6]/a/span[1]")));
        Thread.sleep(500);

        //Save component
        driver.findElement(By.xpath("//*[@id=\"content\"]/div[1]/div/div[1]/nav/div/div[2]/ul/li[6]/a/span[1]")).click();
        driver.findElement(By.xpath("//*[@id=\"components-add__component\"]/div[1]/input")).sendKeys("test");
        driver.findElement(By.xpath("//*[@id=\"components-add__component\"]/div[3]/input")).sendKeys("test");
        driver.findElement(By.id("assigneeType-field")).sendKeys("Project lead (Administrator)");
        driver.findElement(By.xpath("//*[@id=\"component-filter\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"components-add__component\"]/div[5]/button")).click();

        //add component to issue
        driver.findElement(By.xpath("//*[@id=\"content\"]/div[1]/div/div[1]/nav/div/div[2]/ul/li[5]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"edit-issue\"]/span[2]")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("components-textarea")).sendKeys("test");
        driver.findElement(By.id("edit-issue-submit")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"content\"]/div[1]/div/div[1]/nav/div/div[2]/ul/li[7]/a/span[1]")).click();
        int i = 1;
        Thread.sleep(1000);
        //investigate the correction
        while (true) {
            try {
                if ("test".equals(driver.findElement(By.cssSelector("#components-table > tbody.items > tr:nth-child(" + i + ") > td.components-table__name > div > a")).getText())) {
                    return Integer.parseInt(driver.findElement(By.cssSelector("#components-table > tbody.items > tr:nth-child(" + i + ") > td.components-table__issues-count > div > a")).getText());
                }
                i++;
            } catch (NumberFormatException e) {
                break;
            }
        }
        return Integer.parseInt(null);
    }

    public void clearCategorizeIssues() throws InterruptedException {
        driver.findElement(By.xpath("//*[@id=\"content\"]/div[1]/div/div[1]/nav/div/div[2]/ul/li[6]/a")).click();
        int i = 1;
        Thread.sleep(1000);
        while (true) {
            if ("test".equals(driver.findElement(By.cssSelector("#components-table > tbody.items > tr:nth-child(" + i + ") > td.components-table__name > div > a")).getText())) {
                driver.findElement(By.cssSelector("#components-table > tbody.items > tr:nth-child(" + i + ") > td.dynamic-table__actions > div > a > span")).click();
                int id = Integer.parseInt(driver.findElement(By.cssSelector("#components-table > tbody.items > tr:nth-child("+i+")")).getAttribute("data-component-id"));
                driver.findElement(By.xpath("//*[@id=\"deletecomponent_"+id+"\"]")).click();
                Thread.sleep(1000);
                driver.findElement(By.xpath("//*[@id=\"component-"+id+"-delete-dialog\"]/div[2]/div/form/div[1]/fieldset/div[2]/label")).click();
                driver.findElement(By.xpath("//*[@id=\"submit\"]")).click();
                break;
            }
            i++;
        }
    }
}
