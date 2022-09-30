package Sprint_1.Fordon;

public class Train extends Vehicle implements Wheeled {

    private int wagons;
    private final int wheels = 6;

    public Train(int speed, int weight, int wagons) {
        super(speed, weight);
        this.wagons = wagons;
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
