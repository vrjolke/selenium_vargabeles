package com.codecool.vargabeles;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

class EditIssueTests extends JiraTests {

    @Test
    void testEditIssue() {
        boolean issueChanged = jiraTasks.editIssue();
        Assert.assertTrue(issueChanged);
        jiraTasks.restoreEditIssue();
    }

    @Test
    void testEditIssueOnTheIssuePage() {
        boolean issueChanged = jiraTasks.editIssueOnTheIssuePage("task-1");
        Assert.assertTrue(issueChanged);
        finish();
        newDriver();
        jiraTasks.editIssueOnTheIssuePage("story-1");
    }
}
