import java.awt.*;

public class Board {
    final int maxRow = 8;
    final int maxCol = 8;
    public static final int squareSize = 100;

    public void draw(Graphics2D graphics2D){
        int color;
        for(int row = 0; row < maxRow; row++){
            for(int col=0; col < maxCol; col++){
                if(row%2 == 0){
                    color = col%2;
                }
                else color = (col+1)%2;
                switch (color){
                    case 0: graphics2D.setColor(Color.GRAY); break;
                    case 1: graphics2D.setColor(Color.DARK_GRAY);
                }
                graphics2D.fillRect(col*squareSize, row*squareSize, squareSize, squareSize);
            }

        }
    }
}
