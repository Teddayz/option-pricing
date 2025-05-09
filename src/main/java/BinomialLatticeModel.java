import model.option.CallOption;
import model.option.Option;
import model.option.PutOption;
import model.parameters.CompoundedRFR;
import model.parameters.RiskFreeRate;
import model.parameters.TimePeriod;
import model.parameters.Volatility;

public class BinomialLatticeModel {

    private final double initialStockPrice;
    private final CompoundedRFR rfr;
    private final Volatility vpa;
    private final TimePeriod timePeriod;
    private final Option option;
    private double upFactor;
    private double downFactor;

    public BinomialLatticeModel(double initialStockPrice, double rfr,
                                double vpa, int years, int months, Option option) {
        this.initialStockPrice = initialStockPrice;
        this.rfr = new CompoundedRFR(rfr);
        this.timePeriod = new TimePeriod(years, months);
        this.vpa = new Volatility(vpa, this.timePeriod);
        this.option = option;
        this.upFactor = Math.exp(this.vpa.getVolatility());
        this.downFactor = Math.exp(-1 * this.vpa.getVolatility());
    }

    public double getOptionPrice() {
        double riskFreeProbability = (rfr.getRate() - downFactor) / (upFactor - downFactor);
        double upStockPrice = this.initialStockPrice * upFactor;
        double upPayoff = option.calculatePayoff(upStockPrice);
        double downStockPrice = this.initialStockPrice * downFactor;
        double downPayoff = option.calculatePayoff(downStockPrice);
        return (1 / rfr.getRate()) * ((riskFreeProbability * upPayoff) + ((1 - riskFreeProbability) * downPayoff));
    }

    @Override
    public String toString() {
        return "Parameters: " + this.rfr.toString() + this.vpa.toString() + " " + this.timePeriod.toString();
    }
    public static void main(String[] args) {
        BinomialLatticeModel binomialLatticeModel = new BinomialLatticeModel(50, 0.05,
                0.25, 1, 0, new PutOption(47));
        System.out.println(binomialLatticeModel.getOptionPrice());
    }
}
