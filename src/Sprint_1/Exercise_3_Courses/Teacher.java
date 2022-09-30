package Sprint_1.Exercise_3_Courses;

public class Teacher extends Person {

    public Teacher(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return getName();
    }
}
