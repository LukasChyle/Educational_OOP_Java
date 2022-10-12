package Sprint_2.Exchange_money;

import java.util.Random;
import java.util.Scanner;

public class Dialog {

    public Dialog(){
        ExchangeCalculator exCalc = new ExchangeCalculator();
        Scanner inputScan = new Scanner(System.in);

        while (true) {
            int cost = whatToPay();

            System.out.println("Du ska betala " + cost + ", hur mycket ger du?\n\"exit\" för att avsluta");

            while (!inputScan.hasNextInt()) {
                if (inputScan.nextLine().equalsIgnoreCase("exit")) {
                    System.exit(0);
                }
                System.out.println("ej rätt värde, skriv in en summa");
            }
            int given = inputScan.nextInt();

            if(given < cost) {
                System.out.println("Du betalade för lite, fy skäms\n");
                continue;
            }

            System.out.println(exCalc.getMoneyBack(given - cost));
        }
    }

    private int whatToPay() {
        Random random = new Random();
        int minReturn = 50;
        int maxReturn = 10000;

        return random.nextInt(maxReturn - minReturn + 1) + minReturn;
    }
}
