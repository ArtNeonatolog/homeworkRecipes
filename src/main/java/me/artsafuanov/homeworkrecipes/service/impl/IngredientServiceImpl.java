package me.artsafuanov.homeworkrecipes.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.artsafuanov.homeworkrecipes.model.Ingredient;
import me.artsafuanov.homeworkrecipes.service.IngredientService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
public class IngredientServiceImpl implements IngredientService {
    private Map<Integer, Ingredient> mapOfIngredients = new TreeMap<>();

    private final FileIngredientServiceImpl fileIngredientService;

    public IngredientServiceImpl(FileIngredientServiceImpl fileIngredientService) {
        this.fileIngredientService = fileIngredientService;
    }

    @PostConstruct
    private void init () {
        readFromFile();
    }


    @Override
    public Ingredient addIngredient(Ingredient ingredient) {
        if (mapOfIngredients.containsKey(ingredient.getId())) {
            throw new IngredientException("Такой ингредиент уже есть!");
        } else {
            mapOfIngredients.put(ingredient.getId(), ingredient);
            saveToFile();
        }
        return ingredient;
    }

    @Override
    public Ingredient getIngredient(Integer ingredientId) {
        if (mapOfIngredients.containsKey(ingredientId)) {
            return mapOfIngredients.get(ingredientId);
        } else {
            throw new IngredientException("Такого ингредиента нет!");
        }
    }

    @Override
    public Ingredient updateIngredient(Integer ingredientId, Ingredient ingredient) {
        if (mapOfIngredients.containsKey(ingredientId)) {
            mapOfIngredients.put(ingredientId, ingredient);
            saveToFile();
            return ingredient;
        } else {
            throw new IngredientException("Невозможно обновить ингредиент, так как такого ингредиента нет!");
        }
    }

    @Override
    public Ingredient deleteIngredient(Integer ingredientId) {
            return mapOfIngredients.remove(ingredientId);
    }

    @Override
    public List<Ingredient> getAllIngredients() {
        return new ArrayList<>(mapOfIngredients.values());
    }

    private void saveToFile () {
        try {
            String json = new ObjectMapper().writeValueAsString(mapOfIngredients);
            fileIngredientService.saveToFile(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void readFromFile () {
        try {
            String json = fileIngredientService.readFromFile();
            mapOfIngredients = new ObjectMapper().readValue(json, new TypeReference<TreeMap <Integer, Ingredient>> (){
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}


