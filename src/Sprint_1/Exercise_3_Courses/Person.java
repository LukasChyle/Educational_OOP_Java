package Sprint_1.Exercise_3_Courses;

public abstract class Person {

    private String name;

    public Person(String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException("Invalid name, can't be blank or empty");
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
