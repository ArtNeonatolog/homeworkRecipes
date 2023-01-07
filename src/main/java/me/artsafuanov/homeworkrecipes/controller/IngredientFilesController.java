package me.artsafuanov.homeworkrecipes.controller;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.artsafuanov.homeworkrecipes.service.FileIngredientService;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@RestController
@RequestMapping("/files/ingredient")
@Tag(name = "Ингредиенты - работа с файлами", description = "Можно загружать, скачивать ингредиенты")
public class IngredientFilesController {
    private final FileIngredientService fileIngredientService;

    public IngredientFilesController(FileIngredientService fileIngredientService) {
        this.fileIngredientService = fileIngredientService;
    }

    @GetMapping("/export")
    @Operation(summary = "Скачивание файла ингредиентов",
            description = "Данный метод предназначен для скачивания файла ингредиентов"
    )
    public ResponseEntity<InputStreamResource> downloadIngredientFile() throws FileNotFoundException {
        File file = fileIngredientService.getINgredientFile();
        if (file.exists()) {
            InputStreamResource inputStreamResource = new InputStreamResource(new FileInputStream(file));
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM).contentLength(file.length())
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"IngredientFileLog.json\"")
                    .body(inputStreamResource);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping(value = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Загрузка файла ингредиентов",
            description = "Данный метод предназначен для загрузки файла ингредиентов"
    )
    public ResponseEntity<Void> uploadIngredientFile(@RequestParam MultipartFile file) {
        fileIngredientService.cleanIngredientFile();
        File dataFile = fileIngredientService.getINgredientFile();
        try (FileOutputStream fos = new FileOutputStream(dataFile)) {
            IOUtils.copy(file.getInputStream(), fos);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            e.printStackTrace();
        } return ResponseEntity.status(500).build();
    }
}

