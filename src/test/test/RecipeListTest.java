package test;

import exceptions.RecipeNotFoundException;
import model.Recipe;
import model.RecipeList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;


class RecipeListTest {
    private RecipeList testRecipeList;
    Recipe recipe = new Recipe("a","b","c");

    @BeforeEach
    public void runBefore() {
        testRecipeList = new RecipeList();
    }

    @Test
    public void testAddRecipe() {
        assertFalse(testRecipeList.contains(recipe));
        testRecipeList.addRecipe(recipe);
        assertTrue(testRecipeList.contains(recipe));
    }

    @Test
    public void testContains() {
        assertFalse(testRecipeList.contains(recipe));
        testRecipeList.addRecipe(recipe);
        assertTrue(testRecipeList.contains(recipe));
    }

    @Test
    public void testRemoveRecipeNoException() {
        testRecipeList.addRecipe(recipe);
        try {
            testRecipeList.removeRecipe("a");
        } catch (RecipeNotFoundException e) {
            fail("Exception should not have been thrown.");
        }
        assertFalse(testRecipeList.contains(recipe));
    }

    @Test
    public void testRemoveRecipeExceptionThrown() {
        testRecipeList.addRecipe(recipe);
        try {
            testRecipeList.removeRecipe("b");
            fail("Exception should have been thrown.");
        } catch (RecipeNotFoundException e) {
            e.printStackTrace();
        }
        assertTrue(testRecipeList.contains(recipe));
    }


}