import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Window extends JFrame {
	// code for the GUI of the program
	
	public static Color[] colorArray;
	
	public static TextAreaCustom JTextAreaFunction;
	public static TextAreaCustom JTextAreaMinX;
	public static TextAreaCustom JTextAreaMaxX;
	public static TextAreaCustom JTextAreaMinY;
	public static TextAreaCustom JTextAreaMaxY;
	
	//public static JSlider zoomSlider;
	//public static int zoomPercentage = 0;
	
	static Random random;
	
	public static JButtonCustom JButtonDraw;
	public static JButtonCustom JButtonUndo;
	public static JButtonCustom JButtonLink;
	public static JButtonCustom JButtonClear;
	public static JButtonCustom JButtonInfo;
	public static JButtonCustom JButtonInfoImage;
	public static JButtonCustom JButtonResetZoom;
	
	public static JLabel JLabelTitle;
	public static JLabel JLabelValuesX;
	public static JLabel JLabelValuesY;
	public static JLabel JLabelZoom;
	public static JLabel JLabelAuthors;
	public static JLabel JLabelCodeLink;
	
	public static boolean JButtonInfoPressed = false;
	

	
	public static LinkedList<Function> FunctionList = new LinkedList<Function>();
	
	public static Plotter plotter;
	private int plotterDimension;
	public static MouseInput mouseInput;
	
	public static TextAreaCustom [] textAreaArray;
	public static int textAreaSelected = 0;
	private double xMin, xMax, yMin, yMax;
	
	public Window(String windowName, int WIDTH, int HEIGHT) {
		
		
		
		random = new Random();
		colorArray = new Color[6];
		colorArray[0] = Color.yellow; colorArray[1] = Color.blue; colorArray[2] = Color.GRAY; colorArray[3] = Color.BLACK; colorArray[4] = Color.GREEN; colorArray[5] = Color.DARK_GRAY;
		this.setSize(new Dimension(WIDTH, HEIGHT));
		this.setTitle(windowName);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		
		
		//CHANGE INIT FUNCTION HERE
		String initFunction = "x^2";
		
		
		FunctionList.add(new Function(initFunction, Color.red));
		
		//JTextAreaFunction
		JTextAreaFunction = new TextAreaCustom(initFunction, "Enter your function here! ", 5, 1.5, 10, 1);
		this.add(JTextAreaFunction);
		
		//JTextAreaMinX
		JTextAreaMinX = new TextAreaCustom("-5", "MinX", 7.5, 6, 2, 0.5);
		this.add(JTextAreaMinX);
		
		//JTextAreaMaxX
		JTextAreaMaxX = new TextAreaCustom("5", "MaxX", 10.5, 6, 2, 0.5);
		this.add(JTextAreaMaxX);
		
		//JTextAreaMinY
		JTextAreaMinY = new TextAreaCustom("-5", "MinY", 7.5, 9, 2, 0.5);
		this.add(JTextAreaMinY);

		//JTextAreaMaxY
		JTextAreaMaxY = new TextAreaCustom("5", "MaxY", 10.5, 9, 2, 0.5);
		this.add(JTextAreaMaxY);
	
		//JButtonDraw
		JButtonDraw = new JButtonCustom("JButtonDraw", "resources/imgDraw.png", "Draw the function!");
		JButtonDraw.setBounds(140, 120, 120, 40);
		this.add(JButtonDraw);
		
		//JButtonUndo
		JButtonUndo = new JButtonCustom("JButtonUndo", "resources/imgUndo.png", "Undo last draw!");
		JButtonUndo.setBounds(140, 600, 120, 40);
		this.add(JButtonUndo);
			
		//JButtonLink
		JButtonLink = new JButtonCustom("JButtonLink", "resources/imgGit.png", "Visit code location!");
		JButtonLink.setBounds(150, 750, 100, 50);
		this.add(JButtonLink);
		
		//JButtonClear
		JButtonClear = new JButtonCustom("JButtonClear", "resources/imgClear.png", "Left click to clear function field. Right click to clear x and y values!");
		JButtonClear.setBounds(330, 70, 20, 20);
		this.add(JButtonClear);

		//JButtonInfo
		JButtonInfo = new JButtonCustom("JButtonInfo", "resources/imgInfo.png", "Click here for instructions!");
		JButtonInfo.setBounds(175, 405, 40, 40);
		this.add(JButtonInfo);
		
		//JButtonInfoImage
		JButtonInfoImage = new JButtonCustom("JButtonInfoImage", "resources/imgInfoFunctions.png", "");
		JButtonInfoImage.setBounds(47, 265, 600, 700);
		JButtonInfoImage.setVisible(false);
		this.add(JButtonInfoImage);
		
		//JButtonResetZoom
		JButtonResetZoom = new JButtonCustom("JButtonResetZoom", "resources/imgResetZoom.png", "Reset the position Preset!");
		JButtonResetZoom.setBounds(175, 505, 40, 40);
		this.add(JButtonResetZoom);
	
		
		// change initFunction on line 75
		
		
		//JSlider
		//zoomSlider = new JSlider(JSlider.HORIZONTAL, 1, 10, 1);
		//zoomSlider.setBounds(50, 500, 300, 40);
		//this.add(zoomSlider);
		
		
		//Plotter
		plotterDimension = 800;
		this.xMin = Double.parseDouble(JTextAreaMinX.getText());
		this.xMax = Double.parseDouble(JTextAreaMaxX.getText());
		this.yMin = Double.parseDouble(JTextAreaMinY.getText());
		this.yMax = Double.parseDouble(JTextAreaMaxY.getText());
		
		plotter = new Plotter(initFunction, plotterDimension, plotterDimension, xMin, xMax, yMin, yMax);
		plotter.setBounds(400, 0, 1200, 800); 
		plotter.setPreferredSize(new Dimension(plotterDimension, plotterDimension));
		this.add(plotter);
		
		mouseInput = new MouseInput(plotter);
		plotter.addMouseListener(mouseInput);
		plotter.addMouseWheelListener(mouseInput);
		plotter.addMouseMotionListener(mouseInput);
		
		//JLabels
		JLabelTitle = new JLabel("Function Plotter");
		JLabelTitle.setFont(new Font("serif", 1, 25));
		JLabelTitle.setForeground(new Color(0, 132, 204));
		JLabelTitle.setBounds(115, 0, 250, 50);
		this.add(JLabelTitle);
		
		
		JLabelValuesX = new JLabel("(min X, max X)");
		JLabelValuesX.setFont(new Font("helvetica", 1, 12));
		JLabelValuesX.setForeground(Color.BLACK);
		JLabelValuesX.setBounds(160, 200, 250, 50);
		this.add(JLabelValuesX);
		
		JLabelValuesY = new JLabel("(min Y, max Y)");
		JLabelValuesY.setFont(new Font("helvetica", 1, 12));
		JLabelValuesY.setForeground(Color.BLACK);
		JLabelValuesY.setBounds(160, 320, 250, 50);
		this.add(JLabelValuesY);
		
		JLabelZoom = new JLabel("Zoom");
		JLabelZoom.setFont(new Font("helvetica", 1, 12));
		JLabelZoom.setForeground(Color.BLACK);
		JLabelZoom.setBounds(180, 465, 250, 50);
//		this.add(JLabelZoom);
		
		JLabelAuthors = new JLabel("Created by Nikola Pižurica and Igor Franović");
		JLabelAuthors.setFont(new Font("arial", 1, 10));
		JLabelAuthors.setForeground(Color.GRAY);
		JLabelAuthors.setBounds(80, 715, 250, 50);
		this.add(JLabelAuthors);
		
	
				
		textAreaArray = new TextAreaCustom[5];
		
		textAreaArray[0] = JTextAreaFunction;
		textAreaArray[1] = JTextAreaMinX;
		textAreaArray[2] = JTextAreaMaxX;
		textAreaArray[3] = JTextAreaMinY;
		textAreaArray[4] = JTextAreaMaxY;
		
		setIcon("resources/applicationIcon.png");
		
		this.setVisible(true);
		
	
	}
	
	
	public static class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			String actionCommand = event.getActionCommand();
			
			switch(actionCommand) {
				
				case "JButtonDraw" : {
					
					
					String function = JTextAreaFunction.getText();
					
					//Don't draw the same function twice in a row
					if(FunctionList.size() > 0 && function.equalsIgnoreCase(FunctionList.get(FunctionList.size()-1).function)) 
						break;
					
					Color col = Color.red;
					if(FunctionList.size() > 0) 
						col = colorArray[(FunctionList.size() - 1) % colorArray.length];
					
					FunctionList.add(new Function(function, col));
					double xmin = Double.parseDouble(JTextAreaMinX.getText());
					double xmax = Double.parseDouble(JTextAreaMaxX.getText());
					double ymin = Double.parseDouble(JTextAreaMinY.getText());
					double ymax = Double.parseDouble(JTextAreaMaxY.getText());
					
					mouseInput.setAll(xmin, xmax + 0.1, ymin, ymax + 0.1);
					plotter.reset(function, xmin, xmax + 0.1, ymin, ymax + 0.1);
					//plotter.repaint();
					mouseInput.zoomOut(xmin, ymin);
					mouseInput.zoomIn(xmin, ymin);
					
					
				} break;
				case "JButtonUndo" : {
					if(FunctionList.size() > 0) {
						FunctionList.removeLast();
						plotter.repaint();
					}
				} break;
				case "JButtonLink" : {
					openWebpage("https://github.com/IgorFranovic/FunctionPlotter");
				} break;
				case "JButtonClear" : {
					JTextAreaFunction.setText("");
					JTextAreaFunction.grabFocus();
				} break;
				case "JButtonInfo" : {
					JButtonInfoImage.setVisible(!JButtonInfoImage.isVisible());
				} break;
				case "JButtonResetZoom" : {
					double xmin = -5;
					double xmax = 5.1;
					double ymin = -5;
					double ymax = 5.1;
					Window.textAreaArray[1].setText("-5");
					Window.textAreaArray[2].setText("5");
					Window.textAreaArray[3].setText("-5");
					Window.textAreaArray[4].setText("5");
					plotter.reset(plotter.getFunction(), xmin, xmax, ymin, ymax);
					plotter.repaint();
					
					mouseInput.setAll(xmin, xmax, ymin, ymax);
					plotter.reset(plotter.getFunction(), xmin, xmax, ymin, ymax);
					//plotter.repaint();
					mouseInput.zoomOut(xmin, ymin);
					mouseInput.zoomIn(xmin, ymin);
				} break;
			}
			
		}
		public static void openWebpage(String urlString) {
		    try {
		        Desktop.getDesktop().browse(new URL(urlString).toURI());
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
	}
	
	public void setIcon(String icon) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource(icon)));
	}
}
