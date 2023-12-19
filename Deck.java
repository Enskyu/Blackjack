import java.util.Collections;
import java.util.ArrayList;

public class Deck{
    private static ArrayList<Card> cards;

    public Deck() {
        initializeDeck();
        shuffle();
    }

    private void initializeDeck() {
        cards = new ArrayList<Card>();
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        String[] nums = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
      
        for (String suit : suits){
            for (String num : nums){
              cards.add(new Card(suit, num));
            }
        }
    }

    public static ArrayList<Card> getDeck(){
        return cards;
    }
    
    public void shuffle() {
        Collections.shuffle(cards);
    }
    
    public static Card drawCard() {
        return cards.remove(0);
    }
}
