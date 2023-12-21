import java.util.Scanner;
public class Console {

  //constructor
  //game start message
  //display the two cards the player has and the first dealer card
  //actively show remaining cards in the deck
  //receive the card value / call getcardvalue from other classes

    public Console(){
        startMessage();
        //initialBetMessage()
        //scan for bet
        displayCards();
    }

    public void startGame(){
        startMessage();
        Player p1 = new Player();
        Deck deck = new Deck();
        Computer computer = new Computer();
        Money money = new Money();
        // Start scanner
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your bet:");
        int bet = sc.nextInt();
        money.initialBet(bet);
        p1.addCard(drawCard());
        p1.addCard(drawCard());
        p1.showHand();
        System.out.println("would you like to double down?");
        String dd = sc.nextLine();
        if (dd == "yes"){
          money.doubleDown();
        }
       
        // computer shows first card
        // for loop conditionals
        //if player has less than 21, player can hit or stand (do nothing and wait for computer)
        while(p1.getHandValue() < 21){
            System.out.println("Would you like to hit or stand?");
            String action = sc.nextLine();
            // if player hits, add card to hand and show hand
            if (action.equals("hit")){
                p1.addCard(drawCard());
                p1.showHand();
            } else if (action.equals("stand")){
                System.out.println("You chose to stand.");
                break;
            }
        }
        // if player stands, computer draws AND show second card
        while(computer.getHandValue() < 21){
            computer.showHand();
             //computer hits until they have at least 17
            if(computer.getHandValue() < 17){
                computer.addCard(drawCard());
                computer.showHand();
                // compares player and computer hand values  
                // if player value > computer, player wins       GAME OVER
                // if player value <= computer, player loses      GAME OVER
            }
            if(computer.isBust()){
                System.out.println("Computer busts. You win!");
                money.win();
            }
            if(p1.getHandValue() > computer.getHandValue()){
              System.out.println("You win!");
              money.playerWin();
            } else if (p1.getHandValue() <= computer.getHandValue()){
              System.out.println("You lose");
              money.playerLose();
            }
            
        }
        //if player has blackjack (21), player wins
        if (p1.getHandValue() == 21){
            System.out.println("Blackjack!");
            money.BlackJack();
        //if player busts, player loses     GAME OVER
        } else if (p1.getHandValue() > 21){
            System.out.println("Bust! You lose.");
            money.playerLose();
        } else if (computer.getHandValue() > 21){
            //if computer has more than 21, player wins        GAME OVER
            System.out.println("Computer busts! You Win!!! :)");
            money.playerWin();
        }

    }

    public static void startMessage() {
        System.out.println("The Blackjack game starts now.");
    }

    public static void initialBetMessage() {
        System.out.println("Enter your initial bet");
        Scanner sc = new Scanner(System.in);
        int value = sc.nextInt();
        sc.close();

    }

    public String displayCards() { 
      return "";
    }

    public String remainingCards(){
        //should we return the cards after we draw card? so use drawCard method first?
        return "The remaining cards are: " + Deck.getDeck();
    }

    public String displayCardValue(Player player){
        // Player p1 = new Player();
        return "You have drawn a " + player.getHandValue();
    }
}