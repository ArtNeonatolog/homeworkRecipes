package me.artsafuanov.homeworkrecipes.service.impl;

import me.artsafuanov.homeworkrecipes.model.Recipe;
import me.artsafuanov.homeworkrecipes.service.RecipeService;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.TreeMap;

@Service
public class RecipeServiceImpl implements RecipeService {
    private static final Map<Integer, Recipe> mapOfRecipes = new TreeMap<>();

    @Override
    public Recipe addRecipe(Recipe recipe) {
        if (mapOfRecipes.containsKey(recipe.getId())) {
            throw new RecipeException();
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

