package com.codecool.vargabeles;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BrowseIssueTests extends JiraTests{

    @Test
    void testIssueIsAvailable() {
        assertTrue(jiraTasks.issueIsAvailable("SAND-40"));
    }

    @Test
    void testProjectJetiHasThreeIssues(){
        assertTrue(jiraTasks.projectHasNIssues("JETI", 3));
    }

    @Test
    void testProjectToucanHasThreeIssues(){
        assertTrue(jiraTasks.projectHasNIssues("TOUCAN", 3));
    }

    @Test
    void testProjectCoalaHasThreeIssues(){
        assertTrue(jiraTasks.projectHasNIssues("COALA", 3));
    }
}
