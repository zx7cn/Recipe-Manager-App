package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents a recipe having a title, ingredients, and instructions
public class Recipe implements Writable {
    private String title;         // the title of the recipe
    private String ingredients;   // the ingredients of the recipe
    private String instructions;  // the instructions of the recipe

    // REQUIRES: recipeTitle & recipeIngredients & recipeInstructions have non-zero length
    // EFFECTS: recipeTitle of the recipe is set to recipeTitle;
    //          recipeIngredients is set to recipeIngredients;
    //          recipeInstructions is set to recipeInstructions;
    public Recipe(String recipeTitle, String recipeIngredients, String recipeInstructions) {
        this.title = recipeTitle;
        this.ingredients = recipeIngredients;
        this.instructions = recipeInstructions;
    }

    public String getTitle() {
        return title;
    }

    public String getIngredients() {
        return ingredients;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    // This method is adapted from the JsonSerializationDemo project
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("title", title);
        json.put("ingredients", ingredients);
        json.put("instructions", instructions);
        return json;
    }

}
