package sprint_1.exercise_3_Courses;

import java.util.List;

public class Course {

    private final String name;
    private final Teacher teacher;

    public Course(String name, Teacher teacher) {
        if (name.isBlank()) {
            throw new IllegalArgumentException("Invalid name, can't be blank or empty");
        }
        this.name = name;
        this.teacher = teacher;
    }

    public String getName() {
        return name;
    }


    public void printInfo(List<Participant> list) {
        String studentList = "";
        for (Participant p : list) {
            if (p.getCourse().equals(this)) {
                studentList += p.getStudent().getName() + ", ";
            }
        }
        System.out.println("\nCourse: " + name + "\nTeacher: " + teacher.getName() + "\nStudents: " + studentList);
    }

    @Override
    public String toString() {
        return name;
    }
}
