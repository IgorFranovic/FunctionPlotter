import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class MouseInput extends MouseAdapter {
	@Override
	public void mousePressed(MouseEvent e) {
		
		System.out.println("MP at : (" + e.getX() + ", " + e.getY() + ")");
	
	}
}