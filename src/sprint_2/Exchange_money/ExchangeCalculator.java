package sprint_2.Exchange_money;

public class ExchangeCalculator {

    public String getMoneyBack(int remaining) {
        if (remaining <= 0) {
            return "Du får ingen växel";
        }

        int[] values = {1000, 500, 200, 100, 50, 20, 10 , 5, 2, 1};
        StringBuilder moneyBack = new StringBuilder();

        for (int value : values) {
            if (value <= remaining) {
                moneyBack.append("Antal ").append(value);
                if (value >= 20) {
                    moneyBack.append("-lappar: ");
                } else {
                    moneyBack.append("-kronor: ");
                }
                moneyBack.append(remaining / value).append("\n");

                remaining -= value * (remaining / value);
            }
        }
        return moneyBack.toString();
    }
}
