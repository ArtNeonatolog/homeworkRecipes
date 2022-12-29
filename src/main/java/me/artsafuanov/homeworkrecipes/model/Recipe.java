package me.artsafuanov.homeworkrecipes.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class Recipe {

    private final Integer id;

    private static int counter = 0;

    private String name;

    private int timeForCook;  //время в минутах

    private List<Ingredient> ingredients;

    private List<String> stepsForCook;

    public Recipe(String name, int timeForCook, List<Ingredient> ingredients, List<String> stepsForCook) {
        this.id = counter++;
        this.name = name;
        this.timeForCook = timeForCook;
        this.ingredients = ingredients;
        this.stepsForCook = stepsForCook;
    }

}
