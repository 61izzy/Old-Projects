public class Board {
  char[][] board = new char[10][10];
  boolean outOfBounds, isCollision;
  int row, column;
  int[] ship = {2, 3, 3, 4, 5};

  public Board() {
    for (int x = 0; x < 10; x++) {
      for (int y = 0; y < 10; y++) {
        board[x][y] = '-';
      }
    }
  }

  public void displayBoard() {
    char[] row = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};

    System.out.println("  1 2 3 4 5 6 7 8 9 10");
    for (int r = 0; r < 10; r++) {
      System.out.print(row[r]);
      for (int c = 0; c < 10; c++) {
        System.out.print(" " + board[r][c]);
      }
      System.out.println();
    }
  }

  public void placementValidation(char position, int row, int column, int ship) {
    outOfBounds = false;
    isCollision = false;

    if (position == 'H') {
      if (ship + column > 10) {
        outOfBounds = true;
      }
      else {
        for (int c = column; c < column + ship; c++) {
          if (board[row][c] == 'X') {
            isCollision = true;
          }
        }
        if (!isCollision) {
          for (int c = column; c < column + ship; c++) {
            board[row][c] = 'X';
          }
        }
      }
    }
    else {
      if (ship + row > 10) {
        outOfBounds = true;
      }
      else {
        for (int r = row; r < row + ship; r++) {
          if (board[r][column] == 'X') {
            isCollision = true;
          }
        }
        if (!isCollision) {
          for (int r = row; r < row + ship; r++) {
            board[r][column] = 'X';
          }
        }
      }
    }
  }
}