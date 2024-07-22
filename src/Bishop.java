public class Bishop extends Piece{
    public Bishop(int row, int col, int color) {
        super(row, col, color);
        if(color == GamePanel.whitePiece){
            image = getImage("/Pieces/BishopWhite.png");
        }
        if(color == GamePanel.blackPiece){
            image = getImage("/Pieces/BishopBlack.png");
        }
    }
    @Override
    public boolean canMove(int newCol, int newRow){
        if(Math.abs(preCol-newCol)==Math.abs(preRow-newRow)
                && isDiagonalFree(newRow, newCol, GamePanel.piecesOnBoard)) return true;
        return false;
    }
}
