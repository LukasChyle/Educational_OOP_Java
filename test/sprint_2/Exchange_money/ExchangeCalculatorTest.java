package sprint_2.Exchange_money;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class ExchangeCalculatorTest {

    ExchangeCalculator exCalc = new ExchangeCalculator();

    @Test
    void printMoneyBack(){
        assertEquals("""
                Antal 1000-lappar: 5
                Antal 200-lappar: 2
                Antal 10-kronor: 1
                Antal 2-kronor: 2
                """, exCalc.getMoneyBack(5414));

        assertEquals("""
                Antal 1000-lappar: 1
                Antal 500-lappar: 1
                Antal 100-lappar: 1
                Antal 20-lappar: 2
                Antal 5-kronor: 1
                Antal 1-kronor: 1
                """, exCalc.getMoneyBack(1646));

        assertNotEquals("""
                Antal 1000-lappar: 1
                Antal 500-lappar: 1
                Antal 100-lappar: 1
                Antal 5-kronor: 1
                Antal 1-kronor: 1
                """, exCalc.getMoneyBack(1646));

        assertEquals("Du får ingen växel", exCalc.getMoneyBack(0));
        assertEquals("Du får ingen växel", exCalc.getMoneyBack(-50));
        assertNotEquals("Du får ingen växel", exCalc.getMoneyBack(50));
    }





}