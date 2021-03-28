package ui;

import model.Recipe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents the tool used to add new recipes to the recipe list
public class AddTool extends Tool {
    private Recipe recipe;

    public AddTool(UI recipeList, JComponent parent, GridBagConstraints gc) {
        super(recipeList, parent, gc);
    }


    @Override
    protected void createButton() {
        button = new JButton("Add recipe");
        button.setEnabled(false);
    }

    @Override
    protected void addListener() {
        button.addActionListener(new AddToolClickHandler());
    }

    private class AddToolClickHandler implements ActionListener {

        // MODIFIES: recipeList
        // EFFECTS: when button is pressed, gets the title, ingredients, and instructions from the fields in the panel
        //          and constructs a new recipe
        @Override
        public void actionPerformed(ActionEvent e) {
            Toolkit.getDefaultToolkit().beep();
            String title = recipeList.getNewTitle();
            String ingredients = recipeList.getNewIngredients();
            String instructions = recipeList.getNewInstructions();

            recipe = new Recipe(title, ingredients, instructions);
            recipe.setTitle(title);
            recipe.setIngredients(ingredients);
            recipe.setInstructions(instructions);
            recipeList.addRecipe(recipe);

            recipeList.setAddTitleField();
            recipeList.setIngredientsField();
            recipeList.setInstructionsField();
        }
    }

}

