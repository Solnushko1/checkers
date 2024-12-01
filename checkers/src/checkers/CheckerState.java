package checkers;

public interface CheckerState {    
   public boolean findValidMoves(CheckerPosition checker, Board board, 
                              MoveList validMoves);

   public boolean findValidJumps(CheckerPosition checker, Board board,
                              MoveList validJumps);
}