package CheckersUI;

import checkers.Board;
import checkers.Coordinate;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JPanel;


public class CheckersPanel extends JPanel {

    final static String BLACK_PAWN = "/assets/black_pawn.png";
    final static String WHITE_PAWN = "/assets/white_pawn.png";
    final static String BOARD_1 = "/assets/board.png";
    final static String WHITE_KING = "/assets/white_pawn_king.png";
    final static String BLACK_KING = "/assets/black_pawn_king.png";
    final static String GREEN_INDICATOR = "/assets/green_indicator.png";

    ImageIcon  black_pawn, white_pawn, board, black_king, white_king, indicatePosition;
    boolean newBoard = true;
    ArrayList<Point> blackPositions = new ArrayList<Point>();
    ArrayList<Point> whitePositions = new ArrayList<Point>();
    ArrayList<Pawn> pawns = new ArrayList<Pawn>();
    ArrayList<Point> allBoardPoints = new ArrayList<>();
    ArrayList<Integer> possiblemovesindex = new ArrayList<>();
    ArrayList<Integer> bestmovesfromhelp = new ArrayList<>();
    
    Board boardO = new Board();
    String turn = "your turn";
    String user_move = "";
    String computer_move = "";
    int theme = 1;
    

    public CheckersPanel() {

        initAllpositions();
        black_pawn = new ImageIcon(getClass().getResource(BLACK_PAWN));
        white_pawn = new ImageIcon(getClass().getResource(WHITE_PAWN));
        board = new ImageIcon(getClass().getResource(BOARD_1));
        indicatePosition = new ImageIcon(getClass().getResource(GREEN_INDICATOR));
        white_king = new ImageIcon(getClass().getResource(WHITE_KING));
        black_king = new ImageIcon(getClass().getResource(BLACK_KING));
        boardO.initialize();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (theme == 1) {
            board.paintIcon(this, g, 0, 0);
        }
        if (newBoard) {
            drawBoard(g);
        }
        drawPawns(g);
        drawPossibleMoves(g);
        drawBestmovesFromHelp(g);
    }

    public void drawInitBoard(Graphics g) {
        for (Point p : allBoardPoints) {
            System.out.println("" + p.getX() + " " + p.getY());
            black_pawn.paintIcon(this, g, (int) p.getX(), (int) p.getY());
        }
    }


    private void initAllpositions() {
        int row = 0;

        for (int i = 0; i < 32; i++) {
            Point position = new Point();

            if (i != 0 && i % 4 == 0) {
                row++;
            }

            if (row % 2 == 0) {
                position.x = (i % 4) * 75 * 2 + 5 + 75;
            } else {
                position.x = (i % 4) * 75 * 2 + 5;
            }

            position.y = row * 75 + 5;

            allBoardPoints.add(position);
        }
    }

    public void drawPawnAtPostion(Graphics g, int pos, int player) {
        if (player == 1)
        {
            Pawn p = new Pawn(allBoardPoints.get(pos), black_pawn);
            p.posindex = pos;
            pawns.add(p);
        } else {
            Pawn p = new Pawn(allBoardPoints.get(pos), white_pawn);
            p.posindex = pos;
            pawns.add(p);
        }
    }

    public void drawPawns(Graphics g) {
        for (Pawn p : pawns) {
            p.image.paintIcon(this, g, (int) p.point.getX(), (int) p.point.getY());
        }
    }

    public void drawBoard(Graphics g) {
        pawns.clear();

        for (int i = 1; i < 33; i++) {
            Coordinate c = new Coordinate(i);
            int color = 0;

            if (boardO.getChecker(c) != null) {
                color = boardO.getChecker(c).getColor();
            }
            if (color == 2) {

                Pawn p = null;
                if (boardO.getChecker(c).isKing()) {
                    p = new Pawn(allBoardPoints.get(i - 1), white_king);
                } else {
                    p = new Pawn(allBoardPoints.get(i - 1), white_pawn);
                }

                p.posindex = i;
                pawns.add(p);
            }
            if (color == 1) {
                Pawn p = null;
                if (boardO.getChecker(c).isKing()) {
                    p = new Pawn(allBoardPoints.get(i - 1), black_king);
                } else {
                    p = new Pawn(allBoardPoints.get(i - 1), black_pawn);
                }
                p.posindex = i;
                pawns.add(p);
            }

        }
    }

    private void drawBestmovesFromHelp(Graphics g) {

        for (int i = 0; i < bestmovesfromhelp.size(); i++) {
            indicatePosition.paintIcon(this, g, (int) allBoardPoints.get(bestmovesfromhelp.get(i) - 1).getX() + 5, (int) allBoardPoints.get(bestmovesfromhelp.get(i) - 1).getY() + 5);
        }
    }

    public void drawPossibleMoves(Graphics g) {

        for (int i = 0; i < possiblemovesindex.size(); i++) {
            indicatePosition.paintIcon(this, g, (int) allBoardPoints.get(possiblemovesindex.get(i) - 1).getX() + 5, (int) allBoardPoints.get(possiblemovesindex.get(i) - 1).getY() + 5);
        }

    }
   
    
    public Pawn getPawnOfPosition(int pos) {

        for (Pawn p : pawns) {
            if (p.posindex == pos) {
                return p;
            }
        }
        return null;
    }
}
