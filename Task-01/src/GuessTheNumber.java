import java.util.Random;
import java.util.Scanner;

public class GuessTheNumber{

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int rangeStart = 1; // Starting number of the range
        int rangeEnd = 100; // Ending number of the range
        int attempts = 0; // Number of attempts made by the user
        int score = 0; // User's score

        boolean playAgain = true; // Flag to determine if the user wants to play again

        System.out.println("Welcome to the Number Guessing Game! ");

        while (playAgain) {
            int randomNumber = random.nextInt(rangeEnd - rangeStart + 1) + rangeStart;
            System.out.println("I have generated a number between " + rangeStart + " and " + rangeEnd + ". \nNow, It's your turn to guess it?");

            boolean guessedCorrectly = false;

            while (!guessedCorrectly) {
                System.out.print("Enter your guess number: ");
                int guess = scanner.nextInt();
                attempts++;

                if (guess == randomNumber) {
                    System.out.println("Congratulations! You Successfully guessed the correct number!");
                    guessedCorrectly = true;
                    score++;
                } else if (guess < randomNumber) {
                    System.out.println("Your number is lower than random number! Guess again.");
                } else {
                    System.out.println("Your number is higher than random number! Guess again.");
                }
            }

            System.out.println("No. of Attempts are: " + attempts);
            System.out.println("Your Score is: " + score);

            System.out.print("Do you want to play again? (yes/no): ");
            String playAgainResponse = scanner.next();

            if (!playAgainResponse.equalsIgnoreCase("yes")) {
                playAgain = false;
            }
        }

        System.out.println("Thank you for playing Number Guessing Game!");
        scanner.close();
    }
}
