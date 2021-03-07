package test;

import model.Recipe;
import model.RecipeList;
import org.junit.jupiter.api.Test;
import persistence.*;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// This class is adapted from the JsonSerializationDemo project
public class JsonReaderTest extends JsonTest{
    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            RecipeList rl = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyRecipeList() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyRecipeList.json");
        try {
            RecipeList rl = reader.read();
            assertEquals(0, rl.recipesNum());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralRecipeList() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralRecipeList.json");
        try {
            RecipeList rl = reader.read();
            List<Recipe> recipes = rl.getRecipes();
            assertEquals(2, recipes.size());
            checkRecipe("a", "b", "c", recipes.get(0));
            checkRecipe("z", "x", "y", recipes.get(1));
        } catch (IOException e) {
            System.out.println(e);
            fail("Couldn't read from file");
        }
    }
}
