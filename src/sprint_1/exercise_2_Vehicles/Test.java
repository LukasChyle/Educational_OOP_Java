package sprint_1.exercise_2_Vehicles;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public Test() {

        List<Vehicle> vehicles = new ArrayList<>();

        vehicles.add(new Bike(15, 20, 24, 6));
        vehicles.add(new Boat(45, 1100, 300));
        vehicles.add(new Train(90, 7400, 4));
        vehicles.add(new Car(120, 1560, 6, 5));

        List<Wheeled> vehiclesWithWheels = getWheeledList(vehicles);
        printWheeled(vehiclesWithWheels);

        List<Printable> printList = new ArrayList<>(vehicles); //Printable is implemented in the abstract class Vehicle.
        printVehicles(printList);
    }

    // Wheeled is not implemented in the abstract class Vehicle but only in subclasses that have wheels.
    private List<Wheeled> getWheeledList(List<Vehicle> vehicles) {
        List<Wheeled> wheeledList = new ArrayList<>();
        for (Vehicle object : vehicles) {
            if (object instanceof Wheeled) {
                wheeledList.add((Wheeled) object);
            }
        }
        return wheeledList;
    }

    private void printVehicles(List<Printable> list) {
        for (Printable Object : list) {
            Object.printMe();
        }
    }

    private void printWheeled(List<Wheeled> list) {
        for (Wheeled Object : list) {
            System.out.println(Object.getNumberOfWheels());
        }
    }
}