package ui;

import model.Recipe;
import model.RecipeList;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UI extends JFrame {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 500;
    protected RecipeList recipeList;
    private JPanel toolArea;
    private JTextArea textArea;
    private JPanel textPanel;
    protected Font textFieldFont = new Font("TimesRoman", Font.PLAIN, 16);
    private List<Tool> tools = new ArrayList<>();
    private JLabel title;
    private AddTitleField titleField;
    private JLabel ingredients;
    private JLabel instructions;
    private AddIngredientsField ingredientsField;
    private AddInstructionsField instructionsField;
    protected AddTool add;
    private RemoveRecipeTitleField removeTitleField;
    protected RemoveTool removeTool;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final String JSON_STORE = "./data/recipeList.json";

    // EFFECTS: creates the window for the recipe manager app
    public UI() throws IOException {
        super("Recipe Manager");
        recipeList = new RecipeList();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        loadRecipeList();
        initializeGraphics();
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    saveRecipeList();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: initializes the JFrame window for the recipe manager app
    private void initializeGraphics() throws IOException {
        setSize(WIDTH, HEIGHT);
        setLayout(new BorderLayout());
        createTextPanel();
        createTools();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: creates the area which prints all text messages
    private void createTextPanel() {
        textPanel = new JPanel();
        textArea = new JTextArea();
        textArea.setFont(textFieldFont);
        textPanel.setPreferredSize(new Dimension(WIDTH * 3 / 5, HEIGHT));
        Color c = new Color(178, 255, 102);
        textPanel.setBackground(c);

        JEditorPane editorPane = new JEditorPane();
        editorPane.setEditable(false);
        setPreferredSize(new Dimension(WIDTH * 3 / 5, HEIGHT));

        JScrollPane scroll = new JScrollPane(textArea);
        scroll.setPreferredSize(new Dimension(WIDTH * 3 / 5 - 10, HEIGHT - 100));

        textArea.setEditable(false);
        textArea.setBackground(Color.WHITE);
        viewRecipes(recipeList);

        textPanel.add(editorPane);
        textPanel.add(scroll);

        add(textPanel, BorderLayout.WEST);
    }

    // MODIFIES: this
    // EFFECTS: sets the text in the text area to txt
    public void setText(String txt) {
        textArea.setText(txt);
    }

    // MODIFIES: this
    // EFFECTS: instantiates all the tools needed and set their location
    private void createTools() {
        Container toolContainer = getContentPane();
        toolArea = new JPanel();
        toolArea.setLayout(new GridBagLayout());
        toolArea.setPreferredSize(new Dimension(WIDTH / 3, HEIGHT));
        toolContainer.add(toolArea, BorderLayout.EAST);

        GridBagConstraints gc = new GridBagConstraints();

        gc.weightx = 0.5;
        gc.weighty = 0.5;

        gc.gridx = 0;
        gc.gridy = 0;
        JPanel addPanel = new JPanel();
        createAddPanel(addPanel);
        toolArea.add(addPanel, gc);

        gc.gridy = 2;
        JPanel removePanel = new JPanel();
        createRemovePanel(removePanel);
        toolArea.add(removePanel, gc);

        gc.gridy = 3;
        ViewTool viewRecipes = new ViewTool(this, toolArea, gc);
        tools.add(viewRecipes);
    }

    // MODIFIES: this
    // EFFECTS: create a panel where users can add recipes
    private void createAddPanel(JPanel addPanel) {
        addPanel.setLayout(new GridBagLayout());
        addPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Add a recipe:"),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        GridBagConstraints addPanelConstraints = new GridBagConstraints();
        addPanelConstraints.weightx = 0.5;
        addPanelConstraints.weighty = 0.5;
        addPanelConstraints.insets = new Insets(0, 0, 10, 0);

        title = new JLabel("Title: ");
        addPanelConstraints.anchor = GridBagConstraints.LINE_END;
        addPanelConstraints.gridx = 0;
        addPanelConstraints.gridy = 0;
        addPanel.add(title, addPanelConstraints);

        addPanelConstraints.anchor = GridBagConstraints.LINE_START;
        addPanelConstraints.gridx = 1;
        titleField = new AddTitleField(this, addPanel, addPanelConstraints);

        ingredientsPanel(addPanel);
        instructionsPanel(addPanel);

        addPanelConstraints.gridy = 3;
        add = new AddTool(this, addPanel, addPanelConstraints);
    }

    //MODIFIES: this
    //EFFECTS: Create a panel where users can input the instructions of the new recipe
    public void instructionsPanel(JPanel addPanel) {
        addPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Add a recipe:"),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        GridBagConstraints addPanelConstraints = new GridBagConstraints();
        addPanelConstraints.weightx = 0.5;
        addPanelConstraints.weighty = 0.5;
        addPanelConstraints.insets = new Insets(0, 0, 10, 0);

        instructions = new JLabel("Instructions: ");
        addPanelConstraints.anchor = GridBagConstraints.LINE_END;
        addPanelConstraints.gridx = 0;
        addPanelConstraints.gridy = 2;
        addPanel.add(instructions, addPanelConstraints);


        addPanelConstraints.anchor = GridBagConstraints.LINE_START;
        addPanelConstraints.gridx = 1;
        instructionsField = new AddInstructionsField(this, addPanel, addPanelConstraints);
    }

    //MODIFIES: this
    //EFFECTS: Create a panel where users cna input the ingredients of the new recipe
    public void ingredientsPanel(JPanel addPanel) {
        addPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Add a recipe:"),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        GridBagConstraints addPanelConstraints = new GridBagConstraints();
        addPanelConstraints.weightx = 0.5;
        addPanelConstraints.weighty = 0.5;
        addPanelConstraints.insets = new Insets(0, 0, 10, 0);

        ingredients = new JLabel("Ingredients: ");
        addPanelConstraints.anchor = GridBagConstraints.LINE_END;
        addPanelConstraints.gridx = 0;
        addPanelConstraints.gridy = 1;
        addPanel.add(ingredients, addPanelConstraints);
        addPanelConstraints.anchor = GridBagConstraints.LINE_START;
        addPanelConstraints.gridx = 1;
        ingredientsField = new AddIngredientsField(this, addPanel, addPanelConstraints);
    }

    // MODIFIES: this
    // EFFECTS: create a panel where users can remove recipes
    private void createRemovePanel(JPanel completePanel) {
        completePanel.setLayout(new GridBagLayout());
        completePanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Remove a recipe:"),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        GridBagConstraints removePanelConstraints = new GridBagConstraints();
        removePanelConstraints.weightx = 0.5;
        removePanelConstraints.weighty = 0.5;
        removePanelConstraints.insets = new Insets(0, 0, 10, 0);

        title = new JLabel("Title: ");
        removePanelConstraints.anchor = GridBagConstraints.LINE_END;
        removePanelConstraints.gridx = 0;
        removePanelConstraints.gridy = 0;
        completePanel.add(title, removePanelConstraints);

        removePanelConstraints.anchor = GridBagConstraints.LINE_START;
        removePanelConstraints.gridx = 1;
        removeTitleField = new RemoveRecipeTitleField(this, completePanel, removePanelConstraints);

        removePanelConstraints.gridy = 1;
        removeTool = new RemoveTool(this, completePanel, removePanelConstraints);
    }

    // MODIFIES: this
    // EFFECTS: adds the recipe to the recipe list
    public void addRecipe(Recipe r) {
        recipeList.addRecipe(r);
        System.out.println(r.getTitle() + " has been added to your recipe list.");
    }

    // MODIFIES: this
    // EFFECTS: remove the recipe with the given title
    public void removeRecipe(String title) {
        recipeList.removeRecipe(title);
        System.out.println(title + " has been removed from your recipe list.");
    }

    // MODIFIES: this
    // EFFECTS: prints all recipes in the recipe list
    public void viewRecipes(RecipeList recipeList) {
        String allRecipes = "Recipe list: " + recipeList.printAllRecipes();
        setText(allRecipes);
    }

    // MODIFIES: this
    // EFFECTS: loads the recipe list from file
    private void loadRecipeList() throws IOException {
        this.recipeList = jsonReader.read();
    }

    // EFFECTS: saves the recipe list to file
    public void saveRecipeList() throws FileNotFoundException {
        try {
            jsonWriter.open();
            jsonWriter.write(recipeList);
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    // EFFECTS: gets the title entered in the add area
    public String getNewTitle() {
        return titleField.getFieldText();
    }

    // MODIFIES: this
    // EFFECTS: sets the title field in the add area to empty
    public void setAddTitleField() {
        titleField.setEmpty();
    }

    // EFFECTS: gets the title entered in the remove area
    public String getRemoveTitle() {
        return removeTitleField.getFieldText();
    }

    // MODIFIES: this
    // EFFECTS: sets the title field in the remove area to empty
    public void setRemoveTitleField() {
        removeTitleField.setEmpty();
    }

    // EFFECTS: gets the ingredients entered in the add area
    public String getNewIngredients() {
        return ingredientsField.getFieldText();
    }

    // MODIFIES: this
    // EFFECTS: sets the ingredients field in the add area to empty
    public void setIngredientsField() {
        ingredientsField.setEmpty();
    }

    // EFFECTS: gets the instructions entered in the add area
    public String getNewInstructions() {
        return instructionsField.getFieldText();
    }

    // MODIFIES: this
    // EFFECTS: sets the instructions field in the add area to empty
    public void setInstructionsField() {
        instructionsField.setEmpty();
    }

    public static void main(String[] args) throws IOException {
        new UI();
    }
}

