package me.artsafuanov.homeworkrecipes.service.impl;

import me.artsafuanov.homeworkrecipes.model.Ingredient;
import me.artsafuanov.homeworkrecipes.service.IngredientService;

import java.util.Map;
import java.util.TreeMap;

public class IngredientServiceImpl implements IngredientService {
    private static final Map<Integer, Ingredient> mapOfIngredients = new TreeMap<>();


    @Override
    public Ingredient addIngredient(Ingredient ingredient) {
        if (mapOfIngredients.containsKey(ingredient.getId())) {
            throw new RuntimeException("Такой ингредиент уже есть!");
        } else {
            mapOfIngredients.put(ingredient.getId(), ingredient);
        }
        return ingredient;
    }

    @Override
    public Ingredient getIngredient (Integer ingredientId) {
        if (mapOfIngredients.containsKey(ingredientId)) {
            return mapOfIngredients.get(ingredientId);
        } else {
            throw new RuntimeException("Такого ингредиента нет!");
        }
    }
}


