import java.io.*;
import java.util.Scanner;

public class FileHandler {

    public StudentGradeBST loadData(String filename) {
        StudentGradeBST tree = new StudentGradeBST();
        try (Scanner scanner = new Scanner(new File(filename))) {
            while (scanner.hasNextLine()) {
                String[] parts = scanner.nextLine().split(",");
                String name = parts[0];
                double grade = Double.parseDouble(parts[1]);
                tree.insertOrUpdate(new Student(name, grade));
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found. Starting with an empty database.");
        }
        return tree;
    }

    public void saveData(StudentGradeBST tree, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            tree.saveInOrder(writer, tree.getRoot());
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }
}