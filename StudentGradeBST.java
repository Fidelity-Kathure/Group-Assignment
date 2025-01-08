import java.io.*;
import java.util.*;


public class StudentGradeBST {
    private Node root;

    public Node getRoot() {
        return root;
    }

    // Insert or update a student
    public void insertOrUpdate(Student student) {
        root = insertOrUpdateRecursive(root, student);
    }

    private Node insertOrUpdateRecursive(Node node, Student student) {
        if (node == null) {
            return new Node(student);
        }
        if (student.getName().compareTo(node.getStudent().getName()) < 0) {
            node.setLeft(insertOrUpdateRecursive(node.getLeft(), student));
        } else if (student.getName().compareTo(node.getStudent().getName()) > 0) {
            node.setRight(insertOrUpdateRecursive(node.getRight(), student));
        } else {
            // Update grade if student already exists
            node.getStudent().addGrade("defaultCategory", student.getGrades().get("defaultCategory"), 1); // Add or update the default category
        }
        return node;
    }

    // Search for a student by name
    public Student searchStudent(String name) {
        return searchRecursive(root, name);
    }

    private Student searchRecursive(Node node, String name) {
        if (node == null) {
            return null;
        }
        if (name.equals(node.getStudent().getName())) {
            return node.getStudent();
        }
        return name.compareTo(node.getStudent().getName()) < 0
                ? searchRecursive(node.getLeft(), name)
                : searchRecursive(node.getRight(), name);
    }

    // Remove a student by name
    public void removeStudent(String name) {
        root = removeRecursive(root, name);
    }

    private Node removeRecursive(Node node, String name) {
        if (node == null) {
            return null;
        }

        // If the student is found, remove it
        if (name.equals(node.getStudent().getName())) {
            // Node to be deleted
            if (node.getLeft() == null && node.getRight() == null) {
                return null; // No children
            } else if (node.getRight() == null) {
                return node.getLeft(); // One child (left)
            } else if (node.getLeft() == null) {
                return node.getRight(); // One child (right)
            } else {
                // Two children: get the in-order successor (smallest in the right subtree)
                node.setStudent(getMin(node.getRight()).getStudent());
                node.setRight(removeRecursive(node.getRight(), node.getStudent().getName()));
            }
        } else if (name.compareTo(node.getStudent().getName()) < 0) {
            node.setLeft(removeRecursive(node.getLeft(), name));
        } else {
            node.setRight(removeRecursive(node.getRight(), name));
        }

        return node;
    }

    private Node getMin(Node node) {
        return node.getLeft() == null ? node : getMin(node.getLeft());
    }

    // Calculate the average grade of all students
    public double calculateAverageGrade() {
        int[] stats = calculateStats(root);
        return stats[0] == 0 ? 0 : (double) stats[1] / stats[0];
    }

    private int[] calculateStats(Node node) {
        if (node == null) return new int[]{0, 0};
        int[] leftStats = calculateStats(node.getLeft());
        int[] rightStats = calculateStats(node.getRight());
        int count = 1 + leftStats[0] + rightStats[0];
        int total = (int) node.getStudent().calculateWeightedAverage() + leftStats[1] + rightStats[1]; // Include weighted average
        return new int[]{count, total};
    }

    // Get the highest grade
    public double getHighestGrade() {
        return findExtremeGrade(root, true);
    }

    // Get the lowest grade
    public double getLowestGrade() {
        return findExtremeGrade(root, false);
    }

    private double findExtremeGrade(Node node, boolean isMax) {
        if (node == null) return isMax ? Double.MIN_VALUE : Double.MAX_VALUE;
        double extreme = node.getStudent().calculateWeightedAverage();
        double leftExtreme = findExtremeGrade(node.getLeft(), isMax);
        double rightExtreme = findExtremeGrade(node.getRight(), isMax);
        return isMax ? Math.max(extreme, Math.max(leftExtreme, rightExtreme))
                     : Math.min(extreme, Math.min(leftExtreme, rightExtreme));
    }

    // Display grades in ascending order
    public void displayGradesInAscendingOrder() {
        inOrderTraversal(root);
    }

    private void inOrderTraversal(Node node) {
        if (node != null) {
            inOrderTraversal(node.getLeft());
            System.out.println(node.getStudent().getName() + ": " + node.getStudent().calculateWeightedAverage());
            inOrderTraversal(node.getRight());
        }
    }


    public void saveInOrder(BufferedWriter writer, Node node) throws IOException {
        if (node != null) {
            saveInOrder(writer, node.getLeft());
            writer.write(node.getStudent().getName() + "," + node.getStudent().calculateWeightedAverage());
            writer.newLine();
            saveInOrder(writer, node.getRight());
        }
    }
}