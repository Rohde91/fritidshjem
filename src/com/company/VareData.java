package com.company;

import java.io.Serializable;

public class VareData implements Serializable {

    private int amount;
    private String varer;
    private double price;

    //Default
    public VareData(){

    }

    //Constructor
    public VareData(int amount, String varer, double price) {
        this.amount = amount;
        this.varer = varer;
        this.price = price;
    }

    //Setter and getter
    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getVarer() {
        return varer;
    }

    public void setVarer(String varer) {
        this.varer = varer;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
