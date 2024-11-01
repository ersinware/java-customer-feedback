package repository.Tests;

import org.junit.jupiter.api.BeforeEach;

import repository.FeedbackRepository;
import repository.types.Feedback;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FeedbackRepositoryTest {

    @BeforeEach
    public void setup() {
        // Clear any existing feedbacks (assuming a method to clear feedbacks exists or we reset the HashMap)
        FeedbackRepository.feedbacks.clear();

        // Initialize with some feedbacks for testing
        FeedbackRepository.shareFeedback("user1", "Feedback 1", "Content 1", "Category 1", 5);
        FeedbackRepository.shareFeedback("user1", "Feedback 2", "Content 2", "Category 2", 4);
        FeedbackRepository.shareFeedback("user2", "Feedback 3", "Content 3", "Category 3", 3);
    }

    @Test
    public void testGetAllFeedbackTitlesPositive() {
        String[] titles = FeedbackRepository.getAllFeedbackTitles();
        assertEquals(3, titles.length);
        assertTrue(Arrays.asList(titles).contains("Feedback 1"));
        assertTrue(Arrays.asList(titles).contains("Feedback 2"));
        assertTrue(Arrays.asList(titles).contains("Feedback 3"));
    }

    @Test
    public void testGetAllFeedbackTitlesNegative() {
        // Assuming no feedbacks exist initially after clearing
        FeedbackRepository.feedbacks.clear();
        String[] titles = FeedbackRepository.getAllFeedbackTitles();
        assertEquals(0, titles.length);
    }


    @Test
    public void testGetAllFeedbackTitlesOfAuthenticatedUserPositive() {
        String[] user1Titles = FeedbackRepository.getAllFeedbackTitlesOfAuthenticatedUser("user1");
        assertNotNull(user1Titles);
        assertEquals(2, user1Titles.length);
        assertTrue(Arrays.asList(user1Titles).contains("Feedback 1"));
        assertTrue(Arrays.asList(user1Titles).contains("Feedback 2"));
    }

    @Test
    public void testGetAllFeedbackTitlesOfAuthenticatedUserNegative() {
        String[] nonexistentUserTitles = FeedbackRepository.getAllFeedbackTitlesOfAuthenticatedUser("nonexistentUser");
        assertNull(nonexistentUserTitles);
    }

    @Test
    public void testGetFeedbackDetailsNegative() {
        String details = FeedbackRepository.getFeedbackDetails("Nonexistent Feedback");
        assertNull(details);
    }

    @Test
    public void testSetStatusOfFeedbackPositive() {
        boolean statusSet = FeedbackRepository.setStatusOfFeedback("user1", "Feedback 1", "Resolved");
        assertTrue(statusSet);

        Feedback feedback = getFeedbackByTitle("Feedback 1");
        assertNotNull(feedback);
        assertEquals("Resolved", feedback.getStatus());
    }

    @Test
    public void testSetStatusOfFeedbackNegative() {
        boolean statusSet = FeedbackRepository.setStatusOfFeedback("user1", "Nonexistent Feedback", "Resolved");
        assertFalse(statusSet);
    }

    @Test
    public void testResponseFeedbackPositive() {
        boolean responded = FeedbackRepository.responseFeedback("Feedback 1", "Response to Feedback 1");
        assertTrue(responded);

        Feedback feedback = getFeedbackByTitle("Feedback 1");
        assertNotNull(feedback);
        assertTrue(feedback.getResponses().contains("Response to Feedback 1"));
    }

    @Test
    public void testResponseFeedbackNegative() {
        boolean responded = FeedbackRepository.responseFeedback("Nonexistent Feedback", "Response to Feedback");
        assertFalse(responded);
    }

    // Helper method to get Feedback by title
    private Feedback getFeedbackByTitle(String title) {
        for (ArrayList<Feedback> feedbacks : FeedbackRepository.feedbacks.values()) {
            for (Feedback feedback : feedbacks) {
                if (feedback.getTitle().equals(title)) {
                    return feedback;
                }
            }
        }
        return null;
    }
}
