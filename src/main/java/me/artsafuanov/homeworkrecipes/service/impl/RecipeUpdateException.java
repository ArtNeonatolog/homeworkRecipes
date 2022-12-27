package me.artsafuanov.homeworkrecipes.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class RecipeUpdateException extends RecipeException{
    public String toString () {
        return "Невозможно обновить рецепт, т.к. такого рецепта нет!";
    }
}
