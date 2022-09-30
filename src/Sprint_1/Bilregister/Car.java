package Sprint_1.Bilregister;

public class Car {

    private final String registrationNumber;
    private final String brand;
    private final String model;
    private CarOwner owner;

    public Car(String registrationNumber, String brand, String model) {
        this.registrationNumber = registrationNumber;
        this.brand = brand;
        this.model = model;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public String getModel() {
        return model;
    }

    public String getBrand() {
        return brand;
    }

    public CarOwner getOwner() {
        return owner;
    }

    public void buyCar(CarOwner owner) {
        this.owner = owner;
    }

    public void sellCar(){
        this.owner = null;
    }

    public void printOwnerOfCar(){
        if (owner == null) {
            System.out.println("\n" + brand + " " + model +
                    ", registration number " + registrationNumber + ", has no owner.");
        } else {
            System.out.println(owner + " owns " + brand + " " + model +
                    ", registration number " + registrationNumber);
        }
    }

    @Override
    public String toString() {
        return brand + " " + model + ", registration number " + registrationNumber;
    }
}