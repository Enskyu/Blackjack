import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("How many games would you like to play?");
        int length = sc.nextInt();
        sc.nextLine();
        Console game = new Console();
        
        for(int i = 0; i < length-1; i++) {
            game.startGame();
        }
    }
}