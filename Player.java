import java.util.ArrayList;
import java.util.Scanner;
public class Player {
  Scanner sc = new Scanner(System.in);
  private ArrayList<Card> hand;
  private String name;

  public Player(String name) {
    this.name = name;
    hand = new ArrayList<>();
  }
  
  public void addCard(Card card) {
    hand.add(card); 
  }

  public void showHand() {
    System.out.println(name + "'s current hand: ");
    for (Card card : hand) {
      System.out.print(card.toString() + ", ");
    }
    System.out.println();
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

  public boolean isBlackjack() {
    return getHandValue() == 21 && hand.size() == 2;
  }

  //temp drawCard method
  public String drawCard(){
    return "";
  }

  public ArrayList<Card> getHand(){
    return hand;
  }

}
