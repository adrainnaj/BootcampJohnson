package org.example;

import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Give me two number " );
        System.out.println("Enter the first number: " );

        Scanner scanner = new Scanner(System.in);
        int userInput = scanner.nextInt();

        System.out.println("Enter the second number: " );
        int userSecondInput = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Please select an operation +,-,*,/");
        String operation = scanner.nextLine();

        int sum = userInput + userSecondInput;
        int difference = userInput - userSecondInput;
        int product = userInput * userSecondInput;
        int quotient = userInput / userSecondInput;


        switch (operation){
            case "+":
                System.out.println(sum);
                break;
            case "-":
                System.out.println(difference);
                break;
            case "*":
                System.out.println(product);
                break;
            case "/":
                System.out.println(quotient);
                break;
            default:
                System.out.println("Choose a Symbol");
        }
    }
}