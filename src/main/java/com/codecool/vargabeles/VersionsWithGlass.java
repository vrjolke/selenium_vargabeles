package com.codecool.vargabeles;

import org.openqa.selenium.By;

class VersionsWithGlass extends JiraTasks{


    boolean createNewVersion() {
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
            System.out.println("Version 2.0.1 is exists in Glass documentation too!");
            newVersionIsVisibleInGlass = true;
        } catch (Exception e) {
            System.out.println("Something wrong... test fail!");
        }
        return newVersionIsVisibleInGlass;
    }

    boolean deleteNewVersion() throws InterruptedException{
        driver.navigate().to("https://jira.codecool.codecanvas.hu/projects/PP3/summary");
        driver.findElement(By.xpath("//*[@id=\"content\"]/div[1]/div/div[1]/nav/div/div[2]/ul/li[3]/a")).click();
        Thread.sleep(500);
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
        return true;
    }

    boolean connectIssueToVersion() throws InterruptedException {
        driver.findElement(By.xpath("//*[@id=\"content\"]/div[1]/div/div[1]/nav/div/div[2]/ul/li[5]/a/span[1]")).click();
        driver.findElement(By.xpath("//*[text()='TestConnectIssueToVersion']")).click();
        Thread.sleep(500);
        driver.findElement(By.xpath("//*[@id=\"edit-issue\"]/span[2]")).click();
        Thread.sleep(500);
        driver.findElement(By.id("fixVersions-textarea")).sendKeys("2.0.1");
        Thread.sleep(500);
        driver.findElement(By.id("edit-issue-submit")).click();
        System.out.println("Test issue is connected to Version 2.0.1!");
        return true;
    }
}