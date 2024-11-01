package repository.Tests;

import actions.Actions;
import constants.Constants;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ActionsTests {
    @Test
    public void testShareFeedbackPositive() {
        Actions.signIn("user", "pass");

        boolean shared = Actions.shareFeedback("Feedback 4", "Feedback 4 content", "Example Category", 2);
        assertTrue(shared);

        String[] titles = Actions.getAllFeedbackTitlesOfAuthenticatedUser();
        assertNotNull(titles);
        // there are 3 feedbacks added statically
        assertEquals(4, titles.length);
        assertEquals("Feedback 4", titles[3]);
    }

    @Test(expected = RuntimeException.class)
    public void testShareFeedbackNegative() {
        Actions.shareFeedback("user1", "Feedback 1", "Duplicate Content", 6);
    }

    @Test
    public void testSignOutPositive() {
        assertTrue(Actions.signOut(Actions.getAuthenticatedUsername()));
    }

    @Test(expected = RuntimeException.class)
    public void testSignOutNegative() {
        Actions.signOut(null);
    }

    @Test
    public void testSetStatusOfFeedbackPositive() {
        Actions.signIn("ekaraer", "pass");
        Actions.shareFeedback("F99", "Some thougts", "Example Category", 5);
        assertTrue(Actions.setStatusOfFeedback(Actions.getAuthenticatedUsername(), "F99", Constants.FEEDBACK_STATUS_FIXED));
    }

    @Test(expected = RuntimeException.class)
    public void testSetStatusOfFeedbackNegative() {
        // sign in as admin
        Actions.signIn("ekaraer", "pass");
        Actions.shareFeedback("F99", "Some thougts", "Example Category", 5);
        Actions.setStatusOfFeedback(Actions.getAuthenticatedUsername(), "F99", "SOME INVALID STATUS");
    }
}
