package ui;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

// Represents the text field where users input the ingredients of the new recipe they want to add
public class AddIngredientsField extends TextField {
    public AddIngredientsField(UI recipeList, JComponent parent, GridBagConstraints gc) {
        super(recipeList, parent, gc);
    }

    @Override
    protected void addListener() {
        textField.getDocument().addDocumentListener(new AddIngredientsHandler());
    }

    private class AddIngredientsHandler implements DocumentListener {
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

        // MODIFIES: recipeList
        // EFFECTS: enables the add button if the title field, ingredients field, and instructions field are not empty
        public void changed() {
            String ingredients = textField.getText();
            recipeList.add.setEnabled(!recipeList.getNewTitle().isEmpty() && !ingredients.isEmpty()
                    && !recipeList.getNewInstructions().isEmpty());
        }
    }
}
