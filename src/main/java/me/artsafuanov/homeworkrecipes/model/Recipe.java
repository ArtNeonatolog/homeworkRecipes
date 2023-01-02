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

    private String name;

    private Integer timeForCook;  //время в минутах

    private List<Ingredient> ingredients;

    private List<String> stepsForCook;


}
