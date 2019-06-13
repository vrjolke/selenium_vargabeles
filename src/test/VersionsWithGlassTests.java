import com.codecool.vargabeles.VersionsWithGlass;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

class VersionsWithGlassTests extends JiraTests{

    private VersionsWithGlass versionsWithGlass = new VersionsWithGlass();

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