package com.example.lab2.models;


import lombok.AllArgsConstructor;

import java.time.LocalDate;

public class Buy {

    public Buy(Integer id ,Integer idUser, Float cost,String name,LocalDate date,String userName){
        this.id = id;
        this.idUser = idUser;
        this.cost = cost;
        this.name = name;
        this.date = date;
        this.userName = userName;
    }
    private Integer id;

    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    private Integer idUser;
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