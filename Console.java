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

  JPanel gamePanel = new JPanel(); // THe main container that blackjack is going to go onto.

  JPanel buttonPanel = new JPanel(); // The container for containing the buttons at the bottom of the screen
  JButton hitButton = new JButton("Hit"); // The hit button
  JButton standButton = new JButton("Stand"); // The stand button
  JButton doubleDowButton = new JButton("Double Down"); // the double down button
  JPanel playerPanel = new JPanel(); // The container for the player's cards
  JPanel computerPanel = new JPanel(); // The container for the computer's cards

  
  /**
   * Constructor for the Console class.
   *
   * @param f      The main frame for the game.
   * @param deck   The deck used in the game.
   * @param money  The money pool for the game.
   * @param player The player object for the game.
   */
  public Console(Main f, Deck deck, Money money, Player player) {
    frame = f;
    this.deck = deck;
    this.money = money;
    p1 = player;
    computer = new Computer(computerPanel);
    
    // Sets the frame to a border layout, basically elements can be put in quadronts n, s, e, w and center
    frame.setLayout(new BorderLayout()); 
    gamePanel.setLayout(new GridLayout(2, 1)); // Get two rows in the game container, top for the dealer, bottom for the player.
    frame.add(gamePanel, BorderLayout.CENTER); // Add the game panel to the screen putting it in the center of the screen

    gamePanel.add(computerPanel); // Add the computer's panel into the game panel (it auto goes into top because that's the next avaliable space in the gridlayout)
    computerPanel.setLayout(new GridLayout(0, 5, 10, 10)); // Give 5 card slots to the computer container
    computerPanel.setBackground(new Color(53, 101, 77)); // Set the background of the computers panel to dark green
    // Do the same thing but for the player
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
    frame.add(buttonPanel, BorderLayout.SOUTH); // Add the buttonpanel container to the bottom of the screen (south quadrant)

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

    hitButton.addMouseListener(new MouseAdapter() { // Add a mouse listener to the hit button 
      @Override
      public void mousePressed(MouseEvent e){ // Overwrite the native method in the MouseAdapter class
        if(e.getButton() == MouseEvent.BUTTON1){ // Check that the button has been left clicked
          playerAction(1); // Call the playeraction method with the act being left click or 1
        }
      }
    });
    standButton.addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e){
        if(e.getButton() == MouseEvent.BUTTON1){
          playerAction(2);
        }
      }
    });

    doubleDowButton.addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e){
        if(e.getButton() == MouseEvent.BUTTON1){
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
    buttonPanel.remove(doubleDowButton); // the double down button should only be there when the player has two cards, as soon as the player does an action the button is removed from the button panel
    buttonPanel.updateUI(); // Have to remove the button from the screen but updating the interface of the whole container
    if(act == 0) {
      return; // HAS TO RETURN so it doesn't contact the win checks below this line
    }
    if(act == 1) { // if Hit has been pressed
      p1.addCard(deck.drawCard()); // Give the player a card
      if(p1.getHandValue() < 21) {
        return; // HAS TO RETURN so it dosn't contact the win checks below this line
      }
    }
    computer.showHiddenCard();
    while(computer.getHandValue() < 17) {
      computer.addCard(deck.drawCard());
      computer.showHand();
    }
    if(p1.getHandValue() > 21) {
      System.out.println("You lose $" + money.getPool());
      money.playerLose();
      loseScreen();
      frame.gameDone();
    } else if(computer.isBust()) {
      System.out.println("Computer busts. You win $" + money.getPool());
      money.playerWin();
      winScreen();
      frame.gameDone();
    } else if (p1.getHandValue() == 21) {
      System.out.println("Blackjack! You win $" + (money.getPool() * 1.5));
      money.BlackJack();
      blackjackScreen();
      frame.gameDone();
    } else if(p1.getHandValue() > computer.getHandValue()) {
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