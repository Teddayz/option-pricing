package model.parameters;

import static model.parameters.TimePeriod.isValidTimePeriod;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TimePeriodTest {

    @Test
    public void getTimePeriodReturnsCorrectOutput() {
        TimePeriod timePeriod = new TimePeriod(9,10);
        assertEquals(timePeriod.getTimePeriod(), 9 * 12 + 10);
    }

    @Test
    public void invalidYearReturnsFalse() {
        TimePeriod timePeriod = new TimePeriod(-1, 1);
        assertFalse(isValidTimePeriod(timePeriod));
    }

    @Test
    public void invalidMonthReturnsFalse() {
        TimePeriod timePeriod = new TimePeriod(0,0);
        assertFalse(isValidTimePeriod(timePeriod));

        timePeriod = new TimePeriod(0, -1);
        assertFalse(isValidTimePeriod(timePeriod));

        timePeriod = new TimePeriod(0, 12);
        assertFalse(isValidTimePeriod(timePeriod));

    }

    @Test
    public void validTimePeriodReturnsTrue() {
        TimePeriod timePeriod = new TimePeriod(0, 11);
        assertTrue(isValidTimePeriod(timePeriod));

        timePeriod = new TimePeriod(12, 0);
        assertTrue(isValidTimePeriod(timePeriod));
    }

    @Test
    public void notEqualTimePeriodsReturnsFalse() {
        TimePeriod timePeriod = new TimePeriod(12, 0);
        TimePeriod timePeriod2 = new TimePeriod(12, 1);
        assertFalse(timePeriod.equals(timePeriod2));
    }

    @Test
    public void equalTimePeriodsReturnsTrue() {
        TimePeriod timePeriod = new TimePeriod(0, 12);
        TimePeriod timePeriod2 = new TimePeriod(1,0);
        assertTrue(timePeriod.equals(timePeriod2));
    }
}
