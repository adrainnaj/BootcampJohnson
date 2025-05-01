package org.example;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

import static org.example.Ledger.showAll;
import static org.example.Ledger.showLedger;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Transaction> transactions = TransactionFileManager.readFile();
        Reports report = new Reports();

        while (true) {
            System.out.println("\nHome Screen\n");
            System.out.println("D) Add Deposit");
            System.out.println("P) Make a Payment");
            System.out.println("L) Ledger");
            System.out.println("X) Exit");
            System.out.println("Please select one of the following options 'D','P','L', or 'X'");

            String choice = scanner.nextLine().trim().toUpperCase();

            switch (choice) {
                case "D":
                    addDeposit(transactions, scanner);
                    break;
                case "P":
                    makePayment(transactions, scanner);
                    break;
                case "L":
                    Ledger.showLedger(transactions, scanner);
                    break;
                case "X":
                    return;
            }
        }
    }

    private static void addDeposit(List<Transaction> transactions, Scanner scanner) {
        System.out.println("Add Deposit");

        System.out.println("Enter description");
        String description = scanner.nextLine();

        System.out.println("Enter Vendor");
        String vendor = scanner.nextLine();

        System.out.println("Enter Amount: ");
        double amount = Double.parseDouble(scanner.nextLine());

        Transaction deposit = new Transaction(LocalDate.now(), LocalTime.now(), description, vendor, amount);
        TransactionFileManager.appendTransaction(deposit);
        transactions.add(deposit);
        System.out.println("Deposit added!");
    }

    private static void makePayment(List<Transaction> transactions, Scanner scanner) {
        System.out.println("What amount are paying today?");
        double negativeAmount = Double.parseDouble(scanner.nextLine());

        System.out.println("Payment Description");
        String description = scanner.nextLine();

        System.out.println("Vendor");
        String vendor = scanner.nextLine();

        try {
            negativeAmount = negativeAmount * -1;

            Transaction payment = new Transaction(LocalDate.now(), LocalTime.now(), description, vendor, negativeAmount);
            TransactionFileManager.appendTransaction(payment);
            System.out.println("Payment of $" + negativeAmount + " successful.");

        } catch (NumberFormatException ex) {
            System.out.println("Input invalid");
        }
    }
}