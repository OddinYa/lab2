package com.example.lab2.models;
import lombok.Getter;
import lombok.Setter;


import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class User  {

    public User(Money money,String name){

        this.money = money;
        this.spending = 0;
        this.name = name;
        this.buyList = new ArrayList<>();

    }

    private  Integer id;
    private Money money;
    private float spending;
    private String name;

    private List<Buy> buyList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Money getMoney() {
        return money;
    }

    public void setMoney(Money money) {
        this.money = money;
    }

    public float getSpending() {
        return spending;
    }

    public void setSpending(float spending) {
        this.spending = spending;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Buy> getBuyList() {
        return buyList;
    }

    public void setBuyList(List<Buy> buyList) {
        this.buyList = buyList;
    }
//  @Override
 //  public void spendMoney(Buy buy) {
 //      buyList.add(buy);
 //      spending+=buy.getCost();
 //      money.setCash(money.getCash()- buy.getCost());
 //  }

 //  @Override
 //  public void makeMoney(float cash) {
 //       money.setCash(money.getCash()+cash);
 //   }
}