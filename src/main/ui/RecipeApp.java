package ui;

import model.Recipe;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class RecipeApp {
    private List<Recipe> recipeCollections = new ArrayList<Recipe>();
    private Scanner input;

    // EFFECTS: runs the recipe application
    public RecipeApp() {
        runRecipe();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    // This method is adapt from the runTeller method
    private void runRecipe() {
        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }
        System.out.println("\nBye!");

    }

    private void init() {
        input = new Scanner(System.in);
        recipeCollections = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("1")) {
            viewAllRecipes();
        } else if (command.equals("2")) {
            selectRecipe();
        } else if (command.equals("3")) {
            doAddRecipe();
        } else if (command.equals("4")) {
            removeRecipe();
        } else {
            System.out.println("Invalid Section");
        }
    }

    // EFFECTS: displays menu of options
    private void displayMenu() {
        System.out.println("\nMain Menu");
        System.out.println("\t1 - View all recipe titles");
        System.out.println("\t2 - Select a recipe");
        System.out.println("\t3 - Add a new recipe");
        System.out.println("\t4 - Remove a recipe");
        System.out.println("\tq - Quit");
    }

    // EFFECTS: display all recipes in the current collection
    private void viewAllRecipes() {
        System.out.println("Recipes");
        int index = 0;
        for (Recipe recipe : recipeCollections) {
            index = index + 1;
            System.out.println(index + "." + recipe.getTitle());
        }
    }

    // MODIFIES: this
    // EFFECTS: add a new recipe to the current collection
    private void doAddRecipe() {
        System.out.println("Enter the title of the recipe:");
        String title = input.next();
        System.out.println("Enter the ingredients of the recipe:");
        String ingredients = input.next();
        System.out.println("Enter the instructions of the recipe:");
        String instructions = input.next();
        recipeCollections.add(new Recipe(title,ingredients,instructions));
        System.out.println("A new recipe is added to your collection!");
    }

    // EFFECTS: select a recipe from the collection and display its details
    private void selectRecipe() {
        System.out.println("Select from:");
        viewAllRecipes();
        System.out.println("Enter the title of the recipe you want to select:");
        String title = input.next();
        for (Recipe recipe : recipeCollections) {
            if (recipe.getTitle().equals(title)) {
                System.out.println("Title: " + recipe.getTitle());
                System.out.println("Ingredients: " + recipe.getIngredients());
                System.out.println("Instructions: " + recipe.getInstructions());
            } else {
                System.out.println("Invalid Selection");
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: remove a selected recipe from the collection
    private void removeRecipe() {
        viewAllRecipes();
        System.out.println("Enter the title of the recipe you want to remove:");
        String title = input.next();
        for (Iterator<Recipe> i = recipeCollections.listIterator();i.hasNext();) {
            Recipe recipe = i.next();
            if (recipe.getTitle().equals(title)) {
                i.remove();
                System.out.println("The recipe has been removed from your collection!");
            }
        }
    }
}


