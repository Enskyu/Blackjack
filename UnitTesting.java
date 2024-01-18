import javax.swing.*;
import java.awt.*;

public class UnitTesting {

    public static void main(String[] args) {
        testLoseScreen();
    }

    public static void testLoseScreen() {
        Console console = createMockConsole();
        console.loseScreen(); // Call the method you want to test
    }

    private static Console createMockConsole() {
        Main mockFrame = new Main();
        Deck mockDeck = new Deck();
        Money mockMoney = new Money();
        Player mockPlayer = new Player("test");

        return new Console(mockFrame, mockDeck, mockMoney, mockPlayer);
    }
}