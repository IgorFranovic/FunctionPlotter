import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;

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
		this.addMouseWheelListener(new MouseInput());
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
		/*
		if(xmin <= 0 && xmax > 0) {
			int x0 = (int)(-xmin / precX);
			g.drawLine(x0, 0, x0, height);
		}
		if(ymin <= 0 && ymax > 0) {
			int y0 = height - (int)(-ymin / precY);
			g.drawLine(0, y0, width, y0);
		}
		*/
		g.drawLine(width / 2, 0, width / 2, height);
		g.drawLine(0, height / 2, width, height /2);
	}
	
	private void drawFunction(Graphics g) throws Exception {
//		g.setColor(Color.RED);
		
		double absXMIN = Math.abs(xmin);
		double absXMAX = Math.abs(xmax);
		
		double absYMIN = Math.abs(ymin);
		double absYMAX = Math.abs(ymax);
	
		for(int i = 0; i < width; i++) {
			double x1 = xmin + i*precX;
			double y1 = f(x1);
			double x2 = x1 + precX;
			double y2 = f(x2);
			int yStartingPoint = height - (int)((y1 - ymin) / precY);
			int yEndingPoint   = height - (int)((y2 - ymin) / precY);
			
			int offsetY = 0;
			int offsetX = 0;
			
			if(absXMIN < absXMAX) {
				offsetX = (int)((absXMAX - absXMIN) * (width / (absXMIN + absXMAX) / 2));
			} else if(absXMIN > absXMAX) {				
				offsetX = -(int)((absXMIN - absXMAX) * (width / (absXMIN + absXMAX) / 2));			
			} 
			if(absYMIN < absYMAX) {
				offsetY = -(int)((absYMAX - absYMIN) * (height / (absYMIN + absYMAX) / 2));
			} else if(absYMIN > absYMAX) {
				offsetY = (int)((absYMIN - absYMAX) * (height / (absYMIN + absYMAX) / 2));			
			}
			//margin of error
			double moe = 1;
		
			if(Math.abs(Math.abs(yStartingPoint) - Math.abs(yEndingPoint)) < width / 40) {
			if(y2 < ymax * moe && y2 > ymin * moe && x1 < xmax * moe && x1 > xmin * moe) {
				g.drawLine(i + offsetX, yStartingPoint + offsetY, i + 1 + offsetX, yEndingPoint + offsetY);
			}
			
			}

		}
		
	}
	private void drawGrid(Graphics g) {
		g.setColor(Color.black);
		double absXMIN = Math.abs(xmin);
		double absXMAX = Math.abs(xmax);
		
		double absYMIN = Math.abs(ymin);
		double absYMAX = Math.abs(ymax);
		double absMAXx = Math.max(absXMIN, absXMAX);
		double absMAXy = Math.max(absYMIN, absYMAX);
		int halfDim = width / 2;
		double scale = halfDim / absMAXx;
		//x axis grid
		for(int i = (int)xmin; i <= 0; i++) {
			g.drawString(""+i, (int) (halfDim + i*scale - 5) , halfDim + 20);
		}
		for(int i = 0; i <= (int)(xmax); i++) {
			g.drawString(""+i, (int) (halfDim + i*scale - 5) , halfDim + 20);
		}
		scale = halfDim / absMAXy;
		//y axis grid
		for(int i = (int)ymin; i <= 0; i++) {
			if(i != 0)
				g.drawString(""+i, (int) (halfDim + 5) , (int)(halfDim - (i * scale)));	
		}
		for(int i = 0; i <= (int)(ymax); i++) {
			if(i != 0)
				g.drawString(""+i, (int) (halfDim + 5) , (int)(halfDim - (i * scale)));	
		}
	}

	@Override
	protected void paintComponent(Graphics g2) {
		Graphics2D g = (Graphics2D)g2;
		
		g.clearRect(0, 0, width, height);
		drawAxes(g);
		drawGrid(g);
		Random r = new Random();
		g.setStroke(new BasicStroke(2));
		int funcNum = 0;
		g.setFont(new Font("arial", 1, 20));
		try {
			for(Function function : Window.FunctionList) {
				
				reset(function.function, xmin, xmax, ymin, ymax);
				g.setColor(function.color);
				g.drawString(function.function, 50, 50 + (funcNum * 30));
				drawFunction(g);
				funcNum ++;
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
			g.setFont(new Font("courier", 1, 40));
			g.drawString("ERROR", this.getWidth()-20, this.getHeight()/2);
		}
	}
	
}
