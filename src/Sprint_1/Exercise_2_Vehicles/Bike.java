package Sprint_1.Exercise_2_Vehicles;

public class Bike extends Vehicle implements Wheeled {

    private final int numberOfGears;
    private int currentGear;
    private final int wheels = 2;

    public Bike(int speed, int weight, int numberOfGears, int currentGear) {
        super(speed, weight);
        this.numberOfGears = numberOfGears;
        this.currentGear = currentGear;
    }

    public void setCurrentGear(int currentGear) {
        this.currentGear = currentGear;
    }

    public void printMe() {
        System.out.println("Bike: speed " + getSpeed() + ", weight " + getWeight() + ", number of gears " + numberOfGears + ", current gear " + currentGear);
    }

    @Override
    public String getNumberOfWheels() {
        return "Bike got " + wheels + " wheels";
    }
}
