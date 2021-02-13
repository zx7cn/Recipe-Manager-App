package model;

public class Recipe {
    private String title;         // the title of the recipe
    private String ingredients;   // the details of the recipe
    private String instructions;  // the instructions of the recipe

    // REQUIRES: recipeName has a non-zero length
    // EFFECTS: title of the recipe is set to recipeTitle;
    //          details is set to recipeDetails;
    //          instructions is set to recipeInstructions;
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
