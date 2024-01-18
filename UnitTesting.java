import javax.swing.*;
import java.awt.*;

public class UnitTesting {

    public static void main(String[] args) {
        testLoseScreen();
    }

    public static void testLoseScreen() {  
        Main mockFrame = new Main();
        Deck mockDeck = new Deck();
        Money mockMoney = new Money();
        Player mockPlayer = new Player("test");

        Console console = new Console(mockFrame, mockDeck, mockMoney, mockPlayer);
        mockFrame.getContentPane().removeAll();
        mockFrame.repaint();

        console.loseScreen(); // Call the method you want to test
        mockFrame.setVisible(true);
    }
}