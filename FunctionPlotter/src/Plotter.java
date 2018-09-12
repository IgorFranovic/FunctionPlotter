import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Plotter extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 75304563973285884L;
	
	private Parser parser;
	
	private int width, height;
	private double xmin, xmax, ymin, ymax;
	private double precX, precY;

	public Plotter(String function, int width, int height, double xmin, double xmax, double ymin, double ymax) {
		this.parser = new Parser(function);
		this.width = width;
		this.height = height;
		this.xmin = xmin;
		this.xmax = xmax;
		this.ymin = ymin;
		this.ymax = ymax;
		this.precX = (xmax - xmin) / width;
		this.precY = (ymax - ymin) / height;
		
		this.addMouseListener(new MouseInput());
	}
	
	public void reset(String function, double xmin, double xmax, double ymin, double ymax) {
		this.parser = new Parser(function);
		this.xmin = xmin;
		this.xmax = xmax;
		this.ymin = ymin;
		this.ymax = ymax;
		this.precX = (xmax - xmin) / width;
		this.precY = (ymax - ymin) / height;
	}
	
	private double f(double x) throws Exception {
		return parser.eval(x);
	}
	
	private void drawAxes(Graphics g) {
		g.setColor(Color.BLACK);
		if(xmin <= 0 && xmax > 0) {
			int x0 = (int)(-xmin / precX);
			g.drawLine(x0, 0, x0, height);
		}
		if(ymin <= 0 && ymax > 0) {
			int y0 = height - (int)(-ymin / precY);
			g.drawLine(0, y0, width, y0);
		}
	}
	
	private void drawFunction(Graphics g) throws Exception {
		g.setColor(Color.RED);
		for(int i = 0; i < width; i++) {
			double x1 = xmin + i*precX;
			double y1 = f(x1);
			double x2 = x1 + precX;
			double y2 = f(x2);
			g.drawLine(i, height - (int)((y1 - ymin) / precY), i+1, height - (int)((y2 - ymin) / precY));
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		g.clearRect(0, 0, width, height);
		drawAxes(g);
		try {
			drawFunction(g);
		}
		catch (Exception e) {
			System.out.println("ne valja");
			g.setFont(new Font("courier", 1, 40));
			g.drawString("ERROR", this.getWidth()-20, this.getHeight()/2);
		}
	}
	
}
