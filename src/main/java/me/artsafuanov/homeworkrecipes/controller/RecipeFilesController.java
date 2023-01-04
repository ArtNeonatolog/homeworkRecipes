package me.artsafuanov.homeworkrecipes.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.artsafuanov.homeworkrecipes.service.FileRecipeService;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@RestController
@RequestMapping("/files/recipe")
@Tag(name = "Рецепты - работа с файлами", description = "Можно загружать, скачивать рецепты")
public class RecipeFilesController {
    private final FileRecipeService fileRecipeService;

    public RecipeFilesController(FileRecipeService fileRecipeService) {
        this.fileRecipeService = fileRecipeService;
    }

    @GetMapping("/export")
    @Operation(summary = "Скачивание файла рецептов",
            description = "Данный метод предназначен для скачивания файла рецептов"
    )
    public ResponseEntity<InputStreamSource> downloadRecipeFile() throws FileNotFoundException {
        File file = fileRecipeService.getRecipeFile();
        if (file.exists()) {
            InputStreamResource inputStreamResource = new InputStreamResource(new FileInputStream(file));
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM).contentLength(file.length())
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"RecipeFileLog.json\"")
                    .body(inputStreamResource);
        } return ResponseEntity.noContent().build();
    }


    @PostMapping(value = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Загрузка файла рецептов",
            description = "Данный метод предназначен для загрузки файла рецептов"
    )
    public ResponseEntity<Void> uploadRecipeFile(@RequestParam MultipartFile file) {
        fileRecipeService.cleanRecipeFile();
        File dataFile = fileRecipeService.getRecipeFile();
        try (FileOutputStream fos = new FileOutputStream(dataFile)) {
            IOUtils.copy(file.getInputStream(), fos);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            e.printStackTrace();
        } return ResponseEntity.status(500).build();
    }
}
