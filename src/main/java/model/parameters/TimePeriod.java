package model.parameters;

public class TimePeriod {

    private final int years;
    private final int months;

    public TimePeriod(int years, int months) {
        this.years = years;
        this.months = months;
    }

    public static boolean isValidTimePeriod(TimePeriod test) {
        if (test.getMonths() > 11 | test.getMonths() < 0) {
            return false;
        } else if (test.getYears() < 0) {
            return false;
        } else {
            return test.getTimePeriod() > 0;
        }
    }

    public int getYears() {
        return this.years;
    }

    public int getMonths() {
        return this.months;
    }

    public double getTimePeriod() {
        return this.years * 12 + this.months;
    }

    @Override
    public String toString() {
        return "Time period of this option is " + this.getTimePeriod() + " number of months";
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TimePeriod)) {
            return false;
        }

        TimePeriod otherTimePeriod = (TimePeriod) other;
        return this.getTimePeriod() == otherTimePeriod.getTimePeriod();
    }

}
