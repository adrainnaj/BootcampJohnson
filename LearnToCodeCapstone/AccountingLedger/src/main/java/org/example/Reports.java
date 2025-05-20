package org.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Reports {
    public static void showReports() {
        List<Transaction> transactions = TransactionFileManager.readFile();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nReports Menu:\n");
            System.out.println("A) Search Vendor");
            System.out.println("B) Month to Date");
            System.out.println("C) Back to Ledger Screen");
            System.out.println("D) Back to Home Screen");

            String reportChoice = scanner.nextLine().trim().toUpperCase();

            switch (reportChoice) {
                case "A":
                    searchVendor();
                    break;
                case "B":
                    monthToDate();
                    break;
                case "C":
                    Ledger.showLedger(transactions, scanner);
                    break;
                case "D":
                    return;
            }
        }
    }

    public static void searchVendor() {
        List<Transaction> transactions = TransactionFileManager.readFile();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Vendor to search: ");
        String vendor = scanner.nextLine();

        boolean found = false;
        for (Transaction transaction : transactions) {
            if (transaction.getVendor().toLowerCase().contains(vendor)) {
                String row = transaction.toString();
                System.out.println(row);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No transactions for from this Vendor");
        }
    }

    public static void monthToDate() {
        List<Transaction> transactions = TransactionFileManager.readFile();
        LocalDate today = LocalDate.now();

        System.out.println("Month to Date Transactions");

        System.out.println("Date | Time | Description | Vendor| Amount");
        for (Transaction transaction : transactions) {
            LocalDate date = transaction.getDate();

            if (date.getMonth() == today.getMonth() && date.getYear() == today.getYear()) {
                System.out.println(transaction.toString());
            }
        }
    }
}