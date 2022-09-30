package Sprint_1.Exercise_1_CarRegistry;

import java.util.ArrayList;

public class CarOwner extends Person {

    public CarOwner(String name, String address, int age) {
        super(name, address, age);
    }

    public void printOwnedCars(ArrayList<Car> cars) {
        String carsOwned = "";
        boolean hasCar = false;

        for (Car car : cars) {
            try {
                if (car.getOwner().equals(this)) {
                    carsOwned += "\n" + car;
                    hasCar = true;
                }
            } catch (NullPointerException e) {
                // The car had no owner.
            }
        }
        if (hasCar) {
            System.out.println("\n" + this.getName() + " at " + this.getAddress() + ", owns these cars:" + carsOwned);
        } else {
            System.out.println("\n" + this.getName() + " at " + this.getAddress() + ", does not own any car");
        }
    }
}
