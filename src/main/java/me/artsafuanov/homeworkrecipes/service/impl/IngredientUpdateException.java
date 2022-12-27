package me.artsafuanov.homeworkrecipes.service.impl;

import java.lang.constant.Constable;

public class IngredientUpdateException extends RuntimeException {
    public String toString () {
        return "Невозможно обновить ингредиент, так как такого ингредиента нет!";
    }
}
