package ui;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

// Represents the text field where users input the title of the new recipe they want to add
public class AddTitleField extends TextField {

    public AddTitleField(UI recipeList, JComponent parent, GridBagConstraints gc) {
        super(recipeList, parent, gc);
    }

    @Override
    protected void addListener() {
        textField.getDocument().addDocumentListener(new AddItemNameHandler());
    }

    private class AddItemNameHandler implements DocumentListener {
        @Override
        public void insertUpdate(DocumentEvent e) {
            changed();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            changed();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            changed();
        }

        // MODIFIES: todoList
        // EFFECTS: enables the add button if the title field, ingredients field, and instructions field are not empty
        private void changed() {
            String title = textField.getText();
            recipeList.add.setEnabled(!title.isEmpty() && !recipeList.getNewIngredients().isEmpty()
                    && !recipeList.getNewInstructions().isEmpty());
        }
    }
}

