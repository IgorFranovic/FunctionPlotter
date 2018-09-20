import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;


public class Plotter extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 75304563973285884L;
	
	private String function;
	
	private Parser parser;
	
	private int width, height;
	private double xmin, xmax, ymin, ymax;
	private double precX, precY;
	
	public String getFunction() {
		return function;
	}
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public double getXmin() {
		return xmin;
	}

	public double getXmax() {
		return xmax;
	}

	public double getYmin() {
		return ymin;
	}

	public double getYmax() {
		return ymax;
	}

	public double getPrecX() {
		return precX;
	}

	public double getPrecY() {
		return precY;
	}
	
	public Plotter(String function, int width, int height, double xmin, double xmax, double ymin, double ymax) {
		this.function = function;
		this.parser = new Parser(function);
		this.width = width;
		this.height = height;
		this.xmin = xmin;
		this.xmax = xmax;
		this.ymin = ymin;
		this.ymax = ymax;
		this.precX = (xmax - xmin) / width;
		this.precY = (ymax - ymin) / height;
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
	
	private void drawGrid(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.setFont(new Font("arial", 1, 20));
		int segmentX, segmentY;
		if((xmax - xmin)/10 >= 1) {
			segmentX = (int)((xmax - xmin)/10);
		}
		else {
			segmentX = 1;
		}
		if((ymax - ymin)/10 >= 1) {
			segmentY = (int)((ymax - ymin)/10);
		}
		else {
			segmentY = 1;
		}
		int x0, y0;
		BasicStroke b1 = new BasicStroke(1);
		BasicStroke b2 = new BasicStroke(2);
		if(xmin <= 0 && xmax > 0) {
			x0 = (int)(-xmin / precX);
			g.setColor(Color.BLACK);
			g.setStroke(b2);
			g.drawLine(x0, 0, x0, height);
		}
		else {
			x0 = 20;
		}
		g.setStroke(b1);
		for(int i = (int)ymin; i <= ymax; i += segmentY) {
			int y = height - (int)((i - ymin) / precY);
			g.setColor(Color.BLACK);
			g.drawString(Integer.toString(i), x0-20, y+20);
			g.setColor(Color.LIGHT_GRAY);
			g.drawLine(0, y, width, y);
		}
		if(ymin <= 0 && ymax > 0) {
			y0 = height - (int)(-ymin / precY);
			g.setColor(Color.BLACK);
			g.setStroke(b2);
			g.drawLine(0, y0, width, y0);
		}
		else {
			y0 = height-20;
		}
		g.setStroke(b1);
		for(int i = (int)xmin; i <= xmax; i += segmentX) {
			int x = (int)((i - xmin) / precX);
			g.setColor(Color.BLACK);
			g.drawString(Integer.toString(i), x-20, y0+20);
			g.setColor(Color.LIGHT_GRAY);
			g.drawLine(x, 0, x, height);
		}
	}
	
	private void drawFunction(Graphics g) throws Exception {
		double x1, y1, x2, y2, prevDeltaY, currDeltaY, nextDeltaY;
		int yStartingPoint, yEndingPoint;
		boolean discontinuity = false;
		prevDeltaY = 0;
		for(int i = 0; i < width; i++) {
			x1 = xmin + i*precX;
			y1 = f(x1);
			x2 = x1 + precX;
			y2 = f(x2);
			currDeltaY = y2-y1;
			discontinuity = false;
			if((y1 < ymin && y2 > ymax) || (y1 > ymax && y2 < ymin)) {
				discontinuity = true;
			}
			else if(currDeltaY*prevDeltaY < 0 || Math.abs(currDeltaY) > Math.abs(prevDeltaY)) {
				double y1Next, y2Next;
				y1Next = f(x1 + precX/width);
				y2Next = f(x2 - precX/width);
				nextDeltaY = y2Next - y1Next;
				if(Math.abs(nextDeltaY) >= Math.abs(currDeltaY)) {
					discontinuity = true;
				}
			}
			if(!discontinuity) {
				prevDeltaY = currDeltaY;
				yStartingPoint = height - (int)((y1 - ymin) / precY);
				yEndingPoint = height - (int)((y2 - ymin) / precY);
				g.drawLine(i, yStartingPoint, i+1, yEndingPoint);
			}
			else {
				prevDeltaY = 0;
			}
		}
		
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		g2d.clearRect(0, 0, width, height);
		drawGrid(g2d);
		g2d.setFont(new Font("arial", 1, 20));
		g2d.setStroke(new BasicStroke(2));
		int funcNum = 0;
		try {
			for(Function function : Window.FunctionList) {
				reset(function.function, xmin, xmax, ymin, ymax);
				g2d.setColor(function.color);
				g2d.drawString(function.function, 50, 50 + (funcNum * 30));
				drawFunction(g2d);
				funcNum ++;
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
