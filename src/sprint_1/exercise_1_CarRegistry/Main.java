package sprint_1.exercise_1_CarRegistry;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        ArrayList<CarOwner> carOwners = new ArrayList<>();
        ArrayList<Car> cars = new ArrayList<>();

        carOwners.add(new CarOwner("Sara", "Björkvägen 10", 35));
        carOwners.add(new CarOwner("Lars", "Gropvägen 16", 43));
        carOwners.add(new CarOwner("Emil", "Kalkgatan 29", 22));

        cars.add(new Car("ABC 123", "Saab", "9000" ));
        cars.add(new Car("POP 321", "Volvo", "940" ));
        cars.add(new Car("FUE 489", "BMW", "545i" ));

        cars.get(0).buyCar(carOwners.get(0));
        cars.get(1).buyCar(carOwners.get(1));
        cars.get(0).buyCar(carOwners.get(1));

        cars.get(2).printOwnerOfCar();

        carOwners.get(0).printOwnedCars(cars);
        carOwners.get(1).printOwnedCars(cars);
        carOwners.get(2).printOwnedCars(cars);



    }
}
