package sprint_3.exchange_money_v2;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ExchangeCalculator_v2 {

    public String getMoneyBack(int remaining, List<JCheckBox> boxList) {

        if (remaining <= 0) {
            return "You don't get any change";
        }

        List<Integer> values = new ArrayList<>();
        for (JCheckBox box : boxList) {
            if (box.isSelected()) {
                values.add(Integer.parseInt(box.getText()));
            }
        }

        if (values.isEmpty()) {
            return "You don't accept any values\n remaining: " + remaining;
        }

        StringBuilder moneyBack = new StringBuilder();
        moneyBack.append("Quantity:\n");
        for (int value : values) {
            if (value <= remaining) {
                moneyBack.append(value);
                if (value >= 20) {
                    moneyBack.append("-bills: ");
                } else {
                    moneyBack.append("-coins: ");
                }
                moneyBack.append(remaining / value).append("\n");

                remaining -= value * (remaining / value);
            }
        }
        moneyBack.append("Remaining: ").append(remaining);
        return moneyBack.toString();
    }
}
