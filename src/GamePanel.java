import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GamePanel extends JPanel implements Runnable{
    public static final int width = 1100;
    public static int height = 800;
    final int FPS = 60;
    Thread gameThread;
    Board gameBoard = new Board();
    public static int whitePiece = 0;
    public static int blackPiece = 1;
    public static int playersColor = 1;
    public static ArrayList<Piece> startingPieces = new ArrayList<>();
    public static ArrayList<Piece> piecesOnBoard = new ArrayList<>();
    King kingWhite = new King(0,3,0);
    King kingBlack = new King(7,3,1);
    Mouse mouse = new Mouse();
    Piece holdPiece;

    public GamePanel(){
        setPreferredSize(new Dimension(width, height));
        setBackground(Color.WHITE);
        setPiecesLists();
        addMouseMotionListener(mouse);
        addMouseListener(mouse);
    }

    public void update(){
        movingPiece();
    }

    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;
        gameBoard.draw(graphics2D);
        for(Piece piece : piecesOnBoard){
             piece.draw(graphics2D);
        }
        if(holdPiece != null){
            graphics2D.setColor(Color.RED);
            graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
            graphics2D.fillRect(holdPiece.col*Board.squareSize, holdPiece.row*Board.squareSize,
                    Board.squareSize, Board.squareSize);
            graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
            holdPiece.draw(graphics2D);
        }
    }

    @Override
    public void run() {
        double drawInterval = (double) 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        while(gameThread != null){
            currentTime = System.nanoTime();
            delta += (currentTime-lastTime)/drawInterval;
            lastTime=currentTime;
            if(delta>=1){
                delta=0;
                update();
                repaint();
            }
        }
    }
    public void launch(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    public void setPiecesLists(){
        piecesOnBoard.removeAll(startingPieces);
        for(int i = 0; i < 8; i++){
            startingPieces.add(new Pawn(1,i,0));
            startingPieces.add(new Pawn(6,i,1));
        }
        startingPieces.add(new Rook(0,0,0));
        startingPieces.add(new Rook(0,7,0));
        startingPieces.add(new Rook(7,0,1));
        startingPieces.add(new Rook(7,7,1));
        startingPieces.add(new Knight(0,1,0));
        startingPieces.add(new Knight(0,6,0));
        startingPieces.add(new Knight(7,1,1));
        startingPieces.add(new Knight(7,6,1));
        startingPieces.add(new Bishop(0,2,0));
        startingPieces.add(new Bishop(0,5,0));
        startingPieces.add(new Bishop(7,2,1));
        startingPieces.add(new Bishop(7,5,1));
        startingPieces.add(kingWhite);
        startingPieces.add(kingBlack);
        startingPieces.add(new Queen(0,4,0));
        startingPieces.add(new Queen(7,4,1));
        piecesOnBoard.addAll(startingPieces);
    }
    public void movingPiece(){
        if(mouse.pressed){
            if(holdPiece == null){
                for(Piece piece : piecesOnBoard){
                    if(piece.color == playersColor && piece.col == mouse.x/Board.squareSize
                            && piece.row == mouse.y/Board.squareSize){
                        holdPiece = piece;
                    }
                }
            }
            else{
                holdPiece.posX = mouse.x - Board.squareSize/2;
                holdPiece.posY = mouse.y - Board.squareSize/2;
                holdPiece.col = holdPiece.getCol(holdPiece.posX);
                holdPiece.row = holdPiece.getRow(holdPiece.posY);
            }
        }
        if(!mouse.pressed) {
            if (holdPiece == null) return;
            if (!holdPiece.canMove(holdPiece.getCol(holdPiece.posX), holdPiece.getRow(holdPiece.posY))
                || holdPiece.isOutOfBonds() || isFieldOccupied())
            {
                for(Piece piece : piecesOnBoard){
                    if(holdPiece.canTake(piece)){
                        holdPiece.takePiece(piece);
                        holdPiece = null;
                        return;
                    }
                }
                holdPiece.returnToPreviousPosition();
                checkingForCheck();
                holdPiece = null;
                return;

            }
            holdPiece.fixPosition();
            checkingForCheck();
            holdPiece = null;
        }
    }
    public boolean isFieldOccupied() {
        for (Piece piece : piecesOnBoard) {
            if (piece.col == holdPiece.col && piece.row == holdPiece.row
                    && piece != holdPiece){
                return true;
            }
        }
        return false;
    }
    public void checkingForCheck(){
        if(kingWhite.isUnderCheck() || kingBlack.isUnderCheck()){
            System.out.println("Check!");
        }
    }

}
