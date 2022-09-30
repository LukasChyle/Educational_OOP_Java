package Sprint_1.Fordon;

public abstract class Vehicle implements Printable {

    private int speed;
    private final int weight;

    public Vehicle(int speed, int weight) {
        this.speed = speed;
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
