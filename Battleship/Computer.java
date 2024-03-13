public class Computer extends Board {
  public void placeShips() {
    char[] position = {'H', 'V'};

    for (int i = 0; i < 5; i++) {
      do {
        do {
          row = (int)(10 * Math.random());
          column = (int)(10 * Math.random());
        }
        while (board[row][column] == 'X');

        placementValidation(position[(int)(2 * Math.random())], row, column, ship[i]);
      }
      while (outOfBounds || isCollision);
    }
    System.out.println("The enemy has placed their ships.");
  }
}