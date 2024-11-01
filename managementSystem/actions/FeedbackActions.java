package actions;

import constants.Constants;
import repository.Repository;
import util.Util;

public class FeedbackActions {
    protected static String[] getAllFeedbackTitles(String username) {
        if (Util.isNullExist(username))
            throw new RuntimeException("All parameters must be provided");

        if (!UserActionsUtil.isAuthenticatedUserAdmin())
            throw new RuntimeException("You are not authorized to use this service");

        return Repository.getAllFeedbackTitles();
    }

    protected static String[] getAllFeedbackTitlesOfAuthenticatedUser(String username) {
        if (Util.isNullExist(username))
            throw new RuntimeException("All parameters must be provided");

        if (!UserActionsUtil.isUserAuthenticated(username))
            throw new RuntimeException("You are not authorized to use this service");

        return Repository.getAllFeedbackTitlesOfAuthenticatedUser(username);
    }

    protected static String getFeedbackDetails(String username, String title) {
        if (Util.isNullExist(username, title))
            throw new RuntimeException("All parameters must be provided");

        if (!UserActionsUtil.isUserAuthenticated(username))
            throw new RuntimeException("You are not authorized to use this service");

        return Repository.getFeedbackDetails(title);
    }

    protected static boolean shareFeedback(
            String owner,
            String title,
            String thoughts,
            String categoryName,
            int rate) {
        if (Util.isNullExist(owner, title, thoughts, categoryName))
            throw new RuntimeException("All parameters must be provided");

        if (rate > 5 || rate < 1)
            throw new RuntimeException("Rate must be between 1 and 5");

        if (!UserActionsUtil.isUserAuthenticated(owner))
            throw new RuntimeException("You are not authorized to use this service");

        if (!Repository.isCategoryExist(categoryName))
            throw new RuntimeException("Category you try to share in does not exist");

        return Repository.shareFeedback(owner, title, thoughts, categoryName, rate);
    }

    protected static boolean setStatusOfFeedback(String owner, String title, String status) {
        if (Util.isNullExist(owner, title, status))
            throw new RuntimeException("All parameters must be provided");

        if (!UserActionsUtil.isAuthenticatedUserAdmin())
            throw new RuntimeException("You are not authorized to use this service");

        if (!status.equals(Constants.FEEDBACK_STATUS_VIEWED) && !status.equals(Constants.FEEDBACK_STATUS_FIXED))
            throw new RuntimeException("Status must be FEEDBACK_STATUS_VIEWED or FEEDBACK_STATUS_FIXED");

        return Repository.setStatusOfFeedback(owner, title, status);
    }

    protected static boolean responseFeedback(String title, String response) {
        if (Util.isNullExist(title, response))
            throw new RuntimeException("All parameters must be provided");

        return Repository.responseFeedback(title, response);
    }

    protected static boolean responseFeedbackByTemplate(String title, String response) {
        if (Util.isNullExist(title, response))
            throw new RuntimeException("All parameters must be provided");

        if (!UserActionsUtil.isAuthenticatedUserAdmin())
            throw new RuntimeException("You are not authorized to use this service");

        if (!response.equals(Constants.FEEDBACK_RESPONSE_TEMPLATE_ONE)
                && !response.equals(Constants.FEEDBACK_RESPONSE_TEMPLATE_TWO)
                && !response.equals(Constants.FEEDBACK_RESPONSE_TEMPLATE_THREE))
            throw new RuntimeException("The template is not defined.");

        return Repository.responseFeedback(title, response);
    }
}
