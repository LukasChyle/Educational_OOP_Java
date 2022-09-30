package Sprint_1.Exercise_3_Courses;

import java.util.List;

public class Course {

    private final String name;
    private final Teacher teacher;

    public Course(String name, Teacher teacher) {
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
