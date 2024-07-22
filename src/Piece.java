import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Piece {
    public BufferedImage image;
    public int col, row, preCol, preRow, posX, posY;
    public int color;

    public Piece(int row, int col, int color) {
        this.row = row;
        this.col = col;
        this.color = color;
        posX = getPosX(col);
        posY = getPosY(row);
        preCol = col;
        preRow = row;
    }
    public BufferedImage getImage(String imagePath){
        try{
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(imagePath)));
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
        return image;
    }
    public int getPosX(int col){
        return col * Board.squareSize;
    }
    public int getPosY(int row){
        return row * Board.squareSize;
    }
    public int getCol(int posX){
        return(posX + Board.squareSize/2)/Board.squareSize;
    }
    public int getRow(int posY){
        return(posY + Board.squareSize/2)/Board.squareSize;
    }
    public void draw(Graphics2D graphics2D){
        graphics2D.drawImage(image, posX, posY, Board.squareSize, Board.squareSize, null);
    }
    public boolean canMove(int newCol, int newRow){
        return false;
    }

    public void returnToPreviousPosition(){
        col = preCol;
        row = preRow;
        posX = col*Board.squareSize;
        posY = row*Board.squareSize;
    }
    public boolean isOutOfBonds(){
        if(col < 0 || col > 7 || row < 0 || row > 7) return true;
        return false;
    }
    public boolean isColFree(int newRow, ArrayList<Piece> currentPieces){
        for(Piece piece : currentPieces){
            if(piece.row < newRow && piece.row > preRow
                || piece.row > newRow && piece.row < preRow){
                if(piece.col != preCol) continue;
                return false;
            }
        }
        return true;
    }
    public boolean isRowFree(int newCol, ArrayList<Piece> currentPieces){
        for(Piece piece : currentPieces){
            if(piece.col < newCol && piece.col > preCol
                || piece.col > newCol && piece.col < preCol){
                if(piece.row != preRow) continue;
                return false;
            }
        }
        return true;
    }
    public boolean isDiagonalFree(int newRow, int newCol, ArrayList<Piece> currentPieces){
        for(Piece piece : currentPieces){
            if(Math.abs(piece.col-newCol)==Math.abs(piece.row-newRow) && piece!=this){
                if(piece.row >= newRow && piece.row >= preRow
                    || piece.row <= newRow && piece.row <= preRow) continue;
                if(piece.col >= newCol && piece.col >= preCol
                    || piece.col <= newCol && piece.col <= preCol) continue;
                return false;
            }
        }
        return true;
    }
    private void destroyPiece(Piece piece){
        GamePanel.piecesOnBoard.remove(piece);
    }
    public void takePiece(Piece piece){
        col = piece.col;
        row = piece.row;
        destroyPiece(piece);
        fixPosition();
    }
    public boolean canTake(Piece piece){
        if (piece.color != GamePanel.playersColor && piece.col == col
            && piece.row == row && canMove(getCol(posX), getRow(posY))) {
            return true;
        }
        return false;
    }

    public void fixPosition(){
        posX = getPosX(col);
        posY = getPosY(row);
        preCol = col;
        preRow = row;
    }
}
