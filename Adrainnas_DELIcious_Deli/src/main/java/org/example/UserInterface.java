package org.example;

import java.util.Scanner;

import static org.example.Cheese.CheeseType.american;

public class UserInterface {
    private static Scanner scanner;
    private static Order currentOrder = new Order();


    public static void display() {

        scanner = new Scanner(System.in);

        //very start of the app..
        // we are just making sure the app is running

        boolean isRunnning = true;

        while (isRunnning) {
            System.out.println(" ");
            System.out.println("-------Welcome to Adrainna's DELI-cious Delight!------");
            System.out.println(" ");
            System.out.println("S) Start a new order");
            System.out.println("X) Exit App");

            String selection = scanner.nextLine().trim();

            switch (selection.toUpperCase()) {

                case "S":
                    //call Order menu
                    currentOrder = new Order();
                    showOrderView();
                    break;

                case "X":
                    //exit aPP
                    System.out.println("Thank you for stopping by!");
                    isRunnning = false;
                    break;

                default:
                    System.out.println("Error: Please trying again");
            }
        }

    } //end of the display method

    private static void showOrderView() {
        boolean ordering = true;


        while (ordering) {
            System.out.println("What would you like to do ?\n");
            System.out.println("1) Add a sandwich");
            System.out.println("2) Add a drink");
            System.out.println("3) Add chips");
            System.out.println("4) Checkout");
            System.out.println("5) Cancel Order");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    Sandwich sandwich = SandwichBuilder.buildSandwich(scanner);
                    currentOrder.addItemToOrder(sandwich);
                    break;

                case "2":
                    // drink view
                    currentOrder.addItemToOrder(addDrinkToOrder());
                    break;

                case "3":
                    currentOrder.addItemToOrder(addChipsToOrder());
                    break;

                case "4":
                    System.out.println("Receipt");

                    for (OrderItem item : currentOrder.getItems()) {
                        System.out.println(item);
                    }
                    System.out.printf("Total: $%.2f\n", currentOrder.getOrderTotal());


                    FileManager.save(currentOrder);
                    //we're no longer ordering on this order so it's changed to false
                    System.out.println("Thank you for your order! ");
                    ordering = false;
                    //create a new order because the last order has been checked out
                    currentOrder = new Order();
                    break;

                case "5":
                    System.out.println("Are you sure you wanna cancel this order?Yes or no?");
                    String confirm = scanner.nextLine().trim();
                    if (confirm.equalsIgnoreCase("yes")) {
                        currentOrder = null;
                        System.out.println("Order has been canceled. Returning to the main menu.\n");
                        ordering = false;
                    }
                    else System.out.println("Continue with order.\n");
                    break;
                default:
                    System.out.println("Invalid choice.Please try again.");
            }
        }   // end of the while loop
    }

    private static Chips addChipsToOrder() {
        System.out.println("Would you like chips? Yes or No?");
        String orderChips = UserInterface.scanner.nextLine().trim();

        if (orderChips.equalsIgnoreCase("yes")){
            Chips chips = new Chips(orderChips);
//            currentOrder.addItemToOrder(chips);
            System.out.println("Chips added to order. Don't forget to grab them! \n");
            return chips;
        }
        else {
            System.out.println("No chips added.\n");
        }
        return null;
    }

    //addDrinkToOrder
    private static Drink addDrinkToOrder() {
        System.out.println("Choose a Drink Flavor: ");
        System.out.println("Type name of Flavor or select 'done' when finished");
        for (DrinkFlavor flavor : DrinkFlavor.values()) {
            System.out.println("-" + flavor.toString());
        }
        DrinkFlavor drinkFlavor = null;
        while (drinkFlavor == null) {
            System.out.println("Drink flavor: ");
            System.out.println();
            String userInput = scanner.nextLine();
            for (DrinkFlavor flavor : DrinkFlavor.values()) {
                if (flavor.name().equalsIgnoreCase(userInput)) {
                    drinkFlavor = flavor;
                    break;
                }
            }
            if (drinkFlavor == null) {
                System.out.println("Invalid flavor");
            }
        }
        System.out.println("Choose a drink size(Small, Medium, Large)");
        DrinkSize drinkSize = null;
        while (drinkSize == null) {
            String userInput = scanner.nextLine();
            for (DrinkSize size : DrinkSize.values()) {
                if (size.name().equalsIgnoreCase(userInput)) {
                    drinkSize = size;
                    break;
                }
            }
            if (drinkSize == null) {
                System.out.println("Invalid Size. Try again.");
            }
        }
        Drink drink = new Drink(drinkFlavor, drinkSize);
        System.out.println("Added: " + drink);
        return drink;
    }
}