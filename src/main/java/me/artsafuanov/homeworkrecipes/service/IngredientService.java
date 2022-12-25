package me.artsafuanov.homeworkrecipes.service;

import me.artsafuanov.homeworkrecipes.model.Ingredient;
import org.springframework.stereotype.Service;

@Service
public interface IngredientService {
        Ingredient addIngredient (Ingredient ingredient);

        Ingredient getIngredient (Integer ingredientId);

    }


