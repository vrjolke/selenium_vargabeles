package com.codecool.vargabeles;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BrowseProjectTests extends JiraTests{


    @Test
    void testProjectIsAvailable(){
        assertEquals(true, jiraTasks.projectIsAvailable("PP1"));
    }

    @Test
    void testCoalaProjectIsAvailable(){
        assertEquals(true, jiraTasks.projectIsAvailable("COALA"));
    }

    @Test
    void testToucanProjectIsAvailable(){
        assertEquals(true, jiraTasks.projectIsAvailable("JETI"));
    }

    @Test
    void testJetiProjectIsAvailable(){
        assertEquals(true, jiraTasks.projectIsAvailable("TOUCAN"));
    }

}
