//Description: A very badly programmed game of battleship with added computer ai
//Author: Kevin Yang
//Date: July 3, 2021

import java.util.Scanner;

class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    int column, row, playerHitCount = 0, computerHitCount = 0;
    Player player = new Player();
    Computer computer = new Computer();
    Board playerTurn = new Board();
    Board computerTurn = new Board();
    AI computerAI = new AI();

    System.out.println("==============================");
    System.out.println("Welcome to Battleship!");
    System.out.println("==============================");
    System.out.println("First you need to choose the location of your ships.");

    player.placeShips();

    System.out.print("Hit enter for the enemy to choose their ship locations. ");
    scanner.nextLine();

    computer.placeShips();

    System.out.print("Hit enter to start guessing. ");
    scanner.nextLine();

    while (playerHitCount < 17 && computerHitCount < 17) {
      System.out.print("Hit enter for your turn ");
      scanner.nextLine();

      System.out.println("\nEnemy grid");
      playerTurn.displayBoard();

      System.out.println("\nIt's your turn to guess.");

      do {
        player.getCoordinates();

        row = player.row;
        column = player.column;

        if (playerTurn.board[row][column] != '-') {
          System.out.println("You've already striked that coordinate. Please choose a different coordinate.");
        }
      }
      while (playerTurn.board[row][column] != '-');

      if (computer.board[row][column] == 'X') {
        System.out.println("\nYou got a hit!");
        playerTurn.board[row][column] = 'X';
        playerHitCount++;
      }
      else {
        System.out.println("\nNope, that was a miss.");
        playerTurn.board[row][column] = 'O';
      }

      playerTurn.displayBoard();

      System.out.println("\nTotal hits = " + playerHitCount + " out of 17");

      if (playerHitCount == 17) {
        System.out.println("You win!");
      }
      else {
        System.out.print("Hit enter for the computer turn. ");
        scanner.nextLine();

        row = computerAI.nextRow;
        column = computerAI.nextColumn;
        
        System.out.println("\nComputer player guesses row " + (char)(row + 65) + " and column " + (column + 1));

        if (player.board[row][column] == 'X') {
          System.out.println("Computer got a hit.");
          computerTurn.board[row][column] = 'X';
          computerHitCount++;
        }
        else {
          System.out.println("Computer missed.");
          computerTurn.board[row][column] = 'O';
        }

        System.out.println("\nYour grid");
        computerTurn.displayBoard();

        System.out.println("\nTotal hits = " + computerHitCount + " out of 17");

        if (computerHitCount == 17) {
          System.out.println("Computer wins!");
        }
        else {
          computerAI.getMove(row, column, computerTurn.board);
        }
      }
    }

    System.out.println("Thanks for playing.");
  }
}