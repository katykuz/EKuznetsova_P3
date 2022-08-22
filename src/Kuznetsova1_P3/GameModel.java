/*
 * Ekaterina Kuznetsova
 * CPSC 5002, Seattle University
 * This is free and unencumbered software released into the public domain.
 */
//TODO - package

/**
 * TODO - class description
 *
 * @author Ekaterina Kuznetsova
 * @version 1.0
 */
public class GameModel {

/**
 * TODO -
 *
 * @author Ekaterina Kuznetsova
 * @version 1.0
 */

    //private fields:
    //2D array representing TicTacToe board
    private int[][] board;
    //size of board array
    private final int SIZE;
    //number of players
    private final int NUMBER_OF_PLAYERS;
    //array to hold player char values
    private char[] playerName;
    //array to hold number of wins for each player
    private int[] winStat;
    //integer values for row, column, and number of ties between players
    private int player1, player2, tieStat;

    /**
     * No-argument constructor initializes the necessary variables for TTT game.
     */
    public TicTacToe() {
        //initialize size of board array
        SIZE = 3;
        //initialize number of players
        NUMBER_OF_PLAYERS = 2;
        //initialize player 1D array
        playerName = new char[NUMBER_OF_PLAYERS];
        //player 0 represents character O with unicode of 79
        playerName[0] = 79;
        //player 0 represents character O with unicode of 88
        playerName[1] = 88;
        //initialize 1D array to hold wining statistics of players
        winStat = new int[NUMBER_OF_PLAYERS];
        //initialize variable for rows (will be used for user input)
        tieStat = 0;
    }

    /**
     * playGame method sets up the game by displaying the empty TTT board,
     * repeating player turns until a player wins or there's a tie, displays
     * game statistics at the end of the game, and clears the board before
     * the next game.
     */
    public void playGame(Scanner keyboard) {
        //declare a variable to track a win/tie through the while-loop
        boolean someoneWonOrTie;

        //begin do-while loop so players keep taking turns until
        //a winner or tie
        do {
            //players take turns
            someoneWonOrTie = playerTurns(keyboard);
            //conclude while loop with a boolean check
        } while (!someoneWonOrTie);

        //display game statistics
        displayStatistics();

    }

    /**
     * playerTurns method loops between each player; the method calls necessary
     * methods for receiving/validating inputs, setting the inputs to the board,
     * checking for a winner and if the board is full after each player's turn
     *
     * @param keyboard      Scanner object for user input
     * @return boolean      whether or not there's a winner or a tie
     */
    public boolean playerTurns(Scanner keyboard) {
        //initialize boolean variable to be able to terminate loop
        boolean someoneWonOrTie = false;


        //players take turns through for-loop
        for (int i = 0; i < NUMBER_OF_PLAYERS && !someoneWonOrTie; i++) {

            //prompt user
            System.out.println(playerName[i] + ", it is your turn.");


            //check if board has winner by calling checkWinner method
            if (checkWinner(i)) {
                //display a message if there's a win
                System.out.println("\n" + playerName[i] + " won the game!");
                //keep track of player's win
                winStat[i]++;
                //leave loop
                someoneWonOrTie = true;

                //check if there is a tie (the board is full but no winner)
            } else if (!doesBoardHaveSpace()) {
                //if the board is full, display message
                System.out.println("No winner - it was a tie!");
                //keep count of ties
                tieStat++;
                //leave loop
                someoneWonOrTie = true;
            }

        }
        //return boolean
        return someoneWonOrTie;
    }


    /**
     * checkWinner method checks if the sum of user-selected row, column,
     * or the diagonal equals the product of the size of the board and the
     * unicode number value of player's character (79 for O and 88 for X);
     * if true, then that player is a winner.
     *
     * @param playerIndex     int index value representing player
     * @return boolean  whether or not winner exists
     */
    public boolean checkWinner(int playerIndex) {

        //playerName[i] represents unicode value of current player
        //size is int value of board, equals row length


    }



    /**
     * displayStatistics method prints how many games each player won
     * and how many ties there have been
     */
    public void displayStatistics() {
        //print game stats header
        System.out.println("Game Stats");
        //loop through each player and print number of wins
        for (int i = 0; i < NUMBER_OF_PLAYERS; i++) {
            System.out.println(playerName[i] + " has won " + winStat[i]
                    + " games.");
        }
        //print number of ties
        System.out.println("There have been " + tieStat + " ties.");
    }

}
