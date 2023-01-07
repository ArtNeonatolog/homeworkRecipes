package me.artsafuanov.homeworkrecipes.service;

import org.springframework.stereotype.Service;

import java.io.File;

@Service
public interface FileIngredientService {
    boolean saveToFile(String json);

    String readFromFile();

    boolean cleanIngredientFile();

    File getINgredientFile();
}
