package me.artsafuanov.homeworkrecipes.service;

import org.springframework.stereotype.Service;

@Service
public interface FileIngredientService {
    boolean saveToFile(String json);

    String readFromFile();
}
