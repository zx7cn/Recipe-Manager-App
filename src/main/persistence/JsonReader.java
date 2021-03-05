package persistence;

import model.Recipe;
import model.RecipeList;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads recipe list from JSON data stored in file
// This class is adapted from the JsonSerializationDemo project
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads recipe list from file and returns it;
    // throws IOException if an error occurs reading data from file
    public RecipeList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseRecipeList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses recipe list from JSON object and returns it
    private RecipeList parseRecipeList(JSONObject jsonObject) {
        RecipeList rl = new RecipeList();
        addRecipes(rl, jsonObject);
        return rl;
    }


    // MODIFIES: rl
    // EFFECTS: parses recipes from JSON object and adds them to recipe list
    private void addRecipes(RecipeList rl, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("recipes");
        for (Object json : jsonArray) {
            JSONObject nextRecipe = (JSONObject) json;
            addRecipe(rl, nextRecipe);
        }
    }

    // MODIFIES: rl
    // EFFECTS: parses recipe from JSON object and adds it to recipe list
    private void addRecipe(RecipeList rl, JSONObject jsonObject) {
        String title = jsonObject.getString("title");
        String ingredients = jsonObject.getString("ingredients");
        String instructions = jsonObject.getString("instructions");
        Recipe recipe = new Recipe(title, ingredients, instructions);
        rl.addRecipe(recipe);
    }


}
