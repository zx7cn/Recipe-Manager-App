package ui;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

// Represents the text field where users input the instructions of the new recipe they want to add
public class AddInstructionsField extends TextField {
    public AddInstructionsField(UI recipeList, JComponent parent, GridBagConstraints gc) {
        super(recipeList, parent, gc);
    }

    @Override
    protected void addListener() {
        textField.getDocument().addDocumentListener(new AddInstructionsHandler());
    }

    private class AddInstructionsHandler implements DocumentListener {
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
        public void changed() {
            String instructions = textField.getText();
            recipeList.add.setEnabled(!recipeList.getNewTitle().isEmpty() && !recipeList.getNewIngredients().isEmpty()
                    && !instructions.isEmpty());
        }
    }

}
