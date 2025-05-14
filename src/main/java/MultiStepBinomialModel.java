
import java.util.Arrays;
import model.option.Option;
import model.option.CallOption;
import model.option.PutOption;
import model.parameters.CompoundedRFR;
import model.parameters.RiskFreeRate;
import model.parameters.TimePeriod;
import model.parameters.Volatility;

import static model.parameters.RiskFreeRate.isValidRiskFreeRate;

public class MultiStepBinomialModel {
    private final double initialStockPrice;
    private final CompoundedRFR rfr;
    private final Volatility vpa;
    private final TimePeriod timePeriod;
    private final int steps;
    private final Option option;
    private final double upFactor;
    private final double downFactor;

    public MultiStepBinomialModel(double initialStockPrice,
                                  double rfr,
                                  double vpa,
                                  int years,
                                  int months,
                                  int steps,
                                  Option option) {
        this.initialStockPrice = initialStockPrice;
        RiskFreeRate riskFreeRate = new RiskFreeRate(rfr);
        if (isValidRiskFreeRate(riskFreeRate)) {
            this.timePeriod = new TimePeriod(years, months);
            double dt = (this.timePeriod.getTimePeriod() / 12.0) / steps;
            this.rfr = new CompoundedRFR(riskFreeRate);
            this.vpa = new Volatility(vpa, this.timePeriod);
            this.steps = steps;
            this.option = option;

            double sigmaMonth = this.vpa.getVolatility();

            double sigmaStep = sigmaMonth / Math.sqrt(steps);
            this.upFactor   = Math.exp(sigmaStep);
            this.downFactor = Math.exp(-sigmaStep);
        } else {
            throw new IllegalArgumentException(model.parameters.RiskFreeRate.invalidMessage);
        }
    }

    public double getOptionPrice() {
        double[][] payoff = buildPayoffTree();
        return payoff[0][0];
    }

    public double[][] buildPayoffTree() {
        int N = steps;
        double R = rfr.getRate();
        double q = (R - downFactor) / (upFactor - downFactor);

        // stock price tree
        double[][] stock = new double[N+1][N+1];
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= i; j++) {
                stock[i][j] = initialStockPrice
                        * Math.pow(upFactor, j)
                        * Math.pow(downFactor, i - j);
            }
        }

        // option price tree
        double[][] payoff = new double[N+1][N+1];
        for (int j = 0; j <= N; j++) {
            payoff[N][j] = option.calculatePayoff(stock[N][j]);
        }

        for (int i = N - 1; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                double cont    = (q * payoff[i+1][j+1] + (1 - q) * payoff[i+1][j]) / R;
                double exercise = option.calculatePayoff(stock[i][j]);
                payoff[i][j]   = Math.max(cont, exercise);
            }
        }
        return payoff;
    }

    private double[] round(double[] arr) {
        double[] r = new double[arr.length];
        for (int i = 0; i < arr.length; i++) {
            r[i] = Math.round(arr[i] * 10000.0) / 10000.0;
        }
        return r;
    }

    public void displayTree() {
        double[][] payoff = buildPayoffTree();
        int N = steps;

        double[][] stock = new double[N+1][N+1];
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= i; j++) {
                stock[i][j] = initialStockPrice
                        * Math.pow(upFactor, j)
                        * Math.pow(downFactor, i - j);
            }
        }

        System.out.println("\nStock Price Tree:");
        for (int i = 0; i <= N; i++) {
            System.out.println(Arrays.toString(round(stock[i])));
        }
        System.out.println("\nOption Value Tree:");
        for (int i = 0; i <= N; i++) {
            System.out.println(Arrays.toString(round(payoff[i])));
        }
    }

    @Override
    public String toString() {
        return "Parameters: " + rfr.toString()
                + vpa.toString() + " " + timePeriod.toString()
                + " steps=" + steps;
    }

    public static void main(String[] args) {
        int steps = 5;

        MultiStepBinomialModel callModel = new MultiStepBinomialModel(
                50,
                0.05,
                0.25,
                1,
                0,
                steps,
                new CallOption(47)
        );

        System.out.println("Call price: " + callModel.getOptionPrice());
        callModel.displayTree();

        MultiStepBinomialModel putModel = new MultiStepBinomialModel(
                50,
                0.05,
                0.25,
                1,
                0,
                steps,
                new PutOption(115.0)
        );
        System.out.println("\nPut price:  " + putModel.getOptionPrice());
        putModel.displayTree();
    }
}
