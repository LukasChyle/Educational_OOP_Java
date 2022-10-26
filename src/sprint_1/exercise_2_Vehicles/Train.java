package sprint_1.exercise_2_Vehicles;

public class Train extends Vehicle implements Wheeled {

    private int wagons;
    private final int wheels;

    public Train(int speed, int weight, int wagons) {
        super(speed, weight);
        if (wagons < 0) {
            throw new IllegalArgumentException(wagons + " Invalid number for wagons");
        }
        this.wagons = wagons;
        this.wheels = 6;
    }

    private void connectWagon() {
        wagons++;
    }

    public void printMe() {
        System.out.println("Train: speed " + getSpeed() + ", weight " + getWeight() + ", wagons " + wagons);
    }

    @Override
    public String getNumberOfWheels() {
        return "Train got " + (wheels + (wagons * 4)) + " wheels";
    }
}
