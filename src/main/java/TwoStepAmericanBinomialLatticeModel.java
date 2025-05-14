import model.option.Option;

public class TwoStepAmericanBinomialLatticeModel extends BinomialLatticeModel {

    private double twoUpPrice;
    private double upDownPrice;
    private double twoDownPrice;
    private double upPrice;
    private double downPrice;

    public TwoStepAmericanBinomialLatticeModel(double initialStockPrice, double rfr,
                                               double vpa, int years, int months, Option option) {
        super(initialStockPrice, rfr, vpa, years, months, option);
        this.twoUpPrice = Math.pow(super.getUpFactor(), 2) * initialStockPrice;
        this.upDownPrice = getUpFactor() * getDownFactor() * initialStockPrice;
        this.twoDownPrice = Math.pow(super.getDownFactor(), 2) * initialStockPrice;
    }





}
