package Sprint_1.Uppgift_3;

import java.util.List;

public class Teacher extends Person {

    public Teacher(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return getName();
    }
}
