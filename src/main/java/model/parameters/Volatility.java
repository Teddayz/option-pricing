package model.parameters;

public class Volatility {
    private double rate;

    public Volatility(double rate) {
        this.rate = rate;
    }
    /** 
     * Returns the volatility per annum rate.
     * @return double
     */
    public double getRate() {
        return this.rate;
    }

    /**
     * Returns true if a given volatility per annum is a valid volatility per annum.
     *  
     */
    public static boolean isValidVolatility(float test) {
        return test >= 0;
    }

    @Override 
    public String toString() {
        return "Volatility per annum is " + this.rate + "%";
    }
}
