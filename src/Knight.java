public class Knight extends Piece{

    public Knight(int row, int col, int color) {
        super(row, col, color);
        if(color == GamePanel.whitePiece){
            image = getImage("/Pieces/KnightWhite.png");
        }
        if(color == GamePanel.blackPiece){
            image = getImage("/Pieces/KnightBlack.png");
        }
    }
    @Override
    public boolean canMove(int newCol, int newRow){
        if((Math.abs(newCol-preCol)==1 && Math.abs(newRow-preRow)==2)
        || (Math.abs(newRow-preRow)==1 && Math.abs(newCol-preCol)==2)) return true;
        return false;
    }
}
