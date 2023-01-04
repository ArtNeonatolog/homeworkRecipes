package me.artsafuanov.homeworkrecipes.service;

import me.artsafuanov.homeworkrecipes.model.Recipe;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public interface RecipeService {
    Recipe addRecipe (Recipe recipe);
    Recipe getRecipe (Integer recipeId);

    Recipe updateRecipe (Integer recipeId, Recipe recipe);

    Recipe deleteRecipe (Integer recipeId);

    List<Recipe> getAllRecipes ();

    void addRecipesFromInputStream (InputStream inputStream) throws IOException;
}
