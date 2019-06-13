package com.codecool.vargabeles;

import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class JiraComponentTest {

    private static JiraTasks jiraTasks;

    @BeforeAll
    static void init() {
        jiraTasks = new JiraTasks();
    }


    @AfterAll
    static void finish() {
        jiraTasks.getDriver().close();
    }

    @Test
    void testComponent() throws InterruptedException{
        int result = jiraTasks.categorizeIssues();
        Assert.assertEquals(result, 1);
        jiraTasks.clearCategorizeIssues();
    }
}