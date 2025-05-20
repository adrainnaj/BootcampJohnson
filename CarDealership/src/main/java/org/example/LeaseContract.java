package org.example;

public class LeaseContract extends Contract {

    private static final double INTEREST_RATE = 0.04;
    private static final double LEASE_FEE = 0.07;
    private static final double ENDING_VALUE_RATE = 0.50;
    private static final int LEASE_MONTHS = 36;

    public LeaseContract(String date, String customerName, String customerEmail, Vehicle vehicleSold) {
        super(date, customerName, customerEmail, vehicleSold);
    }


    @Override
    public double getTotalPrice() {
        double price = getVehicleSold().getPrice();
        double leaseFee = price * LEASE_FEE;
        double expectedEndingFee = price * ENDING_VALUE_RATE;

        return price + leaseFee + expectedEndingFee;
    }

    @Override
    public double getMonthlyPayment() {
        double price = getVehicleSold().getPrice();
        double leaseFee = price * LEASE_FEE;
        double interest = price * INTEREST_RATE * (LEASE_FEE/12);
        double totalAmount = price + interest + leaseFee;

        return totalAmount / LEASE_MONTHS;
    }
}