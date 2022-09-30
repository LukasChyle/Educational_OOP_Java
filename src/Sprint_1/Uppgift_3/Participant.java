package Sprint_1.Uppgift_3;

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
