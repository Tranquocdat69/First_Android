package com.example.myapplication;

import java.util.Date;

public class Shopping {
    private int Id;
    private String name;
    private int count;
    private double price;
    private Date dateBought;

    public Shopping(int id, String name, int count, double price, Date dateBought) {
        Id = id;
        this.name = name;
        this.count = count;
        this.price = price;
        this.dateBought = dateBought;
    }

    public Shopping(String name, int count, double price, Date dateBought) {
        this.name = name;
        this.count = count;
        this.price = price;
        this.dateBought = dateBought;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getDateBought() {
        return dateBought;
    }

    public void setDateBought(Date dateBought) {
        this.dateBought = dateBought;
    }
}
