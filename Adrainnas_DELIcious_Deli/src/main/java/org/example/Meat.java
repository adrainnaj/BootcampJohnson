package org.example;

import org.w3c.dom.ls.LSOutput;

public class Meat {
    public enum MeatType {
        steak, ham, salami, roastBeef, chicken, bacon
    }

    private MeatType meatType;
    private boolean isExtra;

    public Meat(MeatType meatType, boolean isExtra) {
        this.meatType = meatType;
        this.isExtra = isExtra;
    }

    public String getName() {
        if (isExtra) {
            return "Extra : " + meatType.name().toLowerCase();
        } else {
            return meatType.name().toLowerCase();
        }
    }
    public double getPrice(Sandwich.Size size){
        double price = 0.00;

        switch (size){
            case small:
                price = isExtra ? 0.50 : 1.00;
                break;
            case medium:
                price = isExtra ? 1.00 : 2.00;
                break;
            case large:
                price = isExtra ? 1.50 : 3.00;
                break;
            default:
                price = 0.0; // in case they type something on accident
        }
        return price;
    }

    public boolean isExtra(){
        return isExtra;
    }
    public MeatType getType(){
        return meatType;
    }
}

