import com.codecool.vargabeles.JiraTasks;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


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
    void testLogin() {
        try{
            jiraTasks.logout();
        }catch (Exception e){
            System.out.println("Logging out...");
        }
        boolean hasLoggedIn = jiraTasks.login();
        Assert.assertTrue(hasLoggedIn);
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


}
