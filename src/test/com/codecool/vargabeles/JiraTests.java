package com.codecool.vargabeles;

import org.junit.jupiter.api.*;

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
}
