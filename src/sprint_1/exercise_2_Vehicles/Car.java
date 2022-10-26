package sprint_1.exercise_2_Vehicles;

public class Car extends Vehicle implements Wheeled {

    private final int numberOfGears;
    private int currentGear;
    private final int wheels = 4;

    public Car(int speed, int weight, int numberOfGears, int currentGear) {
        super(speed, weight);
        if (numberOfGears <= 0) {
            throw new IllegalArgumentException(numberOfGears + " Invalid number for numberOfGears");
        } else if (currentGear < 0 || currentGear > numberOfGears) {
            throw new IllegalArgumentException(currentGear + " Invalid number for currentGear");
        }
        this.numberOfGears = numberOfGears;
        this.currentGear = currentGear;
    }

    public void setCurrentGear(int currentGear) {
        this.currentGear = currentGear;
    }

    public void printMe() {
        System.out.println("Car: speed " + getSpeed() + ", weight " + getWeight() + ", number of gears " + numberOfGears + ", current gear " + currentGear);
    }

    @Override
    public String getNumberOfWheels() {
        return "Car got " + wheels + " wheels";
    }
}
