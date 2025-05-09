package model.parameters;

import static model.parameters.RiskFreeRate.isValidRiskFreeRate;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class RiskFreeRateTest {

    @Test
    public void isValidRiskFreeRateReturnsTrue() {
        RiskFreeRate riskFreeRate = new RiskFreeRate(0.0);
        assertTrue(isValidRiskFreeRate(riskFreeRate));

        riskFreeRate = new RiskFreeRate(-1.0);
        assertFalse(isValidRiskFreeRate(riskFreeRate));

        riskFreeRate = new RiskFreeRate(0.05);
        RiskFreeRate riskFreeRate2 = new RiskFreeRate(0.20);
        assertEquals(isValidRiskFreeRate(riskFreeRate), isValidRiskFreeRate(riskFreeRate2));
    }
}
