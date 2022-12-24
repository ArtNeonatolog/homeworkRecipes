package me.artsafuanov.homeworkrecipes.controller;

import me.artsafuanov.homeworkrecipes.model.Recipe;
import me.artsafuanov.homeworkrecipes.service.RecipeService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recipe")
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {

        this.recipeService = recipeService;
    }

    @GetMapping("/{recipeId}")
    public Recipe getRecipeById (@PathVariable("recipeId") Integer recipeId) {
        return recipeService.getRecipe(recipeId);
    }

    @PostMapping
    public Recipe addRecipe(@RequestBody Recipe recipe) {
        return recipeService.addRecipe(recipe);
    }

}

