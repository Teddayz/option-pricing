package model.parameters;

public class CompoundedRFR {
    private double rate;

    public CompoundedRFR(double rate) {
        this.rate = Math.exp(rate);
    }

    /**
     * Returns true if a given risk-free-rate is a valid risk-free-rate.
     *
     */
    public static boolean isValidRiskFreeRate(CompoundedRFR test) {
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

        CompoundedRFR that = (CompoundedRFR) other;
        return Double.compare(this.rate, that.rate) == 0;
    }

    @Override
    public String toString() {
        return "Risk-free interest rate is " + this.rate + "% ";
    }
}
