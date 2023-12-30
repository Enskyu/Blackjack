
// @Carter for money class
public class Money {
  public int playerMoney = 1000;
  public int pool;

  public int initialBet(int bet) {
    playerMoney -= bet;
    pool += bet;
    // temp return statement
    return 0;
  }

  public int getPool() {
    return pool;
  }

  public void playerLose() {
    playerMoney -= pool;
    pool = 0;
  }

  public void playerWin() {
    playerMoney += (pool * 2);
    pool = 0;
  }

  public void tie() {
    pool = 0;
  }

  public int getBalance() {
    return playerMoney;
  }

  public void BlackJack() {
    playerMoney += (pool * 2.5);
    pool = 0;
      
  }
  
  public void doubleDown() {
    pool += pool;
    
  }
}