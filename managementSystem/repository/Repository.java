package repository;

public class Repository {
    public static boolean addNewUser(
            String username,
            String password,
            String name,
            String surname) {
        return UserRepository.addNewUser(username, password, name, surname);
    }

    public static String[] getPasswordAndAuthorization(String username) {
        return UserRepository.getPasswordAndAuthorization(username);
    }

    //

    public static String[] getAllCategoryNames() {
        return CategoryRepository.getAllCategoryNames();
    }

    public static boolean addCategory(String categoryName) {
        return CategoryRepository.addCategory(categoryName);
    }

    public static boolean removeCategory(String categoryName) {
        return CategoryRepository.removeCategory(categoryName);
    }

    public static boolean editCategory(String oldCategoryName, String categoryName) {
        return CategoryRepository.editCategory(oldCategoryName, categoryName);
    }

    public static boolean isCategoryExist(String oldCategoryName) {
        return CategoryRepository.isCategoryExist(oldCategoryName);
    }

    //

    public static String[] getAllFeedbackTitles() {
        return FeedbackRepository.getAllFeedbackTitles();
    }

    public static String[] getAllFeedbackTitlesOfAuthenticatedUser(String username) {
        return FeedbackRepository.getAllFeedbackTitlesOfAuthenticatedUser(username);
    }

    public static String getFeedbackDetails(String title) {
        return FeedbackRepository.getFeedbackDetails(title);
    }

    public static boolean shareFeedback(
            String owner,
            String title,
            String thoughts,
            String categoryName,
            int rate) {
        return FeedbackRepository.shareFeedback(owner, title, thoughts, categoryName, rate);
    }

    public static boolean setStatusOfFeedback(String owner, String title, String status) {
        return FeedbackRepository.setStatusOfFeedback(owner, title, status);
    }

    public static boolean responseFeedback(String title, String response) {
        return FeedbackRepository.responseFeedback(title, response);
    }
}
