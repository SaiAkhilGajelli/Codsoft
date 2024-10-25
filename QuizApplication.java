import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

class Question {
    String questionText;
    String[] options;
    int correctAnswerIndex;

    public Question(String questionText, String[] options, int correctAnswerIndex) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswerIndex = correctAnswerIndex;
    }
}

public class QuizApplication {
    private static List<Question> questions = new ArrayList<>();
    private static int score = 0;

    public static void main(String[] args) {
        loadQuestions();
        runQuiz();
        displayResults();
    }

    private static void loadQuestions() {
        questions.add(new Question("What is the capital of France?", new String[]{"Berlin", "Madrid", "Paris", "Rome"}, 2));
        questions.add(new Question("What is the largest planet in our solar system?", new String[]{"Earth", "Jupiter", "Saturn", "Mars"}, 1));
        questions.add(new Question("Who wrote 'Romeo and Juliet'?", new String[]{"Charles Dickens", "Mark Twain", "William Shakespeare", "Jane Austen"}, 2));
        questions.add(new Question("What is the chemical symbol for water?", new String[]{"H2O", "O2", "CO2", "H2"}, 0));
        questions.add(new Question("What is the powerhouse of the cell?", new String[]{"Nucleus", "Ribosome", "Mitochondria", "Endoplasmic Reticulum"}, 2));
    }

    private static void runQuiz() {
        Scanner scanner = new Scanner(System.in);

        for (Question question : questions) {
            System.out.println("\n" + question.questionText);
            for (int i = 0; i < question.options.length; i++) {
                System.out.println((i + 1) + ") " + question.options[i]);
            }

            // Start a timer for the question
            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    System.out.println("\nTime's up! Moving to the next question...\n");
                }
            };
            timer.schedule(task, 30000); // 30 seconds

            // Get user's answer
            int userAnswer = -1;
            try {
                System.out.print("Your answer (1-4): ");
                userAnswer = scanner.nextInt() - 1;
            } catch (Exception e) {
                System.out.println("Invalid input! Moving to the next question...\n");
            }

            timer.cancel(); // Stop the timer if the user answers

            if (userAnswer >= 0 && userAnswer < question.options.length) {
                if (userAnswer == question.correctAnswerIndex) {
                    score++;
                    System.out.println("Correct!");
                } else {
                    System.out.println("Incorrect. The correct answer was: " + question.options[question.correctAnswerIndex]);
                }
            } else {
                System.out.println("No answer given or invalid choice.");
            }
        }

        scanner.close();
    }

    private static void displayResults() {
        System.out.println("\nQuiz finished! Your score: " + score + " out of " + questions.size());
        System.out.printf("Your percentage: %.2f%%\n", (score / (double) questions.size()) * 100);
    }
}
