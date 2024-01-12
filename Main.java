import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Main extends JFrame{
    private Deck deck;
    private Money money;
    private Player player;
    private Console console;
    private SelectMoney selMon;

    public Main(){
        super("blackjack");
        deck = new Deck(); // Initalize deck
        money = new Money(); // Initalize money
        selMon = new SelectMoney(this, money.getBalance()); // Initalize selector panel for betting
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(600, 500));
        this.setSize(600, 600);
        this.setResizable(true);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    public void startGame(int bet){ // called by button Button in SelectMoney.java ln 35
        this.getContentPane().removeAll(); // Clear all elements from the screen
        this.repaint(); // I have no clue what this does it works, don't touch it.
        if(deck.getDeck().size()<70){ // if the deck size is smaller then 70 then "shuffle" also known as just getting a new deck.
            System.out.println("shuffling");
            deck = new Deck();
        }
        money.initialBet(bet);
        player = new Player("x"); // heh don't worry about this for now. also u don't need this anymore because you can just say "player's hand"
        console = new Console(this, deck, money, player); // Initalize your class wooooo
    }
    public void gameDone(){
        this.addMouseListener(new MouseAdapter() { // Called in method playerAction in Console.java
            public void mousePressed(MouseEvent e){ // Purpose is to detect a click anywhere on the screen and continue by calling the bet money screen up again
                selectMoney(this);
            }
        });
    }
    public void selectMoney(MouseAdapter e){ // called by gameDone method in Main.java ln 38
        this.removeMouseListener(e); // remove the mouse listener to prevent progress of the user being reset. (if the user is in the middle of clicking a bet and the screen resets because of the click listener it's bad)
        this.getContentPane().removeAll();// Clear all the contents of the screen
        this.repaint(); // again I have no idea what this does but it works.
        selMon = new SelectMoney(this, money.getBalance()); // Create a new screen for the betting to go onto.
        this.setVisible(true); // This line always has to go last.
    }

    public static void main(String[] args) {
        Main m = new Main();
    }
}