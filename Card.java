// https://www.youtube.com/watch?v=GMdgjaDdOjI     youtube tutorial
//https://github.com/ImKennyYip/blackjack-java     cards visuals for later

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import java.awt.Image;

/**
 * The {@code Card} class represents a playing card in a standard deck.
 * It extends {@link JLabel} to display the card as an image.
 */
public class Card extends JLabel{ // Because a image in Java swing is literally just a image on a invisible text label/
    private String suit;
    private String num;

    /**
     * Constructs a new card with the specified suit, number, and visibility.
     *
     * @param suit    the suit of the card (e.g., "Hearts", "Diamonds").
     * @param num     the number or face of the card (e.g., "2", "King", "Ace").
     * @param hidden  true if the card should be initially hidden, false otherwise.
     */
    public Card(String suit, String num, boolean hidden) {
        super();
        this.suit = suit;
        this.num = num;
        ImageIcon image = new ImageIcon(getImagePath()); // Look at comments in setHidden method (ln 107)
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
    public String getImagePath() { // You guys didn't use the first letter of the suit rather the whole card, and that wasn't working.
        return "./cards/" + num +"-"+suit.substring(0, 1) + ".png";
    }

    /**
     * Sets the card as hidden, displaying the back of the card.
     * Returns the card itself for method chaining.
     *
     * @return the card itself.
     */
    public Card setHidden(){ // Returns a card because it needs to happen between drawing the card and putting the card in the computer's deck so insted of setting it to a variable I just made sethidden return itself to the call.
        ImageIcon image = new ImageIcon("./cards/BACK.png"); // get a icon image of the back
        Image img = image.getImage(); // Create a image type
        Image newimg = img.getScaledInstance(100, 150,Image.SCALE_SMOOTH); // Create a new image type that is a smaller scale of the previous image
        image = new ImageIcon(newimg); // set the type of it back to a ImageIcon
        this.setIcon(image); // Set it as the background of the JLabel.
        this.updateUI(); // Not nessasary? not sure.
        return this;
    }

    /**
     * Reveals the front of the card by updating its image.
     */
    public void reveal(){ // Look at comment in setHidden method (ln 107)
        ImageIcon image = new ImageIcon(getImagePath());
        Image img = image.getImage();
        Image newimg = img.getScaledInstance(100, 150,Image.SCALE_SMOOTH);
        image = new ImageIcon(newimg);
        this.setIcon(image);
    }
}