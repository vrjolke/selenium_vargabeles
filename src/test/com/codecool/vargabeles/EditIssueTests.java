package com.codecool.vargabeles;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

class EditIssueTests extends JiraTests {

    @Test
    void testEditIssue() {
        boolean issueChanged = jiraTasks.editIssue();
        Assert.assertTrue(issueChanged);
        jiraTasks.restoreEditIssue();
        finish();
        init();
    }

    @Test
    void testEditIssueOnTheIssuePage() {
        boolean issueChanged = jiraTasks.editIssueOnTheIssuePage("task-1");
        Assert.assertTrue(issueChanged);
        finish();
        init();
        jiraTasks.editIssueOnTheIssuePage("story-1");
    }
}
