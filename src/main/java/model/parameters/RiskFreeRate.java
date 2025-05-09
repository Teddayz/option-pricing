package model.parameters;

public class RiskFreeRate {
    
    private double rate;

    public RiskFreeRate(double rate) {
        this.rate = rate;
    }

    /**
     * Returns true if a given risk-free-rate is a valid risk-free-rate.
     * 
     */
    public static boolean isValidRiskFreeRate(double test) {
        return test >= 0;
    }

    public double getRate() {
        return this.rate;
    }

    @Override
    public String toString() {
        return "Risk-free interest rate is " + this.rate + "% ";
    }
}