import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;


public class MouseInput extends MouseAdapter {
	
	private Plotter plotter;
	
	private double xmin, xmax, ymin, ymax;
	
	private double lastX, lastY;
	private double zoomStep, dragStep;
	
	public MouseInput(Plotter plotter) {
		this.plotter = plotter;
		this.xmin = plotter.getXmin();
		this.xmax = plotter.getXmax();
		this.ymin = plotter.getYmin();
		this.ymax = plotter.getYmax();
		this.zoomStep = 0.1;
		this.dragStep = 0.5;
	}
	
	public void setAll(double xmin, double xmax, double ymin, double ymax) {
		this.xmin = xmin;
		this.xmax = xmax;
		this.ymin = ymin;
		this.ymax = ymax;
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		lastX = xmin + e.getX()*plotter.getPrecX();
		lastY = ymax - e.getY()*plotter.getPrecY();
		//System.out.printf("(%.2f, %.2f)\n", lastX, lastY);
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		double currX = xmin + e.getX()*plotter.getPrecX();
		double currY = ymax - e.getY()*plotter.getPrecY();
		double deltaX = dragStep*(currX - lastX);
		double deltaY = dragStep*(currY - lastY);
		xmin = xmin - deltaX;
		xmax = xmax - deltaX;
		ymin = ymin - deltaY;
		ymax = ymax - deltaY;
		Window.textAreaArray[1].setText(String.format("%.2f", xmin));
		Window.textAreaArray[2].setText(String.format("%.2f", xmax));
		Window.textAreaArray[3].setText(String.format("%.2f", ymin));
		Window.textAreaArray[4].setText(String.format("%.2f", ymax));
		plotter.reset(plotter.getFunction(), xmin, xmax, ymin, ymax);
		plotter.repaint();
		lastX = currX;
		lastY = currY;
		//System.out.printf("(%.2f, %.2f)\n", lastX, lastY);
	}
	
	public void zoomIn(double x, double y) {
		xmin = xmin + zoomStep*(x - xmin);
		xmax = xmax - zoomStep*(xmax - x);
		ymin = ymin + zoomStep*(y - ymin);
		ymax = ymax - zoomStep*(ymax - x);
		Window.textAreaArray[1].setText(String.format("%.2f", xmin));
		Window.textAreaArray[2].setText(String.format("%.2f", xmax));
		Window.textAreaArray[3].setText(String.format("%.2f", ymin));
		Window.textAreaArray[4].setText(String.format("%.2f", ymax));
		plotter.reset(plotter.getFunction(), xmin, xmax, ymin, ymax);
		plotter.repaint();
	}
	
	public void zoomOut(double x, double y) {
		xmin = xmin - zoomStep*(x - xmin);
		xmax = xmax + zoomStep*(xmax - x);
		ymin = ymin - zoomStep*(y - ymin);
		ymax = ymax + zoomStep*(ymax - x);
		Window.textAreaArray[1].setText(String.format("%.2f", xmin));
		Window.textAreaArray[2].setText(String.format("%.2f", xmax));
		Window.textAreaArray[3].setText(String.format("%.2f", ymin));
		Window.textAreaArray[4].setText(String.format("%.2f", ymax));
		plotter.reset(plotter.getFunction(), xmin, xmax, ymin, ymax);
		plotter.repaint();
	}
	
	@Override
    public void mouseWheelMoved(MouseWheelEvent e) {
		double x = xmin + e.getX()*plotter.getPrecX();
		double y = ymax - e.getY()*plotter.getPrecY();
		if (e.getWheelRotation() < 0) {
            // rotated up
			zoomIn(x, y);
        } else {
            // rotated down
        	zoomOut(x, y);
        }
    }
}
