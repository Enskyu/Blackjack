
public class Money{
    private int playerMoney = 1000;
    private int pool;
    private boolean gameover = false;

    public void initialBet(int bet) {
        pool += bet;
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
        else {
            return false;
        }
    }
}