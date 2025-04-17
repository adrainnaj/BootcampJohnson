package org.example;

import java.util.Scanner;
//M = P[r(1+r)^n] / [(1+r)^n-1]
//r = interest rate
//p = principle
//n = loan length
public class Main {
    public static void main(String[] args) {
        System.out.println("Enter your Principle");
        Scanner scanner = new Scanner(System.in);
        double principle = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Enter your Interest Rate");
        double interestRate = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Enter your Loan Years");
        double loanLength = scanner.nextDouble();
        double month = loanLength * 12;
        scanner.nextLine();
        // Convert annual interest rate to monthly and make it a decimal
        double monthlyInterest = (interestRate / 12) / 100;

        // 1+r
        double v = 1 + monthlyInterest;
        double n = Math.pow(v, month);
        double left = principle * (monthlyInterest * n);
        double right = n - 1;
        double monthlyPayment = left / right;

        double totalInterest = (monthlyPayment * month) - principle;
        System.out.printf("Your monthly payment is: $%.2f%n", monthlyPayment);
        System.out.printf("Total interest over the life of the loan: $%.2f%n", totalInterest);
    }
}