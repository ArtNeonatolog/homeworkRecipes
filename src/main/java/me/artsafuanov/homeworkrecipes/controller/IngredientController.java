package me.artsafuanov.homeworkrecipes.controller;

import me.artsafuanov.homeworkrecipes.model.Ingredient;
import me.artsafuanov.homeworkrecipes.service.IngredientService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ingredient")
public class IngredientController {
    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping("/{ingredientId}")
    public Ingredient getIngredientById (@PathVariable("ingredientId") Integer ingredientId) {
        return ingredientService.getIngredient(ingredientId);
    }

    @PostMapping
    public Ingredient addIngredient (@RequestBody Ingredient ingredient) {
        return ingredientService.addIngredient(ingredient);
    }

}

