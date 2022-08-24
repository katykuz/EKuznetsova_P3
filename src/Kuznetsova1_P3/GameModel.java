/*
 * Ekaterina Kuznetsova
 * CPSC 5002, Seattle University
 * This is free and unencumbered software released into the public domain.
 */
package Kuznetsova1_P3;
import java.util.*;

/**
 * The GameModel class is a blueprint for a specific multiplayer card game
 * where players receive 7 random cards each, compare the value of their to
 * a card in a central 'discard' stack of cards, and either get more cards or
 * no cards from a 'deal' stack of cards depending on the result of the 
 * comparison. The first player to get to zero cards wins the game. 
 * This class simulates the different components and actions  of the game with
 * methods - including the deal stack, discard stack, passing cards to players,
 * comparing cards, discarding cards, and getting more cards, just to name a
 * few.
 *
 * @author Ekaterina Kuznetsova
 * @version 1.0
 */
public class GameModel {
    
    //private fields:
    //number of players
    private final int NUMBER_OF_PLAYERS;
    private final int NUM_CARDS_IN_DECK;
    private final int NUM_CARDS_PER_PLAYER;
    private int PlayerVal;
    //private ArrayList<Integer> playerVal;
    private ArrayList<Queue<Integer>> playerCards;
    private ArrayList<Integer> cards;
    //create stack objects for deck of cards and discard stack
    private Stack<Integer> dealStack;
    private Stack<Integer> discardStack;


    /**
     * No-argument constructor initializes the necessary variables for the game.
     */
    public GameModel() {
        //initialize number of players
        NUMBER_OF_PLAYERS = 2;
        NUM_CARDS_IN_DECK = 52;
        NUM_CARDS_PER_PLAYER = 7;

        //initialize the playerCards ArrayList by setting the initial capacity
        //and then adding a queue to each index of the ArrayList
        playerCards = new ArrayList<Queue<Integer>>(NUMBER_OF_PLAYERS);

        //initialize the ArrayList with the total number of cards
        cards = new ArrayList<>(NUM_CARDS_IN_DECK);

        //initialize the deck and discard stacks
        dealStack = new Stack();
        discardStack = new Stack();

        //call method to allocate all cards
        allocateCards();
    }
    
    
    /**
     * The allocateCards method sets up all the cards to be able to begi the
     * game; it calls a method to create and shuffle the deal Stack; puts
     * 7 cards into each players' queues; places one starting card into the
     * discard stack from the deal stack.
     */
    public void allocateCards() {

        //call method to add cards to the deck Stack
        addShuffleAndPushDeck();

        //put a queue per player into the ArrayList of queues
        for (int i = 0; i < NUMBER_OF_PLAYERS; ++i) {
            playerCards.add(new Queue<Integer>());
        }

        //put 7 cards into each players' queue
        for (int i = 0; i < NUMBER_OF_PLAYERS; i++) {
            for (int j = 0; j < NUM_CARDS_PER_PLAYER; j++)
                playerCards.get(i).enqueue(dealStack.pop());
        }

        //pop card off deckStack and push it onto discardStack
        discardStack.push(dealStack.pop());

    }


    /**
     * The addShuffleAndPushDeck method adds 'cards' into an integer ArrayList
     * in an incrementing order; then the Shuffle method provided randomizes
     * the order of the cards in the ArrayList; the ArrayList of cards is then
     * pushed into the Deal Stack.
     *
     * Shuffles the cards using the
     * <a href="https://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle">
     * Fisher-Yates algorithm</a>
     */
    private void addShuffleAndPushDeck() {

        //add 52 cards to the ArrayList
        for (int i = 1; i <= NUM_CARDS_IN_DECK; i++) {
            cards.add(i);
        }

        //Shuffle algorithm provided randomizes the cards in ArrayList
        Random rand = new Random();
        for (int i = cards.size(); i > 1; i--) {
            int j = rand.nextInt(i);
            int temp = cards.get(i - 1);
            cards.set(i - 1, cards.get(j));
            cards.set(j, temp);
        }

        //Traverse the ArrayList and push each card to the dealStack
        for (int i = 0; i < cards.size(); i++) {
            dealStack.push(cards.get(i));
        }
    }


    /**
     * The userTurnPromptFormatted method returns a string that states which
     * player's turn it is.
     *
     * @param playerNum integer variable representing the player
     * @return  a string statement
     */
    public String userTurnPromptFormatted(int playerNum) {

        //return string with player number to differentiate players
        return "Player " + (playerNum + 1) + ", it is your turn. Cards: ";

    }


    /**
     * The printPlayerQueue method  takes in a number that
     * represents the current player and returns the string representation of
     * the player's queue.
     *
     * @param playerNum integer variable representing player
     * @return String representation of queue
     */
    public String printPlayerQueue(int playerNum) {

        //return player's current queue in string format
        return playerCards.get(playerNum).toString();
    }


    /**
     * peekAtDiscardStack method returns the string value
     * of the top card from the discard Stack using the peek method.
     *
     * @return String representing the card value from discard Stack
     */
    public String peekAtDiscardStack() {

        //return the string representation of the top card in the discard stack
        return String.valueOf(discardStack.peek());
    }

    /**
     * The getPlayerHeadCard method is a 'getter' and returns the value at the
     * head of the specific player's queue.
     *
     * @param playerNum integer value representing the player number
     * @return integer value of head of player queue
     */
    public String getPlayerHeadCard(int playerNum) {
        //access the player's head card
        return String.valueOf(playerCards.get(playerNum).peek());
    }

    /**
     * cardComparison method compares the current player's head card to the top
     * card on the discard stack and allocates the necessary number of cards
     * based on the comparison; the method also returns a string stating
     * the results of the comparison.
     *
     * @param playerNum     integer value representing the current player
     * @return a String variable stating the result of the comparison
     */
    public String cardComparison(int playerNum) {

        //if the player card is greater than discard card
        if (playerCards.get(playerNum).peek() > discardStack.peek()) {

            //put the played card into the discard Stack from the player's queue
            discardStack.push(playerCards.get(playerNum).dequeue());

            //return a message to user with comparison result
            return "Your card is HIGHER, your turn is over.";
        }

        //if the player card is smaller than discard card
        else if (playerCards.get(playerNum).peek() < discardStack.peek()) {

            //check if the stack needs to be refilled
            checkDealStackNeedForRefill();

            //take card from the deal Stack and put onto player queue
            playerCards.get(playerNum).enqueue(dealStack.pop());

            if (!checkTie()) {

                //check if the stack needs to be refilled
                checkDealStackNeedForRefill();

                //take card from the deal Stack and put onto player queue
                playerCards.get(playerNum).enqueue(dealStack.pop());

                //put the played card into the discard Stack from the
                // player's queue
                discardStack.push(playerCards.get(playerNum).dequeue());

                //return a message to user with comparison result
                return "Your card is LOWER, pick up 2 cards.";
            }
            else
                return "There's no cards left to play! Game is tied.";
        }

        //if the player and discard cards are equal
        else {

            //check if the stack needs to be refilled
            checkDealStackNeedForRefill();

            //take one cards from the deal Stack and put onto player queue
            playerCards.get(playerNum).enqueue(dealStack.pop());

            //put the played card into the discard Stack from the player's queue
            discardStack.push(playerCards.get(playerNum).dequeue());

            //return a message to user with comparison result
            return "Your card is EQUAL, pick up 1 card.";
        }

    }
    

    /**
     * The checkDealStackNeedForRefill method checks if the deal
     * Stack is empty using the empty method of the Stack class; if
     * it is empty, all but the top card from the discard stack are
     * put into the deal stack.
     */
    public void checkDealStackNeedForRefill() {

        //if the deckStack is empty, move all but top value from discard Stack
        if (dealStack.empty()) {

            //temporary variable to hold head card from discard Stack
            int temp = discardStack.pop();

            do {
                //pop a card off discardStack, and push it to deal Stack
                dealStack.push(discardStack.pop());

                //keep repeating until the discard Stack is empty

            } while (!discardStack.empty());

            //put the temporary card value back on the discard Stack
            discardStack.push(temp);

        }
    }

    /**
     * checkWinner method checks if current player's queue is empty; the 
     * method returns true; If it is not, the method returns false. If the 
     * queue is empty, then the player has won the game.
     *
     * @param playerNum     integer value representing current player
     * @return boolean      whether or not winner exists
     */
    public boolean checkWinner(int playerNum) {

        //check if the player's queue is empty, return true if it is
        if (playerCards.get(playerNum).empty())
            return true;
        //if the queue is not empty, return false
        else
            return false;

    }

    /**
     * The checkTie method checks if both the deal and discard Stacks are empty
     * at the end of the player's turn; if they are both empty, method returns
     * true, signifying a tie.
     *
     * @return true or false depending on if both stacks are empty
     */
    public boolean checkTie() {

        //if deal stacks is empty and discard stack is 1,
        //return true
        return dealStack.empty() && discardStack.size() == 1;

    }


    /**
     * The winnerMessageFormatted method returns a string that states which
     * player has won the game.
     *
     * @param playerNum integer variable representing the player
     * @return  a string statement
     */
    public String winnerMessageFormatted(int playerNum) {

        return "Player " + (playerNum + 1) + " won the game!";
    }


    /**
     * the getNUMBER_OF_PLAYERS method is a getter that would allow
     * other classes to retrieve the number of players without being
     * able to modify this number.
     *
     * @return number of players in the game
     */
    public int getNUMBER_OF_PLAYERS(){

        //return integer value of number of players
        return NUMBER_OF_PLAYERS;

    }


    /**
     * The getPlayerQueue method is a 'getter' and takes in a number that
     * represents the current player and returns the string representation of
     * the player's queue.
     *
     * @param playerNum integer variable representing player
     * @return String representation of queue
     */
    public String getPlayerQueue(int playerNum) {

        //return player's current queue in string format
        return playerCards.get(playerNum).toString();

    }
}

