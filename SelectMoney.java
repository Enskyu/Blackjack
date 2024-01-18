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
    private Button money;
    private JLabel placeBetsLabel;
    public JPanel playerPanel;

    /**
     * Constructor for the SelectMoney class.
     *
     * @param f The main frame for the game.
     * @param playerBalance The initial balance of the player.
     */
    public SelectMoney(Main f, int playerBalance) {
        frame = f;
        balance = playerBalance;
        // Create a array to store the clips that the user wants to bid so that when u add or remove a chip we can keep track of how much to add or remove.
        coins = new ArrayList<>(); 
        coins.add(100);

        setLayout(new FlowLayout());

        placeBetsLabel = new JLabel("Place your bets:");
        add(placeBetsLabel);
        // Set font of buttons.
        Font buttonFont = new Font("Arial", Font.BOLD, 14);

        // Clicking this removes the latest chip they added.
        money = new Button(-100, this); 
        // Set the text of that button to the user's bet amount.
        money.setText(Integer.toString(bet));
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

        // The button to start the actual game.
        JButton button = new JButton("deal");
        // Set up a mouselistener to the start button to make it work.
        button.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                // Call back to main class and start the game.
                frame.startGame(bet);
            }
        });
        button.setFont(buttonFont);
        button.setPreferredSize(new Dimension(80, 40));
        // Add the start button to the container.
        this.add(button);
        // Add the whole bet money container to the frame.
        frame.add(this);

        JLabel someText = new JLabel("Your balance remaining: ");
        add(someText);
        // Display the user's balance to the user.
        bal = new JLabel(Integer.toString(balance-bet));
        // Add that balance to the screen.
        this.add(bal);
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
         * @param p     The JPanel to which the button is added.
         */
        public Button(int amt, JPanel p) {
            // Create a button object with the chamge anount as the text displayed.
            super(Integer.toString(amt)); 
            this.amt = amt;
            // Add this to the bet panel.
            p.add(this);
            // Add mouse listener to the button to make it work.
            this.addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent e) {
                    // Check that it is a left click.
                    if(e.getButton() == MouseEvent.BUTTON1) {
                        // Add money to the pot.
                        addMoney(getAmount()); 
                    }
                }
            });
        }

        /**
         * Get the amount associated with the button.
         *
         * @return The amount associated with the button.
         */
        // Only used in the button class that extends JButton (SelectMoney.java ln 39)
        private int getAmount() {
            return this.amt;
        }

        /**
         * Change the amount associated with the button.
         *
         * @param newamt The new amount to set.
         */
        // Change the amount to change by.
        public void changeamt(int newamt) { 
            this.amt = newamt;
        }
    }

    /**
     * Add or remove money from the betting pile based on the specified amount.
     *
     * @param amt The amount to be added or removed.
     */
    // Change the money added to the betting pile.
    public void addMoney(int amt) {
        // Check if the user is trying to remove a chip.
        if (amt < 0) { 
            if (coins.size() > 0) {
                // Remove the chip's value from the bet amount.
                bet += amt; 
                money.changeamt(coins.get(coins.size() - 1));
                coins.remove(coins.size() - 1);
                money.changeamt(coins.size() > 0 ? coins.get(coins.size() - 1) * -1 : -100);
            }
        } else {
            // Check if the user has enough balance to add the chip.
            if (balance - bet - amt >= 0) {
                // Add the chip that wants to be added to the list of coins.
                coins.add(amt); 
                bet += amt;
                // Change the subtract chip button to this chip's value.
                money.changeamt(-1 * amt); 
                System.out.println(amt);
                System.out.println(bet);
                System.out.println(coins);
            } else {
                    System.out.println("Not enough balance to place this bet.");
            }
        }

        // Update the value of the bet.
        money.setText(Integer.toString(bet));
        // Update the balance of the user.
        bal.setText(Integer.toString(balance - bet));
    }
}
