package me.artsafuanov.homeworkrecipes.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.artsafuanov.homeworkrecipes.model.Ingredient;
import me.artsafuanov.homeworkrecipes.service.IngredientService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
public class IngredientServiceImpl implements IngredientService {
    private Map<Integer, Ingredient> mapOfIngredients = new TreeMap<>();

    private static Integer lastId = 0;

    private final FileIngredientServiceImpl fileIngredientService;

    public IngredientServiceImpl(FileIngredientServiceImpl fileIngredientService) {
        this.fileIngredientService = fileIngredientService;
    }

    @PostConstruct
    private void init () {
        try {
            fileIngredientService.readFromFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public Integer addIngredient(Ingredient ingredient) {
            mapOfIngredients.put(lastId, ingredient);
            saveToFile();
        return lastId++;
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
            DataFile dataFile = new DataFile (lastId + 1, mapOfIngredients);
            String json = new ObjectMapper().writeValueAsString(dataFile);
            fileIngredientService.saveToFile(json);
        } catch (JsonProcessingException e) {
            throw new IngredientException("Файл с ингредиентами не удалось сохранить!");
        }
    }

    @Override
    public void addIngredientsFromInputStream(InputStream inputStream) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] array = StringUtils.split(line, '|');
                Ingredient ingredient = new Ingredient(String.valueOf(array[0]), Integer.valueOf(array[1]), String.valueOf(array[2]));
                addIngredient(ingredient);
            }
        }
    }


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private static class DataFile {
        private Integer id;
        private Map<Integer, Ingredient> ingredients;
    }


}


