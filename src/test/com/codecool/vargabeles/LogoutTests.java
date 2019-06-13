package com.codecool.vargabeles;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static junit.framework.TestCase.assertEquals;

public class LogoutTests extends JiraTests{

    @Test
    void testLogout() {
        boolean isLoggedIn = jiraTasks.logout();
        assertEquals(false, isLoggedIn);
    }



}
