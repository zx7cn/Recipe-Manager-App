package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Represents the tool used to remove recipes from the recipe list
//The classes in the ui package are adapted from the project To-Do-List found on GitHub
public class RemoveTool extends Tool {
    public RemoveTool(UI recipeList, JComponent parent, GridBagConstraints gc) {
        super(recipeList, parent, gc);
    }

    @Override
    protected void createButton() {
        button = new JButton("Remove recipe");
        button.setEnabled(false);
    }

    @Override
    protected void addListener() {
        button.addActionListener(new RemoveToolCLickHandler());
    }

    private class RemoveToolCLickHandler implements ActionListener {

        // MODIFIES: recipeList
        // EFFECTS: when button is pressed, remove the recipe with the given title and clear text field
        @Override
        public void actionPerformed(ActionEvent e) {
            Toolkit.getDefaultToolkit().beep();
            String title = recipeList.getRemoveTitle();
            recipeList.removeRecipe(title);
            recipeList.setRemoveTitleField();
        }
    }

}

