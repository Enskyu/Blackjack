
// @Carter for money class
public class Money {
  public int playerMoney = 1000;
  public int pool;
  public boolean gameover = false;

  public int initialBet(int bet) {
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
    playerMoney += pool;
    pool = 0;
  }

  public void tie() {
    pool = 0;
  }

  public int getBalance() {
    return playerMoney;
  }

  public void BlackJack() {
    playerMoney += (pool * 1.5);
    pool = 0;

  }

  public void doubleDown() {
    pool += pool;

  }

  public boolean balCheck() {
    if (playerMoney <= 0) {
      System.out.println("You have run out of money. Game over");
      return true;
    }
    else { return false;}
  }

  public boolean checkBet(int playerMoney) {
    if (pool > playerMoney) {
      pool = 0;
      return true;
      
    }
    else { return false;}
  }
}