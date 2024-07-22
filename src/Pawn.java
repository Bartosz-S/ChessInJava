public class Pawn extends Piece{
    private boolean firstMove;
    public Pawn(int row, int col, int color){
        super(row, col, color);
        if(color == GamePanel.whitePiece){
            image = getImage("/Pieces/PawnWhite.png");
        }
        if(color == GamePanel.blackPiece){
            image = getImage("/Pieces/PawnBlack.png");
        }
        firstMove = true;
    }
    @Override
    public boolean canMove(int newCol, int newRow){
        if(preCol==newCol && firstMove && (preRow-newRow==2 || preRow-newRow==1)){
            if(!isColFree(newRow, GamePanel.piecesOnBoard)) return false;
            firstMove = false;
            return true;
        }
        if(newCol==preCol && preRow-newRow==1) return true;
        return false;
    }
    @Override
    public boolean canTake(Piece piece){
        if(piece.getClass()==Pawn.class){

        }
        if (piece.color != GamePanel.playersColor && piece.col == col
                && piece.row == row && Math.abs(piece.col-preCol)==1 ) {
            if(preRow-piece.row==1) return true;
            //if(piece.getClass()==Pawn.class && preRow-piece.row==2) return true;
        }
        return false;
    }
}
