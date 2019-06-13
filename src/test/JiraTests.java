import com.codecool.vargabeles.JiraTasks;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;

class JiraTests {

    static JiraTasks jiraTasks;

    @BeforeAll
    static void init() {
        jiraTasks = new JiraTasks();
    }

    @AfterAll
    static void finish() {
        jiraTasks.getDriver().close();
    }
}