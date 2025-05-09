import model.RiskFreeRate;
import model.Volatility;
import model.TimePeriod;

public class Main {

    private RiskFreeRate rfr;
    private Volatility vpa;
    private TimePeriod timePeriod;

    public Main(double rfr, double vpa, int years, int months) {
        this.rfr = new RiskFreeRate(rfr);
        this.vpa = new Volatility(vpa);
        this.timePeriod = new TimePeriod(years, months);
    }
    

    @Override
    public String toString() {
        return "Parameters: " + this.rfr.toString() + this.vpa.toString() + " " + this.timePeriod.toString();
    }
    public static void main(String[] args) {
        Main main = new Main(5.0, 20.0, 3, 5);
        System.out.println(main.toString());
    }
}
