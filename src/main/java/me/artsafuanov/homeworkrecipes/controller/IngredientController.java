package me.artsafuanov.homeworkrecipes.controller;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.artsafuanov.homeworkrecipes.model.Ingredient;
import me.artsafuanov.homeworkrecipes.service.IngredientService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/ingredient")
@Tag(name = "Ингредиенты", description = "CRUD-операции для работы с ингредиентами")
public class IngredientController {
    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping()
    @Operation(
            summary = "Получение списка всех игредиентов",
            description = "Данный метод предназначен для получения списка всех ингредиентов"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Получены все ингредиенты",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Ingredient.class))
                            )
                    }
            )
    })
    public List<Ingredient> getAllIngredients() {
        return this.ingredientService.getAllIngredients();
    }

    @GetMapping("/{ingredientId}")
    @Operation(
            summary = "Получение ингредиента по Id",
            description = "Можно найти нужный ингредиент по Id"
    )
    public Ingredient getIngredientById(@PathVariable("ingredientId") Integer ingredientId) {
        return ingredientService.getIngredient(ingredientId);
    }

    @PostMapping
    @Operation(
            summary = "Создание ингредиента",
            description = "При помощи данного метода можно создать новый ингредиент")
    public Integer addIngredient(@RequestBody Ingredient ingredient) {
        return ingredientService.addIngredient(ingredient);
    }

    @PutMapping("/{ingredientId}")
    @Operation(
            summary = "Обновление ингредиента",
            description = "При помощи данного метода можно обновить ингредиент")
    public Ingredient updateIngredient(@PathVariable("ingredientId") Integer ingredientId, @RequestBody Ingredient ingredient) {
        return ingredientService.updateIngredient(ingredientId, ingredient);
    }

    @DeleteMapping("/{ingredientId}")
    @Operation(
            summary = "Удаление ингредиента",
            description = "При помощи данного метода можно удалить ингредиент"
    )
    public Ingredient deleteIngredient(@PathVariable("ingredientId") Integer ingredientId) {
        return ingredientService.deleteIngredient(ingredientId);
    }

    @PostMapping(value = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(
            summary = "Добавление ингредиентов",
            description = "При помощи данного метода можно добавлять ингредиенты в файл")
    public ResponseEntity<Object> addIngredientsFromFile(@RequestParam MultipartFile file) {
        try (InputStream inputStream = file.getInputStream()) {
            ingredientService.addIngredientsFromInputStream(inputStream);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(e.toString());
        }
    }
}

