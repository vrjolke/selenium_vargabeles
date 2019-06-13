package com.codecool.vargabeles;

import org.junit.Assert;
import org.junit.jupiter.api.*;

import static com.codecool.vargabeles.JiraTests.jiraTasks;

class ComponentsWithGlassTest {

    private ComponentsWithGlass componentsWithGlass;

    @BeforeEach
    void newDriver(){
        componentsWithGlass = new ComponentsWithGlass();
        componentsWithGlass.login();
    }

    @AfterEach
    void finish() {
        componentsWithGlass.getDriver().close();

    }

    @Test
    void testComponent() throws InterruptedException{
        int result = componentsWithGlass.categorizeIssues();
        Assert.assertEquals(result, 1);
        componentsWithGlass.clearCategorizeIssues();
    }
}