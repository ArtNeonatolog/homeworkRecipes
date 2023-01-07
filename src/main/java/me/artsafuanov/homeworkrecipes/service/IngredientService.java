package me.artsafuanov.homeworkrecipes.service;

import me.artsafuanov.homeworkrecipes.model.Ingredient;
import me.artsafuanov.homeworkrecipes.model.Recipe;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IngredientService {
        Ingredient addIngredient (Ingredient ingredient);

        Ingredient getIngredient (Integer ingredientId);

    Ingredient updateIngredient (Integer recipeId, Ingredient ingredient);

    Ingredient deleteIngredient (Integer ingredientId);

    List<Ingredient> getAllIngredients ();

    }


