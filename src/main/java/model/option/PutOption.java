package model.option;

public class PutOption extends Option {
    
    public PutOption(double strikePrice) {
        super(strikePrice);
    }

    @Override
    public double calculatePayoff(double stockPrice) {
        return Math.max(0, getStrikePrice() - stockPrice);
    }
}
