package org.example;

public class SalesContract extends Contract {

    private static final double SALES_TAX_RATE = 0.05;
    private static final double RECORDING_FEE = 100.0;
    private static final double PROCESSING_FEE_UNDER_1000 = 295.0;
    private static final double PROCESSING_FEE_OVER_1000 = 495.0;

    private boolean isFinance;
    private double processingFee;

    public SalesContract(String date, String customerName, String customerEmail,
                         Vehicle vehicleSold, boolean isFinance, double processingFee) {
        super(date, customerName, customerEmail, vehicleSold);
        this.isFinance = isFinance;
        this.processingFee = processingFee;
    }

    public boolean getIsFinance() {
        return isFinance;
    }

    public void setFinance(boolean isFinance) {
        this.isFinance = isFinance;
    }

    public double getProcessingFee(double vehiclePrice) {
        if(vehiclePrice < 10000){
            return PROCESSING_FEE_UNDER_1000;
        }
        else{
            return PROCESSING_FEE_OVER_1000;
        }
    }

    public void setProcessingFee(double processingFee) {
        this.processingFee = processingFee;
    }

    @Override
    public double getTotalPrice() {
        double vehiclePrice = getVehicleSold().getPrice(); // manufacturer price
        double salesTax = vehiclePrice * SALES_TAX_RATE; // sales tax amount
        //double recordingFee = RECORDING_FEE; // recording fee
        //double processingFee; //

        return vehiclePrice + salesTax + RECORDING_FEE + getProcessingFee(vehiclePrice);
    }

    @Override
    public double getMonthlyPayment(){
        // not financed => paid in full, no monthly
        if (!isFinance){
            return  0.0;
        }

        // isFinanced - two possibilities



        double totalPrice = getTotalPrice();
        double interest;
        int loanMonths;

        // (1) 4.25% over 48 months IF price >= 10000
        if (totalPrice >= 10000){
            interest = 0.0425;
            loanMonths = 48;
        }
        else {
            // (2) 5.25% over 24 months IF price < 10000
            interest = 0.0525;
            loanMonths = 24;
        }

        // to get the monthly payment, (total price * (1 + interest))/loanMonths
            return (totalPrice * (1 + interest))/loanMonths;

    }
}
