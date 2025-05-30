package org.example;

public enum DrinkSize  {
    small(2.00), medium(2.50), large(3.00);
    final double drinkPrice;

    DrinkSize(double drinkPrice) {
        this.drinkPrice = drinkPrice;
    }


    public double getPrice() {
        return drinkPrice;
    }
}
