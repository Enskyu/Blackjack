import java.util.ArrayList;
import java.util.Scanner;

public class Computer extends Player{
  private ArrayList<Card> hand;
  public Computer() {
    super("Computer");
    hand = super.getHand();
  }

  public void addCard(Card card) {
    hand.add(card);
  }

  public void showFirst() {
    System.out.println("The first dealer card is " + hand.get(0));;
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

  // public boolean computerAction() {
  //   while (getHandValue() < 17) {
  //     addCard(new Card());
  //   }
  //   return getHandValue() >= 17;
  // }

}