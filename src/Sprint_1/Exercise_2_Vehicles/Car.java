package Sprint_1.Exercise_2_Vehicles;

public class Car extends Vehicle implements Wheeled {

    private final int numberOfGears;
    private int currentGear;
    private final int wheels = 4;

    public Car(int speed, int weight, int numberOfGears, int currentGear) {
        super(speed, weight);
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
