import java.util.Map;
import java.util.TreeMap;

public class StudentScoreManager {

    private TreeMap<String, Integer> scores;

    public StudentScoreManager() {
        scores = new TreeMap<>();
    }

    // Add or update a student's score
    public void addOrUpdateScore(String name, int score) {
        boolean exists = scores.containsKey(name);
        scores.put(name, score);
        if (exists) {
            System.out.println("Updated " + name + "'s score to " + score);
        } else {
            System.out.println("Added " + name + " with score " + score);
        }
    }

    // Remove a student record
    public void removeStudent(String name) {
        if (scores.remove(name) != null) {
            System.out.println("Removed student: " + name);
        } else {
            System.out.println("Student not found: " + name);
        }
    }

    // Get score for a specific student (returns null if not found)
    public Integer getScore(String name) {
        return scores.get(name);
    }

    // Display all students in sorted order
    public void displayAllStudents() {
        System.out.println("\n--- All Students (Sorted by Name) ---");
        for (Map.Entry<String, Integer> entry : scores.entrySet()) {
            System.out.printf("%-20s : %d%n",
                    entry.getKey(), entry.getValue());
        }
        System.out.println("Total: " + scores.size() + " students\n");
    }

    // Display students whose names fall in [start, end]
    public void displayStudentsInRange(String start, String end) {
        System.out.println("\nStudents from '" + start
                + "' to '" + end + "':");
        Map<String, Integer> range = scores.subMap(start, true, end, true);
        if (range.isEmpty()) {
            System.out.println("  None found.");
        } else {
            for (Map.Entry<String, Integer> e : range.entrySet()) {
                System.out.printf("  %-20s : %d%n",
                        e.getKey(), e.getValue());
            }
        }
    }

    // --- Main: demo ---
    public static void main(String[] args) {
        StudentScoreManager mgr = new StudentScoreManager();

        mgr.addOrUpdateScore("Ali",    88);
        mgr.addOrUpdateScore("Hamza",  75);
        mgr.addOrUpdateScore("Kiran",  92);
        mgr.addOrUpdateScore("Basit",  81);
        mgr.addOrUpdateScore("Zain",   67);
        mgr.addOrUpdateScore("Rohan",  70);

        mgr.displayAllStudents();

        mgr.addOrUpdateScore("Hamza", 85);   // update

        System.out.println("Score of Kiran: "
                + mgr.getScore("Kiran"));
        System.out.println("Score of Dani:  "
                + mgr.getScore("Dani"));   // null

        mgr.displayStudentsInRange("Basit", "Kiran");

        mgr.removeStudent("Zain");
        mgr.displayAllStudents();
    }
}