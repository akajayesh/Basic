import java.util.Scanner;
import java.util.Random;

public class game {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        String playAgain = "yes";

        while (playAgain.equalsIgnoreCase("yes")) {
            System.out.println("Let's play Rock, Paper, Scissors!");
            System.out.print("Enter your choice (rock, paper, or scissors): ");
            String userChoice = scanner.nextLine().toLowerCase();

            int computerChoice = random.nextInt(3);  // 0 = rock, 1 = paper, 2 = scissors
            String computerChoiceStr = "";

            switch (computerChoice) {
                case 0:
                    computerChoiceStr = "rock";
                    break;
                case 1:
                    computerChoiceStr = "paper";
                    break;
                case 2:
                    computerChoiceStr = "scissors";
                    break;
            }

            System.out.println("Computer chose: " + computerChoiceStr);

            if (userChoice.equals(computerChoiceStr)) {
                System.out.println("It's a tie!");
            } else if ((userChoice.equals("rock") && computerChoiceStr.equals("scissors")) ||
                    (userChoice.equals("paper") && computerChoiceStr.equals("rock")) ||
                    (userChoice.equals("scissors") && computerChoiceStr.equals("paper"))) {
                System.out.println("You win!");
            } else {
                System.out.println("Computer wins!");
            }

            System.out.print("Would you like to play again? (yes/no): ");
            playAgain = scanner.nextLine().toLowerCase();
        }

        scanner.close();
        System.out.println("Thanks for playing!");
    }
}
