public class Main {
    private int rfr;
    private float vpa;
    private int timePeriod;

    public Main(int rfr, float vpa, int timePeriod) {
        this.rfr = rfr;
        this.vpa = vpa;
        this.timePeriod = timePeriod;
    }

    /**
     * Returns true if a given risk-free-rate is a valid risk-free-rate.
     * 
     */
    public static boolean isValidrfr(int test) {
        return test >= 0;
    }

    /**
     * Returns true if a given volatility per annum is a valid volatility per annum.
     * 
     */
    public static boolean isValidvpa(float test) {
        return test >= 0;
    }

    // TODO: Change this to be a valid time period in the future
    // Convert to date time then equate >= 0?
    public static boolean isValidTimePeriod(int test) {
        return test >=0;
    }

    @Override
    public String toString() {
        return "Parameters: " + this.rfr + "%, " + this.vpa + "σ, " + this.timePeriod + "months";
    }
    public static void main(String[] args) {
        System.out.println("Hello World");
    }
}
