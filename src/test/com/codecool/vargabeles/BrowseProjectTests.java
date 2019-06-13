package com.codecool.vargabeles;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BrowseProjectTests extends JiraTests{


    @Test
    void testProjectIsAvailable(){
        assertTrue(jiraTasks.projectIsAvailable("PP1"));
    }

    @Test
    void testCoalaProjectIsAvailable(){
        assertTrue(jiraTasks.projectIsAvailable("COALA"));
    }

    @Test
    void testToucanProjectIsAvailable(){
        assertTrue(jiraTasks.projectIsAvailable("JETI"));
    }

    @Test
    void testJetiProjectIsAvailable(){
        assertTrue(jiraTasks.projectIsAvailable("TOUCAN"));
    }

}
