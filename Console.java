import java.util.Scanner;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Console {
    private Deck deck;
    private Player p1;
    private Computer computer;
    private Money money;
    private Scanner sc;
  //constructor
  //game start message
  //display the two cards the player has and the first dealer card
  //actively show remaining cards in the deck
  //receive the card value / call getcardvalue from other classes

    //window
    int boardWidth = 600;
    int boardHeight = boardWidth;

    int cardWidth = 110; //1:1.4
    int cardHeight = 154;
  
    JFrame frame = new JFrame("Black Jack");
    JPanel gamePanel = new JPanel(){

        
        @Override
        public void paintComponent(Graphics g){
            super.paintComponent(g);
    
            try {
                //draw hidden card
                Image hiddenCardImg = new ImageIcon(getClass().getResource("./cards/BACK.png")).getImage();
                g.drawImage(hiddenCardImg, 20, 20, cardWidth, cardHeight, null);
    
                //draw dealer's card
                for(int i = 0; i < computer.getHand().size(); i++){
                    Card card = computer.get(i);
                    Image cardImg = new ImageIcon(getClass().getResource(card.getImagePath())).getImage();
                    g.drawImage(cardImg, cardWidth + 25 + (cardWidth + 5)*i, 20, cardWidth, cardHeight, null);
                }
            } catch (Exception e){
                e.printStackTrace();
            }
    
              
        }
    };
    JPanel buttonPanel = new JPanel();
    JButton hitButton = new JButton("Hit");
    JButton standButton = new JButton("Stand");
  
    public Console(){
        deck = new Deck();
        computer = new Computer();
        money = new Money();
        sc = new Scanner(System.in);
      
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(boardWidth, boardHeight);
        frame.setVisible(true);
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);

        gamePanel.setLayout(new BorderLayout());
        gamePanel.setBackground(new Color(53, 101, 77));
        frame.add(gamePanel);

        hitButton.setFocusable(false);
        buttonPanel.add(hitButton);
        standButton.setFocusable(false);
        buttonPanel.add(standButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);
      
        startMessage();
        initialBetMessage();
        displayCards();
        startGame();

    }

    public void startMessage(){
      
        System.out.println("The Blackjack game starts now.");
        System.out.println("What is your name?");
        String name = sc.nextLine();
        p1 = new Player(name);
        System.out.println("Welcome " + name + "! you have $" + String.valueOf(money.getBalance()));
    }

    public void startGame(){
        if (money.balCheck() == true) {
          System.exit(0);
        }
        p1.clearHand();
        computer.clearHand();
        System.out.println("------------------------------------");
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
                computer.showHand();
                computer.addCard(deck.drawCard());
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
            System.out.println("You lose $" + money.getPool());
            money.playerLose();
        } else if(computer.isBust()){
            System.out.println("Computer busts. You win $" + money.getPool());
            money.playerWin();
        } else if (p1.getHandValue() == 21) {
            System.out.println("Blackjack! You win $" + (money.getPool() * 1.5));
            money.BlackJack();
        } else if(p1.getHandValue() > computer.getHandValue()){
            System.out.println("You win $" + money.getPool());
            money.playerWin();
        } else if (p1.getHandValue() <= computer.getHandValue() || p1.getHandValue() > 21){
            System.out.println("You lose $" + money.getPool());
            money.playerLose();
        }
      System.out.println("You now have $" + String.valueOf(money.getBalance()));
    }

    public void initialBetMessage() {
        System.out.println("Enter your initial bet");
        int value;
        value = sc.nextInt();
        sc.nextLine();
        if (money.checkBet(value) == false) {
          money.initialBet(value);
        }
        else {
          System.out.println("You cannot afford this bet. Enter a new bet");
          initialBetMessage();
        }
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