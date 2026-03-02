
import java.util.*;


class Student{
    
    private int id;
    private String name;
    private String department;
    private int year;
    private long phoneno;
    
    public Student(int id, String name, String department, int year, long phoneno) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.year = year;
        this.phoneno = phoneno;
    }
    
    public void display() {
        System.out.println(
            "ID: " + id +
            ", Name: " + name +
            ", Dept: " + department +
            ", Year: " + year +
            ", Phone Number: " + phoneno
        );
    }
    
    public int getId() {
        return id;
    }
    
}

class StudentManager {
    private List<Student> students = new ArrayList<>();

    public void addStudent(Student student) {
        students.add(student);
    }

    public void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students available.");
            return;
        }

        for (Student s : students) {
            s.display();
        }
    }
    
    public void searchStudentById(int id) {
        for (Student s : students) {
            if (s.getId() == id) {
                s.display();
                return;
            }
        }
        System.out.println("Student not found.");
    }
}

class StudentApp {
    private Scanner scanner = new Scanner(System.in);
    private StudentManager manager = new StudentManager();
    public void start() {
        int choice = 0;

        while (choice != 4) {
            showMenu();
            
            choice = scanner.nextInt();

            if (choice == 1) {
                addStudent();
            } 
            else if (choice == 2) {
                manager.displayAllStudents();
            } 
            else if (choice == 3) {
                searchStudent();
            } 
            else if (choice == 4) {
                System.out.println("Exiting Student Management System.");
            } 
            else {
                System.out.println("Invalid option.");
            }
        }
    }
    
    private void showMenu() {
        System.out.println("\n=== Student Management System ===");
        System.out.println("1. Add Student");
        System.out.println("2. View All Students");
        System.out.println("3. Search Student by ID");
        System.out.println("4. Exit");
        System.out.print("Enter choice: ");
    }

    private void addStudent() {
        System.out.print("Enter Student ID: ");
        int id = scanner.nextInt();

        System.out.print("Enter Student Name: ");
        scanner.nextLine();
        String name = scanner.nextLine();

        System.out.print("Enter Department: ");
        String dept = scanner.nextLine();

        System.out.print("Enter Year: ");
        int year = scanner.nextInt();

        System.out.print("Enter Phone Number: ");
        long phoneno = scanner.nextLong();

        Student student = new Student(id, name, dept, year, phoneno);
        manager.addStudent(student);

        System.out.println("Student added successfully.");
    }
    
    private void searchStudent() {
        System.out.print("Enter Student ID to search: ");
        int id = scanner.nextInt();
        manager.searchStudentById(id);
    }
}



public class StudentManagement{
	public static void main(String[] args) {
        StudentApp app = new StudentApp();
        app.start();
    }
}