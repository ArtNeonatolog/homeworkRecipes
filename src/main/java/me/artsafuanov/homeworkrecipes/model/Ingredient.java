package me.artsafuanov.homeworkrecipes.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ingredient {
    private Integer id;

    private static int counter = 0;

    private String name;

    private int amountOfIngredients;  //количество

    private String unit;  //единица измерения


}

