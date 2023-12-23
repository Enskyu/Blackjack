// https://www.youtube.com/watch?v=GMdgjaDdOjI     youtube tutorial
//https://github.com/ImKennyYip/blackjack-java     cards visuals for later
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

    public String toString() {
        return num + " of " + suit;
    }
}