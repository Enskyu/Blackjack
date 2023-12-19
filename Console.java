import java.lang.Math;
import java.util.ArrayList;

public class Console {

  //constructor
  //game start message
  //display the two cards the player has and the first dealer card
  //actively show remaining cards in the deck
  //receive the card value / call getcardvalue from other classes
  
    public Console(){
        startMessage();
        displayCards();
    }

    public String startMessage() {
        return "The Blackjack game starts now.";
    }

    public String displayCards() {
        //return two beginning cards
        // show first dealer card
        Card firstCard = Deck.drawCard();
        Card secondCard = Deck.drawCard();
        return "The cards are: " + firstCard + " and " + secondCard;
    }

    public String remainingCards(){
        //should we return the cards after we draw card? so use drawCard method first?
        return "The remaining cards are: " + Deck.getDeck();
    }

    public String displayCardValue(){
      return "You have drawn a " + Deck.getCardValue();
    }
}