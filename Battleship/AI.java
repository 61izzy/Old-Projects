public class AI {
  int nextRow = (int)(10 * Math.random()), nextColumn = (int)(10 * Math.random()), firstRow, firstColumn, direction;
  int[] possibleMoves = new int[4];
  boolean sequence = false, isCollision = false;

  public void getMove(int row, int column, char[][] computerTurn) {
    if (computerTurn[row][column] == 'X') {
      if (sequence) {
        if (row != firstRow) {
          nextRow = row > firstRow ? row + 1 : row - 1;
          nextColumn = column;
          if (nextRow > 9 || nextRow < 0) {
            nextRow = row > firstRow ? firstRow - 1 : firstRow + 1;
          }
          else if (computerTurn[nextRow][nextColumn] != '-') {
            nextRow = row > firstRow ? firstRow - 1 : firstRow + 1;
          }
        }
        else /*if (column != firstColumn)*/ {
          nextColumn = column > firstColumn ? column + 1 : column - 1;
          nextRow = row;
          if (nextColumn > 9 || nextColumn < 0) {
            nextColumn = column > firstColumn ? firstColumn - 1 : firstColumn + 1;
          }
          else if (computerTurn[nextRow][nextColumn] != '-') {
            nextColumn = column > firstColumn ? firstColumn - 1 : firstColumn + 1;
          }
        }
        if (nextRow > 9 || nextRow < 0 || nextColumn > 9 || nextColumn < 0) {
          sequence = false;
          nextRow = (int)(10 * Math.random());
          nextColumn = (int)(10 * Math.random());
        }
        while (computerTurn[nextRow][nextColumn] != '-') {
          sequence = false;
          nextRow = (int)(10 * Math.random());
          nextColumn = (int)(10 * Math.random());
        }
      }
      else {
        if ((row == 0 || computerTurn[row - 1][column] != '-') && (column == 9 || computerTurn[row][column + 1] != '-') && (row == 9 || computerTurn[row + 1][column] != '-') && (column == 0 || computerTurn[row][column - 1] != '-')) {
          do {
            nextRow = (int)(10 * Math.random());
            nextColumn = (int)(10 * Math.random());
          }
          while (computerTurn[nextRow][nextColumn] != '-');
        }
        else {
          possibleMoves[0] = row - 1;
          possibleMoves[1] = column + 1;
          possibleMoves[2] = row + 1;
          possibleMoves[3] = column - 1;
          firstRow = row;
          firstColumn = column;
          sequence = true;
          do {
            isCollision = false;
            direction = (int)(4 * Math.random());
            if ((possibleMoves[direction] < 0 || possibleMoves[direction] > 9) || ((direction%2 == 0 ? computerTurn[possibleMoves[direction]][column] : computerTurn[row][possibleMoves[direction]]) != '-')) {
              isCollision = true;
            }
          }
          while (isCollision);
          nextRow = direction%2 == 0 ? possibleMoves[direction] : row;
          nextColumn = direction%2 == 0 ? column : possibleMoves[direction];
        }
      }
    }
    else {
      if (sequence) {
        if (row != firstRow) {
          if (row > firstRow) {
            if ((firstRow == 0 || computerTurn[firstRow - 1][column] == 'O') && row - firstRow == 1) {
              if ((firstColumn == 0 || computerTurn[firstRow][firstColumn - 1] != '-') && (firstColumn == 9 || computerTurn[firstRow][firstColumn + 1] != '-')) {
                sequence = false;
                do {
                  nextRow = (int)(10 * Math.random());
                  nextColumn = (int)(10 * Math.random());
                }
                while (computerTurn[nextRow][nextColumn] != '-');
              }
              else {
                do {
                  isCollision = false;
                  direction = (int)(2 * Math.random()) * 2 + 1;
                  if ((possibleMoves[direction] < 0 || possibleMoves[direction] > 9) || computerTurn[firstRow][possibleMoves[direction]] != '-') {
                    isCollision = true;
                  }
                }
                while (isCollision);
                nextRow = firstRow;
                nextColumn = possibleMoves[direction];
              }
            }
            else if (firstRow > 0 && computerTurn[firstRow - 1][column] == '-') {
              nextRow = firstRow - 1;
              nextColumn = column;
            }
            else /*if (firstRow > 0 && (computerTurn[firstRow - 1][column] == 'X' && row - firstRow == 1 || computerTurn[firstRow - 1][column] == 'O' && row - firstRow > 1) || firstRow == 0 && row - firstRow > 1)*/ {
              sequence = false;
            }
          }
          else /*if (row < firstRow)*/ {
            if ((firstRow == 9 || computerTurn[firstRow + 1][column] == 'O') && firstRow - row == 1) {
              if ((firstColumn == 0 || computerTurn[firstRow][firstColumn - 1] != '-') && (firstColumn == 9 || computerTurn[firstRow][firstColumn + 1] != '-')) {
                sequence = false;
                do {
                  nextRow = (int)(10 * Math.random());
                  nextColumn = (int)(10 * Math.random());
                }
                while (computerTurn[nextRow][nextColumn] != '-');
              }
              else {
                do {
                  isCollision = false;
                  direction = (int)(2 * Math.random()) * 2 + 1;
                  if ((possibleMoves[direction] < 0 || possibleMoves[direction] > 9) || computerTurn[firstRow][possibleMoves[direction]] != '-') {
                    isCollision = true;
                  }
                }
                while (isCollision);
                nextRow = firstRow;
                nextColumn = possibleMoves[direction];
              }
            }
            else if (firstRow < 9 && computerTurn[firstRow + 1][column] == '-') {
              nextRow = firstRow + 1;
              nextColumn = column;
            }
            else /*if (firstRow < 9 && (computerTurn[firstRow + 1][column] == 'X' && firstRow - row == 1 || computerTurn[firstRow + 1][column] == 'O' && firstRow - row > 1) || firstRow == 9 && firstRow - row > 1)*/ {
              sequence = false;
            }
          }
        }
        else /*if (column != firstColumn)*/ {
          if (column > firstColumn) {
            if ((firstColumn == 0 || computerTurn[row][firstColumn - 1] == 'O') && column - firstColumn == 1) {
              if ((firstRow == 0 || computerTurn[firstRow - 1][firstColumn] != '-') && (firstRow == 9 || computerTurn[firstRow + 1][firstColumn] != '-')) {
                sequence = false;
                do {
                  nextRow = (int)(10 * Math.random());
                  nextColumn = (int)(10 * Math.random());
                }
                while (computerTurn[nextRow][nextColumn] != '-');
              }
              else {
                do {
                  isCollision = false;
                  direction = (int)(2 * Math.random()) * 2;
                  if ((possibleMoves[direction] < 0 || possibleMoves[direction] > 9) || computerTurn[possibleMoves[direction]][firstColumn] != '-') {
                    isCollision = true;
                  }
                }
                while (isCollision);
                nextRow = possibleMoves[direction];
                nextColumn = firstColumn;
              }
            }
            else if (firstColumn > 0 && computerTurn[row][firstColumn - 1] == '-') {
              nextRow = row;
              nextColumn = firstColumn - 1;
            }
            else /*if (firstColumn > 0 && (computerTurn[row][firstColumn - 1] == 'X' && column - firstColumn == 1 || computerTurn[row][firstColumn - 1] == 'O' && column - firstColumn > 1) || firstColumn == 0 && column - firstColumn > 1)*/ {
              sequence = false;
            }
          }
          else /*if (column < firstColumn)*/ {
            if ((firstColumn == 9 || computerTurn[row][firstColumn + 1] == 'O') && firstColumn - column == 1) {
              if ((firstRow == 0 || computerTurn[firstRow - 1][firstColumn] != '-') && (firstRow == 9 || computerTurn[firstRow + 1][firstColumn] != '-')) {
                sequence = false;
                do {
                  nextRow = (int)(10 * Math.random());
                  nextColumn = (int)(10 * Math.random());
                }
                while (computerTurn[nextRow][nextColumn] != '-');
              }
              else {
                do {
                  isCollision = false;
                  direction = (int)(2 * Math.random()) * 2;
                  if ((possibleMoves[direction] < 0 || possibleMoves[direction] > 9) || computerTurn[possibleMoves[direction]][firstColumn] != '-') {
                    isCollision = true;
                  }
                }
                while (isCollision);
                nextRow = possibleMoves[direction];
                nextColumn = firstColumn;
              }
            }
            else if (firstColumn < 9 && computerTurn[row][firstColumn + 1] == '-') {
              nextRow = row;
              nextColumn = firstColumn + 1;
            }
            else /*if (firstColumn < 9 && (computerTurn[row][firstColumn + 1] == 'X' && firstColumn - column == 1 || computerTurn[row][firstColumn + 1] == 'O' && firstColumn - column > 1) || firstColumn == 9 && firstColumn - column > 1)*/ {
              sequence = false;
            }
          }
        }
      }
      if (!sequence) {
        do {
          nextRow = (int)(10 * Math.random());
          nextColumn = (int)(10 * Math.random());
        }
        while (computerTurn[nextRow][nextColumn] != '-');
      }
    }
  }
}