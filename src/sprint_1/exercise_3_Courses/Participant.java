package sprint_1.exercise_3_Courses;

public class Participant {

    private final Student student;
    private final Course course;

    public Participant(Student student, Course course) {
        this.student = student;
        this.course = course;
    }

    public Student getStudent() {
        return student;
    }

    public Course getCourse() {
        return course;
    }
}
