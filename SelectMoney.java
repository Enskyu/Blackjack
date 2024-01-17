import javax.swing.*;
import java.awt.*;

import java.util.ArrayList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * SelectMoney class represents a graphical user interface for selecting and managing betting amounts in a Blackjack game.
 */
public class SelectMoney extends JPanel {
  private Main frame;
  private JLabel bal;
  private ArrayList<Integer> coins;
  private int bet = 100;
  private int balance;
  Button money;
  public boolean gameover = false;
  private JLabel placeBetsLabel;

  /**
   * Constructor for the SelectMoney class.
   *
   * @param f The main frame for the game.
   * @param playerBalance The initial balance of the player.
   */
  public SelectMoney(Main f, int playerBalance) {
    frame = f;
    balance = playerBalance;
    coins = new ArrayList<>(); // Create a array to store the clips that the user wants to bid so that when u add or remove a chip we can keep track of how much to add or remove.
    coins.add(100);

    setLayout(new FlowLayout());

    placeBetsLabel = new JLabel("Place your bets:");
    add(placeBetsLabel);
    Font buttonFont = new Font("Arial", Font.BOLD, 14); // Set font of buttons

    money = new Button(-100, this); //clicking this removes the latest chip they added.
    money.setText(Integer.toString(bet)); // Set the text of that button to the user's bet amount
    money.setFont(buttonFont);
    money.setPreferredSize(new Dimension(60, 20));

    // Change button text color to RED!
    money.setForeground(Color.RED);

    Button[] buttons = {money, new Button(1, this), new Button(5, this),
            new Button(25, this), new Button(50, this), new Button(100, this)};

    for (Button button : buttons) {
        button.setFont(buttonFont);
        button.setPreferredSize(new Dimension(60, 40));
        add(button);
    }

    JButton button = new JButton("deal"); // the button to start the actual game
    button.addMouseListener(new MouseAdapter() { // set up a mouselistener to the start button to make it work
      public void mousePressed(MouseEvent e) {
        frame.startGame(bet); // Call back to main class and start the game
      }
    });
    button.setFont(buttonFont);
    button.setPreferredSize(new Dimension(80, 40));
    this.add(button); // add the start button to the container.
    frame.add(this); // add the whole bet money container to the frame.

    JLabel someText = new JLabel("Your balance remaining: ");
    add(someText);
    bal = new JLabel(Integer.toString(balance-bet)); // Display the user's balance to the user
    this.add(bal); // Add that balance to the screen
  }

  /**
   * Button class represents a custom JButton used for managing betting amounts.
   */
  public class Button extends JButton { 
    private int amt;

    /**
     * Constructor for the Button class.
     *
     * @param amt The amount to change by.
     * @param p   The JPanel to which the button is added.
     */
    public Button(int amt, JPanel p) {
      super(Integer.toString(amt)); // Create a button object with the chamge anount as the text displaied
      this.amt = amt;
      p.add(this); // add this to the bet panel.
      this.addMouseListener(new MouseAdapter() { // Add mouse listener to the button to make it work.
        public void mousePressed(MouseEvent e){
          if(e.getButton() == MouseEvent.BUTTON1) { // check that it is a left click
            addMoney(getAmount()); // add money to the pot.
          } // Somehow the line above this comment works when the amount is changed (thanks to the button that displaies the users total be (SelectMoney.java ln 22))
        }
      });
    }

    /**
     * Get the amount associated with the button.
     *
     * @return The amount associated with the button.
     */
    private int getAmount() { // Only used in the button class that extends JButton (SelectMoney.java ln 39)
      return this.amt;
    }

    /**
     * Change the amount associated with the button.
     *
     * @param newamt The new amount to set.
     */
    public void changeamt(int newamt) { // Change the amount to change by. Again this is only used by the button at SelectMoney.java ln 22. it's the one that takes away chips from the pile in case the user wants to bet lesss
      this.amt = newamt;
    }
  }

  /**
   * Add or remove money from the betting pile based on the specified amount.
   *
   * @param amt The amount to be added or removed.
   */
  public void addMoney(int amt) { // change the money added to the betting pile
    if (amt < 0) { // Check if the user is trying to remove a chip
        if (coins.size() > 0) {
            bet += amt; // remove the chip's value from the bet amount
            money.changeamt(coins.get(coins.size() - 1));
            coins.remove(coins.size() - 1);
            money.changeamt(coins.size() > 0 ? coins.get(coins.size() - 1) * -1 : -100);
        }
    } else {
        if (balance - bet - amt >= 0) { // Check if the user has enough balance to add the chip
            coins.add(amt); // Add the chip that wants to be added to the list of coins
            bet += amt;
            money.changeamt(-1 * amt); // Change the subtract chip button to this chip's value.
            System.out.println(amt);
            System.out.println(bet);
            System.out.println(coins);
        } else {
            System.out.println("Not enough balance to place this bet.");
        }
    }

    money.setText(Integer.toString(bet)); // Update the value of the bet
    bal.setText(Integer.toString(balance - bet)); // Update the balance of the user
  }
}
