import com.codecool.vargabeles.JiraTasks;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class JiraTests {

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
    @Tag("login")
    void testLogin() {
        if (jiraTasks.isLoggedIn()) {
            jiraTasks.logout();
        }
        boolean hasLoggedIn = jiraTasks.login();
        Assert.assertTrue(hasLoggedIn);
    }

    @Test
    @Tag("login")
    void testLoginWithEmptyCredentials() {
        if (jiraTasks.isLoggedIn()) {
            jiraTasks.logout();
        }
        boolean hasLoggedIn = jiraTasks.loginWithEmptyCredentials();

        assertEquals(false, hasLoggedIn);
        assertEquals("Sorry, your username and password are incorrect - please try again.", jiraTasks.getDriver().findElement(By.xpath("//*[@id=\"usernameerror\"]/p")).getText());
    }

    @Test
    void testLogout() {
        boolean hasLoggedIn = jiraTasks.logout();
        Assert.assertTrue(hasLoggedIn);
    }

    @Test
    @Tag("browseIssue")
    void testIssueIsAvailable() {
        assertEquals(true, jiraTasks.issueIsAvailable("SAND-40"));
    }

    @Test
    @Tag("browseIssue")
    void testProjectJetiHasThreeIssues(){
        assertEquals(true, jiraTasks.projectHasNIssues("JETI", 3));
    }

    @Test
    @Tag("browseIssue")
    void testProjectToucanHasThreeIssues(){
        assertEquals(true, jiraTasks.projectHasNIssues("TOUCAN", 3));
    }

    @Test
    @Tag("browseIssue")
    void testProjectCoalaHasThreeIssues(){
        assertEquals(true, jiraTasks.projectHasNIssues("COALA", 3));
    }


    @Test
    @Tag("permissionWithGlass")
    void testCheckPermission(){
        assertEquals(3, jiraTasks.checkPermissions().size());
    }

    @Test
    @Tag("permissionWithGlass")
    void testCheckPermissionWithGlass(){
        assertEquals(3, jiraTasks.checkPermissionsWithGlass().size());
    }

    @Test
    @Tag("permissionWithGlass")
    void testComparePermission(){
        assertTrue(jiraTasks.checkPermissions().equals(jiraTasks.checkPermissionsWithGlass()));
    }

}
