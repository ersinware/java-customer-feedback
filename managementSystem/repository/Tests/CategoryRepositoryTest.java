package repository.Tests;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static repository.Repository.*;

public class CategoryRepositoryTest {

    @Test
    public void testGetAllCategoryNames() {
        // Retrieve the actual array of category names which are added statically
        String[] actual = getAllCategoryNames();

        System.out.println(Arrays.toString(actual));

        // Define the expected array of category names
        String[] expected = {"Example Category", "Example Category 2", "Example Category 3", "Test Category", "Now I am dont"};

        // Perform the assertion
        assertArrayEquals(expected, actual, "The categories should match the expected values.");
    }

    @Test
    public void addNonExistingCategory() {
        assertTrue(addCategory("Test Category"));
    }

    @Test
    public void addExistingCategory() {
        assertFalse(addCategory("Example Category"));
    }

    @Test
    public void removeNonExistingCategory() {
        assertFalse(removeCategory("Non Existed Category"));
    }

    @Test
    public void removeExistingCategory() {
        addCategory("Example Category4");
        assertTrue(removeCategory("Example Category4"));
    }

    @Test
    public void editNonExistCategory() {
        assertFalse(editCategory("Non existed category", "Maybe existed category"));
    }

    @Test
    public void editExistCategory() {
        addCategory("I am here");
        assertTrue(editCategory("I am here", "Now I am dont"));
        String[] array = getAllCategoryNames();
        List<String> arrayList = new ArrayList<>(Arrays.asList(array));
        assertFalse(arrayList.contains("I am here"));
        assertTrue(arrayList.contains("Now I am dont"));
    }

    @Test
    public void isCategoryExistTest() {
        assertTrue(isCategoryExist("Example Category"));
    }

    @Test
    public void isCategoryNonExistTest() {
        assertFalse(isCategoryExist("I'm not exist in here"));
    }
}