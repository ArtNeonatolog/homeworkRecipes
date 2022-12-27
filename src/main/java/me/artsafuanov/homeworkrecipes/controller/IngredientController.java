package me.artsafuanov.homeworkrecipes.controller;

import me.artsafuanov.homeworkrecipes.model.Ingredient;
import me.artsafuanov.homeworkrecipes.service.IngredientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ingredient")
public class IngredientController {
    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping("/all")
    public List<Ingredient> getAllIngredients () {
        return this.ingredientService.getAllIngredients();
    }

    @GetMapping("/{ingredientId}")
    public Ingredient getIngredientById (@PathVariable("ingredientId") Integer ingredientId) {
        return ingredientService.getIngredient(ingredientId);
    }

    @PostMapping
    public Ingredient addIngredient (@RequestBody Ingredient ingredient) {
        return ingredientService.addIngredient(ingredient);
    }

    @PutMapping("/{ingredientId}")
    public Ingredient updateIngredient (@PathVariable("ingredientId") Integer ingredientId, @RequestBody Ingredient ingredient) {
        return ingredientService.updateIngredient(ingredientId, ingredient);
    }

    @DeleteMapping("/{ingredientId}")
    public Ingredient deleteIngredient (@PathVariable("ingredientId") Integer ingredientId) {
        return ingredientService.deleteIngredient(ingredientId);
    }

}

