import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Console game = new Console();
        Scanner sc = new Scanner(System.in);
        System.out.println("How many games would you like to play?");
        int Length = sc.nextInt();

        for(int i = 0; i < Length; i++) {
            // Scanner sc = new Scanner(System.in);
            // String input = sc.nextLine();
            // System.out.println("This is the input: " + input);
            game.startGame();
        }
    }
}