import org.junit.Test;
import static org.junit.Assert.*;

public class JUnitTest {
    private static final double VISA_INTEREST = 10.0;
    private static final double MC_INTEREST = 5.0;
    private static final double DISCOVER_INTEREST = 1.0;
    private static final double BALANCE = 100;

    private static final double VISA_BALANCE = ((BALANCE * (VISA_INTEREST / 100))+ BALANCE);
    private static final double MC_BALANCE = ((BALANCE * (MC_INTEREST / 100))+ BALANCE);
    private static final double DISCOVER_BALANCE = ((BALANCE * (DISCOVER_INTEREST / 100))+ BALANCE);

    @Test
    public void testCase1(){
        //create a person with first, and last name as params
        Person testPerson = new Person('Levi','Fogle');

        //add a wallet to the person
        testPerson.addWallet("firstWallet");

        //add a card to the wallet that we just accessed passing in
        //the card name, interest, and current ballance
        testPerson.get('firstWallet').addCard('Visa', VISA_INTEREST, BALANCE);
        testPerson.get('firstWallet').addCard('MC', MC_INTEREST, BALANCE);
        testPerson.get('firstWallet').addCard('Discover', DISCOVER_INTEREST, BALANCE);

        //figure total balance for each person
        double totalBalace = VISA_BALANCE + MC_BALANCE + DISCOVER_BALANCE;

        //make sure each card interest is calculated correctly
        assertTrue(testPerson.get('firstWallet').getCard('Visa').calculateInterest() == VISA_BALANCE);
        assertTrue(testPerson.get('firstWallet').getCard('MC').calculateInterest() == MC_BALANCE);
        assertTrue(testPerson.get('firstWallet').getCard('Discover').calculateInterest() == DISCOVER_BALANCE);

        //now do for the entire person
        assertTrue(testPerson.calculateTotalInterest() == totalBalace);
    }

    @Test
    public void testCase2(){
        //create a person with first, and last name as params
        Person testPerson = new Person('Levi','Fogle');

        //add a wallets to the person
        testPerson.addWallet("firstWallet");
        testPerson.addWallet("secondWallet");

        //add a card to the wallet that we just accessed passing in
        //the card name, interest, and current ballance
        testPerson.get('firstWallet').addCard('Visa', VISA_INTEREST, BALANCE);
        testPerson.get('firstWallet').addCard('Discover', DISCOVER_INTEREST, BALANCE);

        testPerson.get('secondWallet').addCard('MC', MC_INTEREST, BALANCE);

        //figure total balance for each wallet
        double firstWalletBalance = VISA_BALANCE + DISCOVER_BALANCE;
        double secondWalletBalance = MC_BALANCE;

        //make sure each card interest is calculated correctly
        assertTrue(testPerson.addWallet("firstWallet").getCard('Visa').calculateInterest() == VISA_BALANCE);
        assertTrue(testPerson.addWallet("firstWallet").getCard('Discover').calculateInterest() == DISCOVER_BALANCE);

        assertTrue(testPerson.get('secondWallet').getCard('MC').calculateInterest() == MC_BALANCE);

        //make sure each wallet is calculated correctly
        assertTrue(testPerson.getWallet('firstWallet').calculateInterest() == firstWalletBalance);
        assertTrue(testPerson.getWallet('secondWallet').calculateInterest() == secondWalletBalance);
    }

    @Test
    public void testCase3(){
        //create 2 persons with first, and last name as params
        Person firstPerson = new Person('Levi','Fogle');
        Person secondPerson = new Person('John','Smith');

        //add a wallets to the people
        firstPerson.addWallet("firstWallet");
        secondPerson.addWallet("secondWallet");

        //add cards to the wallet that we just accessed passing in
        //the card name, interest, and current ballance
        firstPerson.get('firstWallet').addCard('Visa', VISA_INTEREST, BALANCE);
        firstPerson.get('firstWallet').addCard('MC', MC_INTEREST, BALANCE);

        secondPerson.get('secondWallet').addCard('Visa', VISA_INTEREST, BALANCE);
        secondPerson.get('secondWallet').addCard('MC', MC_INTEREST, BALANCE);

        //figure total balance for each wallet
        double visaMCBalance = VISA_BALANCE + MC_BALANCE;

        //make sure each wallet interest is calculated correctly
        assertTrue(firstPerson.get('firstWallet').calculateInterest() == visaMCBalance);
        assertTrue(secondPerson.get('secondWallet').calculateInterest() == visaMCBalance);

        //now do for the entire person
        assertTrue(firstPerson.calculateTotalInterest() == visaMCBalance);
        assertTrue(secondPerson.calculateTotalInterest() == visaMCBalance);
    }
}