package model.parameters;

public class TimePeriod {

    private int years;
    private int months;

    public TimePeriod(int years, int months) {
        this.years = years;
        this.months = months;
    }

    public static boolean isValidTimePeriod(int testYear, int testMonth) {
        if (testYear < 0) {
            return false;
        } else if (testYear == 0) {
            return testMonth > 0;
        } else {
            return testMonth >= 0;
        }
    }

    public double getTimePeriod() {
        return this.years * 12 + this.months;
    }

    @Override
    public String toString() {
        return "Time period of this option is " + this.years + " years and " +
         this.months + " months";
    }
    
}
