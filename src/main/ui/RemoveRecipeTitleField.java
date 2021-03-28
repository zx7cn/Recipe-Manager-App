package ui;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

//Represents the text field where users input the title of the recipe they want to remove
public class RemoveRecipeTitleField extends TextField {
    public RemoveRecipeTitleField(UI recipeList, JComponent parent, GridBagConstraints gc) {
        super(recipeList, parent, gc);
    }

    @Override
    protected void addListener() {
        textField.getDocument().addDocumentListener(new RemoveRecipeTitleHandler());
    }

    private class RemoveRecipeTitleHandler implements DocumentListener {
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
        // EFFECTS: enables the button if the text field is not empty
        public void changed() {
            String title = recipeList.getRemoveTitle();
            recipeList.removeTool.setEnabled(!title.isEmpty());
        }
    }
}
