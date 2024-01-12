import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Console{
  private Main frame; // The frame for the game to go onto. Remember that Main extends JFrame
  private Deck deck; // The deck that this game is using (a deck can be used in multiple games of blackjack and is given by the constructor ln 34)
  private Player p1; // The player object for this game also given through the constructor ln 34
  private Computer computer; // The computer object for this game, intalized ln 39
  private Money money; // The money (or I like to call it the pool of this class given through constructor in ln 34)
  //constructor
  //game start message
  //display the two cards the player has and the first dealer card
  //actively show remaining cards in the deck
  //receive the card value / call getcardvalue from other classes

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

  
  public Console(Main f, Deck deck, Money money, Player player){
    frame = f;
    this.deck = deck;
    this.money = money;
    p1 = player;
    computer = new Computer(computerPanel);

    frame.setLayout(new BorderLayout()); // Sets the frame to a border layout, basically elements can be put in quadronts n, s, e, w and center
    gamePanel.setLayout(new GridLayout(2, 1)); // Get two rows in the game container, top for the dealer, bottom for the player.
    frame.add(gamePanel, BorderLayout.CENTER); // Add the game panel to the screen putting it in the center of the screen

    gamePanel.add(computerPanel); // Add the computer's panel into the game panel (it auto goes into top because taht's the next avaliable space in the gridlayout)
    computerPanel.setLayout(new GridLayout(0, 5, 10, 10)); // Give 5 card slots to the computer container
    computerPanel.setBackground(new Color(53, 101, 77)); // Set the background of the computers panel to dark green
    gamePanel.add(playerPanel); // Add the computer's panel to the game container
    playerPanel.setLayout(new GridLayout(0, 5, 10, 10)); // give 5 card slots to the player's container
    playerPanel.setBackground(new Color(53, 101, 77)); // Set the background of the computer's panel to dark green
    p1.setPanel(playerPanel); // Add the player panel to the game container.

    hitButton.setFocusable(false); // I have no idea what this does and it dosn't seem to make a diff anyways
    buttonPanel.add(hitButton); // Add this button to the buttonpanel container.
    standButton.setFocusable(false);
    buttonPanel.add(standButton); // Add this button to the buttonpanel container.
    doubleDowButton.setFocusable(false);
    buttonPanel.add(doubleDowButton); // Add this button to the buttonpanel container.
    frame.add(buttonPanel, BorderLayout.SOUTH); // Add the buttonpanel container to the bottom of the screen (south quadrant)

    /*
     * Long explination of the listeners below
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
        if(e.getButton() == MouseEvent.BUTTON1){ // Check that the button has been leftclicked
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

  private void playerAction(int act){
    buttonPanel.remove(doubleDowButton); // the double down button should only be there when the player has two cards, as soon as the player does an action the button is removed from the button panel
    buttonPanel.updateUI(); // Have to remove the button from the screen but updating the interface of the whole container
    if(act == 0){ // if double down button has been pressed
      // do stuff
      return; // HAS TO RETURN so it doesn't contact the win checks below this line
    }
    if(act == 1){ // if Hit has been pressed
      p1.addCard(deck.drawCard()); // Give the player a card
      if(p1.getHandValue() < 21){
        return; // HAS TO RETURN so it dosn't contact the win checks below this line
      }
    }// If it wasn't those two methods it has to be stand.
    computer.showHiddenCard();
    while(computer.getHandValue() < 17){
      computer.addCard(deck.drawCard());
      computer.showHand();
    }
    if(p1.getHandValue() > 21){
      System.out.println("You lose $" + money.getPool());
      money.playerLose();
      frame.loseScreen();
      frame.gameDone();
    } else if(computer.isBust()){
      System.out.println("Computer busts. You win $" + money.getPool());
      money.playerWin();
      frame.loseScreen();
      frame.gameDone();
    } else if (p1.getHandValue() == 21) {
      System.out.println("Blackjack! You win $" + (money.getPool() * 1.5));
      money.BlackJack();
      frame.loseScreen();
      frame.gameDone();
    } else if(p1.getHandValue() > computer.getHandValue()){
      System.out.println("You win $" + money.getPool());
      money.playerWin();
      frame.loseScreen();
      frame.gameDone();
    } else if (p1.getHandValue() <= computer.getHandValue() || p1.getHandValue() > 21){
      System.out.println("You lose $" + money.getPool());
      money.playerLose();
      frame.loseScreen();
      frame.gameDone();
    }
  }

  public void startGame(){
    if (money.balCheck() == true) {
      System.exit(0);
    }
    p1.clearHand();
    computer.clearHand(); // I don't think this is nessasary because the computer has just been initalized and dosn't have a hand
    p1.addCard(deck.drawCard());
    p1.addCard(deck.drawCard());
    computer.addCard(deck.drawCard().setHidden()); // See the sethidden method in Card.java ln 51
    computer.addCard(deck.drawCard());
  }

  public void loseScreen(){
        
  }

}