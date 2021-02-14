package test;

import model.Recipe;
import model.RecipeList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class RecipeListTest {
    private RecipeList testRecipeList;
    private Recipe testRecipe;

    @BeforeEach
    public void runBefore() {
        testRecipeList = new RecipeList();
    }

    @Test
    public void testAddRecipe() {
        Recipe recipe = new Recipe("a","b","c");
        testRecipeList.addRecipe(recipe);
        assertTrue(testRecipeList.contains(recipe));
    }
}