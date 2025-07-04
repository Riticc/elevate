import java.util.*;

class Question {
    String questionText;
    List<String> options;
    int correctOption; 

    public Question(String questionText, List<String> options, int correctOption) {
        this.questionText = questionText;
        this.options = options;
        this.correctOption = correctOption;
    }

    public boolean isCorrect(int userAnswer) {
        return userAnswer == correctOption;
    }

    public void displayQuestion() {
        System.out.println("\n" + questionText);
        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + ". " + options.get(i));
        }
    }
}

public class QuizApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Question> questions = new ArrayList<>();

        // Sample questions
        questions.add(new Question("What is the capital of France?",
                Arrays.asList("Berlin", "Madrid", "Paris", "Rome"), 3));
        questions.add(new Question("Which language is used for Android development?",
                Arrays.asList("Swift", "Kotlin", "Python", "Ruby"), 2));
        questions.add(new Question("What is 5 + 7?",
                Arrays.asList("10", "11", "12", "13"), 3));

        int score = 0;

        System.out.println("🎯 Welcome to the Java Console Quiz!");
        System.out.println("Answer the following questions:\n");

        for (int i = 0; i < questions.size(); i++) {
            Question q = questions.get(i);
            q.displayQuestion();

            System.out.print("Your answer (1-4): ");
            int userAnswer = scanner.nextInt();

            if (q.isCorrect(userAnswer)) {
                System.out.println(" Correct!");
                score++;
            } else {
                System.out.println(" Incorrect. The correct answer was option " + q.correctOption);
            }
        }

        System.out.println("\n🎉 Quiz Completed!");
        System.out.println("Your Score: " + score + "/" + questions.size());

        scanner.close();
    }
}
