package ui;

import model.Recipe;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class RecipeApp {
    private List<Recipe> recipeCollections = new ArrayList<Recipe>();
    private Scanner input;
    private Recipe recipe;

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
        recipeCollections = new ArrayList<Recipe>();
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("1")) {
            viewAllRecipes();
        } else if (command.equals("3")) {
            addRecipe();
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
        System.out.println("\tq - Quit");
    }


    private void viewAllRecipes() {
        System.out.println("Recipes");
        int index = 0;
        for (Recipe recipe : recipeCollections) {
            index = index + 1;
            System.out.println(index + "." + recipe.getTitle());
        }
    }

    private void addRecipe() {
        System.out.println("Enter the title of the recipe:");
        String title = input.next();
        System.out.println("Enter the ingredients of the recipe:");
        String ingredients = input.next();
        System.out.println("Enter the instructions of the recipe:");
        String instructions = input.next();
        recipeCollections.add(new Recipe(title, ingredients, instructions));
        System.out.println("A new recipe is added to your collection!");
    }


}
