package org.example;

import java.util.List;
import java.util.Scanner;

public class Ledger {

   public static void showLedger(List<Transaction> transactions, Scanner scanner) {
        boolean inLedger = true;

        while (inLedger) {
            System.out.println("Ledger Screen");
            System.out.println("A) Show All Entries");
            System.out.println("B) Show Deposits");
            System.out.println("C) Show Payments");
            System.out.println("D) Show Reports");
            System.out.println("E) Return back to the Home Screen");
            System.out.println("Please choice an option ");

            String choice = scanner.nextLine().toUpperCase();

            switch (choice) {
                case "A":
                    showAll();
                    break;
                case "B":
                    showDeposits();
                    break;
                case "C":
                    showPayments();
                    break;
                case "D":
                    Reports.showReports();
                    break;
                case "E":
                    return;
            }
        }
    }
    public static void showAll() {
            System.out.println("\n Show all transaction ");
            List<Transaction>transactions = TransactionFileManager.readFile();
            for (Transaction transaction : transactions) {
                System.out.println(transaction.toString());
            }
        }

    public static void showDeposits(){
        System.out.println("Show Deposits");
        List<Transaction>transactions = TransactionFileManager.readFile();
        for(Transaction transaction : transactions){
            if(transaction.getAmount()>0){
                System.out.println(transaction.toString());
            }
        }
    }

    public static void showPayments(){
        System.out.println("Show Payments");
        List<Transaction>transactions = TransactionFileManager.readFile();

        for(Transaction transaction : transactions){
            if(transaction.getAmount()<0){
                System.out.println(transaction.toString());
            }
        }
    }

    public static void showReports(){
        System.out.println("Show Reports");
        List<Transaction>transactions = TransactionFileManager.readFile();

        for(Transaction transaction : transactions){
            if(transaction.getReports) {
                System.out.println(transaction);
            }
        }
    }
}