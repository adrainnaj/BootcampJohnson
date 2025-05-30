package org.example;

import java.util.ArrayList;
import java.util.List;

import static org.example.Sandwich.Size.*;

public class Sandwich implements OrderItem{


    public enum Size {
        small(4), medium(8), large(12);
        public final int inches;

        Size(int inches) {
            this.inches = inches;
        }
    }
    public enum BreadType {
        white, wheat, rye, wrap;
    }
    public enum RegularTopping {
        lettuce, peppers, onions, tomatoes, jalapenos, cucumbers,
        pickles, guacamole, mushrooms,
    }
    public enum Sauce {
        mayo, mustard, ketchup, ranch, thousandIslands, vinaigrette
    }

    private Size sandwichSize;
    private BreadType breadType;
    private boolean isToasted;


    private List<Meat> meats;
    private List<Cheese> cheeses;
    private List<RegularTopping> regularToppings;
    private List<Sauce> sauces;


    public Sandwich(Size sandwichSize, BreadType breadType, boolean isToasted) {
        this.sandwichSize = sandwichSize;
        this.breadType = breadType;
        this.isToasted = isToasted;

        this.meats = new ArrayList<>();
        this.cheeses = new ArrayList<>();
        this.regularToppings = new ArrayList<>();
        this.sauces = new ArrayList<>();
    }

    public void addMeat(Meat meat) {
        meats.add(meat);
    }
    public void removeMeat(Meat meat){
        meats.remove(meat);
    }

    public void addCheese(Cheese cheese) {
        cheeses.add(cheese);
    }
    public void removeCheese(Cheese cheese){
        cheeses.remove(cheese);
    }

    public void addRegularTopping(RegularTopping regularTopping) {
        regularToppings.add(regularTopping);
    }
    public void removeRegularToppings(RegularTopping regularTopping){
        regularToppings.remove(regularTopping);
    }

    public void addSauce(Sauce sauce) {
        sauces.add(sauce);
    }
    public void removeSauce(Sauce sauce){
        sauces.remove(sauce);
    }

    private double getTotalPrice() {
        double basePrice = 0.00;

        switch (sandwichSize) {
            case small:
                basePrice = 5.50;
                break;
            case medium:
                basePrice = 7.00;
                break;
            case large:
                basePrice = 8.50;
                break;
        }
        for (Meat meat : meats) {
            basePrice += meat.getPrice(sandwichSize);
        }
        for (Cheese cheese : cheeses) {
            basePrice += cheese.getPrice(sandwichSize);
        }
        return basePrice;
    }
    @Override
    public double getCostOfItem() {
        return getTotalPrice();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(sandwichSize.name()).append(" sandwich on ").append(breadType).append("\n");

        if (isToasted) sb.append("Toasted! \n");

        if (!meats.isEmpty()) {
            sb.append("Meat: ");
            for (Meat meat : meats) {
                sb.append(meat.getName()).append(" ");
            }
            sb.append("\n");
        }
        if(!cheeses.isEmpty()){
            sb.append("Cheese: ");
            for(Cheese cheese : cheeses){
                sb.append(cheese.getName()).append(" ");
            }
            sb.append("\n");
        }
        if(!regularToppings.isEmpty()){
            sb.append("Toppings: ");
            for(RegularTopping regularTopping : regularToppings){
                sb.append(regularTopping.name().toLowerCase()).append(" ");
            }
            sb.append("\n");
        }
        if (!sauces.isEmpty()){
            sb.append("Sauces: ");
            for (Sauce sauce : sauces){
                sb.append(sauce.name().toLowerCase()).append(" ");
            }
            sb.append("\n");
        }
        sb.append("Total : $").append(String.format("%.2f", getTotalPrice())).append("\n");
        return sb.toString();
    }

    public Size getSandwichSize() {
        return sandwichSize;
    }

    public BreadType getBreadType() {
        return breadType;
    }

    public boolean isToasted() {
        return isToasted;
    }

    public List<Meat> getMeats() {
        return meats;
    }

    public List<Cheese> getCheeses() {
        return cheeses;
    }

    public List<RegularTopping> getRegularToppings() {
        return regularToppings;
    }

    public List<Sauce> getSauces() {
        return sauces;
    }
}
