package model.option;

public class CallOption extends Option {

    public CallOption(double strikePrice) {
        super(strikePrice);
    }

    @Override
    public double calculatePayoff(double stockPrice) {
        return Math.max(0, stockPrice - getStrikePrice());
    }
}
