package org.example;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class TransactionFileManager {
    public  static void appendTransaction(Transaction transaction){

        String filePath = "src/main/resources/transactions.csv";
    File file = new File(filePath);

    try {
        File folder = file.getParentFile();
        if (!folder.exists()) {
            folder.mkdirs();
        }
        boolean fileExists = file.exists();
        boolean isEmpty = !fileExists || file.length() == 0;

        FileWriter writer = new FileWriter(file, true);

        if (isEmpty) {
            writer.write("date|time|description|vendor|amount\n");
        }
        writer.write(transaction.toString() + "\n");

        writer.close();
    }
    catch(IOException ex){
            System.out.println("Something went wrong while saving the transaction.");
            ex.printStackTrace();

        }
    }

    public static List<Transaction> readFile() {
        try {
            FileReader fileReader = new FileReader("src/main/resources/transactions.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            bufferedReader.readLine();

            String input;
            List<Transaction>transactionList = new ArrayList<>();
            while ((input = bufferedReader.readLine()) != null){
                String[] row = input.split("\\|");

                LocalDate parsedDate = LocalDate.parse(row[0]);
                LocalTime parsedTime = LocalTime.parse(row[1]);
                String description = row[2];
                String vendor = row[3];
                double amount = Double.parseDouble(row[4]);

                Transaction transaction = new Transaction(parsedDate, parsedTime, description, vendor, amount);
                transactionList.add(transaction);
            }
            bufferedReader.close();

            return transactionList;

        } catch (IOException ex) {
            System.out.println("Failed to load file");
            ex.printStackTrace();
            return new ArrayList<>();

        }
    }
}
