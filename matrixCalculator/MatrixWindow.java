package matrixCalculator;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MatrixWindow extends JPanel implements ActionListener {
	
	/**every matrix window will have a corresponding matrix to store values into*/
	private Matrix matrix;
	
	/**the frame for the matrix window*/
	private Window window;
	
	/**array of text fields used to store the matrix's values*/
	private JTextField[] tfArray;
	
	/**the labels for the matrix window*/
	private JLabel title;
	
	/**the buttons in charge of setting values or canceling*/
	private JButton enter, cancel;
	
	/**background color for the matrices*/
	private Color background, foreground;
	
	/**used to alternate through the background color*/
	private static int iColor = 0;
	
	public MatrixWindow(Matrix matrix){
		
		this.matrix = matrix;
		
//		set the layout for this panel
		setLayout(new GridBagLayout());
		
//		create matrix title, inside the panel since not all windows will be big enough
		title = new JLabel(matrix.getRows() + "x" + matrix.getColumns() + " Matrix " + matrix.getID());
		
//		panel that will contain the text fields used to enter values into the array
		JPanel matrixPanel = new JPanel(new GridLayout(matrix.getRows(), matrix.getColumns(),3,3));
		
//		create the tfArray object and add the text fields to the matrix panel
		tfArray = new JTextField[matrix.getRows()*matrix.getColumns()];
		
		NumberKeyInput kListener = new NumberKeyInput();
		
		for(int i = 0; i < tfArray.length; i++){
			tfArray[i] = new JTextField(3);
			tfArray[i].setHorizontalAlignment(JTextField.CENTER);
			tfArray[i].addKeyListener(kListener);
			matrixPanel.add(tfArray[i]);
		}
		
//		create the buttons
		enter = new JButton("Enter");
		cancel = new JButton("Cancel");
		enter.addActionListener(this);
		cancel.addActionListener(this);
		
//		add all components to the matrix window
		Window.addComponent(this, title, 0, 0, 2, 1, Window.none, Window.center, 4);
		Window.addComponent(this, matrixPanel, 0, 1, 2, 1, Window.none, Window.center, 4);
		Window.addComponent(this, enter, 0, 2, 1, 1, Window.hor, Window.center, 4);
		Window.addComponent(this, cancel, 1, 2, 1, 1, Window.hor, Window.center, 4);

		//set the theme of the matrix window, and alternate through the colors
		if(iColor == 0){
			iColor = 1;
			background = Color.BLACK;
			foreground = Color.WHITE;
		}else if(iColor == 1){
			iColor = 0;
			background = new Color(80,80,80);
			foreground = Color.BLACK;
		}
		
		Window.setTheme(this, background, foreground, MatrixCalculator.defaultFont, true, matrixPanel);
//		FOR NOW: make the title label borderless
		title.setBorder(null);
		
//		create the "actual" matrix window
		window = new Window(this, "Matrix Window", Window.DISPOSE_ON_CLOSE, false);
		
	}
	
	/**
	 * Gets the matrix of the corresponding window
	 * 
	 * @return the matrix of the window
	 */
	public Matrix getMatrix(){
		return matrix;
	}
	
	/**
	 * Overrides the setVisible method of the superclass
	 * 
	 * @param value determines the visibility of the window
	 */
	public void setVisible(boolean value){
		window.setVisible(value);
	}
	
	/**
	 * Gets the jframe object of this matrix window
	 * 
	 * @return the JFrame of the matrix window
	 */
	public Window getWindow(){
		return window;
	}
	
	public void actionPerformed(ActionEvent e) {
		
		Object source = e.getSource();
		
		if(source == cancel){
			
//			cancel the matrix
			matrix.cancelMatrix();
			
//			dispose the window
			window.dispose();
			
		}else if(source == enter){
			
//			cycle through all values inside the text fields in order and then add them to the matrix
			LinkedList<Double> tList = new LinkedList<Double>();
			
			for(int i = 0; i < tfArray.length; i++){
				
				if(tfArray[i].getText().length() > 0){
					tList.add(Double.parseDouble(tfArray[i].getText()));
				}else{
					tList.add(0.0);
				}
				
			}
			
//			add all the values to the matrix
			matrix.insertValues(tList);
			
//			get rid of the window
			window.dispose();
			
		}
		
	}
	
	/**
	 * Adds a window listener to the jframe of this class
	 * 
	 * @param listener the window listener to be added to the window
	 */
	public void addWindowListener(WindowListener listener){
		window.addWindowListener(listener);
	}
}
