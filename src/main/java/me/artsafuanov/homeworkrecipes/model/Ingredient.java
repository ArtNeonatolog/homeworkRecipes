package me.artsafuanov.homeworkrecipes.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Ingredient {
    private final Integer id;

    private static int counter = 0;

    private String name;

    private int amountOfIngredients;  //количество

    private String unit;  //единица измерения

    public Ingredient(String name, int amountOfIngredients, String unit) {
        this.id = counter++;
        this.name = name;
        this.amountOfIngredients = amountOfIngredients;
        this.unit = unit;
    }

}

