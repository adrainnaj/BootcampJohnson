package org.example;

public class Chips implements  OrderItem {

    String type;

    public Chips(String type){
        this.type = type;
    }

    @Override
    public double getCostOfItem() {
        return 1.50;
    }

    @Override
    public String toString() {
        return "Chips - $" + String.format("%.2f",getCostOfItem());
    }
}
