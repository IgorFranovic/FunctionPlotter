import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
	
	
	public void keyPressed(KeyEvent event) {
		int key = event.getKeyCode();
		
		switch(key) {
			
			case KeyEvent.VK_ESCAPE : System.exit(1);
				break;
			case KeyEvent.VK_ENTER : {
				Window.JButtonDraw.doClick();
				Window.JTextAreaFunction.grabFocus();
			} break;
			case KeyEvent.VK_TAB : {
				// to the right
				updateTextAreaSelection();
				if(Window.textAreaSelected >= Window.textAreaArray.length - 1) {
					Window.textAreaSelected = 0;
					Window.textAreaArray[0].grabFocus();
					removeWhitespace(Window.textAreaArray[Window.textAreaArray.length - 1]);
				} else {
					removeWhitespace(Window.textAreaArray[Window.textAreaSelected]);
					Window.textAreaSelected ++;
					Window.textAreaArray[Window.textAreaSelected].grabFocus();
					
				}
			} break;
		
			/*
			case KeyEvent.VK_CONTROL : {
				// to the left
				updateTextAreaSelection();
				if(Window.textAreaSelected == 0) {
					Window.textAreaSelected = Window.textAreaArray.length - 1;
					Window.textAreaArray[Window.textAreaSelected].grabFocus();
				} else {
					Window.textAreaSelected --;
					Window.textAreaArray[Window.textAreaSelected].grabFocus();
				}
				
			}
			*/
		}
	}
	public void removeWhitespace(TextAreaCustom textArea) {
		int i = 0;
		//remove leading whitespace
		for(i = 0; textArea.getText().charAt(i) == ' '; i++) {}		
		textArea.setText(textArea.getText().substring(i));	
		//remove final whitespace
		for(i = textArea.getText().length() - 1; textArea.getText().charAt(i) == ' '; i--) {}
		textArea.setText(textArea.getText().substring(0, i + 1));
	}
	public void updateTextAreaSelection() {
		if(Window.JTextAreaFunction.hasFocus())
			Window.textAreaSelected = 0;
		else if(Window.JTextAreaMinX.hasFocus())
			Window.textAreaSelected = 1;
		else if(Window.JTextAreaMaxX.hasFocus())
			Window.textAreaSelected = 2;
		else if(Window.JTextAreaMinY.hasFocus())
			Window.textAreaSelected = 3;
		else if(Window.JTextAreaMaxY.hasFocus())
			Window.textAreaSelected = 4;
	}

		
}
