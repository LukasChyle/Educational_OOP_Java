package Sprint_1.Exercise_2_Vehicles;

public class Boat extends Vehicle {

    private final int deadWeight;

    public Boat(int speed, int weight, int deadWeight) {
        super(speed, weight);
        if (deadWeight < 0) {
            throw new IllegalArgumentException(deadWeight + " Invalid number for deadWeight");
        }
        this.deadWeight = deadWeight;
    }

    public void turn(int direction) {
        if (direction >= 1) {
            System.out.println("Turning left");
        } else if (direction == 0) {
            System.out.println("Going straight");
        } else {
            System.out.println("Turning right");
        }
    }

    public void printMe() {
        System.out.println("Boat: speed " + getSpeed() + ", weight " + getWeight() + ", dead weight " + deadWeight);
    }
}
