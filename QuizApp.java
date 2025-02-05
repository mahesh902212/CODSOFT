import java.util.*;
import java.util.concurrent.TimeUnit;

class Question {
    String questionText;
    String[] options;
    String correctAnswer;

    public Question(String questionText, String[] options, String correctAnswer) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    public boolean checkAnswer(String userAnswer) {
        return correctAnswer.equalsIgnoreCase(userAnswer);
    }
}

public class QuizApp {
    private static final int TIME_LIMIT = 30; // Time limit in seconds
    private static int score = 0;
    private static List<Question> quizQuestions = new ArrayList<>();
    private static List<String> userAnswers = new ArrayList<>();
    
    public static void main(String[] args) throws InterruptedException {
        // Add questions to the quiz
        loadQuizQuestions();

        // Start the quiz
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < quizQuestions.size(); i++) {
            Question currentQuestion = quizQuestions.get(i);
            System.out.println("\n" + (i + 1) + ". " + currentQuestion.questionText);

            // Display options
            for (int j = 0; j < currentQuestion.options.length; j++) {
                System.out.println((j + 1) + ". " + currentQuestion.options[j]);
            }

            // Start timer
            System.out.println("You have " + TIME_LIMIT + " seconds to answer.");
            long startTime = System.currentTimeMillis();
            String userAnswer = "";
            while ((System.currentTimeMillis() - startTime) < TIME_LIMIT * 1000) {
                if (scanner.hasNextLine()) {
                    userAnswer = scanner.nextLine().trim();
                    break;
                }
            }

            // After time or answer submission
            if (userAnswer.isEmpty()) {
                System.out.println("Time's up! No answer submitted.");
                userAnswer = "No Answer";
            }

            // Check answer
            if (currentQuestion.checkAnswer(userAnswer)) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Incorrect! The correct answer is: " + currentQuestion.correctAnswer);
            }

            userAnswers.add(userAnswer);
        }

        // Show final result
        showResult();
    }

    public static void loadQuizQuestions() {
        quizQuestions.add(new Question("What is the capital of France?", 
            new String[]{"Berlin", "Madrid", "Paris", "Rome"}, "Paris"));
        quizQuestions.add(new Question("Which planet is known as the Red Planet?", 
            new String[]{"Earth", "Mars", "Jupiter", "Saturn"}, "Mars"));
        // Add more questions here...
    }

    public static void showResult() {
        System.out.println("\nQuiz Finished!");
        System.out.println("Your final score: " + score + "/" + quizQuestions.size());
        System.out.println("\nSummary of Answers:");
        for (int i = 0; i < quizQuestions.size(); i++) {
            Question q = quizQuestions.get(i);
            System.out.println((i + 1) + ". " + q.questionText);
            System.out.println("Your answer: " + userAnswers.get(i));
            System.out.println("Correct answer: " + q.correctAnswer);
            System.out.println();
        }
    }
}
