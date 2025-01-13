import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        StudentGradeBST studentTree = new StudentGradeBST();
        FileHandler fileHandler = new FileHandler();

        // Load data from file
        studentTree = fileHandler.loadData("students.txt");

        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n=== Student Grade Tracking System ===");
            System.out.println("1. Add or Update Student");
            System.out.println("2. Display All Students");
            System.out.println("3. Search Student");
            System.out.println("4. Remove Student");
            System.out.println("5. Display Class Statistics");
            System.out.println("6. Save and Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Clear the buffer

            switch (choice) {
                case 1:
                    addOrUpdateStudent(scanner, studentTree);
                    break;
                case 2:
                    System.out.println("\nStudent Grades:");
                    studentTree.displayGradesInAscendingOrder();
                    break;
                case 3:
                    searchStudent(scanner, studentTree);
                    break;
                case 4:
                    removeStudent(scanner, studentTree);
                    break;
                case 5:
                    displayStatistics(studentTree);
                    break;
                case 6:
                    fileHandler.saveData(studentTree, "students.txt");
                    System.out.println("Data saved. Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 6);

        scanner.close();
    }

    private static void addOrUpdateStudent(Scanner scanner, StudentGradeBST tree) {
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        System.out.print("Enter student grade: ");
        double grade = scanner.nextDouble();
        scanner.nextLine(); 

        
        Student student = new Student(name, grade);
        student.addGrade("defaultCategory", grade, 1.0); 
        tree.insertOrUpdate(student);
        System.out.println("Student record added/updated.");
    }

    private static void searchStudent(Scanner scanner, StudentGradeBST tree) {
        System.out.print("Enter student name to search: ");
        String name = scanner.nextLine();
        Student student = tree.searchStudent(name);
        if (student != null) {
            System.out.println("Found: " + student);
        } else {
            System.out.println("Student not found.");
        }
    }

    private static void removeStudent(Scanner scanner, StudentGradeBST tree) {
        System.out.print("Enter student name to remove: ");
        String name = scanner.nextLine();
        tree.removeStudent(name); 
        System.out.println("Student record removed.");
    }

    private static void displayStatistics(StudentGradeBST tree) {
        System.out.println("\nClass Statistics:");
        System.out.println("Average Grade: " + tree.calculateAverageGrade());
        System.out.println("Highest Grade: " + tree.getHighestGrade());
        System.out.println("Lowest Grade: " + tree.getLowestGrade());
    }
}
