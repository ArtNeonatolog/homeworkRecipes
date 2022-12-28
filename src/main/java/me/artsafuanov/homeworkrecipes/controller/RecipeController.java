package me.artsafuanov.homeworkrecipes.controller;

import me.artsafuanov.homeworkrecipes.model.Recipe;
import me.artsafuanov.homeworkrecipes.service.RecipeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recipe")
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {

        this.recipeService = recipeService;
    }

    @GetMapping()
    public List<Recipe> getAllRecipes () {
        return this.recipeService.getAllRecipes();
    }

    @GetMapping("/{recipeId}")
    public Recipe getRecipeById (@PathVariable("recipeId") Integer recipeId) {
        return recipeService.getRecipe(recipeId);
    }

    @PostMapping
    public Recipe addRecipe(@RequestBody Recipe recipe) {

        return recipeService.addRecipe(recipe);
    }

    @PutMapping("/{recipeId}")
    public Recipe updateRecipe (@PathVariable("recipeId")Integer recipeId, @RequestBody Recipe recipe){
        return recipeService.updateRecipe(recipeId, recipe);
    }

    @DeleteMapping("/{recipeId}")
    public Recipe deleteRecipe (@PathVariable("recipeId") Integer recipeId) {
        return recipeService.deleteRecipe(recipeId);
    }

}

