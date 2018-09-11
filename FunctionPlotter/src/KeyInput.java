import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
	
	public void keyPressed(KeyEvent event) {
		int key = event.getKeyCode();
		
		switch(key) {
			
			case KeyEvent.VK_ESCAPE : System.exit(1);
				break;
			case KeyEvent.VK_ENTER : Window.JButtonDraw.doClick();
				break;
		}
	}

		
}
