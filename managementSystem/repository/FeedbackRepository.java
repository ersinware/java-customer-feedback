package repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import repository.types.Feedback;

public class FeedbackRepository {
    // username -> feedbacks of user
    public static final HashMap<String, ArrayList<Feedback>> feedbacks = new HashMap<>() {
        {
            put("user", new ArrayList<>() {
                {
                    add(new Feedback("user", "Feedback 1", "Content of Feedback 1", "Example Category 3", 5));
                    add(new Feedback("user", "Feedback 2", "Content of Feedback 2", "Example Category 2", 2));
                    add(new Feedback("user", "Feedback 3", "Content of Feedback 3", "Example Category 1", 3));
                }
            });
        }
    };

    public static String[] getAllFeedbackTitles() {
        Collection<ArrayList<Feedback>> values = feedbacks.values();
        ArrayList<String> _feedbacks = new ArrayList<>();

        for (ArrayList<Feedback> __feedbacks : values)
            for (int i = 0; i < __feedbacks.size(); i++)
                _feedbacks.add(__feedbacks.get(i).getTitle());

        return _feedbacks.toArray(new String[_feedbacks.size()]);
    }

    public static String[] getAllFeedbackTitlesOfAuthenticatedUser(String username) {
        ArrayList<Feedback> _feedbacks = feedbacks.getOrDefault(username, null);

        if (_feedbacks == null) {
            System.out.println("\nFeedback could not find");

            return null;
        }

        String[] __feedbacks = new String[_feedbacks.size()];

        for (int i = 0; i < _feedbacks.size(); i++)
            __feedbacks[i] = _feedbacks.get(i).getTitle();

        return __feedbacks;
    }

    public static String getFeedbackDetails(String title) {
        for (ArrayList<Feedback> feedbacks : feedbacks.values())
            for (int i = 0; i < feedbacks.size(); i++)
                if (feedbacks.get(i).getTitle().equals(title))
                    return feedbacks.get(i).getDetails();

        System.out.println("\nFeedback could not find");

        return null;
    }

    public static boolean shareFeedback(
            String owner,
            String title,
            String thoughts,
            String categoryName,
            int rate) {
        ArrayList<Feedback> _feedbacks = feedbacks.getOrDefault(owner, null);

        if (_feedbacks == null) {
            _feedbacks = new ArrayList<>();
            _feedbacks.add(new Feedback(owner, title, thoughts, categoryName, rate));
            feedbacks.put(owner, _feedbacks);

            return true;
        }

        _feedbacks.add(new Feedback(owner, title, thoughts, categoryName, rate));

        return true;
    }

    public static boolean setStatusOfFeedback(String owner, String title, String status) {
        ArrayList<Feedback> _feedbacks = feedbacks.getOrDefault(owner, null);

        if (_feedbacks == null) {
            System.out.println("\nFeedback could not find");

            return false;
        }

        for (int i = 0; i < _feedbacks.size(); i++)
            if (_feedbacks.get(i).getTitle().equals(title)) {
                _feedbacks.get(i).setStatus(status);

                return true;
            }

        System.out.println("\nFeedback could not find");

        return false;
    }

    public static boolean responseFeedback(String title, String response) {
        for (ArrayList<Feedback> feedbacks : feedbacks.values())
            for (int i = 0; i < feedbacks.size(); i++)
                if (feedbacks.get(i).getTitle().equals(title)) {
                    feedbacks.get(i).addResponse(response);

                    return true;
                }

        System.out.println("\nFeedback could not find");

        return false;
    }
}
