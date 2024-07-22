public class Queen extends Piece{

    public Queen(int row, int col, int color) {
        super(row, col, color);
        if(color == GamePanel.whitePiece){
            image = getImage("/Pieces/QueenWhite.png");
        }
        if(color == GamePanel.blackPiece){
            image = getImage("/Pieces/QueenBlack.png");
        }
    }
    @Override
    public boolean canMove(int newCol, int newRow){
        if(((newCol-preCol)!=0 || (newRow-preRow)!=0) && ((newRow==preRow) || (newCol==preCol))){
            if(newRow!=preRow && isColFree(newRow, GamePanel.piecesOnBoard)) return true;
            if(newCol!=preCol && isRowFree(newCol, GamePanel.piecesOnBoard)) return true;
        }
        if(Math.abs(preCol-newCol)==Math.abs(preRow-newRow)
                && isDiagonalFree(newRow, newCol, GamePanel.piecesOnBoard)) return true;
        return false;
    }
}
