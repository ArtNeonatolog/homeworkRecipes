package me.artsafuanov.homeworkrecipes.model;

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

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAmountOfIngredients() {
        return amountOfIngredients;
    }

    public String getUnit() {
        return unit;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAmountOfIngredients(int amountOfIngredients) {
        this.amountOfIngredients = amountOfIngredients;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

}
