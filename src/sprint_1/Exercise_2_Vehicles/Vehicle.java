package sprint_1.Exercise_2_Vehicles;

public abstract class Vehicle implements Printable {

    private int speed;
    private final int weight;

    public Vehicle(int speed, int weight) {
        this.speed = speed;
        if (weight <= 0) {
            throw new IllegalArgumentException(weight + " Invalid number for weight");
        }
        this.weight = weight;
    }

    public int getSpeed() {
        return speed;
    }

    public int getWeight() {
        return weight;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
