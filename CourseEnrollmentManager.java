import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

public class CourseEnrollmentManager {

    private TreeMap<String, TreeSet<String>> enrollments;

    public CourseEnrollmentManager() {
        enrollments = new TreeMap<>();
    }

    public void addStudentToCourse(String course, String studentName) {
        enrollments
                .computeIfAbsent(course, k -> new TreeSet<>())
                .add(studentName);
        System.out.println(studentName
                + " enrolled in " + course);
    }
    public void removeStudentFromCourse(String course, String studentName) {
        TreeSet<String> students = enrollments.get(course);
        if (students != null && students.remove(studentName)) {
            System.out.println(studentName
                    + " removed from " + course);
            // Clean up empty course entries
            if (students.isEmpty()) {
                enrollments.remove(course);
            }
        } else {
            System.out.println(studentName
                    + " is not enrolled in " + course);
        }
    }

    public boolean isStudentEnrolled(String course, String studentName) {
        TreeSet<String> students = enrollments.get(course);
        return students != null && students.contains(studentName);
    }

    public void displayCourseRoster(String course) {
        TreeSet<String> students = enrollments.get(course);
        System.out.println("\n--- Roster: " + course + " ---");
        if (students == null || students.isEmpty()) {
            System.out.println("  No students enrolled.");
        } else {
            int i = 1;
            for (String name : students) {
                System.out.println("  " + i++ + ". " + name);
            }
            System.out.println("  Total: " + students.size());
        }
    }


    public void displayAllCourses() {
        System.out.println("\n=== All Course Enrollments ===");
        for (Map.Entry<String, TreeSet<String>> entry
                : enrollments.entrySet()) {
            System.out.println("\nCourse: " + entry.getKey());
            int i = 1;
            for (String student : entry.getValue()) {
                System.out.println("  " + i++ + ". " + student);
            }
        }
    }

    public static void main(String[] args) {
        CourseEnrollmentManager mgr = new CourseEnrollmentManager();

        mgr.addStudentToCourse("Data Structures", "Ali");
        mgr.addStudentToCourse("Data Structures", "Hamza");
        mgr.addStudentToCourse("Data Structures", "Kiran");
        mgr.addStudentToCourse("Data Structures", "Basit");
        mgr.addStudentToCourse("Data Structures", "Ali");   // duplicate

        mgr.addStudentToCourse("OOP",             "Zain");
        mgr.addStudentToCourse("OOP",             "Hamza");
        mgr.addStudentToCourse("OOP",             "Rohan");

        mgr.displayCourseRoster("Data Structures");
        mgr.displayCourseRoster("OOP");

        System.out.println("\nIs Kiran in DS? "
                + mgr.isStudentEnrolled("Data Structures", "Kiran"));
        System.out.println("Is Ali in OOP?  "
                + mgr.isStudentEnrolled("OOP", "Ali"));

        mgr.removeStudentFromCourse("OOP", "Rohan");
        mgr.displayAllCourses();
    }
}