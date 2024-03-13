import java.util.Scanner;

public class Player extends Board {
  Scanner scanner = new Scanner(System.in);

  public void placeShips() {
    char position;

    for (int i = 0; i < 5; i++) {
      System.out.print("Hit enter to place the next ship. ");
      scanner.nextLine();

      System.out.println("\nYour current grid of ships.");
      displayBoard();

      System.out.println("\nNow you need to place a ship of length " + ship[i]);

      do {
        do {
          getCoordinates();

          if (board[row][column] == 'X') {
            System.out.println("This spot is already occupied. Please relocate your ship.");
          }
        }
        while (board[row][column] == 'X');

        System.out.print("Horizontal or vertical? ");
        position = scanner.next().toUpperCase().charAt(0);

        while (!(position == 'H' || position =='V')) {
          System.out.print("Please enter horizontal or vertical: ");
          position = scanner.next().toUpperCase().charAt(0);
        }

        placementValidation(position, row, column, ship[i]);

        if (outOfBounds) {
          System.out.println("Your ship has gone off the grid. Please relocate your ship.");
        }
        if (isCollision) {
          System.out.println("Your ship has collided with another ship. Please relocate your ship.");
        }
      }
      while (outOfBounds || isCollision);
      
      scanner.nextLine();
    }

    System.out.println("\nLocation of my ships");
    displayBoard();
  }

  public void getCoordinates() {
    char rowChar;
    System.out.print("Which row? (A-J) ");
    rowChar = scanner.next().toUpperCase().charAt(0);

    while (rowChar < 65 || rowChar > 74) {
      System.out.print("Please choose a row from A-J: ");
      rowChar = scanner.next().toUpperCase().charAt(0);
    }

    row = rowChar - 65;

    System.out.print("Which column? (1-10) ");
    do {
      column = inputValidation();
      if (column < 1 || column > 10) {
        System.out.print("Please choose a column from 1-10: ");
      }
    }
    while (column < 1 || column > 10);

    column--;
  }

  public int inputValidation() {
    int column = 0;
    boolean isValid;
    
    do {
      isValid = true;
      try {
        column = scanner.nextInt();
      }
      catch (Exception e) {
        isValid = false;
        scanner.nextLine();
        System.out.print("Invalid input! Please choose a column from 1-10: ");
      }
    }
    while (!isValid);

    return column;
  }
}