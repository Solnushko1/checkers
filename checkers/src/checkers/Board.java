package checkers;

import java.io.Serializable;

public class Board implements Serializable {
   private CheckerPosition[] board = new CheckerPosition[32];
   private MoveList history = new MoveList();  // История ходов.
   protected Board next = null;


   public Board copy() {
      Board newBoard = new Board();
      Coordinate temp = null;
      newBoard.setHistory(history.copy());
      for (int i = 1; i < 33; i++) {
         temp = new Coordinate(i);
         if (getChecker(temp) != null)
            newBoard.setChecker(getChecker(temp).copy(), temp);
      }
      return newBoard;
   }

   public CheckerPosition getChecker(Coordinate c) {
      return board[c.get() - 1];
   }

   public void setChecker(CheckerPosition checker, Coordinate c) {
      board[c.get() - 1] = checker;
      checker.setPosition(c);
   }

   public void removeChecker(CheckerPosition checker) {
      board[checker.getPosition().get() - 1] = null;
   }

   public boolean vacantCoordinate(Coordinate c) {
      return (getChecker(c) == null);
   }

   public int evaluate() {
      int score = 0;
      Coordinate c = null;
      for (int i = 1; i < 33; i++) {
         c = new Coordinate(i);
         if (getChecker(c) != null)
            score = score + getChecker(c).getValue();
      }
      return score;
   }

   public void setHistory(MoveList history) {
      this.history = history;
   }

   public MoveList getHistory() {
      return history;
   }

   public void addMoveToHistory(Move move) {
      history.add(move);
   }

   public void initialize() {
      initializeTop();
      initializeMiddle();
      initializeBottom();
   }

   private void initializeTop() {
      for (int i = 1; i < 13; i++)
         board[i - 1] = new BlackChecker(new Coordinate(i));
   }

   private void initializeMiddle() {
      for (int i = 13; i < 21; i++)
         board[i - 1] = null;
   }

   private void initializeBottom() {
      for (int i = 21; i < 33; i++)
         board[i - 1] = new WhiteChecker(new Coordinate(i));
   }

   // Для класса BoardList.
   public void setNext(Board next) {
      this.next = next;
   }

   // Для класса BoardList.
   public Board getNext() {
      return next;
   }

   public String toString() {
      String stringBoard[][] = new String[8][8];
      Coordinate temp = null;
      for (int i = 1; i < 33; i++) {
         temp = new Coordinate(i);
         if (getChecker(temp) != null)
            stringBoard[temp.column()][temp.row()] = getChecker(temp).toString();
      }

      String s = " +---+---+---+---+---+---+---+---+\n ";
      for (int y = 0; y < 8; y++) {
         for (int x = 0; x < 8; x++) {
            // Определяем цвет клетки
            String cellColor = ((x + y) % 2 != 0) ? "dark" : "light";  // dark - темная клетка, light - светлая клетка

            if (stringBoard[x][y] != null) {
               s = s + "| " + stringBoard[x][y] + " ";
            } else {
               s = s + "|   "; // Пустая клетка
            }

            // Печать цветовых меток для отладки
            // System.out.print(cellColor + " ");
         }
         s = s + "| (" + ((y * 4) + 1) + "-" + ((y * 4) + 4) + ")";
         s = s + "\n +---+---+---+---+---+---+---+---+\n ";
      }
      return s;
   }
}
