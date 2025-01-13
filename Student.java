import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Date;

public class Student {
    private String name;
    private Map<String, Double> grades; 
    private Map<String, Double> weights; 
    private List<GradeHistoryEntry> gradeHistory; 
    
    // Constructor to accept the initial grade and the name
    public Student(String name, double grade) {
        this.name = name;
        this.grades = new HashMap<>();
        this.weights = new HashMap<>();
        this.gradeHistory = new ArrayList<>();
        
        
        addGrade("exam1", grade, 1.0);
    }

   
    public void addGrade(String category, double grade, double weight) {
        grades.put(category, grade);
        weights.put(category, weight);
        gradeHistory.add(new GradeHistoryEntry(grade, new Date().toString())); // Record history with timestamp
    }

    // Calculate weighted average for all grades
    public double calculateWeightedAverage() {
        double totalWeight = 0;
        double weightedSum = 0;
        for (String category : grades.keySet()) {
            double grade = grades.get(category);
            double weight = weights.get(category);
            weightedSum += grade * weight;
            totalWeight += weight;
        }
        return totalWeight == 0 ? 0 : weightedSum / totalWeight;
    }

    public String getName() {
        return name;
    }

    public Map<String, Double> getGrades() {
        return grades;
    }

    public List<GradeHistoryEntry> getGradeHistory() {
        return gradeHistory;
    }
}
    

