import java.util.Random;
import java.util.Scanner;

public class GuessingGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random=new Random();
        int score=0;
        int rounds=0;

        while(true){
            System.out.println("Welcome to the Guessing Game!");
            System.out.println("I'm thinking of a number between 1 and 100.");

            int numberToGuess = random.nextInt(100)+1;
            int attempts = 0;
            int maxAttempts = 6;

            while (attempts < maxAttempts) {
                System.out.println("Enter Your Guess attempt" +" "+ (attempts+ " " + 1) + "):");
                int guess = scanner.nextInt();
                attempts++;

                if (guess < numberToGuess) {
                    System.out.println("Too low! Try again.");
                }
                else if (guess > numberToGuess) {
                    System.out.println("Too high! Try again.");
                }
                else{
                    System.out.println("Congratulation! You guessed the number in" +" "+ attempts + " " + "attempts.");
                    score += 10 -attempts;
                    rounds++;
                    break;
                }
            }

            if (attempts == maxAttempts) {
                System.out.println("Sorry, you didn't guess the number. It was"+" "+numberToGuess + ".");
            }

            System.out.println("Play again? (yes/no): ");
            String response = scanner.next().toLowerCase();

            if (! response.equals("yes")) {
                break;
            }
        }
        System.out.println("Game over! Your final score is" + score + "points.");
        System.out.println("You won" + " "+rounds +" "+ "round(s).");
    }
}