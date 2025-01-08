import java.util.*;

public class GradeHistoryEntry {
    private double grade;
    private String date;  // Store the date when the grade was updated
    
    public GradeHistoryEntry(double grade, String date) {
        this.grade = grade;
        this.date = date;
    }

    public double getGrade() {
        return grade;
    }

    public String getDate() {
        return date;
    }
}