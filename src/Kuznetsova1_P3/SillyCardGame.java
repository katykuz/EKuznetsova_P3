/*
 * Ekaterina Kuznetsova
 * CPSC 5002, Seattle University
 * This is free and unencumbered software released into the public domain.
 */
package Kuznetsova1_P3;
import java.util.*;

public class SillyCardGame {

    /**
     * The main method creates a Scanner object, runs the basic methods of
     * the game, and closes the Scanner object; the basic methods include
     * a welcome method, a method running the game that the user can repeat,
     * and a goodbye method.
     *
     * @param args A string array containing the command line arguments.
     */
    public static void main(String[] args) {

        //create scanner object for user input
        Scanner keyboard = new Scanner(System.in);
        //declare variable to hold user response
        String repeat;

        //welcome message
        welcome();


        //do-while loop for user to repeat game
        do {

            //create a GameModel object
            GameModel cardGame = new GameModel();

            //declare a variable to track a win/tie through the while-loop
            boolean someoneWonOrTie;

            //begin do-while loop so players keep taking turns until
            //a winner
            do {
                //players take turns
                someoneWonOrTie = playATurn(cardGame);
                //conclude while loop with a boolean check
            } while (!someoneWonOrTie);


            //prompt user for repeat of game
            System.out.print("\nRepeat the game? Enter yes to repeat: ");
            //consume next line character
            keyboard.nextLine();
            //get user input
            repeat = keyboard.nextLine();
            //conclude do-while loop with verification of user response
        } while (repeat.equalsIgnoreCase("yes"));


        //goodbye message
        goodbye();

        //close scanner
        keyboard.close();
    }


    /**
     * Welcome method welcomes the user and introduces the program
     */
    public static void welcome() {
        //create welcome string
        String welcome = "Welcome to the Silly Card Game!";
        //print string in middle of 80 characters
        System.out.printf("%13s\n", welcome);
        //print message
        //TODO: WELCOME MESSAGE
        System.out.println("This is a game of ...");
    }

    /**
     * goodbye method prints a farewell message and thanks the user
     */
    public static void goodbye() {
        //print goodbye message
        System.out.println("\nThanks for playing! Goodbye for now!");
    }

    /**
     * playerTurns method loops between each player...
     *
     * @return boolean      whether or not there's a winner or a tie
     */
    public static boolean playATurn(GameModel cardGame) {
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
            System.out.println("Discard pile card:" +
                    cardGame.drawFromDeckToDiscard());

            //print players top card
            System.out.println("Your current card: " +
                    cardGame.getPlayerHeadCard(i));

            //print card comparison
            System.out.println(cardGame.cardComparison(i));

            //TODO- delete before turn in
//            cardGame.printStack();

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
