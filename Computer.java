import java.util.ArrayList;
import javax.swing.JPanel;

public class Computer extends Player {
    private ArrayList<Card> hand;
    private JPanel panel;
    public Computer(JPanel p) {
        super("Computer", p);
        hand = super.getHand();
        panel = p;
    }

    public void addCard(Card card) {
        hand.add(card);
        panel.add(hand.get(hand.size() - 1));
        panel.updateUI();
    }

    public void clearHand() {
        hand.clear();
    }

    public void showFirst() {
        System.out.println("The first dealer card is " + hand.get(0));
    }

    public void showHiddenCard() {
        hand.get(0).reveal();
    }

    public int getHandValue() {
        int value = 0;
        int aceCount = 0;
        for (Card card : hand) {
            int cardValue = card.getValue();
            if (cardValue == 11) {
                aceCount++;
            }
            value += cardValue;
        }
        while (value > 21 && aceCount > 0) {
            value -= 10;
            aceCount--;
        }
        return value;
    }

    public boolean isBust() {
        return getHandValue() > 21;
    }

    public boolean isBlackJack() {
        return getHandValue() == 21;
    }

}