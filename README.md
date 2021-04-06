# Recipe Manager App

## CPSC 210 Project
This application will allow users to manage their recipe collections by adding, selecting, viewing, and 
removing recipes. The intended audience is anyone who cooks or love to collect recipes. I'm interested in this project 
because I am a big fan of cooking, and I would like to have an application to help me 
manage recipes.

## User stories:
- As a user, I want to be able to view all titles of the recipe in my collection
- As a user, I want to be able to select a recipe and view it in detail
- As a user, I want to be able to add a new recipe to my current collection
- As a user, I want to be able to remove a recipe from my current collection
- As a user, I want to be able to save my recipe list to file
- As a user, I want to be able to load my recipe list from file

### Phase 4: Task 2
I completed option 1. The removeRecipe() method in the RecipeList class has a robust design. It throws a 
RecipeNotFoundException when the title of the given recipe does not match any recipes in the current recipe list. 
The tests of this method is in the RecipeListTest class.

I also added a type hierarchy to the project.
In the ui package, I designed a hierarchy of tools. There is an abstract class called Tool, which has 3 subclasses: 
AddTool (add a new recipe), RemoveTool (remove a recipe), and ViewTool (view all recipes). 
Each class overrides 2 methods inherited from the super class: createButton() and addListener().


