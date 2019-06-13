package com.codecool.vargabeles;

import org.openqa.selenium.By;

public class ComponentsWithGlass extends JiraTasks {

    public int categorizeIssues() throws InterruptedException {
        driver.navigate().to("https://jira.codecool.codecanvas.hu/browse/PP3");
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
