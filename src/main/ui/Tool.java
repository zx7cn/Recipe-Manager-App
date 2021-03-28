package ui;

import javax.swing.*;
import java.awt.*;

//Represents a collection of tools needed
public abstract class Tool {
    protected JButton button;
    protected UI recipeList;

    public Tool(UI recipeList, JComponent parent, GridBagConstraints gc) {
        this.recipeList = recipeList;
        createButton();
        addToParent(parent, gc);
        addListener();
    }

    // EFFECTS: creates a button for the tool
    protected abstract void createButton();

    // MODIFIES: parent
    // EFFECTS: adds the button to the parent component with the given constraints
    public void addToParent(JComponent parent, GridBagConstraints gc) {
        parent.add(button, gc);
    }

    // EFFECTS: adds listener to the button
    protected abstract void addListener();

    // MODIFIES: this
    // EFFECTS: sets the button to enabled to disabled
    public void setEnabled(boolean b) {
        button.setEnabled(b);
    }
}
