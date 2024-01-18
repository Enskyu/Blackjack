import javax.swing.ImageIcon;
import javax.swing.JLabel;

import java.awt.Image;

/**
 * The {@code Card} class represents a playing card in a standard deck.
 * It extends {@link JLabel} to display the card as an image.
 */
public class Card extends JLabel{
    private String suit;
    private String num;

    /**
     * Constructs a new card with the specified suit, number, and visibility.
     *
     * @param suit    the suit of the card (e.g., "Hearts", "Diamonds").
     * @param num     the number or face of the card (e.g., "2", "King", "Ace").
     * @param hidden  true if the card should be initially hidden, false otherwise.
     */
    public Card (String suit, String num, boolean hidden) {
        super();
        this.suit = suit;
        this.num = num;
        ImageIcon image = new ImageIcon(getImagePath());
        Image img = image.getImage();
        Image newimg = img.getScaledInstance(100, 150,Image.SCALE_SMOOTH);
        image = new ImageIcon(newimg);
        this.setIcon(image);
    }

    /**
     * Gets the suit of the card.
     *
     * @return the suit of the card.
     */
    public String getSuit() {
        return suit;
    }

    /**
     * Gets the number or face of the card.
     *
     * @return the number or face of the card.
     */
    public String getNum() {
        return num;
    }

    /**
     * Gets the numerical value of the card.
     *
     * @return the numerical value of the card.
     */
    public int getValue() {
        if (num.equals("A")) {
            return 11;
        } else if (num.equals("J") || num.equals("Q") || num.equals("K")) {
            return 10;
        } else {
            return Integer.parseInt(num);
        }
    }

    /**
     * Returns a string representation of the card.
     *
     * @return a string representation of the card.
     */
    public String toString() {
        return num + " of " + suit;
    }

    /**
     * Gets the file path of the image associated with the card.
     *
     * @return the file path of the image associated with the card.
     */
    public String getImagePath() {
        return "./cards/" + num + "-" + suit.substring(0, 1) + ".png";
    }

    /**
     * Sets the card as hidden, displaying the back of the card.
     * Returns the card itself for method chaining.
     *
     * @return the card itself.
     */

    // Returns a card because it needs to happen between drawing the card and putting the card in the computer's deck.
    public Card setHidden() {
        // Get a icon image of the back.
        ImageIcon image = new ImageIcon("./cards/BACK.png");
        Image img = image.getImage();
        Image newimg = img.getScaledInstance(100, 150,Image.SCALE_SMOOTH);
        // Set the type of it back to a ImageIcon.
        image = new ImageIcon(newimg);
        // Set it as the background of the JLabel.
        this.setIcon(image);
        this.updateUI();
        return this;
    }

    /**
     * Reveals the front of the card by updating its image.
     */
    public void reveal() {
        ImageIcon image = new ImageIcon(getImagePath());
        Image img = image.getImage();
        Image newimg = img.getScaledInstance(100, 150,Image.SCALE_SMOOTH);
        image = new ImageIcon(newimg);
        this.setIcon(image);
    }
}