import java.util.Scanner;
import java.util.Random;

public class example {
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        String play = "yes";

        while (play.equals("yes")) {
            Random cello = new Random();
            int celloNum = cello.nextInt(100) + 1;
            System.out.println("The number to guess is: " + celloNum); // For debugging purposes
            int guess = 0;
            int tries = 0;

            while (guess != celloNum) {
                System.out.print("Enter your guess: ");
                guess = reader.nextInt();
                tries++;

                if (guess > celloNum) {
                    System.out.println("Your guess is too high, try again.");
                } else if (guess < celloNum) {
                    System.out.println("Your guess is too low, try again.");
                } else {
                    System.out.println("Awesome! Your guess is good.");
                    System.out.println("It only took you " + tries + " tries!");
                }
            }

            System.out.print("Would you like to play again? (yes/no): ");
            play = reader.next().toLowerCase();
        }
        reader.close();
    }
}
