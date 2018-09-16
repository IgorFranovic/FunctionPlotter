import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JSlider;

public class Window extends JFrame {
	// code for the GUI of the program
	
	public static Color[] colorArray;
	
	public static TextAreaCustom JTextAreaFunction;
	public static TextAreaCustom JTextAreaMinX;
	public static TextAreaCustom JTextAreaMaxX;
	public static TextAreaCustom JTextAreaMinY;
	public static TextAreaCustom JTextAreaMaxY;
	
	public static JSlider zoomSlider;
	public static int zoomPercentage = 0;
	
	static Random random;
	
	public static JButton JButtonDraw;
	public static JButton JButtonUndo;
	
	public static LinkedList<Function> FunctionList = new LinkedList<Function>();
	
	public static Plotter plotter;
	private int plotterDimension;
	
	public static TextAreaCustom [] textAreaArray;
	public static int textAreaSelected = 0;
	private double xMin, xMax, yMin, yMax;
	
	public Window(String windowName, int WIDTH, int HEIGHT) {
		
		random = new Random();
		colorArray = new Color[5];
		colorArray[0] = Color.yellow; colorArray[1] = Color.blue; colorArray[2] = Color.GRAY; colorArray[3] = Color.BLACK; colorArray[4] = Color.GREEN;
		this.setSize(new Dimension(WIDTH, HEIGHT));
		this.setTitle(windowName);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		
		//CHANGE INIT FUNCTION HERE
		String initFunction = "x^2";
		
//		Color col = new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
		
		FunctionList.add(new Function(initFunction, Color.red));
		
		//JTextAreaFunction
		JTextAreaFunction = new TextAreaCustom(initFunction, "Enter your function here! ", 3, 1, 10, 1);
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
		
		//JButtonUndo
		JButtonUndo = new JButton("Undo");
		JButtonUndo.setBounds(100, 600, 120, 40);
		JButtonUndo.setActionCommand("JButtonUndo");
		JButtonUndo.addKeyListener(new KeyInput());
		JButtonUndo.addActionListener(new ButtonListener());
		this.add(JButtonUndo);
		
		
		plotterDimension = 800;
		this.xMin = Double.parseDouble(JTextAreaMinX.getText());
		this.xMax = Double.parseDouble(JTextAreaMaxX.getText());
		this.yMin = Double.parseDouble(JTextAreaMinY.getText());
		this.yMax = Double.parseDouble(JTextAreaMaxY.getText());
		
		// change initFunction on line 40
		
		//JSlider
		zoomSlider = new JSlider(JSlider.HORIZONTAL, 1, 10, 1);
		zoomSlider.setBounds(50, 500, 300, 40);
		this.add(zoomSlider);
		
		
		//Plotter
		plotter = new Plotter(initFunction, plotterDimension, plotterDimension, xMin, xMax, yMin, yMax);
		plotter.setBounds(400, 0, 1200, 800); 
		plotter.setPreferredSize(new Dimension(plotterDimension, plotterDimension));
		this.add(plotter);
				
		textAreaArray = new TextAreaCustom[5];
		
		textAreaArray[0] = JTextAreaFunction;
		textAreaArray[1] = JTextAreaMinX;
		textAreaArray[2] = JTextAreaMaxX;
		textAreaArray[3] = JTextAreaMinY;
		textAreaArray[4] = JTextAreaMaxY;
		
		this.setVisible(true);
	}
	
	public static class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			String actionCommand = event.getActionCommand();
			
			switch(actionCommand) {
				
				case "JButtonDraw" : {
					
					//add code for drawing the function here
					
					String function = JTextAreaFunction.getText();
//					Color col = new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
					Color col = colorArray[FunctionList.size() - 1];
					FunctionList.add(new Function(function, col));
					double xmin = Double.parseDouble(JTextAreaMinX.getText());
					double xmax = Double.parseDouble(JTextAreaMaxX.getText());
					double ymin = Double.parseDouble(JTextAreaMinY.getText());
					double ymax = Double.parseDouble(JTextAreaMaxY.getText());
					
					plotter.reset(function, xmin, xmax, ymin, ymax);
					plotter.repaint();
					
					
				} break;
				case "JButtonUndo" : {
					FunctionList.removeLast();
					plotter.repaint();
				}
			}
			
		}
		
	}
	
	
	
}
