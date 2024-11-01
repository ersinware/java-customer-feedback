package actions;

import repository.Repository;
import util.Util;

public class CategoryActions {
    protected static String[] getAllCategoryNames() {
        return Repository.getAllCategoryNames();
    }

    protected static boolean addCategory(String username, String categoryName) {
        if (Util.isNullExist(username, categoryName))
            throw new RuntimeException("All parameters must be provided");

        if (!UserActionsUtil.isAuthenticatedUserAdmin())
            throw new RuntimeException("You are not authorized to use this service");

        return Repository.addCategory(categoryName);
    }

    protected static boolean removeCategory(String username, String categoryName) {
        if (Util.isNullExist(username))
            throw new RuntimeException("All parameters must be provided");

        if (!UserActionsUtil.isAuthenticatedUserAdmin())
            throw new RuntimeException("You are not authorized to use this service");

        // remove all feedbacks shared in the category

        return Repository.removeCategory(categoryName);
    }

    protected static boolean editCategory(String username, String oldCategoryName, String categoryName) {
        if (Util.isNullExist(username, oldCategoryName, categoryName))
            throw new RuntimeException("All parameters must be provided");

        if (!UserActionsUtil.isAuthenticatedUserAdmin())
            throw new RuntimeException("You are not authorized to use this service");

        if (!Repository.isCategoryExist(oldCategoryName))
            throw new RuntimeException("Category you try to edit does not exist");

        // change feedback records shared in the category, too

        return Repository.editCategory(oldCategoryName, categoryName);
    }
}