package ui;

import model.Recipe;
import model.RecipeList;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;

// Represents the recipe list application
public class RecipeApp {
    private static final String JSON_STORE = "./data/recipeList.json";
    private RecipeList recipeCollections;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: runs the recipe application
    public RecipeApp() throws FileNotFoundException {
        recipeCollections = new RecipeList();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
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

    // MODIFIES: this
    // EFFECTS: initializes RecipeList
    private void init() {
        input = new Scanner(System.in);
        recipeCollections = new RecipeList();
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("1")) {
            doViewAllRecipes();
        } else if (command.equals("2")) {
            doSelectRecipe();
        } else if (command.equals("3")) {
            doAddRecipe();
        } else if (command.equals("4")) {
            doRemoveRecipe();
        } else if (command.equals("5")) {
            saveRecipeList();
        } else if (command.equals("6")) {
            loadWorkRoom();
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
        System.out.println("\t5 - Save recipe list");
        System.out.println("\t6 - Load recipe list from file");
        System.out.println("\tq - Quit");
    }

    // EFFECTS: display all recipes in the current collection
    private void doViewAllRecipes() {
        System.out.println("Recipes");
        int index = 0;
        for (Iterator<Recipe> i = recipeCollections.iterator(); i.hasNext(); ) {
            Recipe recipe = i.next();
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
        Recipe recipe = new Recipe(title,ingredients,instructions);
        recipeCollections.addRecipe(recipe);
        System.out.println("A new recipe is added to your collection!");
        doViewAllRecipes();
    }

    // EFFECTS: select a recipe from the collection and display its details
    private void doSelectRecipe() {
        System.out.println("Select from:");
        doViewAllRecipes();
        System.out.println("Enter the title of the recipe you want to select:");
        String title = input.next();
        for (Iterator<Recipe> i = recipeCollections.iterator();i.hasNext();) {
            Recipe recipe = i.next();
            if (recipe.getTitle().equals(title)) {
                System.out.println("Title: " + recipe.getTitle());
                System.out.println("Ingredients: " + recipe.getIngredients());
                System.out.println("Instructions: " + recipe.getInstructions());
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: remove a selected recipe from the collection
    private void doRemoveRecipe() {
        doViewAllRecipes();
        System.out.println("Enter the title of the recipe you want to remove:");
        String title = input.next();
        for (Iterator<Recipe> i = recipeCollections.iterator();i.hasNext();) {
            Recipe recipe = i.next();
            if (recipe.getTitle().equals(title)) {
                i.remove();
            }
        }
        System.out.println("The recipe has been removed from your collection!");
        doViewAllRecipes();
    }

    // EFFECTS: saves the recipe list to file
    private void saveRecipeList() {
        try {
            jsonWriter.open();
            jsonWriter.write(recipeCollections);
            jsonWriter.close();
            System.out.println("Saved the recipe list" + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads recipe list from file
    private void loadWorkRoom() {
        try {
            recipeCollections = jsonReader.read();
            System.out.println("Loaded the recipe list from" + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}


