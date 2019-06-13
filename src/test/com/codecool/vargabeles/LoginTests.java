package com.codecool.vargabeles;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LoginTests extends JiraTests{

    @Test
    void testLogin() {
        if (jiraTasks.isLoggedIn()) {
            jiraTasks.logout();
        }
        boolean hasLoggedIn = jiraTasks.login();
        Assert.assertTrue(hasLoggedIn);
    }

   @Test
   void testLoginWithEmptyCredentials() {
        if (jiraTasks.isLoggedIn()) {
            jiraTasks.logout();
        }

        jiraTasks.loginWithWrongCredentials("", "");

        assertEquals("Sorry, your username and password are incorrect - please try again.", jiraTasks.getDriver().findElement(By.xpath("//*[@id=\"usernameerror\"]/p")).getText());
    }

    @Test
    void testLoginWithWrongPassword() {
        if (jiraTasks.isLoggedIn()) {
            jiraTasks.logout();
        }
        jiraTasks.loginWithWrongCredentials(System.getenv("username"), "asd");

        assertEquals("Sorry, your username and password are incorrect - please try again.", jiraTasks.getDriver().findElement(By.xpath("//*[@id=\"usernameerror\"]/p")).getText());
    }
}
