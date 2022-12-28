package me.artsafuanov.homeworkrecipes.service.impl;

import me.artsafuanov.homeworkrecipes.model.Recipe;
import me.artsafuanov.homeworkrecipes.service.RecipeService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
public class RecipeServiceImpl implements RecipeService {
    private final Map<Integer, Recipe> mapOfRecipes = new TreeMap<>();

    @Override
    public Recipe addRecipe(Recipe recipe) {
        if (mapOfRecipes.containsKey(recipe.getId())) {
            throw new RecipeException("Такой рецепт уже есть!");
        } else {
            mapOfRecipes.put(recipe.getId(), recipe);
        }
        return recipe;
    }

    @Override
    public Recipe getRecipe(Integer recipeId) {
        if (mapOfRecipes.containsKey(recipeId)) {
            return mapOfRecipes.get(recipeId);
        } else {
            throw new RecipeException("Такого рецепта нет!");
        }
    }

    @Override
    public Recipe updateRecipe(Integer recipeId, Recipe recipe) {
        if (mapOfRecipes.containsKey(recipeId)) {
            return mapOfRecipes.put(recipeId, recipe);
        } else {
            throw new RecipeException("Невозможно обновить рецепт, так как такого рецепта нет!");
        }
    }

    @Override
    public Recipe deleteRecipe(Integer recipeId) {
            return mapOfRecipes.remove(recipeId);
    }

    @Override
    public List<Recipe> getAllRecipes () {
        return new ArrayList<>(this.mapOfRecipes.values());
    }
}


