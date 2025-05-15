//package com.company.Model;

import java.util.Objects;
import java.util.Scanner;

public class Connect4 {

    /** Global Variables **/
    String[][] board;
    Boolean winner;
    Boolean draw;
    int winningPlayer;
    int playerTurn;

    /** Constructor **/
    public Connect4() {
        winner = false;
        winningPlayer = 0;
        draw = false;
        playerTurn = 1;
        board = new String[6][7];
        newBoard();
        displayBoard();
    }

    /**
     * Builds a better looking board in the console
     **/
    private void newBoard() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                board[i][j] = "-";
            }
        }
    }

    private void displayBoard() {
        System.out.println("*** CONNECT 4 ***");
        for (int i = 0; i < 6; i++) {
            System.out.print("| ");
            for (int j = 0; j < 7; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
        }
        System.out.println(" 1   2   3   4   5   6   7 ");
    }

    /**
     * Valid Input
     **/
    private boolean validInput(String input) {
        return (Objects.equals(input, "1") ||
                Objects.equals(input, "2") ||
                Objects.equals(input, "3") ||
                Objects.equals(input, "4") ||
                Objects.equals(input, "5") ||
                Objects.equals(input, "6") ||
                Objects.equals(input, "7"));
    }

    /**
     * Is Column Full
     * @param column
     * @return true if column is full - false otherwise
     **/
    private boolean isColumnFull(int column) {
        return (board[0][column - 1].equals("X") || board[0][column - 1].equals("O"));
    }

    /**
     * Get Next Available Slot
     * @param column
     * @return the next available row position of a given column
     **/
    private int getNextAvailableSlot(int column) {
        int position = 5;
        boolean found = false;
        while (position >= 0 && !found) {
            if (Objects.equals(board[position][column - 1], "-")) {
                found = true;
            } else {
                position--;
            }
        }
        return position;
    }

    /**
     * Update Board
     **/
    private void updateBoard(int column, int player) {
        int availableRow = getNextAvailableSlot(column);
        if (player == 1) {
            board[availableRow][column - 1] = "X";
        } else {
            board[availableRow][column - 1] = "O";
        }
    }

    /**
     * Is Board Full
     * @return true if board is full - false otherwise
     **/
    private boolean isBoardFull() {
        for (int i = 0; i < 7; i++) {
            if (board[0][i].equals("-")) {
                return false;
            }
        }
        return true;
    }

    /**
     * Check For Win
     **/
    private boolean checkForWin() {
        // Check horizontal win
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 4; j++) {
                if (!board[i][j].equals("-") && board[i][j].equals(board[i][j + 1]) && board[i][j].equals(board[i][j + 2]) && board[i][j].equals(board[i][j + 3])) {
                    return true;
                }
            }
        }

        // Check vertical win
        for (int j = 0; j < 7; j++) {
            for (int i = 0; i < 3; i++) {
                if (!board[i][j].equals("-") && board[i][j].equals(board[i + 1][j]) && board[i][j].equals(board[i + 2][j]) && board[i][j].equals(board[i + 3][j])) {
                    return true;
                }
            }
        }

        // Check diagonal win (\ direction)
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                if (!board[i][j].equals("-") && board[i][j].equals(board[i + 1][j + 1]) && board[i][j].equals(board[i + 2][j + 2]) && board[i][j].equals(board[i + 3][j + 3])) {
                    return true;
                }
            }
        }

        // Check diagonal win (/ direction)
        for (int i = 3; i < 6; i++) {
            for (int j = 0; j < 4; j++) {
                if (!board[i][j].equals("-") && board[i][j].equals(board[i - 1][j + 1]) && board[i][j].equals(board[i - 2][j + 2]) && board[i][j].equals(board[i - 3][j + 3])) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Play Game
     **/
    public void playGame() {
        Scanner scanner = new Scanner(System.in);
        while (!winner && !draw) {
            System.out.println("Player " + playerTurn + ": Please select which column to place your piece (1-7): ");
            String input = scanner.nextLine();
            if (validInput(input)) {
                int column = Integer.parseInt(input);
                if (!isColumnFull(column)) {
                    updateBoard(column, playerTurn);
                    displayBoard();
                    if (checkForWin()) {
                        winner = true;
                        winningPlayer = playerTurn;
                    } else if (isBoardFull()) {
                        draw = true;
                    } else {
                        playerTurn = (playerTurn == 1) ? 2 : 1;
                    }
                } else {
                    System.out.println("Column is full. Try again.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number between 1 and 7.");
            }
        }

        if (winner) {
            System.out.println("Player " + winningPlayer + " wins!");
        } else {
            System.out.println("The game is a draw.");
        }
        scanner.close();
    }

    public static void main(String[] args) {
        Connect4 game = new Connect4();
        game.playGame();
    }
}
