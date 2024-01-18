import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Main extends JFrame {
    private Deck deck;
    private Money money;
    private Player player;
    private Console console;
    private SelectMoney selMon;

    public Main(){
        super("blackjack");
        deck = new Deck(); 
        money = new Money();
        // Initalize selector panel for betting.
        selMon = new SelectMoney(this, money.getBalance()); 
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(600, 500));
        this.setSize(600, 600);
        this.setResizable(true);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void startGame(int bet) { 
        // Clear all elements from the screen.
        this.getContentPane().removeAll(); 
        this.repaint();
        // If the deck size is smaller then 70 then "shuffle" also known as just getting a new deck.
        if (deck.getDeck().size() < 70){ 
            System.out.println("shuffling");
            deck = new Deck();
        }
        money.initialBet(bet);
        player = new Player("Player");
        console = new Console(this, deck, money, player);
    }

    public void gameDone() {
        // Called in method playerAction in Console.java.
        this.addMouseListener(new MouseAdapter() {
            // Purpose is to detect a click anywhere on the screen and continue by calling the bet money screen up again.
            public void mousePressed(MouseEvent e) { 
                selectMoney(this);
            }
        });
    }

    // Called by gameDone method in Main.java ln 38.
    public void selectMoney(MouseAdapter e) {
        // Remove the mouse listener to prevent progress of the user being reset.
        this.removeMouseListener(e);
        // Clear all the contents of the screen.
        this.getContentPane().removeAll();
        this.repaint(); 
        // Create a new screen for the betting to go onto.
        selMon = new SelectMoney(this, money.getBalance());
        this.setVisible(true); 
    }

    public static void main(String[] args) {
        Main m = new Main();
    }
}