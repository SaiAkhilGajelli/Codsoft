import java.util.Scanner;

public class GradeCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input: Number of subjects
        System.out.print("Enter the number of subjects: ");
        int numSubjects = scanner.nextInt();

        // Array to hold marks
        double[] marks = new double[numSubjects];

        // Input: Marks for each subject
        for (int i = 0; i < numSubjects; i++) {
            System.out.print("Enter marks obtained in subject " + (i + 1) + " (out of 100): ");
            marks[i] = scanner.nextDouble();
            // Input validation
            while (marks[i] < 0 || marks[i] > 100) {
                System.out.print("Invalid marks! Please enter again (0-100): ");
                marks[i] = scanner.nextDouble();
            }
        }

        // Calculate Total Marks
        double totalMarks = calculateTotalMarks(marks);

        // Calculate Average Percentage
        double averagePercentage = totalMarks / numSubjects;

        // Calculate Grade
        char grade = calculateGrade(averagePercentage);

        // Display Results
        displayResults(totalMarks, averagePercentage, grade);

        scanner.close();
    }

    private static double calculateTotalMarks(double[] marks) {
        double total = 0;
        for (double mark : marks) {
            total += mark;
        }
        return total;
    }

    private static char calculateGrade(double averagePercentage) {
        if (averagePercentage >= 90) {
            return 'A';
        } else if (averagePercentage >= 75) {
            return 'B';
        } else if (averagePercentage >= 60) {
            return 'C';
        } else if (averagePercentage >= 50) {
            return 'D';
        } else {
            return 'F';
        }
    }

    private static void displayResults(double totalMarks, double averagePercentage, char grade) {
        System.out.printf("\nTotal Marks: %.2f\n", totalMarks);
        System.out.printf("Average Percentage: %.2f%%\n", averagePercentage);
        System.out.println("Grade: " + grade);
    }
}
