package matrixCalculator;

import java.awt.Toolkit;
import java.util.Arrays;
import java.util.LinkedList;

public class WindowDistributor {
	
	/**default toolkit used to get the screen size*/
	private Toolkit tk = Toolkit.getDefaultToolkit();
	
	/**the width and height of the screen*/
	private final int SCREEN_WIDTH = tk.getScreenSize().width, SCREEN_HEIGHT = tk.getScreenSize().height;
	
	/**list of matrices involved in the calculation*/
	private LinkedList<MatrixWindow> matrixWindows;
	
	public WindowDistributor(LinkedList<MatrixWindow> matrixWindows){
		this.matrixWindows = matrixWindows;
	}
	
	/**
	 * Method in charge of displaying the windows in an orderly manner
	 * through the whole screen
	 * 
	 * @param matrices the list of matrices that are going to be displayed
	 */
	public void displayWindows(){
		
//		number of rows of windows displayed on screen
		int rows = 1;
		
//		number of columns of windows displayed on screen
		int columns = 1;
		
//		store the horizontal and vertical positions of every matrix window
		int[] xPositions = new int[1];
		int[] yPositions = new int[1];
		
//		we want to leave a space between the windows of 100px
		int totalWidth = 100;
		
//		the width of the largest window
		int maxWidth = 0;
//		the height of the highest window
		int maxHeight = 0;
		
//		get the max width and height from the list of windows
		for(MatrixWindow window: matrixWindows){
			
			if(maxWidth < window.getWidth()) maxWidth = window.getWidth();
			if(maxHeight < window.getHeight()) maxHeight = window.getHeight();
			
		}
		
//		FOR TESTING ONLY
		System.out.println("\nLargest width: " + maxWidth + "\nLargest height: " + maxHeight + "\n");
		
//		set the initial x and y position for the first object
		xPositions[0] = 100;
		yPositions[0] = 50;
		
//		help iterate through all values inside the list of windows
		boolean outOfBounds = false; 
		int w = 100;
		
//		check the number of windows per row, and set the xPositions alongside
		while(!outOfBounds){
			
			w += maxWidth + 100;
			
//			only if the next position is not out of bounds
			if(w <= SCREEN_WIDTH - maxWidth){
				xPositions = addToArray(xPositions, w);
			}else{
				
//				break from the while loop
				outOfBounds = true;
				
			}
			
		}
		
//		FOR TESTING ONLY: print the length of the xPositions array and all of its values
		System.out.println("xPositions length: " + xPositions.length);
		System.out.println(Arrays.toString(xPositions));
		
//		get the number of rows by dividing the total number of windows by the number of windows per row
		rows = (int)Math.ceil(matrixWindows.size() / xPositions.length);
		
//		recycling variables to get y positions
		outOfBounds = false;
		w = 50;
		
//		we want to check whether the window falls of the screen vertically, if it does, cycle from the beginning
		while(!outOfBounds){
			
			w += maxHeight + 50;
			
//			only if the next position is not out of bounds
			if(w <= SCREEN_HEIGHT - maxHeight){
				
				yPositions = addToArray(yPositions, w);
				
//				else check if there are more rows to set the vertical positions
			}else if(yPositions.length < rows){
				
				w = 50;
				yPositions = addToArray(yPositions, w);
				
//				else make the loop stop
			}else{
				
//				break from the while loop
				outOfBounds = true;
				
			}
			
		}
		
		boolean breakAll = false;
		int a = 0;
		
//		set the positions for the windows
		for(int i = 0; i < yPositions.length; i++){
			
			for(int j = 0; j < xPositions.length; j++){
				
				if(a < matrixWindows.size()){
					matrixWindows.get(a).getWindow().setLocation(xPositions[j], yPositions[i]);
					matrixWindows.get(a).setVisible(true);
					a++;
				}else{
					breakAll = true;
					break;
				}
				
			}
			
			if(breakAll) break;
			
		}
		
		
	}
	
	/**
	 * Helper method used to add values to an integer array
	 * 
	 * @param array the array to which values are going to be added
	 * @param value the value to be added to the array
	 */
	public int[] addToArray(int[] array, int value){
		
		int[] newArray = new int[array.length + 1];
		
		System.arraycopy(array, 0, newArray, 0, array.length);
		
		newArray[array.length] = value;
		
		return newArray;
		
	}
	
//	FOR TESTING
	public static void main(String[] args) {
		
		LinkedList<MatrixWindow> list = new LinkedList<MatrixWindow>();
		
		for(int i = 24; i > 0; i--){
			list.add(new MatrixWindow(new Matrix(4,4)));
		}
		
		list.add(new MatrixWindow(new Matrix(5,5)));
		
		WindowDistributor distributor = new WindowDistributor(list);
		
		distributor.displayWindows();
		
	}
	
}
