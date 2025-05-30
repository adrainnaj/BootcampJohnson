package org.example;

public class Cheese {
    public enum CheeseType {
        american, provolone, cheddar, swiss
    }

    private CheeseType cheeseType;
    private boolean isExtra;

    public Cheese(CheeseType cheeseType,boolean isExtra) {
        this.cheeseType = cheeseType;
        this.isExtra = isExtra;
    }

    public String getName() {
        if (isExtra) {
            return "Extra : " + cheeseType.name().toLowerCase();
        } else {
            return cheeseType.name().toLowerCase();
        }
    }

    public double getPrice(Sandwich.Size size) {
        double price = 0.00;

        switch (size) {
            case small:
                price = isExtra ? .30 : .75;
                break;
            case medium:
                price = isExtra ? .60 : 1.50;
                break;
            case large:
                price = isExtra ? .90 : 2.25;
                break;
            default:
                price = 0.00;
        }
        return price;
    }

    public boolean isExtra(){
        return isExtra;
    }

    public CheeseType getCheeseType() {
        return cheeseType;
    }
}