import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Mouse extends MouseAdapter {
    int x, y;
    public boolean pressed = false;
    @Override
    public void mousePressed(MouseEvent event){
        pressed = true;
    }
    @Override
    public void mouseReleased(MouseEvent event){
        pressed = false;
    }
    @Override
    public void mouseDragged(MouseEvent event){
        y = event.getY();
        x = event.getX();
    }
    @Override
    public void mouseMoved(MouseEvent event){
        y = event.getY();
        x = event.getX();
    }

}
