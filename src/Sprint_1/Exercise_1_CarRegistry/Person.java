package Sprint_1.Exercise_1_CarRegistry;

public class Person {

    private String name;
    private String address;
    private int age;

    public Person(String name, String address, int age) {
        if (name.isBlank() || address.isBlank()){
            throw new IllegalArgumentException("Invalid String input");
        } else if (age < 0) {
            throw new IllegalArgumentException(age + "Invalid number for age");
        }
        this.name = name;
        this.address = address;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

