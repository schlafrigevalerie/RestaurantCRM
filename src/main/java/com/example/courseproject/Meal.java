package com.example.courseproject;

import java.time.LocalDate;
import java.time.LocalTime;

public class Meal {
    int numberOrder, numberTable;
    LocalDate dateMeals;
    LocalTime beginTime, endTime;
    public Meal(int numberOrder, LocalDate dateMeals, LocalTime beginTime, LocalTime endTime, int numberTable){
        this.numberOrder = numberOrder;
        this.dateMeals = dateMeals;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.numberTable = numberTable;
    }

    public int getNumberOrder() {
        return numberOrder;
    }

    public LocalDate getDateMeals() {
        return dateMeals;
    }

    public LocalTime getBeginTime() {
        return beginTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public int getNumberTable() {
        return numberTable;
    }
}
