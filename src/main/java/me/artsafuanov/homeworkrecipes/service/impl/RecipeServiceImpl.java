package me.artsafuanov.homeworkrecipes.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.artsafuanov.homeworkrecipes.model.Recipe;
import me.artsafuanov.homeworkrecipes.service.RecipeService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
public class RecipeServiceImpl implements RecipeService {
    private Map<Integer, Recipe> mapOfRecipes = new TreeMap<>();

    private final FileRecipeServiceImpl fileRecipeService;

    public RecipeServiceImpl(FileRecipeServiceImpl fileRecipeService) {
        this.fileRecipeService = fileRecipeService;
    }

    @PostConstruct
    private void init () {
        readFromFile();
    }

    @Override
    public Recipe addRecipe(Recipe recipe) {
        if (mapOfRecipes.containsKey(recipe.getId())) {
            throw new RecipeException("Такой рецепт уже есть!");
        } else {
            mapOfRecipes.put(recipe.getId(), recipe);
            saveToFile();
        }
        return recipe;
    }

    @Override
    public Recipe getRecipe(Integer recipeId) {
        if (mapOfRecipes.containsKey(recipeId)) {
            return mapOfRecipes.get(recipeId);
        } else {
            throw new RecipeException("Такого рецепта нет!");
        }
    }

    @Override
    public Recipe updateRecipe(Integer recipeId, Recipe recipe) {
        if (mapOfRecipes.containsKey(recipeId)) {
            mapOfRecipes.put(recipeId, recipe);
            saveToFile();
            return recipe;
        } else {
            throw new RecipeException("Невозможно обновить рецепт, так как такого рецепта нет!");
        }
    }

    @Override
    public Recipe deleteRecipe(Integer recipeId) {
            return mapOfRecipes.remove(recipeId);
    }

    @Override
    public List<Recipe> getAllRecipes () {
        return new ArrayList<>(this.mapOfRecipes.values());
    }

    private void saveToFile () {
        try {
            String json = new ObjectMapper().writeValueAsString(mapOfRecipes);
            fileRecipeService.saveToFile(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void readFromFile () {
        try {
            String json = fileRecipeService.readFromFile();
            mapOfRecipes = new ObjectMapper().readValue(json, new TypeReference<TreeMap<Integer, Recipe>>(){
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}


