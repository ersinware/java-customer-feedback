package repository;

import java.util.ArrayList;
import java.util.List;

public class CategoryRepository {
    protected static List<String> categories = new ArrayList<>();

    static {
        categories.add("Example Category");
        categories.add("Example Category 2");
        categories.add("Example Category 3");
    }

    protected static String[] getAllCategoryNames() {
        return categories.toArray(new String[0]);
    }

    protected static boolean addCategory(String categoryName) {
        if (isCategoryExist(categoryName)) {
            System.out.println("\nA category exists with the same name");

            return false;
        }

        categories.add(categoryName);

        return true;
    }

    protected static boolean removeCategory(String categoryName) {
        if (!isCategoryExist(categoryName)) {
            System.out.println("\nCategory does not exist");

            return false;
        }

        return categories.remove(categoryName);
    }

    protected static boolean editCategory(String oldCategoryName, String categoryName) {
        if (!isCategoryExist(oldCategoryName)) {
            System.out.println("\nCategory does not exist");

            return false;

        }

        categories.set(categories.indexOf(oldCategoryName), categoryName);

        return true;
    }

    protected static boolean isCategoryExist(String categoryName) {

        return categories.contains(categoryName);
    }
}
