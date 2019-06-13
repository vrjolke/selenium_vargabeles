package com.codecool.vargabeles;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BrowseIssueTests extends JiraTests{

    @Test
    void testIssueIsAvailable() {
        assertEquals(true, jiraTasks.issueIsAvailable("SAND-40"));
    }

    @Test
    void testProjectJetiHasThreeIssues(){
        assertEquals(true, jiraTasks.projectHasNIssues("JETI", 3));
    }

    @Test
    void testProjectToucanHasThreeIssues(){
        assertEquals(true, jiraTasks.projectHasNIssues("TOUCAN", 3));
    }

    @Test
    void testProjectCoalaHasThreeIssues(){
        assertEquals(true, jiraTasks.projectHasNIssues("COALA", 3));
    }
}
