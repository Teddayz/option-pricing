package model.option;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PutOptionTest {
    @Test
    public void calculatePutOptionPayoffReturnsCorrectOutput() {
        PutOption putOption = new PutOption(50.0);

        double stockPrice = 50.0;
        assertEquals(putOption.calculatePayoff(stockPrice), 0.0);

        stockPrice = 60.0;
        assertEquals(putOption.calculatePayoff(stockPrice), 0.0);

        stockPrice = 40.0;
        assertEquals(putOption.calculatePayoff(stockPrice), 10.0);

    }
}
