package me.artsafuanov.homeworkrecipes.service.impl;

import me.artsafuanov.homeworkrecipes.model.Ingredient;
import me.artsafuanov.homeworkrecipes.model.Recipe;
import me.artsafuanov.homeworkrecipes.service.IngredientService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
public class IngredientServiceImpl implements IngredientService {
    private final Map<Integer, Ingredient> mapOfIngredients = new TreeMap<>();


    @Override
    public Ingredient addIngredient(Ingredient ingredient) {
        if (mapOfIngredients.containsKey(ingredient.getId())) {
            throw new IngredientException("Такой ингредиент уже есть!");
        } else {
            mapOfIngredients.put(ingredient.getId(), ingredient);
        }
        return ingredient;
    }

    @Override
    public Ingredient getIngredient(Integer ingredientId) {
        if (mapOfIngredients.containsKey(ingredientId)) {
            return mapOfIngredients.get(ingredientId);
        } else {
            throw new IngredientException("Такого ингредиента нет!");
        }
    }

    @Override
    public Ingredient updateIngredient(Integer ingredientId, Ingredient ingredient) {

        if (mapOfIngredients.containsKey(ingredientId)) {
            return mapOfIngredients.put(ingredientId, ingredient);
        } else {
            throw new IngredientException("Невозможно обновить ингредиент, так как такого ингредиента нет!");
        }
    }

    @Override
    public Ingredient deleteIngredient(Integer ingredientId) {
            return mapOfIngredients.remove(ingredientId);
    }

    @Override
    public List<Ingredient> getAllIngredients() {
        return new ArrayList<>(mapOfIngredients.values());
    }
}


