package test;

import model.Recipe;
import model.RecipeList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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


}