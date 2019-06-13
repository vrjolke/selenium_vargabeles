package com.codecool.vargabeles;

import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

public class PermissionsWithGlass extends JiraTasks {

    public List<Boolean> checkPermissions() throws InterruptedException {
        loginIfNotLoggedIn();
        driver.navigate().to("https://jira.codecool.codecanvas.hu/projects/PP1?selectedItem=com.codecanvas.glass:glass");
        Thread.sleep(1000);

        driver.findElement(By.xpath("//section[@id='content']/div/div/div[2]/a/span")).click();
        driver.findElement(By.id("view_project_permissions")).click();
        List<Boolean> permissionList = new ArrayList<>();
        if (driver.findElement(By.cssSelector(".permissions-group:nth-child(2) tr:nth-child(4) dd")).getText().equals("Any logged in user")) {
            permissionList.add(true);
        } else {
            permissionList.add(false);
        }
        if (driver.findElement(By.xpath("//*[@id=\"project-config-panel-permissions\"]/jira-permissions-table/div[2]/table/tbody/tr[5]/td[2]/dl/dd")).getText().equals("Any logged in user")) {
            permissionList.add(true);
        } else {
            permissionList.add(false);
        }
        if (driver.findElement(By.cssSelector(".permissions-group:nth-child(2) tr:nth-child(6) dd")).getText().equals("Any logged in user")) {
            permissionList.add(true);
        } else {
            permissionList.add(false);
        }
        return permissionList;
    }

    public List<Boolean> checkPermissionsWithGlass() throws InterruptedException {
        loginIfNotLoggedIn();
        driver.navigate().to("https://jira.codecool.codecanvas.hu/projects/PP1?selectedItem=com.codecanvas.glass:glass");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"content\"]/div[1]/div/div[1]/nav/div/div[2]/ul/li[7]/a/span[1]")).click();
        driver.findElement(By.xpath("//*[@id=\"glass-permissions-nav\"]/a")).click();
        List<Boolean> permissionList = new ArrayList<>();
        if (driver.findElement(By.xpath("//*[@id=\"glass-permissions-panel\"]/div/table/tbody/tr[6]/td[3]/div")).getAttribute("class").equals("glass-true-icon")) {
            permissionList.add(true);
        } else {
            permissionList.add(false);
        }
        if (driver.findElement(By.xpath("//*[@id=\"glass-permissions-panel\"]/div/table/tbody/tr[12]/td[3]")).getAttribute("class").equals("glass-true-icon")) {
            permissionList.add(true);
        } else {
            permissionList.add(false);
        }
        if (driver.findElement(By.xpath("//*[@id=\"glass-permissions-panel\"]/div/table/tbody/tr[18]/td[3]/div")).getAttribute("class").equals("glass-true-icon")) {
            permissionList.add(true);
        } else {
            permissionList.add(false);
        }
        return permissionList;
    }
}
