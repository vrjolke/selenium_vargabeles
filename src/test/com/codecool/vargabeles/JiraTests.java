package com.codecool.vargabeles;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

class JiraTests {

    static JiraTasks jiraTasks;

    @BeforeEach
    void newDriver(){
        jiraTasks = new JiraTasks();
        jiraTasks.login();
    }

    @AfterEach
    void finish() {
        jiraTasks.getDriver().close();

    }
}
