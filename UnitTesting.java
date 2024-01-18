// import org.junit.Before;
// import org.junit.Test;
// import org.mockito.Mock;
// import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

// import static org.junit.Assert.assertEquals;
// import static org.mockito.Mockito.*;

public class UnitTesting {

    public static void main(String[] args) {
        testAddMoney_InsufficientBalance();
    }

    // @Mock
    // private Button mockButton;

    // @Mock
    // private Main mockFrame;

    private SelectMoney selectMoney;

    // @Before
    // public void setUp() {
    //     MockitoAnnotations.initMocks(this);
    //     selectMoney = new SelectMoney(mockFrame, 500);

    //     // Mock the Button class to simulate user interaction
    //     when(mockButton.getAmount()).thenReturn(100);
    // }

    // @Test
    // public void testAddMoney_PositiveAmount() {
    //     // Mock the coins list to simulate a scenario where user has added a chip
    //     ArrayList<Integer> mockCoins = spy(new ArrayList<>());
    //     when(mockCoins.size()).thenReturn(1);
    //     selectMoney.coins = mockCoins;

    //     // Mock the balance to ensure it's sufficient
    //     selectMoney.balance = 600;

    //     // Call the addMoney method
    //     selectMoney.addMoney(mockButton.getAmount());

    //     // Verify that the bet, coins, and balance have been updated correctly
    //     assertEquals(600, selectMoney.bet);
    //     verify(mockCoins).add(100);
    //     verify(mockButton).changeamt(-100);
    // }

    // @Test
    // public void testAddMoney_NegativeAmount() {
    //     // Mock the coins list to simulate a scenario where user has added a chip
    //     ArrayList<Integer> mockCoins = spy(new ArrayList<>());
    //     when(mockCoins.size()).thenReturn(1);
    //     selectMoney.coins = mockCoins;

    //     // Mock the balance to ensure it's sufficient
    //     selectMoney.balance = 600;

    //     // Call the addMoney method with a negative amount to remove a chip
    //     selectMoney.addMoney(-100);

    //     // Verify that the bet, coins, and balance have been updated correctly
    //     assertEquals(500, selectMoney.bet);
    //     verify(mockCoins).remove(0);
    //     verify(mockButton).changeamt(100);
    // }

    // @Test
    public void testAddMoney_InsufficientBalance() {
        
        SelectMoney sm = new SelectMoney();
        // Mock the balance to ensure it's not sufficient
        selectMoney.balance = 100;

        // Call the addMoney method with a positive amount
        selectMoney.addMoney(mockButton.getAmount());

        // Verify that the method prints the correct message
        assertEquals("Not enough balance to place this bet.", systemOutRule.getLog().trim());
    }
}
