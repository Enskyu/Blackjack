import javax.swing.*;
import java.awt.*;

public class UnitTesting {

    public static void main(String[] args) {
        testLoseScreen();
        testWinScreen();
        testBlackjack();
    }

    public static void testLoseScreen() {
        Console console = createMockConsole();
        console.loseScreen();
    }

    public static void testWinScreen() {
        Console console = createMockConsole();
        console.winScreen();
    }

    public static void testBlackjack() {
        Console console = createMockConsole();
        console.blackjackScreen();
    }

    private static Console createMockConsole() {
        Main mockFrame = new Main();
        mockFrame.getContentPane().removeAll();
        mockFrame.repaint();
        Deck mockDeck = new Deck();
        Money mockMoney = new Money();
        Player mockPlayer = new Player("test");

        return new Console(mockFrame, mockDeck, mockMoney, mockPlayer);
    }
}