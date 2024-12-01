package checkers;

import java.io.Serializable;

public class NormalStateWhite implements CheckerState,Serializable {   

   public boolean findValidMoves(final CheckerPosition c, final Board board, 
                              MoveList validMoves) {   
      if (! findValidJumps(c, board, validMoves)) {

         if (GameSearch.validWhiteMove(c.getPosition(), c.getPosition().upLeftMove(), 
             board))
            validMoves.add(new MoveNormal(c, c.getPosition().upLeftMove()));
            
         if (GameSearch.validWhiteMove(c.getPosition(), c.getPosition().upRightMove(), 
             board))
            validMoves.add(new MoveNormal(c, c.getPosition().upRightMove()));
         return false;
     }
     else
      return true; 
      
   }   
   
   
   public boolean findValidJumps(CheckerPosition c, Board board, MoveList validJumps) {
      boolean found = false;
      if (GameSearch.validWhiteJump(c.getPosition(), c.getPosition().upLeftJump(), 
          board)) {
         validJumps.add(new MoveJump(c, c.getPosition().upLeftJump()));
         found = true;
      }
      
      if (GameSearch.validWhiteJump(c.getPosition(), c.getPosition().upRightJump(), 
          board)) {
         validJumps.add(new MoveJump(c, c.getPosition().upRightJump()));
         found = true;
      }
      // Проверка прыжков назад (вверх для черных).
      if (GameSearch.validWhiteJump(c.getPosition(), c.getPosition().upLeftJump(), board)) {
         validJumps.add(new MoveJump(c, c.getPosition().upLeftJump()));
         found = true;
      }
      if (GameSearch.validWhiteJump(c.getPosition(), c.getPosition().upRightJump(), board)) {
         validJumps.add(new MoveJump(c, c.getPosition().upRightJump()));
         found = true;
      }
      return found;
   }
}

