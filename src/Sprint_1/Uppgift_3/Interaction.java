package Sprint_1.Uppgift_3;

import java.util.Scanner;

public class Interaction {

    private final Scanner inputScan;
    private final Management management;

    public Interaction() {
        management = new Management();
        inputScan = new Scanner(System.in);

        printActions();

        int action;
        boolean run = true;

        while (run) {
            System.out.println("\nChoose your action: ");

            try {
                String input = inputScan.nextLine();
                action = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("\nYou have to enter a valid number, enter 1 to get a list of actions.");
                continue;
            }

            if (action < 0 || action > 10) {
                System.out.println("\ninput: " + action + " was out of range, enter 1 to get a list of actions.");
                continue;
            }

            switch (action) {
                case 0 -> run = false;
                case 1 -> printActions();
                case 2 -> createTeacher();
                case 3 -> createStudent();
                case 4 -> createCourse();
                case 5 -> enrollStudent();
                case 6 -> printTeacherNames();
                case 7 -> printCourseNames();
                case 8 -> printAllStudents();
                case 9 -> printStudent();
                case 10 -> printCourse();
            }
        }
        System.out.println("Exit");
    }

    private void printActions() {
        System.out.println("\nAvailable actions:");
        System.out.println("0  - to exit\n" +
                "1  - to print a list of available actions\n" +
                "2  - to create a new teacher\n" +
                "3  - to create a new student\n" +
                "4  - to create a new course\n" +
                "5  - to enroll student to a course\n" +
                "6  - to print names of teachers\n" +
                "7  - to print names of courses\n" +
                "8  - to print list of students\n" +
                "9  - to find student information\n" +
                "10 - to find course information");
    }

    private void createTeacher() {
        System.out.println(" Enter a name of the new teacher");
        management.addTeacher(inputScan.nextLine());
    }

    private void createStudent() {
        System.out.println("Enter a name of the new student");
        management.addStudent(inputScan.nextLine());
    }

    private void createCourse() {
        System.out.println("What teacher will hold the course? (name)");
        String teacherName = inputScan.nextLine();
        Teacher teacher = management.getTeacher(teacherName);

        if (teacher == null) {
            System.out.println("Teacher " + teacherName + " does not exist, course creation aborted");
            return;
        }

        System.out.println("Enter a name of the new course");
        String courseName = inputScan.nextLine();

        management.addCourse(courseName, teacher);
    }

    private void enrollStudent() {
        System.out.println("Enter the name of student you want to enroll");
        String studentName = inputScan.nextLine();
        Student student = management.getStudent(studentName);
        if (student == null) {
            System.out.println("Student " + studentName + " does not exist, action aborted.");
            return;
        }
        System.out.println("Enter the name of the course " + studentName + " will take.");
        String courseName = inputScan.nextLine();
        Course course = management.getCourse(courseName);
        if (course == null) {
            System.out.println("Course " + courseName + " does not exist, action aborted.");
            return;
        }
        management.enrollStudent(student, course);
    }

    private void printTeacherNames() {
        management.printTeacherNames();
    }

    private void printCourseNames() {
        management.printCourseNames();
    }

    private void printAllStudents() {
        management.printAllStudents();
    }

    private void printStudent() {
        System.out.println("Enter the name of the student that you want to find");
        management.printStudent(inputScan.nextLine());
    }

    private void printCourse() {
        System.out.println("Enter the name of the course that you want to find");
        management.printCourse(inputScan.nextLine());
    }
}
