package actions;

public class Actions {
    public static boolean signUp(
            String username,
            String password,
            String name,
            String surname) {
        return UserActions.signUp(
                username,
                password,
                name,
                surname);
    }

    public static boolean signIn(String username, String password) {
        return UserActions.signIn(username, password);
    }

    public static boolean signOut(String username) {
        return UserActions.signOut(username);
    }

    public static boolean isAuthenticatedUserAdmin() {
        return UserActionsUtil.isAuthenticatedUserAdmin();
    }

    public static String getAuthenticatedUsername() {
        return UserActionsUtil.getAuthenticatedUsername();
    }

    //

    public static String[] getAllCategoryNames() {
        return CategoryActions.getAllCategoryNames();
    }

    public static boolean addCategory(String categoryName) {
        return CategoryActions.addCategory(UserActionsUtil.getAuthenticatedUsername(), categoryName);
    }

    public static boolean removeCategory(String categoryName) {
        return CategoryActions.removeCategory(UserActionsUtil.getAuthenticatedUsername(), categoryName);
    }

    public static boolean editCategory(String oldCategoryName, String categoryName) {
        return CategoryActions.editCategory(UserActionsUtil.getAuthenticatedUsername(), oldCategoryName, categoryName);
    }

    //

    public static String[] getAllFeedbackTitles() {
        return FeedbackActions.getAllFeedbackTitles(UserActionsUtil.getAuthenticatedUsername());
    }

    public static String[] getAllFeedbackTitlesOfAuthenticatedUser() {
        return FeedbackActions.getAllFeedbackTitlesOfAuthenticatedUser(UserActionsUtil.getAuthenticatedUsername());
    }

    public static String getFeedbackDetails(String title) {
        return FeedbackActions.getFeedbackDetails(UserActionsUtil.getAuthenticatedUsername(), title);
    }

    public static boolean shareFeedback(
            String title,
            String thoughts,
            String categoryName,
            int rate) {
        return FeedbackActions.shareFeedback(UserActionsUtil.getAuthenticatedUsername(), title, thoughts, categoryName,
                rate);
    }

    // TODO: test
    public static boolean setStatusOfFeedback(String owner, String title, String status) {
        return FeedbackActions.setStatusOfFeedback(owner, title, status);
    }

    public static boolean responseFeedback(String title, String response) {
        return FeedbackActions.responseFeedback(title, response);
    }
    
    public static boolean responseFeedbackWithTemplate(String title, String response) {
        return FeedbackActions.responseFeedbackByTemplate(title, response);
    }
}
