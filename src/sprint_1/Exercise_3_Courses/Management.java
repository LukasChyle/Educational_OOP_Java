package sprint_1.Exercise_3_Courses;

import java.util.ArrayList;
import java.util.List;

public class Management {

    private final List<Participant> participantList;
    private final List<Teacher> teacherList;
    private final List<Student> studentList;
    private final List<Course> courseList;

    public Management() {
        participantList = new ArrayList<>();
        teacherList = new ArrayList<>();
        studentList = new ArrayList<>();
        courseList = new ArrayList<>();
    }

    public void addStudent(String name) {
        if (getStudent(name) == null) {
            studentList.add(new Student(name));
            System.out.println("Student " + name + " is created");
        } else {
            System.out.println("Student " + name + " does already exist , student not created.");
        }
    }

    public void addTeacher(String name) {
        if (getTeacher(name) == null) {
            teacherList.add(new Teacher(name));
            System.out.println("Teacher " + name + " is created");
        } else {
            System.out.println("Teacher " + name + " does already exist , teacher not created.");
        }
    }

    public void addCourse(String name, Teacher teacher) {
        if (getCourse(name) == null) {
            courseList.add(new Course(name, teacher));
            System.out.println("Course " + name + " is created");
        } else {
            System.out.println("Course " + name + " does already exist, course not created.");
        }
    }

    public void enrollStudent(Student student, Course course) {
        if (checkEnrolled(student, course)) {
            System.out.println(student.getName() + " is already in the course " + course.getName());
        } else {
            participantList.add(new Participant(student, course));
            System.out.println(student.getName() + " is now enrolled to " + course.getName());
        }
    }

    private boolean checkEnrolled(Student student, Course course) {
        for (Participant p : participantList) {
            if (p.getCourse().equals(course) && p.getStudent().equals(student)) {
                return true;
            }
        }
        return false;
    }

    public Student getStudent(String name) {
        for (Student s : studentList) {
            if (s.getName().equals(name)) {
                return s;
            }
        }
        return null;
    }

    public Teacher getTeacher(String name) {
        for (Teacher t : teacherList) {
            if (t.getName().equals(name)) {
                return t;
            }
        }
        return null;
    }

    public Course getCourse(String name) {
        for (Course c : courseList) {
            if (c.getName().equals(name)) {
                return c;
            }
        }
        return null;
    }


    public void printCourse(String name) {
        Course course = getCourse(name);
        if (course != null) {
            course.printInfo(participantList);
        } else {
            System.out.println("Course " + name + " does not exist.");
        }
    }

    public void printStudent(String name) {
        Student student = getStudent(name);
        if (student != null) {
            student.printInfo(participantList);
        } else {
            System.out.println("Student " + name + " does not exist.");
        }
    }

    public void printAllStudents() {
        if (studentList.isEmpty()) {
            System.out.println("There is no students.");
            return;
        }
        for (Student s : studentList) {
            s.printInfo(participantList);
        }
    }

    public void printTeacherNames() {
        if (teacherList.isEmpty()) {
            System.out.println("There is no teachers.");
            return;
        }
        System.out.println(teacherList);
    }

    public void printCourseNames() {
        if (courseList.isEmpty()) {
            System.out.println("There is no courses.");
            return;
        }
        System.out.println(courseList);
    }
}
