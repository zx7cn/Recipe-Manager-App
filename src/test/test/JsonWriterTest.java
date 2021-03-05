package test;

import model.Recipe;
import model.RecipeList;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest extends JsonTest{
    @Test
    void testWriterInvalidFile() {
        try {
            RecipeList rl = new RecipeList();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            RecipeList rl = new RecipeList();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyWorkroom.json");
            writer.open();
            writer.write(rl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyWorkroom.json");
            rl = reader.read();
            assertEquals(0, rl.recipesNum());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            RecipeList rl = new RecipeList();
            rl.addRecipe(new Recipe("a","b","c"));
            rl.addRecipe(new Recipe("z","x","y"));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralWorkroom.json");
            writer.open();
            writer.write(rl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralWorkroom.json");
            rl = reader.read();
            List<Recipe> recipes = rl.getRecipes();
            assertEquals(2, recipes.size());
            checkRecipe("a", "b", "c", recipes.get(0));
            checkRecipe("z", "x", "y", recipes.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}







