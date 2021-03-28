package ui;

import javax.swing.*;
import java.awt.*;

//Represents a collection of all the text field in the project
public abstract class TextField {
    UI recipeList;
    JTextField textField;

    public TextField(UI recipeList, JComponent parent, GridBagConstraints gc) {
        this.recipeList = recipeList;
        textField = new JTextField(10);
        textField.setFont(recipeList.textFieldFont);
        addToParent(parent, gc);
        addListener();
    }

    // MODIFIES: parent
    // EFFECTS: add the text field to the parent component with the given constraints
    private void addToParent(JComponent parent, GridBagConstraints gc) {
        parent.add(textField, gc);
    }

    // EFFECTS: add listener to the text field
    protected abstract void addListener();

    // EFFECTS: return the string in the text field
    public String getFieldText() {
        return textField.getText();
    }

    // MODIFIES: this
    // EFFECT: sets the text field to empty
    public void setEmpty() {
        textField.setText("");
    }
}
