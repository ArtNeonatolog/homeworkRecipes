package me.artsafuanov.homeworkrecipes.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ingredient {

    private String name;

    private Integer amountOfIngredients;  //количество

    private String unit;  //единица измерения


}

