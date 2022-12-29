package me.artsafuanov.homeworkrecipes.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Recipe {

    private Integer id;

    private static int counter = 0;

    private String name;

    private int timeForCook;  //время в минутах

    private List<Ingredient> ingredients;

    private List<String> stepsForCook;


}
