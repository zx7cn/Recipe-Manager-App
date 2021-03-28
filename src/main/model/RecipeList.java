package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

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

    // MODIFIES: this
    // EFFECT: remove the recipe from recipe list
    public void removeRecipe(String title) {
        for (Iterator<Recipe> i = recipeCollection.iterator(); i.hasNext(); ) {
            Recipe recipe = i.next();
            if (recipe.getTitle().equals(title)) {
                i.remove();
            }
        }
    }

    public String printAllRecipes() {
        StringBuilder sb = new StringBuilder();
        if (recipeCollection.size() >= 1) {
            int index = 0;
            for (Iterator<Recipe> i = recipeCollection.iterator(); i.hasNext(); ) {
                Recipe recipe = i.next();
                index = index + 1;
                sb.append("\n" + index + ". Title: " + recipe.getTitle()).append("     Ingredients: ")
                        .append(recipe.getIngredients()).append("      Instructions: ")
                        .append(recipe.getInstructions());
            }
        }
        return sb.toString();
    }

    // MODIFIES: todoList text file
    // EFFECTS: saves the items on todoList on to a text file
    public void save(RecipeList list) throws IOException {
        PrintWriter writer = new PrintWriter("recipeList.json", "UTF-8");
        for (Recipe r : recipeCollection) {
            System.out.print("Title: " + r.getTitle());
            System.out.print("   Ingredients: " + r.getIngredients());
            System.out.println("   Instructions: " + r.getInstructions());
            writer.println(r.getTitle() + " " + r.getIngredients() + " " + r.getInstructions());
        }
        writer.close();
    }


    public Iterator<Recipe> iterator() {
        return recipeCollection.iterator();
    }

    // This method is adapted from the JsonSerializationDemo project
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("recipes", recipesToJson());
        return json;
    }

    // EFFECTS: returns things in this recipe list as a JSON array
    // This method is adapted from the JsonSerializationDemo project
    public JSONArray recipesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Recipe r : recipeCollection) {
            jsonArray.put(r.toJson());
        }
        return jsonArray;
    }
}
