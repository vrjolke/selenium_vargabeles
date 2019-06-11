import com.codecool.vargabeles.JiraTasks;
import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class JiraTests {
    static WebDriver driver;

    static JiraTasks jiraTasks;

    @BeforeAll
    static void init() {
        jiraTasks = new JiraTasks();
    }

    @AfterAll
    static void finish() {
        JiraTasks.driver.close();
    }

    @Test
    void testLogin(){
        boolean hasLoggedIn = jiraTasks.login();
        Assert.assertTrue(hasLoggedIn);
    }


}
