package me.artsafuanov.homeworkrecipes.service.impl;

import me.artsafuanov.homeworkrecipes.model.Recipe;
import me.artsafuanov.homeworkrecipes.service.RecipeService;

import java.util.Map;
import java.util.TreeMap;

public class RecipeServiceImpl implements RecipeService {
    private static final Map<Integer, Recipe> mapOfRecipes = new TreeMap<>();

    @Override
    public Recipe addRecipe(Recipe recipe) {
        if (mapOfRecipes.containsKey(recipe.getId())) {
            throw new RuntimeException("Такой рецепт уже есть!");
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
            throw new RuntimeException("Такого ингредиента нет!");
        }
    }
}

