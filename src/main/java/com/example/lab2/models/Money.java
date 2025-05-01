package com.example.lab2.models;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Money  {

    public Money(float cash){

        this.cash = cash;
    }
    private Integer id;
    private float cash;

    public float getCash() {
        return cash;
    }

    public void setCash(float cash) {
        this.cash = cash;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
// @Override
  // public void makeMoney(float cash) {
  //     this.cash += cash;
  // }
}