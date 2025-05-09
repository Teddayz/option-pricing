import model.parameters.RiskFreeRate;
import model.parameters.TimePeriod;
import model.parameters.Volatility;

public class BinomialLatticeModel {

    private final RiskFreeRate rfr;
    private final Volatility vpa;
    private final TimePeriod timePeriod;

//    private final float upFactor;
//    private final float downFactor;

    public BinomialLatticeModel(double rfr, double vpa, int years, int months) {
        this.rfr = new RiskFreeRate(rfr);
        this.timePeriod = new TimePeriod(years, months);
        this.vpa = new Volatility(vpa, this.timePeriod);
    }
    

    @Override
    public String toString() {
        return "Parameters: " + this.rfr.toString() + this.vpa.toString() + " " + this.timePeriod.toString();
    }
    public static void main(String[] args) {
        BinomialLatticeModel binomialLatticeModel = new BinomialLatticeModel(5.0, 20.0, 3, 5);
        System.out.println(binomialLatticeModel.toString());
    }
}
