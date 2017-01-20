package matrixCalculator;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;

public class NumberKeyInput implements KeyListener{
	
	private boolean dot = false;
	
	public void keyTyped(KeyEvent e) {
		
	}

	public void keyPressed(KeyEvent e) {
		
	}

	public void keyReleased(KeyEvent e) {
		
		char charEntered = e.getKeyChar();
		Object source = e.getSource();
		
		if(source instanceof JTextField){
			
			if(charEntered > '9' || charEntered < '0'){
				
				if(!(e.getKeyCode() != e.VK_TAB)){
					((JTextField)source).setText("");
				}
				
			}
			
		}
		
	}

}
