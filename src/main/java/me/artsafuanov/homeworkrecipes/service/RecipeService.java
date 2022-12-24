package me.artsafuanov.homeworkrecipes.service;

import me.artsafuanov.homeworkrecipes.model.Recipe;

public interface RecipeService {
    Recipe addRecipe (Recipe recipe);

    Recipe getRecipe (Integer recipeId);

}
