import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;


public class MouseInput extends MouseAdapter {
	
	private Plotter plotter;
	
	public MouseInput(Plotter plotter) {
		this.plotter = plotter;
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		double x = plotter.getXmin() + e.getX()*plotter.getPrecX();
		double y = plotter.getYmax() - e.getY()*plotter.getPrecY();
		System.out.printf("(%.2f, %.2f)\n", x, y);
	}
	
	@Override
    public void mouseWheelMoved(MouseWheelEvent e) {
		double zoomStep = 0.1;
		double xmin = plotter.getXmin();
		double xmax = plotter.getXmax();
		double ymin = plotter.getYmin();
		double ymax = plotter.getYmax();
		double precX = plotter.getPrecX();
		double precY = plotter.getPrecY();
		double x = xmin + e.getX()*precX;
		double y = ymax - e.getY()*precY;
		double newXmin, newXmax, newYmin, newYmax;
		if (e.getWheelRotation() < 0) {
            // rotated up
			newXmin = xmin + zoomStep*(x - xmin);
			newXmax = xmax - zoomStep*(xmax - x);
			newYmin = ymin + zoomStep*(y - ymin);
			newYmax = ymax - zoomStep*(ymax - x);
        } else {
            // rotated down
        	newXmin = xmin - zoomStep*(x - xmin);
			newXmax = xmax + zoomStep*(xmax - x);
			newYmin = ymin - zoomStep*(y - ymin);
			newYmax = ymax + zoomStep*(ymax - x);
        }
		plotter.reset(plotter.getFunction(), newXmin, newXmax, newYmin, newYmax);
		plotter.repaint();
    }
}
