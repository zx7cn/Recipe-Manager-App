package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// Represents an array list of recipes
public class RecipeList {
    private List<Recipe> recipeCollection;

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

    public void displayRecipes() {
        int index = 0;
        for (Recipe recipe : recipeCollection) {
            index = index + 1;
            System.out.println(index + "." + recipe.getTitle());
        }
    }


    public Iterator<Recipe> iterator() {
        return recipeCollection.iterator();
    }




}
