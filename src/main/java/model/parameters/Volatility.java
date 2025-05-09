package model.parameters;

public class Volatility {
    private double rate;
    private TimePeriod timePeriod;

    public Volatility(double rate, TimePeriod timePeriod) {
        this.rate = rate;
        this.timePeriod = timePeriod;
    }
    /** 
     * Returns the volatility for up and down factor.
     * @return double
     */
    public double getVolatility() {
        return this.rate * Math.sqrt(this.timePeriod.getTimePeriod() /12);
    }

    /**
     * Returns true if a given volatility per annum is a valid volatility per annum.
     *  
     */
    public static boolean isValidVolatility(Volatility test) {
        return test.getVolatility() >= 0;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof Volatility)) {
            return false;
        }

        Volatility that = (Volatility) other;
        return Double.compare(this.rate, that.rate) == 0;
    }

    @Override 
    public String toString() {
        return "Volatility per annum is " + this.rate + "%";
    }
}
