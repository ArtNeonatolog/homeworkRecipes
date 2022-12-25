package me.artsafuanov.homeworkrecipes.service;

import me.artsafuanov.homeworkrecipes.model.Recipe;
import org.springframework.stereotype.Service;

@Service
public interface RecipeService {
    Recipe addRecipe (Recipe recipe);

    Recipe getRecipe (Integer recipeId);

}
