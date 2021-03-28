package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Represents the tool used to view all recipes in the recipe list
public class ViewTool extends Tool {
    public ViewTool(UI recipeList, JComponent parent, GridBagConstraints gc) {
        super(recipeList, parent, gc);
    }

    // EFFECTS: returns the label for the button
    private String getLabel() {
        return "View all recipes";
    }

    // MODIFIES: this
    // EFFECTS: construct a button with the given label
    @Override
    protected void createButton() {
        button = new JButton(getLabel());
    }

    @Override
    protected void addListener() {
        button.addActionListener(new ViewToolClickHandler());
    }

    private class ViewToolClickHandler implements ActionListener {

        // EFFECTS: when button pressed, print all recipes in recipe list
        @Override
        public void actionPerformed(ActionEvent e) {
            Toolkit.getDefaultToolkit().beep();
            recipeList.viewRecipes(recipeList.recipeList);
        }
    }

}
