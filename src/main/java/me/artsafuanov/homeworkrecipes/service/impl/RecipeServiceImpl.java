package me.artsafuanov.homeworkrecipes.service.impl;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.artsafuanov.homeworkrecipes.model.Ingredient;
import me.artsafuanov.homeworkrecipes.model.Recipe;
import me.artsafuanov.homeworkrecipes.service.RecipeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.*;
import java.util.*;

@Service
public class RecipeServiceImpl implements RecipeService {
    private Map<Integer, Recipe> mapOfRecipes = new TreeMap<>();

    private static Integer lastId = 0;

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
    public Integer addRecipe(Recipe recipe) {
            mapOfRecipes.put(lastId, recipe);
            saveToFile();
        return lastId++;
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
                DataFile dataFile = new DataFile(lastId + 1, mapOfRecipes);
                String json = new ObjectMapper().writeValueAsString(dataFile);
                fileRecipeService.saveToFile(json);
            } catch (JsonProcessingException e) {
                throw new RecipeException("Файл с рецептами не удалось сохранить!");
            }
        }

        @Override
    public void addRecipesFromInputStream(InputStream inputStream) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] array = StringUtils.split(line, '|');
               Recipe recipe = new Recipe(String.valueOf(array[0]), Integer.valueOf(array[1]), List.of(new Ingredient(Integer.valueOf(array[3]), String.valueOf(array[4]), Integer.valueOf(array[5]), String.valueOf(array[6]))), List.of(String.valueOf(array[7])));
                addRecipe(recipe);
            }
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private static class DataFile {
        private Integer id;
        private Map<Integer, Recipe> recipes;
    }

}


