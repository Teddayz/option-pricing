import model.option.CallOption;
import model.option.Option;
import model.option.PutOption;
import model.parameters.CompoundedRFR;
import model.parameters.RiskFreeRate;
import model.parameters.TimePeriod;
import model.parameters.Volatility;

import static model.parameters.RiskFreeRate.isValidRiskFreeRate;

public class BinomialLatticeModel {

    private final double initialStockPrice;
    private final RiskFreeRate RiskFreeRate;
    private final CompoundedRFR rfr;
    private final Volatility vpa;
    private final TimePeriod timePeriod;
    private final Option option;
    private final double upFactor;
    private final double downFactor;

    public BinomialLatticeModel(double initialStockPrice, double rfr,
                                double vpa, int years, int months, Option option) {
        this.initialStockPrice = initialStockPrice;
        this.RiskFreeRate = new RiskFreeRate(rfr);
        if (isValidRiskFreeRate(RiskFreeRate)) {
            this.rfr = new CompoundedRFR(RiskFreeRate);
            this.timePeriod = new TimePeriod(years, months);
            this.vpa = new Volatility(vpa, this.timePeriod);
            this.option = option;
            this.upFactor = Math.exp(this.vpa.getVolatility());
            this.downFactor = Math.exp(-1 * this.vpa.getVolatility());
        } else {
            throw new IllegalArgumentException(model.parameters.RiskFreeRate.invalidMessage);
        }
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
        BinomialLatticeModel binomialLatticeModel1 = new BinomialLatticeModel(50, 0.05,
                0.20,1,0,new CallOption(53));
        System.out.println(binomialLatticeModel1.getOptionPrice());
    }
}
