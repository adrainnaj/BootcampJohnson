package org.example;

import java.util.Scanner;

//import static org.example.UserInterface.scanner;

public class SandwichBuilder {
    Scanner scanner;

    public static Sandwich buildSandwich(Scanner scanner) {
        System.out.println("Build Your Sandwich");


        Sandwich.Size size = null;
        while (size == null) {
            System.out.println("Choose sandwich size: (Small, Medium, Large)");
            String choice = scanner.nextLine();
            for (Sandwich.Size sizes : Sandwich.Size.values()) {
                if (sizes.name().equalsIgnoreCase(choice)) {
                    size = sizes;
                    break;
                }
            }
            if (size == null) {
                System.out.println("Invalid size. Please try again.");
            }
        }

        Sandwich.BreadType breadType = null;
        while (breadType == null) {
            System.out.println("Choose your Bread Type: (White, Wheat, Rye, or Wrap)");
            String choice = scanner.nextLine();
            for (Sandwich.BreadType bread : Sandwich.BreadType.values()) {
                if (bread.name().equalsIgnoreCase(choice)) {
                    breadType = bread;
                    break;
                }
            }
            if (breadType == null) {
                System.out.println("Invalid Option. Please try again.");
            }
        }
        // Do you want it toasted
        System.out.println("Do you want your Sandwich toasted ? Yes or No?");
        String options = scanner.nextLine();
        boolean toasted = options.equalsIgnoreCase("yes");

        Sandwich sandwich = new Sandwich(size, breadType, toasted);

        //Choose the meat.
        System.out.println("Choose the Meat Type");
        System.out.println("Type name to add meat or 'none' to skip, 'done' when finished.");
        for (Meat.MeatType mType : Meat.MeatType.values()) {
            System.out.println(mType.name().toLowerCase() + " ");
        }
        System.out.println();

        while (true) {
            System.out.println("Add Meat or 'done': ");
            String userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase("done") || userInput.equalsIgnoreCase("none")){
                break;
            }
            Meat.MeatType meatType = null;
            for (Meat.MeatType mType : Meat.MeatType.values()) {
                if (mType.name().equalsIgnoreCase(userInput)) {
                    meatType = mType;
                    break;
                }
            }
            if (meatType == null) {
                System.out.println("Invalid option. Please try again.");
            }

            sandwich.addMeat(new Meat(meatType,false));

            System.out.println("Would you like extra ? Yes or No?");
            boolean extra = scanner.nextLine().equalsIgnoreCase("yes");

            if(extra) {
                sandwich.addMeat(new Meat(meatType, extra));
            }

        }

        //Choose the cheese.
        System.out.println("Choose your Cheese Type");
        System.out.println("Type name to add cheese or 'none' and 'done' when finished.");
        for (Cheese.CheeseType cType : Cheese.CheeseType.values()) {
            System.out.println(cType.name().toLowerCase());
        }
        System.out.println();

        while (true) {
            System.out.println("Cheese or 'done': ");
            String userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase("done") || userInput.equalsIgnoreCase("none")) {
                break;
            }
            Cheese.CheeseType cheeseType = null;
            for (Cheese.CheeseType cType : Cheese.CheeseType.values()) {
                if (cType.name().equalsIgnoreCase(userInput)) {
                    cheeseType = cType;
                    break;
                }
            }

            sandwich.addCheese(new Cheese(cheeseType, false));
            if (cheeseType == null) {
                System.out.println("Invalid Option.Please try again.");
            }
            System.out.println("Would you like extra ? Yes or No?");
            boolean extra = scanner.nextLine().equalsIgnoreCase("yes");

            if(extra){
                sandwich.addCheese(new Cheese(cheeseType, extra));
            }
        }

        //Choose the topping.
        System.out.println("\nChoose your Toppings");
        System.out.println("Type name to add toppings or 'done' when finished.");
        for (Sandwich.RegularTopping regularTopping : Sandwich.RegularTopping.values()) {
            System.out.println(" - " + regularTopping.name().toLowerCase());
        }
        System.out.println();

        while (true) {
            System.out.println("Add Toppings or 'done' when finished: ");
            String userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase("done")) {
                break;
            }
            Sandwich.RegularTopping regularTopping = null;
            for (Sandwich.RegularTopping topping : Sandwich.RegularTopping.values()) {
                if (topping.name().equalsIgnoreCase(userInput)) {
                    regularTopping = topping;
                    break;
                }
            }
            if (regularTopping == null) {
                System.out.println("Invalid Option.Please try again.");
            } else {
                sandwich.addRegularTopping(regularTopping);
            }
        }

        System.out.println("\nChoose your Sauce");
        System.out.println("Type name to add sauce or 'done' when finished.");
        for (Sandwich.Sauce sauce : Sandwich.Sauce.values()) {
            System.out.println(" - " + sauce.name().toLowerCase());
        }
        System.out.println();

        while (true) {
            System.out.println("Add Sauce or 'done' when finished: ");
            String userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase("done")) {
                break;
            }
            Sandwich.Sauce sauce = null;
            for (Sandwich.Sauce s : Sandwich.Sauce.values()) {
                if (s.name().equalsIgnoreCase(userInput)) {
                    sauce = s;
                    break;
                }
            }
            if (sauce == null) {
                System.out.println("Invalid Option.Please try again.");
            } else {
                sandwich.addSauce(sauce);
            }
        }
        System.out.println("You sandwich has been created!");
        System.out.println(sandwich.toString());
        return sandwich;
    }
}