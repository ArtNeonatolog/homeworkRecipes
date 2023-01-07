package me.artsafuanov.homeworkrecipes.service;

import org.springframework.stereotype.Service;

import java.io.File;

@Service
public interface FileRecipeService {
    boolean saveToFile(String json);

    String readFromFile();

    boolean cleanRecipeFile();

    File getRecipeFile();
}
