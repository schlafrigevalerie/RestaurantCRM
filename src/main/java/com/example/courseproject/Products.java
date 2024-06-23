package com.example.courseproject;


public class Products {
    int quantity;
    String title, unitOfMeasurement;
    public Products(String title, String unitOfMeasurement, int quantity){
        this.title = title;
        this.unitOfMeasurement = unitOfMeasurement;
        this.quantity = quantity;
    }
    public int getQuantity(){
        return quantity;
    }
    public String getTitle(){
        return title;
    }
    public String getUnitOfMeasurement(){
        return unitOfMeasurement;
    }

}
