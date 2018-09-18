import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingUtilities;

public class JButtonCustom extends JButton {
	
	String actionCommand;
	String imageLocation;
	
	public JButtonCustom(String actionCommand, String imageLocation, String tooltip) {
		this.setActionCommand(actionCommand);
		this.addActionListener(new Window.ButtonListener());
		this.imageLocation = imageLocation;
		this.setToolTipText(tooltip);
		this.addKeyListener(new KeyInput());
		if(actionCommand.equals("JButtonClear"))
			this.addMouseListener(new MouseListener());
		
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
	
	public static class MouseListener extends MouseAdapter {
		@Override
		public void mousePressed(MouseEvent e) {
			if(SwingUtilities.isRightMouseButton(e)) {
				Window.JTextAreaMinX.setText("");
				Window.JTextAreaMaxX.setText("");
				Window.JTextAreaMinY.setText("");
				Window.JTextAreaMaxY.setText("");
			}
		}
	}
	
}
