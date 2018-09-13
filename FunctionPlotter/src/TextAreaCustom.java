import java.awt.Font;

import javax.swing.JTextArea;

public class TextAreaCustom extends JTextArea {
	

	
	public TextAreaCustom(String text, String toolTipText, double posX, double posY, double sizeX, double sizeY) {
		int scaleHor = 20, scaleVer = 40;
		
		this.setText(text);
		this.setFont(new Font("arial", 1, 15));
		this.setToolTipText(toolTipText);
		this.addKeyListener(new KeyInput());
		this.setBounds((int) (posX * scaleHor),(int) (posY * scaleVer),(int) (sizeX * scaleHor),(int) (sizeY * scaleVer));
		
		
	}

}
