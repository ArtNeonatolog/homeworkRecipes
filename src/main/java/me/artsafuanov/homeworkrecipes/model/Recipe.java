package me.artsafuanov.homeworkrecipes.model;

import java.util.List;

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

        public Integer getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getTimeForCook() {
            return timeForCook;
        }

        public void setTimeForCook(int timeForCook) {
            this.timeForCook = timeForCook;
        }

        public List<Ingredient> getIngredients() {
            return ingredients;
        }

        public void setIngredients(List<Ingredient> ingredients) {
            this.ingredients = ingredients;
        }

        public List<String> getStepsForCook() {
            return stepsForCook;
        }

        public void setStepsForCook(List<String> stepsForCook) {
            this.stepsForCook = stepsForCook;
        }

    }
