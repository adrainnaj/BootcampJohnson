package org.example;

import java.io.File;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class FileManager {

    public static void save(Order currentOrder) {

        //generate the file name 20250529-160611.txt
        //supposed to be in a receipts
        String dir = "receipts";
        File filePath = new File(dir, createFileName());

        try (FileWriter fw = new FileWriter(filePath)) {
            fw.write("RECEIPT\n");
            fw.write("\n--------\n");


            for (OrderItem item : currentOrder.getItems())
                fw.write(item.toString()+ "\n");
            //what is written to the file
            fw.write("--------\n");
            fw.write("Total: $" + String.format("%.2f", currentOrder.getOrderTotal()));

        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    private static String createFileName() {

        LocalDateTime currentDate = LocalDateTime.now();
        StringBuilder fileName = new StringBuilder();

        System.out.println(currentDate.toLocalDate() + " " + currentDate.toLocalTime());

        for (String dateString : currentDate.toLocalDate().toString().split("-")) {
            fileName.append(dateString);
        }

        fileName.append("-");

        for (String timeString : currentDate.toLocalTime().truncatedTo(ChronoUnit.SECONDS).toString().split(":")) {
            fileName.append(timeString);
        }
        fileName.append(".txt");

        return fileName.toString();
    }
}
