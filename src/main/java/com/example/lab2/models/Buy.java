package com.example.lab2.models;


import java.time.LocalDate;

public class Buy {

    public Buy(float cost, String name,LocalDate date){
        this.cost = cost;
        this.name = name;
        this.date = date;
    }
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    private float cost;
    private String name;
    private LocalDate date;

}