package org.example;

public class Drink implements OrderItem {

    private DrinkFlavor flavor;
    private DrinkSize size;

    public Drink(DrinkFlavor flavor, DrinkSize size){
        this.flavor = flavor;
        this.size = size;
    }

    public double getPrice(){
    return size.getPrice();
    }

    public DrinkFlavor getFlavor() {
        return flavor;
    }

    public void setFlavor(DrinkFlavor flavor) {
        this.flavor = flavor;
    }

    public DrinkSize getSize() {
        return size;
    }

    public void setSize(DrinkSize size) {
        this.size = size;
    }


    @Override
    public double getCostOfItem() {
        return size.drinkPrice;
    }

    @Override
    public String toString() {
        return size.name()+ " " + flavor + "  $ " + String.format("%.2f", getCostOfItem())+"\n";
    }
}
