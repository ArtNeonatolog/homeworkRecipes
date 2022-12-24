package me.artsafuanov.homeworkrecipes.service;

import me.artsafuanov.homeworkrecipes.model.Ingredient;

public interface IngredientService {
        Ingredient addIngredient (Ingredient ingredient);

        Ingredient getIngredient (Integer ingredientId);

    }


