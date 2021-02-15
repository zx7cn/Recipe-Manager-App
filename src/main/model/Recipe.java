package model;

public class Recipe {
    private String title;         // the title of the recipe
    private String ingredients;   // the ingredients of the recipe
    private String instructions;  // the instructions of the recipe

    // REQUIRES: recipeTitle & recipeIngredients & recipeInstructions have non-zero length
    // EFFECTS: recipeTitle of the recipe is set to recipeTitle;
    //          recipeIngredients is set to recipeIngredients;
    //          recipeInstructions is set to recipeInstructions;
    public Recipe(String recipeTitle, String recipeIngredients, String recipeInstructions) {
        title = recipeTitle;
        ingredients = recipeIngredients;
        instructions = recipeInstructions;
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


}
