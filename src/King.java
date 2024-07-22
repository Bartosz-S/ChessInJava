public class King extends Piece{
    public King(int row, int col, int color) {
        super(row, col, color);
        if(color == GamePanel.whitePiece){
            image = getImage("/Pieces/KingWhite.png");
        }
        if(color == GamePanel.blackPiece){
            image = getImage("/Pieces/KingBlack.png");
        }
    }
    public boolean isUnderCheck(){
        for(Piece piece : GamePanel.piecesOnBoard){
            if(piece.canMove(col, row) && piece.color!=color) return true;
        }
        return false;
    }
    @Override
    public boolean canMove(int newCol, int newRow){
        if(Math.abs(col - preCol) <= 1 && Math.abs(row - preRow) <= 1) return true;
        return false;
    }
    @Override
    public boolean canTake(Piece piece){
        if (piece.color != GamePanel.playersColor && piece.col == col
                && piece.row == row) {
            return true;
        }
        return false;
    }
}
