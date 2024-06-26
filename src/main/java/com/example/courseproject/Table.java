package com.example.courseproject;

public class Table {
    int number, capacity;
    String status;
    Table(int number, int capacity, String status){
        this.number = number;
        this.capacity = capacity;
        this.status = status;
    }
    public int getNumber(){
        return number;
    }
    public int getCapacity(){
        return capacity;
    }
    public String getStatus(){
        return status;
    }
}
