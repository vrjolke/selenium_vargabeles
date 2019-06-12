import com.codecool.vargabeles.JiraTasks;
import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


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

}
