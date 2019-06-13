package com.codecool.vargabeles;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class VersionsWithGlassTests extends JiraTests {

    private VersionsWithGlass versionsWithGlass;

    @BeforeEach
    void newDriver(){
        versionsWithGlass = new VersionsWithGlass();
        versionsWithGlass.login();
    }

    @AfterEach
    void finish() {
        versionsWithGlass.getDriver().close();

    }

    @Test
    void testCreateNewVersion() throws InterruptedException {
        boolean versionIsCreated = versionsWithGlass.createNewVersion();
        versionsWithGlass.deleteNewVersion();
        Assert.assertTrue(versionIsCreated);
    }

    @Test
    void testConnectIssueToVersion() throws InterruptedException {
        versionsWithGlass.createNewVersion();
        boolean issueIsConnected = versionsWithGlass.connectIssueToVersion();
        versionsWithGlass.deleteNewVersion();
        Assert.assertTrue(issueIsConnected);
    }

    @Test
    void testDeleteNewVersion() throws InterruptedException {
        versionsWithGlass.createNewVersion();
        boolean versionIsDeleted = versionsWithGlass.deleteNewVersion();
        Assert.assertTrue(versionIsDeleted);
    }
}