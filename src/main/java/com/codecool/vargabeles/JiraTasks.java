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

    public int categorizeIssues() {
        login();
        driver.navigate().to("https://jira.codecool.codecanvas.hu/browse/PP3");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"content\"]/div[1]/div/div[1]/nav/div/div[2]/ul/li[6]/a/span[1]")));
        driver.findElement(By.xpath("//*[@id=\"content\"]/div[1]/div/div[1]/nav/div/div[2]/ul/li[6]/a/span[1]")).click();
        driver.findElement(By.xpath("//*[@id=\"components-add__component\"]/div[1]/input")).sendKeys("test");
        driver.findElement(By.xpath("//*[@id=\"components-add__component\"]/div[3]/input")).sendKeys("test");
        driver.findElement(By.id("assigneeType-field")).sendKeys("Project lead (Administrator)");
        driver.findElement(By.xpath("//*[@id=\"component-filter\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"components-add__component\"]/div[5]/button")).click();

        driver.findElement(By.xpath("//*[@id=\"content\"]/div[1]/div/div[1]/nav/div/div[2]/ul/li[5]/a")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='edit-issue']/span[2])")));
        driver.findElement(By.xpath("//*[@id='edit-issue']/span[2])")).click();
        driver.findElement(By.id("components-textarea")).sendKeys("test");
        driver.findElement(By.id("edit-issue-submit")).click();
        driver.findElement(By.xpath("//*[@id=\"content\"]/div[1]/div/div[1]/nav/div/div[2]/ul/li[7]/a/span[2]")).click();
        int i = 0;
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

    public void clearCategorizeIssues() {
        driver.findElement(By.xpath("//*[@id=\"content\"]/div[1]/div/div[1]/nav/div/div[2]/ul/li[6]/a")).click();
        int i = 1;
        while (true) {
            if ("test".equals(driver.findElement(By.cssSelector("#components-table > tbody.items > tr:nth-child(" + i + ")")).getText())) {
                driver.findElement(By.cssSelector("#components-table > tbody.items > tr:nth-child(" + i + ") > td.dynamic-table__actions > div > a > span")).click();
                int id = Integer.parseInt(driver.findElement(By.cssSelector("#components-table > tbody.items > tr:nth-child("+i+")")).getAttribute("data-component-id"));
                driver.findElement(By.xpath("//*[@id=\"deletecomponent_"+id+"\"]")).click();
                driver.findElement(By.xpath("//*[@id=\"component-"+id+"-delete-dialog\"]/div[2]/div/form/div[1]/fieldset/div[2]/label")).click();
                driver.findElement(By.xpath("//*[@id=\"submit\"]")).click();
                break;
            }
            i++;
        }
        logout();
    }
}
