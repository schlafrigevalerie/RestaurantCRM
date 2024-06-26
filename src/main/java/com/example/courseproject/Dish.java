package com.example.courseproject;

public class Dish {
    String nameDish, ingredient;
    float quantityInDishes;
    public Dish(String nameDish, String ingredient, float quantityInDishes){
        this.nameDish = nameDish;
        this.ingredient = ingredient;
        this.quantityInDishes = quantityInDishes;
    }

    public float getQuantityInDishes() {
        return quantityInDishes;
    }

    public String getIngredient() {
        return ingredient;
    }

    public String getNameDish() {
        return nameDish;
    }
}
