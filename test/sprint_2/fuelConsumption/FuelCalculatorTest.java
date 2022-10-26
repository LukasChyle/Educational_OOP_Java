package sprint_2.fuelConsumption;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FuelCalculatorTest {

    FuelCalculator fuelCalculator = new FuelCalculator();

    @Test
    void averageConsumption() {
        assertEquals(0.5, fuelCalculator.averageConsumption(10, 20));
        assertEquals(1.5, fuelCalculator.averageConsumption(30, 20));
        assertNotEquals(1.4, fuelCalculator.averageConsumption(30, 20));
    }

    @Test
    void mileageOfTheYear() {
        assertEquals(1500, fuelCalculator.mileageOfTheYear(1500, 3000));
        assertEquals(400, fuelCalculator.mileageOfTheYear(100, 500));
        assertNotEquals(300, fuelCalculator.mileageOfTheYear(100, 500));
    }

    @Test
    void StringToDouble() {
        assertEquals(25, fuelCalculator.StringToDouble("25.0"));
        assertEquals(45, fuelCalculator.StringToDouble("45  "));
        assertNotEquals(40, fuelCalculator.StringToDouble("45"));
        assertThrows(NumberFormatException.class,
                () -> {
                    fuelCalculator.StringToDouble("asfg");
                });
    }
}