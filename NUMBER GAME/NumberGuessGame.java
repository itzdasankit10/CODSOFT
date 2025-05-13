package company.Javass.CodeSoft;


    import java.util.Random;
import java.util.Scanner;

    public class NumberGuessGame {
        private static final int MIN_RANGE = 1;
        private static final int MAX_RANGE = 100;
        private static final int MAX_ATTEMPTS = 7;

        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            Random random = new Random();
            boolean playAgain;
            int totalRounds = 0;
            int totalScore = 0;

            System.out.println("Welcome to the Ultimate Number Guessing Challenge! 🤖");

            do {
                totalRounds++;
                int secretNumber = random.nextInt(MAX_RANGE - MIN_RANGE + 1) + MIN_RANGE;
                System.out.printf("\nRound %d: Guess a number between %d and %d. You have %d attempts.\n",
                        totalRounds, MIN_RANGE, MAX_RANGE, MAX_ATTEMPTS);

                int attemptsLeft = MAX_ATTEMPTS;
                int guess;
                boolean correct = false;

                while (attemptsLeft > 0) {
                    System.out.printf("Attempts left: %d. Enter your guess: ", attemptsLeft);
                    if (!scanner.hasNextInt()) {
                        System.out.println("That's not even a number, buddy! Try again.");
                        scanner.next();
                        continue;
                    }

                    guess = scanner.nextInt();
                    attemptsLeft--;

                    if (guess == secretNumber) {
                        correct = true;
                        int roundScore = attemptsLeft + 1; // More points for fewer attempts
                        totalScore += roundScore;
                        System.out.printf("Bingo! You nailed it in %d attempts. Round score: %d\n",
                                (MAX_ATTEMPTS - attemptsLeft), roundScore);
                        break;
                    } else if (guess < secretNumber) {
                        System.out.println("Too low! Aim higher! 🚀");
                    } else {
                        System.out.println("Too high! Bring it down a notch! 🎯");
                    }
                }

                if (!correct) {
                    System.out.printf("Out of attempts! The number was %d. Better luck next time!\n", secretNumber);
                }

                System.out.printf("Your total score after %d %s: %d\n",
                        totalRounds, (totalRounds == 1 ? "round" : "rounds"), totalScore);

                System.out.print("Wanna play again? (yes/no): ");
                String response = scanner.next().trim().toLowerCase();
                playAgain = response.startsWith("y");

            } while (playAgain);

            System.out.println("Thanks for playing! Final score: " + totalScore + ". See you soon! ✌️");
            scanner.close();
        }
    }


