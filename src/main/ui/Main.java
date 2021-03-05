package ui;

import java.io.FileNotFoundException;

// This class is adapted from the JsonSerializationDemo project
public class Main {
    public static void main(String[] args) {
        try {
            new RecipeApp();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }
    }
}
