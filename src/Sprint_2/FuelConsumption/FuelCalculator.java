package Sprint_2.FuelConsumption;

import javax.swing.*;
import java.util.Scanner;

public class FuelCalculator {
    public static void main(String[] args) {
        FuelCalculator fuelCalculator = new FuelCalculator();
        fuelCalculator.dialog();
    }


    private void dialog() {
        Scanner inputScan = new Scanner(System.in);
        System.out.println("What mileage did you have last year?");
        String old = inputScan.nextLine();
        System.out.println("What is your current mileage?");
        String current = inputScan.nextLine();
        System.out.println("How much gas did you consume between this and last mileage?");
        String gas = inputScan.nextLine();

        System.out.println("Average gas consumption: " +
                averageConsumption(StringToDouble(gas), mileageOfTheYear(StringToDouble(old), StringToDouble(current))) +
                "\nMiles done since last mileage: " + mileageOfTheYear(StringToDouble(old), StringToDouble(current)));
    }


    public double averageConsumption(double liters, double miles) {
        return liters / miles;
    }

    public double mileageOfTheYear(double oldMileage, double currentMileage) {
        return currentMileage - oldMileage;
    }

    public double StringToDouble(String s) {
        return Double.parseDouble(s);
    }
}
