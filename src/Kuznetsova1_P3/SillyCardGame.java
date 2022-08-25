/*
 * Ekaterina Kuznetsova
 * CPSC 5002, Seattle University
 * This is free and unencumbered software released into the public domain.
 */
package Kuznetsova1_P3;
import java.util.*;

/**
 * The SillyCardGame class starts the Silly Card Game, outputs to the screen
 * players' cards, the card at the top of the discard stack, the result of
 * the comparison, the action for the player, and the result of the game! The
 * class also asks if the user wants to play the game again and reads input
 * from the keyboard.
 *
 * @Ekaterina Kuznetsova
 * @version 1.0
 */
public class SillyCardGame {

    /**
     * The main method creates a Scanner object, runs the basic methods of
     * the game, and closes the Scanner object; the basic methods include
     * a welcome method, a method running the game that the user can repeat,
     * and a goodbye method.
     *
     * @param args A string array containing the command line arguments.
     * @throws IllegalArgumentException when queue or stack is empty
     */
    public static void main(String[] args)
            throws IllegalArgumentException{

        //create scanner object for user input
        Scanner keyboard = new Scanner(System.in);

        //declare variable to hold user response
        String repeat;

        //welcome message
        welcome();

        do {

            //create a GameModel object
            GameModel cardGame = new GameModel();

            //declare a variable to track a win/tie through the while-loop
            boolean someoneWonOrTie;

            //begin do-while loop so players keep taking turns until
            //a winner
            do {

                //players take turns
                someoneWonOrTie = printPlayATurn(cardGame);

            //conclude while loop with a boolean check
            } while (!someoneWonOrTie);

            //prompt user for repeat of game
            System.out.print("\nRepeat the game? Enter no to quit: ");

            //get user input
            repeat = keyboard.nextLine();

        //conclude do-while loop with verification of user response
        } while (!repeat.equalsIgnoreCase("no"));

        //goodbye message
        goodbye();

        //close scanner
        keyboard.close();
    }

    /**
     * Welcome method welcomes the user and introduces the program
     */
    public static void welcome() {

        //print welcome message
        System.out.println("*** Welcome to the Silly Card Game! ***");

        //print welcome message
        System.out.println("\nThis is a card game simulation where players " +
                "compare their cards against \nthe card at the top of the " +
                "discard pile. Players are instructed to take\ncards from " +
                "the deal pile based on their card. The first player to run\n" +
                "out of cards is the winner. If the runs out of cards for " +
                "players to draw,\nthe game is a tie.\n");

    }

    /**
     * goodbye method prints a farewell message and thanks the user
     */
    public static void goodbye() {

        //print goodbye message
        System.out.println("\nThanks for playing! Goodbye for now!");
    }

    /**
     * printPlayATurn method calls the functions necessary to print the game
     * state, the players' cards, and the discard stack cards as the game loops
     * between players
     *
     * @return boolean      whether or not there's a winner or a tie
     */
    public static boolean printPlayATurn(GameModel cardGame) {

        //initialize boolean variable to be able to terminate loop
        boolean someoneWonOrTie = false;

        //players take turns through for-loop
        for (int i = 0; i < cardGame.getNUMBER_OF_PLAYERS() &&
                !someoneWonOrTie; i++) {

            System.out.println("\n" + cardGame.userTurnPromptFormatted(i));
            //prompt user

            //display player queue
            System.out.println(cardGame.getPlayerQueue(i));

            //print deck stack for comparison
            System.out.println("Discard pile card: " +
                    cardGame.peekAtDiscardStack());

            //print players top card
            System.out.println("Your current card: " +
                    cardGame.getPlayerHeadCard(i));

            //print card comparison
            System.out.println(cardGame.cardComparison(i));

            //check if board has winner by calling checkWinner method
            if (cardGame.checkWinner(i)) {
                //display a message if there's a win
                System.out.println(cardGame.winnerMessageFormatted(i));

                //leave loop
                someoneWonOrTie = true;

                //check if there is a tie
            } else if (cardGame.checkTie()) {
                //leave loop
                someoneWonOrTie = true;
            }

        }
        //return boolean
        return someoneWonOrTie;
    }
}