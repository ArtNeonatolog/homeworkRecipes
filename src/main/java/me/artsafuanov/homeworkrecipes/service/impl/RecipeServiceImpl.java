package me.artsafuanov.homeworkrecipes.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.artsafuanov.homeworkrecipes.model.Ingredient;
import me.artsafuanov.homeworkrecipes.model.Recipe;
import me.artsafuanov.homeworkrecipes.service.FileRecipeService;
import me.artsafuanov.homeworkrecipes.service.RecipeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

@Service
public class RecipeServiceImpl implements RecipeService {
    private Map<Integer, Recipe> mapOfRecipes = new TreeMap<>();

    private final FileRecipeServiceImpl fileRecipeService;

    public RecipeServiceImpl(FileRecipeServiceImpl fileRecipeService) {
        this.fileRecipeService = fileRecipeService;
    }

    @PostConstruct
    private void init() {
        try {
            fileRecipeService.readFromFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        if (recipe == null) {
            throw new RecipeException("Невозможно обновить рецепт, так как такого рецепта нет!");
        }
        mapOfRecipes.put(recipeId, recipe);
        saveToFile();
        return recipe;
        }

        @Override
        public Recipe deleteRecipe (Integer recipeId){
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
                throw new RecipeException("Файл с рецептами не удалось сохранить!");
            }
        }
    }


