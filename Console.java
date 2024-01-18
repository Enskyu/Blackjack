import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Console class represents the graphical user interface for the Blackjack game.
 */
public class Console {
    private Main frame; 
    private Deck deck; 
    private Player p1; 
    private Computer computer; 
    private Money money; 

    //window
    int boardWidth = 600;
    int boardHeight = boardWidth;

    int cardWidth = 110; //1:1.4
    int cardHeight = 154;

    // THe main container that Blackjack is going to go onto.
    JPanel gamePanel = new JPanel();

    // The container for containing the buttons at the bottom of the screen.
    JPanel buttonPanel = new JPanel(); 
    JButton hitButton = new JButton("Hit");
    JButton standButton = new JButton("Stand");
    JButton doubleDowButton = new JButton("Double Down");
    // The container for the player's cards.
    JPanel playerPanel = new JPanel();
    // The container for the computer's cards.
    JPanel computerPanel = new JPanel();

    
    /**
     * Constructor for the Console class.
     *
     * @param f         The main frame for the game.
     * @param deck      The deck used in the game.
     * @param money     The money pool for the game.
     * @param player    The player object for the game.
     */
    public Console(Main f, Deck deck, Money money, Player player) {
        frame = f;
        this.deck = deck;
        this.money = money;
        p1 = player;
        computer = new Computer(computerPanel);
        
        // Sets the frame to a border layout, basically elements can be put in quadronts n, s, e, w and center.
        frame.setLayout(new BorderLayout());
        // Get two rows in the game container, top for the dealer, bottom for the player.
        gamePanel.setLayout(new GridLayout(2, 1));
        // Add the game panel to the screen putting it in the center of the screen.
        frame.add(gamePanel, BorderLayout.CENTER);

        // Add the computer's panel into the game panel (it auto goes into top because that's the next avaliable space in the gridlayout).
        gamePanel.add(computerPanel);
        // Give 5 card slots to the computer container.
        computerPanel.setLayout(new GridLayout(0, 5, 10, 10));
        // Set the background of the computers panel to dark green.
        computerPanel.setBackground(new Color(53, 101, 77));
        // Do the same thing but for the player.
        gamePanel.add(playerPanel);
        playerPanel.setLayout(new GridLayout(0, 5, 10, 10)); 
        playerPanel.setBackground(new Color(53, 101, 77)); 
        p1.setPanel(playerPanel); 

        hitButton.setFocusable(false); 
        buttonPanel.add(hitButton); 
        standButton.setFocusable(false);
        buttonPanel.add(standButton); 
        doubleDowButton.setFocusable(false);
        buttonPanel.add(doubleDowButton);
        // Add the buttonpanel container to the bottom of the screen (south quadrant).
        frame.add(buttonPanel, BorderLayout.SOUTH); 

        /*
         * Long explanation of the listeners below
         * 
         * basically the Mouseadapter class has a method called mousePressed that will be called when the mouse is presssed
         * in the code we are overriding it to do what we want it to do 
         * 
         * button1 just means left click,
         * button3 is right click
         * button2 means middle click.
         */

        // Add a mouse listener to the hit button.
        hitButton.addMouseListener(new MouseAdapter() {
            @Override
            // Overwrite the native method in the MouseAdapter class.
            public void mousePressed(MouseEvent e) {
                // Check that the button has been left clicked.
                if (e.getButton() == MouseEvent.BUTTON1) {
                    // Call the playeraction method with the act being left click or 1.
                    playerAction(1);
                }
            }
        });
        standButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e){
                if (e.getButton() == MouseEvent.BUTTON1){
                    playerAction(2);
                }
            }
        });

        doubleDowButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e){
                if (e.getButton() == MouseEvent.BUTTON1){
                    playerAction(0);
                }
            }
        });

        startGame();
    }

    /**
     * Handles player actions based on the given action code.
     *
     * @param act The action code: 0 for Double Down, 1 for Hit, 2 for Stand.
     */
    private void playerAction(int act) {
        // The double down button should only be there when the player has two cards, as soon as the player does an action the button is removed from the button panel.
        buttonPanel.remove(doubleDowButton);
        // Have to remove the button from the screen but updating the interface of the whole container.
        buttonPanel.updateUI();
        if (act == 0) {
            p1.addCard(deck.drawCard());
            money.doubleDown();
        }
        // If Hit has been pressed.
        if (act == 1) {
            // Give the player a card.
            p1.addCard(deck.drawCard());
            if (p1.getHandValue() < 21) {
                // HAS TO RETURN so it dosn't contact the win checks below this line.
                return;
            }
        }
        computer.showHiddenCard();
        while(computer.getHandValue() < 17) {
            computer.addCard(deck.drawCard());
            computer.showHand();
        }
        if (p1.getHandValue() > 21) {
            System.out.println("You lose $" + money.getPool());
            money.playerLose();
            loseScreen();
            frame.gameDone();
        } else if (computer.isBust()) {
            System.out.println("Computer busts. You win $" + money.getPool());
            money.playerWin();
            winScreen();
            frame.gameDone();
        } else if (p1.getHandValue() == 21) {
            System.out.println("Blackjack! You win $" + (money.getPool() * 1.5));
            money.BlackJack();
            blackjackScreen();
            frame.gameDone();
        } else if (p1.getHandValue() > computer.getHandValue()) {
            System.out.println("You win $" + money.getPool());
            money.playerWin();
            winScreen();
            frame.gameDone();
        } else if (p1.getHandValue() <= computer.getHandValue() || p1.getHandValue() > 21) {
            System.out.println("You lose $" + money.getPool());
            money.playerLose();
            loseScreen();
            frame.gameDone();
        }
    }

    /**
     * Starts a new game by initializing player and computer hands.
     */
    public void startGame() {
        if (money.balCheck() == true) {
            System.exit(0);
        }
        p1.clearHand();
        computer.clearHand();
        p1.addCard(deck.drawCard());
        p1.addCard(deck.drawCard());
        computer.addCard(deck.drawCard().setHidden());
        computer.addCard(deck.drawCard());
    }

    /**
     * Displays a screen indicating a loss for the player.
     */
    public void loseScreen() {
        playerPanel.removeAll();
        playerPanel.setLayout(new BorderLayout());
        JLabel l = new JLabel("You lose", JLabel.CENTER);
        l.setForeground(Color.white);
        l.setFont(new Font("Arial", Font.BOLD, 40));
        playerPanel.add(l, BorderLayout.CENTER);
        playerPanel.revalidate();
        playerPanel.updateUI();
    }

    /**
     * Displays a screen indicating a win for the player.
     */
    public void winScreen() {
        playerPanel.removeAll();
        playerPanel.setLayout(new BorderLayout());
        JLabel l = new JLabel("You win!", JLabel.CENTER);
        l.setForeground(Color.white);
        l.setFont(new Font("Arial", Font.BOLD, 40));
        playerPanel.add(l, BorderLayout.CENTER);
        playerPanel.revalidate();
        playerPanel.updateUI();
    }

    /**
     * Displays a screen indicating a Blackjack win for the player.
     */
    public void blackjackScreen() {
        playerPanel.removeAll();
        playerPanel.setLayout(new BorderLayout());
        JLabel l = new JLabel("BLACKJACK!", JLabel.CENTER);
        l.setForeground(Color.white);
        l.setFont(new Font("Arial", Font.BOLD, 40));
        playerPanel.add(l, BorderLayout.CENTER);
        playerPanel.revalidate();
        playerPanel.updateUI();
    }
}