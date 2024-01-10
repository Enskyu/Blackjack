import java.util.Scanner;
public class Console {
    private Deck deck;
    private Player p1;
    private Computer computer;
    private Money money;
    private Scanner sc;
    private String name;


  //constructor
  //game start message
  //display the two cards the player has and the first dealer card
  //actively show remaining cards in the deck
  //receive the card value / call getcardvalue from other classes

    public Console(){
        deck = new Deck();
        computer = new Computer();
        money = new Money();
        System.out.println("What is your name?");
        sc = new Scanner(System.in);
        name = sc.nextLine();
        startMessage();
        initialBetMessage();
        displayCards();
        startGame();
    }

    public void startMessage(){
        System.out.println("The Blackjack game starts now.");
    }

    public void startGame(){
        System.out.println("You have $" + String.valueOf(money.getBalance()));
        System.out.println("------------------------------------");
        p1 = new Player(name);
        p1.addCard(deck.drawCard());
        p1.addCard(deck.drawCard());
        p1.showHand();
        computer.addCard(deck.drawCard());
        computer.showHand();
        computer.addCard(deck.drawCard());
        System.out.println("would you like to double down?");
        String dd = sc.nextLine();
        //TODO: finish writing doubleDown method
        if (dd.equals("yes")){
          money.doubleDown();
        }
       
        // computer shows first card
        // for loop conditionals
        //if player has less than 21, player can hit or stand (do nothing and wait for computer)
        while(p1.getHandValue() < 21){
            System.out.println("------------------------------------");
            System.out.println("Would you like to hit or stand?");
            String action = sc.nextLine();
            // if player hits, add card to hand and show hand
            if (action.equals("hit")){
                p1.addCard(deck.drawCard());
                p1.showHand();
                computer.addCard(deck.drawCard());
                computer.showHand();
            } else if (action.equals("stand")){
                System.out.println("You chose to stand.");
                // computer.showHand();
                // while(computer.getHandValue() < 17){
                //     computer.addCard(deck.drawCard());
                //     computer.showHand();
                // }
                break;
            }
        }
        // if player stands, computer draws AND show second card
        computer.showHand();
        while(computer.getHandValue() < 17){
            computer.addCard(deck.drawCard());
            computer.showHand();
        }
        if(p1.getHandValue() > 21){
            System.out.println("You busted. Try again next time.");
            money.playerLose();
        } else if(computer.isBust()){
            System.out.println("Computer busts. You win!");
            money.playerWin();
        } else if (p1.getHandValue() == 21) {
            System.out.println("Blackjack!");
            money.BlackJack();
        } else if(p1.getHandValue() > computer.getHandValue()){
            System.out.println("You win!");
            money.playerWin();
        } else if (p1.getHandValue() <= computer.getHandValue() || p1.getHandValue() > 21){
            System.out.println("You lose");
            money.playerLose();
        }
    }

    public void initialBetMessage() {
        System.out.println("Enter your initial bet");
        int value;
        value = sc.nextInt();
        sc.nextLine();
        money.initialBet(value);
    }

    public String displayCards() { 
      return "";
    }

    public String remainingCards(){
        //should we return the cards after we draw card? so use drawCard method first?
        return "The remaining cards are: " + deck.getDeck();
    }

    public String displayCardValue(Player player){
        // Player p1 = new Player();
        return "You have drawn a " + player.getHandValue();
    }
}