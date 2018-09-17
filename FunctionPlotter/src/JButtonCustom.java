import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class JButtonCustom extends JButton {
	
	String actionCommand;
	String imageLocation;
	
	public JButtonCustom(String actionCommand, String imageLocation) {
		this.setActionCommand(actionCommand);
		this.addActionListener(new Window.ButtonListener());
		this.imageLocation = imageLocation;
		
		this.addKeyListener(new KeyInput());
	
		
		this.setBorderPainted(false);
		this.setContentAreaFilled(false);
		
		Image img;
		try {
			img = ImageIO.read(getClass().getResource(imageLocation));
			this.setIcon(new ImageIcon(img));
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
}
