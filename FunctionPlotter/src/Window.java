import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class Window extends JFrame {
	// code for the GUI of the program
	
	
	public static JTextArea JTextAreaFunction;
	public static JTextArea JTextAreaMin;
	public static JTextArea JTextAreaMax;
	
	public static JButton JButtonDraw;
	
	public Window(String windowName, int WIDTH, int HEIGHT) {
		
		this.setSize(new Dimension(WIDTH, HEIGHT));
		this.setTitle(windowName);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		
		
		
		//JTextAreaFunction
		JTextAreaFunction = new JTextArea("Enter your function here! ");
		JTextAreaFunction.setBounds(30, 50, 120, 40);
		this.add(JTextAreaFunction);
		
		//JTextAreaMin
		JTextAreaMin = new JTextArea();
		JTextAreaMin.setBounds(30, 200, 50, 40);
		this.add(JTextAreaMin);
		
		//JTextAreaMax
		JTextAreaMax = new JTextArea();
		JTextAreaMax.setBounds(100, 200, 50, 40);
		this.add(JTextAreaMax);
		
		//JButtonDraw
		JButtonDraw = new JButton("Draw");
		JButtonDraw.setBounds(30, 120, 120, 40);
		this.add(JButtonDraw);
		
		
		
		this.setVisible(true);
	}
	
}
