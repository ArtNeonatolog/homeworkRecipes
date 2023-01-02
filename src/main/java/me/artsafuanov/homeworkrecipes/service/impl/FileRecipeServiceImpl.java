package me.artsafuanov.homeworkrecipes.service.impl;

import me.artsafuanov.homeworkrecipes.service.FileRecipeService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class FileRecipeServiceImpl implements FileRecipeService {

    @Value("${path.to.recipe.file}")
    private String recipeFilePath;

    @Value("${name.of.recipe.file}")
    private String recipeFileName;

    @Override
    public boolean saveToFile(String json) {
        try {
            cleanRecipeFile();
            Files.writeString(Path.of(recipeFilePath, recipeFileName), json);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public String readFromFile() {
            try {
                return Files.readString(Path.of(recipeFilePath, recipeFileName));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    @Override
    public boolean cleanRecipeFile () {
        try {
            Path path = Path.of(recipeFilePath, recipeFileName);
            Files.deleteIfExists(path);
            Files.createFile(path);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public File getRecipeFile () {
        return new File(recipeFilePath + "/" + recipeFileName );
    }

}
