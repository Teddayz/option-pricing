package model.parameters;

public class RiskFreeRate {
    
    private double rate;
    public static String invalidMessage = "Risk Free Rate must be between 0 and 1";

    public RiskFreeRate(double rate) {
        this.rate = rate;
    }

    /**
     * Returns true if a given risk-free-rate is a valid risk-free-rate.
     * 
     */
    public static boolean isValidRiskFreeRate(RiskFreeRate test) {
        return test.getRate() >= 0 & test.getRate() <= 1.0;
    }

    public double getRate() {
        return this.rate;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof RiskFreeRate)) {
            return false;
        }

        RiskFreeRate that = (RiskFreeRate) other;
        return Double.compare(this.rate, that.rate) == 0;
    }

    @Override
    public String toString() {
        return "Risk-free interest rate is " + this.rate + "% ";
    }
}