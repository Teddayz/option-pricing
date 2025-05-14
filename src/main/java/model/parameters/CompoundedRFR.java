package model.parameters;

public class CompoundedRFR {
    private double rate;

    public CompoundedRFR(RiskFreeRate rate) {
        this.rate = Math.exp(rate.getRate());
    }

    public double getRate() {
        return this.rate;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof CompoundedRFR)) {
            return false;
        }

        CompoundedRFR that = (CompoundedRFR) other;
        return Double.compare(this.rate, that.rate) == 0;
    }

    @Override
    public String toString() {
        return "Compounded risk-free interest rate is " + this.rate + "% ";
    }
}
