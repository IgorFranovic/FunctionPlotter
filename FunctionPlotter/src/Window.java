import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Window extends JFrame {
	// code for the GUI of the program
	
	
	public static JTextArea JTextAreaFunction;
	public static JTextArea JTextAreaMinX;
	public static JTextArea JTextAreaMaxX;
	public static JTextArea JTextAreaMinY;
	public static JTextArea JTextAreaMaxY;
	
	
	public static JButton JButtonDraw;
	
	public Window(String windowName, int WIDTH, int HEIGHT) {
		
		this.setSize(new Dimension(WIDTH, HEIGHT));
		this.setTitle(windowName);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
//		this.addKeyListener(new KeyInput());
		
		
		
		//JTextAreaFunction
		JTextAreaFunction = new JTextArea("Enter your function here! ");
		JTextAreaFunction.setBounds(30, 50, 120, 40);
		this.add(JTextAreaFunction);
		
		//JTextAreaMinX
		JTextAreaMinX = new JTextArea();
		JTextAreaMinX.setBounds(30, 200, 50, 40);
		this.add(JTextAreaMinX);
		
		//JTextAreaMaxX
		JTextAreaMaxX = new JTextArea();
		JTextAreaMaxX.setBounds(100, 200, 50, 40);
		this.add(JTextAreaMaxX);
		
		//JTextAreaMinY
		JTextAreaMinY = new JTextArea();
		JTextAreaMinY.setBounds(30, 300, 50, 40);
		this.add(JTextAreaMinY);

		//JTextAreaMaxY
		JTextAreaMaxY = new JTextArea();
		JTextAreaMaxY.setBounds(100, 300, 50, 40);
		this.add(JTextAreaMaxY);
		
		//JButtonDraw
		JButtonDraw = new JButton("Draw");
		JButtonDraw.setBounds(30, 120, 120, 40);
		JButtonDraw.setActionCommand("JButtonDraw");
		this.add(JButtonDraw);
		
		
		
		
		this.setVisible(true);
	}
	
	public static class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			String actionCommand = event.getActionCommand();
			
			switch(actionCommand) {
				
				case "JButtonDraw" : {
					
					//add code for drawing the function here
					
					
					
					
					
				} break;
			}
			
		}
		
	}
	
}
