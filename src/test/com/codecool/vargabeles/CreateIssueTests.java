package com.codecool.vargabeles;

import org.junit.Assert;
import org.junit.jupiter.api.Test;


public class CreateIssueTests extends JiraTests {


    @Test
    void testCreateIssue() {
        Assert.assertTrue(jiraTasks.createIssue());
    }
}
