package repository.types;

import java.util.ArrayList;

import constants.Constants;

public class Feedback {
    private final String owner;
    private final String title;
    private final String thoughts;
    private final String categoryName;
    private final int rate;
    private String status = Constants.FEEDBACK_STATUS_NOT_VIEWED;
    private final ArrayList<String> responses = new ArrayList<>();

    public Feedback(String owner, String title, String thoughts, String categoryName, int rate) {
        this.owner = owner;
        this.title = title;
        this.thoughts = thoughts;
        this.categoryName = categoryName;
        this.rate = rate;
    }

    public String getOwner() {
        return owner;
    }

    public String getTitle() {
        return title;
    }

    public String getThoughts() {
        return thoughts;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public int getrate() {
        return rate;
    }

    public String getStatus() {
        return status;
    }

    public ArrayList<String> getResponses() {
        return responses;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void addResponse(String response) {
        responses.add(response);
    }

    private String getAllResponses() {
        if (responses.size() == 0)
            return "";

        StringBuilder _responses = new StringBuilder("\nResponses:");
        for (String response : responses)
            _responses.append("\n" + response);

        return _responses.toString();
    }

    public String getDetails() {
        return title + "\n" + categoryName + "\n" + owner + "\n" + thoughts + "\n" + rate + "\n" + status
                + getAllResponses();
    }
}
