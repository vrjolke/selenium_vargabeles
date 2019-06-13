package com.codecool.vargabeles;

import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JiraComponentTest {

    static JiraTasks jiraTasks;

    @BeforeAll
    static void init() {
        jiraTasks = new JiraTasks();
    }


    @AfterAll
    static void finish() {
        jiraTasks.getDriver().close();
    }

    @Test
    void testComponent(){
        int result = jiraTasks.categorizeIssues();
        Assert.assertEquals(result, 1);
        jiraTasks.clearCategorizeIssues();
    }
}