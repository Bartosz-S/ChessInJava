public class Rook extends Piece{

    public Rook(int row, int col, int color) {
        super(row, col, color);
        if(color == GamePanel.whitePiece){
            image = getImage("/Pieces/RookWhite.png");
        }
        if(color == GamePanel.blackPiece){
            image = getImage("/Pieces/RookBlack.png");
        }
    }
    @Override
    public boolean canMove(int newCol, int newRow){
        if(((newCol-preCol)!=0 || (newRow-preRow)!=0) && ((newRow==preRow) || (newCol==preCol))){
            if(newRow!=preRow && isColFree(newRow, GamePanel.piecesOnBoard)) return true;
            if(newCol!=preCol && isRowFree(newCol, GamePanel.piecesOnBoard)) return true;
        }
        return false;
    }
}
