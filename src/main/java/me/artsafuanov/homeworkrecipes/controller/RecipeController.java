package me.artsafuanov.homeworkrecipes.controller;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.artsafuanov.homeworkrecipes.model.Recipe;
import me.artsafuanov.homeworkrecipes.service.RecipeService;
import me.artsafuanov.homeworkrecipes.service.impl.Recipe500Exception;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/recipe")
@Tag(name = "Рецепты", description = "CRUD-операции для работы с рецептами")
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {

        this.recipeService = recipeService;
    }

    @GetMapping()
    @Operation(summary = "Получение списка всех рецептов",
            description = "Данный метод предназначен для получения списка всех рецептов"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Получены все рецепты",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Recipe.class))
                            )
                    }
            )
    })
    public List<Recipe> getAllRecipes () {
        return this.recipeService.getAllRecipes();
    }

    @GetMapping("/{recipeId}")
    @Operation(
            summary = "Получение рецепта по Id",
            description = "Можно найти нужный рецепт по Id"
    )
    public Recipe getRecipeById (@PathVariable("recipeId") Integer recipeId) {
        return recipeService.getRecipe(recipeId);
    }

    @PostMapping
    @Operation(
            summary = "Создание рецепта",
            description = "При помощи данного метода можно создать новый рецепт")
    public Integer addRecipe(@RequestBody Recipe recipe) {
        return recipeService.addRecipe(recipe);
    }

    @PutMapping("/{recipeId}")
    @Operation(
            summary = "Обновление рецепта",
            description = "При помощи данного метода можно обновить рецепт")
    public Recipe updateRecipe (@PathVariable("recipeId")Integer recipeId, @RequestBody Recipe recipe){
        return recipeService.updateRecipe(recipeId, recipe);
    }

    @DeleteMapping("/{recipeId}")
    @Operation(
            summary = "Удаление рецепта",
            description = "При помощи данного метода можно удалить рецепт")
    public Recipe deleteRecipe (@PathVariable("recipeId") Integer recipeId) {
        return recipeService.deleteRecipe(recipeId);
    }

    @PostMapping(value = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(
            summary = "Добавление рецептов",
            description = "При помощи данного метода можно добавлять рецепты в файл")
    public Recipe addRecipesFromFile (@RequestParam MultipartFile file) {
          try(InputStream inputStream = file.getInputStream()) {
                  recipeService.addRecipesFromInputStream(inputStream);
              } catch (IOException e) {
                  e.printStackTrace();
              } throw new Recipe500Exception("Файл с рецептами не удалось загрузить!");
          }
    }


