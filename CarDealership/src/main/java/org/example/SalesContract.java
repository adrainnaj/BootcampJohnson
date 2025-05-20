package org.example;

public class SalesContract extends Contract {

    private static final double SALES_TAX_RATE = 0.05;
    private static final double RECORDING_FEE = 100.0;
    private static final double PROCESSING_FEE_UNDER_1000 = 295.0;
    private static final double PROCESSING_FEE_OVER_1000 = 495.0;

    private boolean isFinance;

    public SalesContract(String date, String customerName, String customerEmail,
                         Vehicle vehicleSold, boolean toFinance) {
        super(date, customerName, customerEmail, vehicleSold);
        this.isFinance = toFinance;
    }

    public boolean isFinance() {
        return isFinance;
    }

    public void setFinance(boolean toFinance) {
        this.isFinance = toFinance;
    }

    @Override
    public double getTotalPrice() {

        double vehiclePrice = getVehicleSold().getPrice();
        double salesTax = vehiclePrice * SALES_TAX_RATE;
        double recordingFee = RECORDING_FEE;
        double processingFee;

        if(vehiclePrice < 10000){
            processingFee = PROCESSING_FEE_UNDER_1000;
        }
        else{
            processingFee = PROCESSING_FEE_OVER_1000;
        }
        return vehiclePrice + salesTax + RECORDING_FEE + processingFee;
    }
    @Override
    public double getMonthlyPayment(){
        if (!isFinance){
            return  0.0;
        }
        double totalPrice = getTotalPrice();
        double interest;
        int loanMonths;

        if (totalPrice >= 10000){
            interest = 0.0425;
            loanMonths = 48;
        }
        else {
            interest = 0.0525;
            loanMonths = 24;
        }
        double totalInterest = totalPrice * interest *((double)loanMonths / 12);
        double totalAmount = totalPrice + totalInterest;

        return totalAmount/loanMonths;
    }
}
