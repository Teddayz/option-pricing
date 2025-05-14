import model.option.CallOption;
import model.option.Option;
import model.option.PutOption;
import model.parameters.CompoundedRFR;

public class TwoStepEuropeanBinomialLatticeModel extends BinomialLatticeModel {
    private final double upPrice;
    private final double upDownPrice;
    private final double downPrice;

    public TwoStepEuropeanBinomialLatticeModel(double initialStockPrice, double rfr,
                                       double vpa, int years, int months, Option option) {
        super(initialStockPrice, rfr, vpa, years, months, option);
        this.upPrice = super.getUpFactor() * super.getUpFactor() * initialStockPrice;
        this.upDownPrice = super.getUpFactor() * super.getDownFactor() * initialStockPrice;
        this.downPrice = super.getDownFactor() * super.getDownFactor() * initialStockPrice;
    }

    public double getOptionPrice() {
        double riskFreeProbability = super.getRiskFreeProbability();
        CompoundedRFR rfr = super.getCompoundedRFR();
        Option option = super.getOption();
        double upPayoff = option.calculatePayoff(upPrice);
        double upDownPayoff = option.calculatePayoff(upDownPrice);
        double downPayoff = option.calculatePayoff(downPrice);
        return (1/Math.pow(Math.exp(rfr.getRate()), 2)) * (Math.pow(riskFreeProbability, 2) * upPayoff +
                2 * riskFreeProbability * (1 - riskFreeProbability) * upDownPayoff +
                Math.pow(1 - riskFreeProbability, 2) * downPayoff);
    }

    public static void main(String[] args) {
        TwoStepEuropeanBinomialLatticeModel twoStepEuropeanBinomialLatticeModel =
                new TwoStepEuropeanBinomialLatticeModel(50, 0.05,
                        0.25, 1, 0, new CallOption(47));
        System.out.println(twoStepEuropeanBinomialLatticeModel.getOptionPrice());
    }

}
