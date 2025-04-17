package org.example;

//FV = P × (1 + (r / 365))^(365 × t)
import org.ietf.jgss.GSSName;
// P = Initial Deposit
// R = annual Interest Rate
// T = Number of Years
// N = Number of Times the interest is compounded per year (daily n = 365)

import java.util.Scanner;

public class FutureValue {
    public static void main(String[] args) {
        System.out.println("Enter Deposit Amount");
        Scanner scanner = new Scanner(System.in);
        double principle = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Enter your Annual Interest Rate");
        double annualInterestRate = scanner.nextDouble();
        double dailyInterestRate = annualInterestRate / 12 / 365;
        scanner.nextLine();
        System.out.println("Enter Loan Years ");
        double years = scanner.nextDouble();
        scanner.nextLine();

        //FV = P × (1 + (r / 365))^(365 × t)
        double rate = annualInterestRate / 100;

        double fv = principle * Math.pow((1 + rate / 365),365 * years);
        double totalInterest = fv - principle;

        System.out.printf("Your future value is: $%.2f%n", fv);
        System.out.printf("Total Interest Earned: $%.2f%n", totalInterest);
    }
}