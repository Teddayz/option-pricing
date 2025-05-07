
public class Main {
    private RiskFreeRate rfr;
    private Volatility vpa;
    private int timePeriod;

    public Main(double rfr, double vpa, int timePeriod) {
        this.rfr = new RiskFreeRate(rfr);
        this.vpa = new Volatility(vpa);
        this.timePeriod = timePeriod;
    }

    

    // TODO: Change this to be a valid time period in the future
    // Convert to date time then equate >= 0?
    public static boolean isValidTimePeriod(int test) {
        return test >=0;
    }

    @Override
    public String toString() {
        return "Parameters: " + this.rfr.toString() + this.vpa.toString() + " " + this.timePeriod + " months";
    }
    public static void main(String[] args) {
        Main main = new Main(5.0, 20.0, 3);
        System.out.println(main.toString());
    }
}
