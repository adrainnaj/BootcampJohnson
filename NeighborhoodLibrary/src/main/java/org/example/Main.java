package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Book[] inventory = new Book[20];

        inventory[0] = new Book(501, "978-3-16-148410-0", "The Great Gatsby", false, null);
        inventory[1] = new Book(502, "978-0-14-044913-6", "Crime and Punishment", true, "Alice Johnson");
        inventory[2] = new Book(503, "978-1-4028-9462-6", "1984", false, null);
        inventory[3] = new Book(504, "978-0-06-112008-4", "To Kill a Mockingbird", true, "David Smith");
        inventory[4] = new Book(505, "978-0-7432-7356-5", "Angels & Demons", true, "Maria Gonzalez");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Welcome to the Library! ");
            System.out.println("1) Home Screen");
            System.out.println("2) Show available Books");
            System.out.println("3) Checked Out Book");
            System.out.println("4) Check In Book");
            System.out.println("5) Exit");

            int userChoice = scanner.nextInt();

            switch (userChoice) {
                case 1:
                    storeHomeScreen(inventory, scanner);
                    break;
                case 2:
                    displayAvailableBooks(inventory, scanner);
                    break;
                case 3:
                    displayCheckedOut(inventory, scanner);
                    break;
                case 4:
                    displayCheckedIn(inventory, scanner);
                    break;
                case 5:
                    System.out.println("Thank You! Have a Great Day!");
                    System.exit(0);
            }
        }
    }

    public static void displayAvailableBooks(Book[] inventory, Scanner scanner) {
        System.out.println("Available Books");
        scanner.nextLine();

        boolean availableBooks = false;

        // Show available books
        for (int i = 0; i < inventory.length; i++) {
            Book currentBook = inventory[i];
            if (currentBook != null && !currentBook.isCheckedOut()) {
                System.out.println("ID: " + currentBook.getId() + "| ISBN: " + currentBook.getIsbn()
                        + "| Title: " + currentBook.getTitle());
                availableBooks = true;
            }
        }
        if (!availableBooks) {
            System.out.println("Currently no available books");
            return;
        }
        System.out.println("Enter the name of the book you want to check out. ");
        String title = scanner.nextLine();

        if (title.equalsIgnoreCase("Exit")) {
            System.out.println("Back to home screen");
            return;
        }
        Book chosenBook = null;

        for (int i = 0; i < inventory.length; i++) {
            Book book = inventory[i];
            if (book != null && !book.isCheckedOut() && book.getTitle().equalsIgnoreCase(title)) {
                chosenBook = book;
                break;
            }
        }

        if (chosenBook != null) {
            System.out.println("Enter your First and Last Name to check out the book: ");
            String fullName = scanner.nextLine();
            System.out.println("The book " + chosenBook.getTitle() + " is checked out successfully to " + fullName);
        } else {
            System.out.println("Book is not available.");
        }
    }

    public static void displayCheckedOut(Book[] inventory, Scanner scanner) {
        System.out.println("Checked Out Books");
        scanner.nextLine();

        boolean checkedOutBooks = false;

        // Show checked out books
        for (int i = 0; i < inventory.length; i++) {
            Book currentBook = inventory[i];
            if (currentBook != null && currentBook.isCheckedOut()) {
                System.out.println("ID: " + currentBook.getId() + "| ISBN: " + currentBook.getIsbn()
                        + "| Title: " + currentBook.getTitle() + "Full Name" + currentBook.getCheckedOutTo());
                checkedOutBooks = true;
            }
        }
        if (!checkedOutBooks) {
            System.out.println("There is no books currently checked out");
        }
        System.out.println("Enter 'C' to check in a book or 'X' to return to the Home Screen ");
        String character = scanner.nextLine();

        if (character.equalsIgnoreCase("X")) {
            System.out.println("Back to Home Screen");
            return;
        } else if (character.equalsIgnoreCase("C")) {
            System.out.println("Check in a book");

        }

    }

    public static void storeHomeScreen(Book[] inventory, Scanner scanner) {
        while (true) {
            System.out.println("Library Home Screen");
            System.out.println("1) Show Available Books");
            System.out.println("2) Show Checked Out Books");
            System.out.println("3) Exit");
            System.out.println("Please choose between (1-3)");

            int userChoose = scanner.nextInt();

            switch (userChoose) {
                case 1:
                    displayAvailableBooks(inventory, scanner);
                    break;
                case 2:
                    displayCheckedOut(inventory, scanner);
                    break;
                case 3:
                    System.out.println("Exit");
                    return;
                default:
                    System.out.println("Error. Please enter  1, 2, or 3. ");
            }
        }
    }

    public static void displayCheckedIn(Book[] inventory, Scanner scanner) {
        System.out.println("What book are you checking in? Enter Book ID or enter 'x' to return to the home screen .");

        String character = scanner.nextLine();

        if (character.equalsIgnoreCase("X")) {
            System.out.println("Back to Home Screen");
            return;
        }

        int bookId = scanner.nextInt();

        boolean isCheckedIn = false;

        for (int i = 0; i < inventory.length; i++) {
            Book currentBook = inventory[i];
            if (currentBook != null && currentBook.getId() == bookId) {
                if(currentBook.isCheckedOut()) {
                    currentBook.checkIn();
                    System.out.println("ID: " + currentBook.getId() + "| ISBN: " + currentBook.getIsbn()
                            + "| Title: " + currentBook.getTitle());
                    System.out.println("Thank You! You are all checked in!");
                    isCheckedIn = true;
                }else {
                    System.out.println("That book is not checked out");
                    isCheckedIn = true;
                }
                return;
            }
        }
    }
}