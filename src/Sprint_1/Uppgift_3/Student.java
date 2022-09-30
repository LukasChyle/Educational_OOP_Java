package Sprint_1.Uppgift_3;

import java.util.List;

public class Student extends Person {

    public Student(String name) {
        super(name);
    }

    public void printInfo(List<Participant> list) {
        String courseList = "";
        for (Participant p : list) {
            if (p.getStudent().equals(this)) {
                courseList += p.getCourse().getName() + ", ";
            }
        }
        System.out.println("\nStudent: " + getName() + "\nCourses: " + courseList);
    }
}
