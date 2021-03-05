package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Collections;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

// Represents an array list of recipes
public class RecipeList implements Writable {
    private List<Recipe> recipeCollection;

    public RecipeList() {
        recipeCollection = new ArrayList<>();
    }

    public boolean contains(Recipe recipe) {
        return recipeCollection.contains(recipe);
    }

    // MODIFIES: this
    // EFFECTS: add a new recipe to recipeCollection
    public void addRecipe(Recipe recipe) {
        recipeCollection.add(recipe);
    }

    // EFFECTS: returns number of recipes in the recipe list
    public int recipesNum() {
        return recipeCollection.size();
    }

    // EFFECTS: returns an unmodifiable list of recipes in the recipe list
    // This method is adapted from the JsonSerializationDemo project
    public List<Recipe> getRecipes() {
        return Collections.unmodifiableList(recipeCollection);
    }

    public Iterator<Recipe> iterator() {
        return recipeCollection.iterator();
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("recipes", recipesToJson());
        return json;
    }

    // EFFECTS: returns things in this recipe list as a JSON array
    private JSONArray recipesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Recipe r : recipeCollection) {
            jsonArray.put(r.toJson());
        }

        return jsonArray;
    }
}
