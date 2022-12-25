package me.artsafuanov.homeworkrecipes.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class IngredientException extends RuntimeException {
    public String toString () {
        return "Такого ингредиента нет!";
    }
}
