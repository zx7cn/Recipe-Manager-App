package model;

import java.util.ArrayList;

// Represents an array list of recipes
public class RecipeList {
    private ArrayList<Recipe> recipeCollection;

    public RecipeList() {
        recipeCollection = new ArrayList<>();
    }

    public boolean contains(Recipe r) {
        return recipeCollection.contains(r);
    }

    // MODIFIES: this
    // EFFECTS: add a new recipe to recipeCollection
    public void addRecipe(Recipe recipe) {
        recipeCollection.add(recipe);
    }



}
