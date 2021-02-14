package model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RecipeTest {
    private Recipe testRecipe;

    @BeforeEach
    public void runBefore() {
        testRecipe = new Recipe("Mango Smoothie", "Mango,Milk", "Just " +
                "put everything into the blender");
    }

    @Test
    public void testConstructor() {
        assertEquals("Mango Smoothie", testRecipe.getTitle());
        assertEquals("Mango,Milk", testRecipe.getIngredients());
        assertEquals("Just put everything into the blender", testRecipe.getInstructions());
    }
}
