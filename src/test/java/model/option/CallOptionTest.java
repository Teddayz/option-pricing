package model.option;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CallOptionTest {
    @Test
    public void calculateCallOptionPayoffReturnsCorrectOutput() {
        CallOption callOption = new CallOption(50.0);

        double stockPrice = 50.0;
        assertEquals(callOption.calculatePayoff(stockPrice), 0.0);

        stockPrice = 60.0;
        assertEquals(callOption.calculatePayoff(stockPrice), 10.0);

        stockPrice = 40.0;
        assertEquals(callOption.calculatePayoff(stockPrice), 0.0);

    }
}
