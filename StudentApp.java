import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

class Person {
    protected String name;

    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }
}

class Student extends Person {
    private int rollNo;
    private String course;
    private double marks;
    private char grade;

    public Student() {
    }

    public Student(int rollNo, String name, String course, double marks) {
        super(name);
        this.rollNo = rollNo;
        this.course = course;
        this.marks = marks;
        this.grade = calculateGrade();
    }

    public void inputDetails(Scanner sc) {
        this.rollNo = readInt(sc, "Enter Roll No: ");
        System.out.print("Enter Name: ");
        sc.nextLine();
        this.name = sc.nextLine().trim();
        System.out.print("Enter Course: ");
        this.course = sc.nextLine().trim();
        this.marks = readMarks(sc, "Enter Marks (0-100): ");
        this.grade = calculateGrade();
    }

    public void displayDetails() {
        System.out.println("Roll No: " + this.rollNo);
        System.out.println("Name: " + this.name);
        System.out.println("Course: " + this.course);
        System.out.println("Marks: " + this.marks);
        System.out.println("Grade: " + this.grade);
    }

    public char calculateGrade() {
        if (marks >= 90)
            return 'A';
        else if (marks >= 80)
            return 'B';
        else if (marks >= 70)
            return 'C';
        else if (marks >= 60)
            return 'D';
        else
            return 'F';
    }

    private static int readInt(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid integer.");
                sc.next();
            }
        }
    }

    private static double readMarks(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                double m = sc.nextDouble();
                if (m < 0 || m > 100) {
                    System.out.println("Marks must be between 0 and 100.");
                } else {
                    return m;
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter a numeric value.");
                sc.next();
            }
        }
    }
}

public class StudentApp {
    private final ArrayList<Student> students = new ArrayList<>();

    public static void main(String[] args) {
        new StudentApp().run();
    }

    private void run() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            printMenu();
            int choice = readMenuChoice(sc);
            switch (choice) {
                case 1 -> addStudent(sc);
                case 2 -> displayAllStudents();
                case 3 -> {
                    System.out.println("Exiting the application. Goodbye!");
                    sc.close();
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
            System.out.println();
        }
    }

    private void printMenu() {
        System.out.println("===== Student Record Menu =====");
        System.out.println("1. Add Student");
        System.out.println("2. Display All Students");
        System.out.println("3. Exit");
        System.out.print("Enter your choice: ");
    }

    private int readMenuChoice(Scanner sc) {
        while (!sc.hasNextInt()) {
            System.out.println("Please enter 1, 2, or 3.");
            sc.next();
            System.out.print("Enter your choice: ");
        }
        return sc.nextInt();
    }

    private void addStudent(Scanner sc) {
        System.out.println();
        Student s = new Student();
        s.inputDetails(sc);
        students.add(s);
        System.out.println("Student added successfully.");
    }

    private void displayAllStudents() {
        System.out.println();
        if (students.isEmpty()) {
            System.out.println("No records to display.");
            return;
        }
        for (Student s : students) {
            s.displayDetails();
            System.out.println("-----------------------------");
        }
    }
}
