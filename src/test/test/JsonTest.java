package test;

import model.Recipe;

import static org.junit.jupiter.api.Assertions.assertEquals;

// This class is adapted from the JsonSerializationDemo project
public class JsonTest {
    protected void checkRecipe(String title, String ingredients, String instructions, Recipe recipe) {
        assertEquals(title, recipe.getTitle());
        assertEquals(ingredients, recipe.getIngredients());
        assertEquals(instructions, recipe.getInstructions());
    }
}
