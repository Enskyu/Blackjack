public class Card {
    private String suit;
    private String num;

    public Card(String suit, String num) {
        this.suit = suit;
        this.num = num;
    }

    public String getSuit() {
        return suit;
    }

    public String getNum() {
        return num;
    }

    public int getValue() {
        if (num.equals("A")) {
            return 11;
        } else if (num.equals("J") || num.equals("Q") || num.equals("K")) {
            return 10;
        } else {
            return Integer.parseInt(num);
        }
    }

    public int fixAceValue(int currentHandValue) {
        if (num.equals("A") && currentHandValue > 21) {
            return 1; // Change the value of Ace from 11 to 1
        } else {
            return getValue();
        }
    }
  
}