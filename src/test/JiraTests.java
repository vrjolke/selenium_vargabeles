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
        System.setProperty("webdriver.chrome.driver", System.getenv("webdriverRoute"));
        driver = new ChromeDriver();
        jiraTasks = new JiraTasks();
    }

    @AfterAll
    static void finish() {
        driver.close();
    }

    @Test
    void login(){
        Assert.assertEquals(1,1);
    }


}
