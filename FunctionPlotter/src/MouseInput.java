import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;


public class MouseInput extends MouseAdapter {
	@Override
	public void mousePressed(MouseEvent e) {
		
		System.out.println("MP at : (" + e.getX() + ", " + e.getY() + ")");
	
	}
	@Override
    public void mouseWheelMoved(MouseWheelEvent e) {
		if (e.getWheelRotation() < 0) {
            System.out.println("Rotated Up... " + e.getWheelRotation());
            Window.zoomSlider.setValue(Window.zoomSlider.getValue() + 1);
        } else {
            System.out.println("Rotated Down... " + e.getWheelRotation());
            Window.zoomSlider.setValue(Window.zoomSlider.getValue() - 1);
        }
    }
}
