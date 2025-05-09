package model.option;

public abstract class Option {
    
    private double strikePrice;

    public Option(double strikePrice) {
        this.strikePrice = strikePrice;
    }

    protected double getStrikePrice() {
        return this.strikePrice;
    }

    public abstract double calculatePayoff(double stockPrice);
}
