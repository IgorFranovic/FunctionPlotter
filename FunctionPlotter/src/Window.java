import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class Window extends JFrame {
	// code for the GUI of the program
	
	
	public static TextAreaCustom JTextAreaFunction;
	public static TextAreaCustom JTextAreaMinX;
	public static TextAreaCustom JTextAreaMaxX;
	public static TextAreaCustom JTextAreaMinY;
	public static TextAreaCustom JTextAreaMaxY;
	
	
	public static JButton JButtonDraw;
	
	
	public static Plotter plotter;
	private int plotterDimension;
	
	

	public Window(String windowName, int WIDTH, int HEIGHT) {
		
		this.setSize(new Dimension(WIDTH, HEIGHT));
		this.setTitle(windowName);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
//		this.setLayout(new FlowLayout());
		this.setLayout(null);
		
		
		
		
		//JTextAreaFunction
		JTextAreaFunction = new TextAreaCustom("", "Enter your function here! ", 3, 1, 10, 1);
		this.add(JTextAreaFunction);
		
		//JTextAreaMinX
		JTextAreaMinX = new TextAreaCustom("-5", "MinX", 5, 6, 2, 0.5);
		this.add(JTextAreaMinX);
		
		//JTextAreaMaxX
		JTextAreaMaxX = new TextAreaCustom("5", "MaxX", 8, 6, 2, 0.5);
		this.add(JTextAreaMaxX);
		
		//JTextAreaMinY
		JTextAreaMinY = new TextAreaCustom("-5", "MinY", 5, 9, 2, 0.5);
		this.add(JTextAreaMinY);

		//JTextAreaMaxY
		JTextAreaMaxY = new TextAreaCustom("5", "MaxY", 8, 9, 2, 0.5);
		this.add(JTextAreaMaxY);
		
		//JButtonDraw
		JButtonDraw = new JButton("Draw");
		JButtonDraw.setBounds(100, 120, 120, 40);
		JButtonDraw.setActionCommand("JButtonDraw");
		JButtonDraw.addKeyListener(new KeyInput());
		JButtonDraw.addActionListener(new ButtonListener());
		this.add(JButtonDraw);
		
		
		plotterDimension = WIDTH - 400;
		
		//Plotter
		plotter = new Plotter(plotterDimension, plotterDimension, -5, 5, -5, 5);
		plotter.setBounds(400, 0, 1200, 800);
		plotter.setPreferredSize(new Dimension(plotterDimension, plotterDimension));
		this.add(plotter);
		
		
		this.setVisible(true);
	}
	
	public static class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			String actionCommand = event.getActionCommand();
			
			switch(actionCommand) {
				
				case "JButtonDraw" : {
					
					//add code for drawing the function here
					
					double xmin = Double.parseDouble(JTextAreaMinX.getText());
					double xmax = Double.parseDouble(JTextAreaMaxX.getText());
					double ymin = Double.parseDouble(JTextAreaMinY.getText());
					double ymax = Double.parseDouble(JTextAreaMaxY.getText());
					
					plotter.reset(xmin, xmax, ymin, ymax);
					plotter.repaint();
					
					
				} break;
			}
			
		}
		
	}
	
	
}
