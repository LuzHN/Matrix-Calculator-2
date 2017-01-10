package matrixCalculator;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Class that is going to be in charge of storing the values entered for
 * every matrix window and then displays the solution matrix of the 
 * calculation
 * 
 * @author Mauricio Hernandez
 *
 */

public class Solution implements WindowListener {
	
	/**list of matrix windows*/
	private LinkedList<MatrixWindow> matrixWindows;
	
	/**tracks the number of windows open*/
	private int windowsOpen;
	
	/** list of matrices taken from the windows*/
	private LinkedList<Matrix> matrices;
	
	/** the operation to be performed */
	private Operation operation;
	
	/** window that will contain the final matrix */
	private Window solution;
	
	/**matrix used to iterate through all the matrices inside the list of matrices*/
	private Matrix resultingMatrix;
	
	/** used to link the windows with the matrix window*/
	private HashMap<Window, Integer> mw;
	
	public Solution(LinkedList<MatrixWindow> matrixWindows, Operation operation){
		
		this.matrixWindows = matrixWindows;
		matrices = new LinkedList<Matrix>();
		this.operation = operation;
		windowsOpen = matrixWindows.size();
		
		System.out.println("Initial windows open: " + windowsOpen);
		
//		add the window listener to all windows
		for(MatrixWindow window: matrixWindows){
			window.addWindowListener(this);
		}
		
		mw = new HashMap<Window, Integer>();
		
		for(int i = 0; i < matrixWindows.size(); i++){
			mw.put(matrixWindows.get(i).getWindow(), matrixWindows.get(i).getMatrix().getID());
		}
		
	}

	public void windowActivated(WindowEvent e) {
		
	}

	public void windowClosed(WindowEvent e) {
		
//		add the matrix from the window just closed to the list of matrices
		
		int index = -1;
		
		Matrix matrix = new Matrix(matrixWindows.getFirst().getMatrix().getRows(), matrixWindows.getFirst().getMatrix().getColumns());
		
		for(MatrixWindow window: matrixWindows){
			
			if((int)mw.get((Window)e.getSource()) == window.getMatrix().getID()){
				
				System.out.println("Found ID: " + window.getMatrix().getID());
				
				matrix = window.getMatrix();
				index++;
				break;
				
			}
			
		}
		
//		add the declared matrix to the matrix array
		matrices.add(index, matrix);
		
		System.out.println("adding another matrix to the list...");
		
		windowsOpen--;
		
		if(windowsOpen == 0){
			
			System.out.println("0 windows open, operation selected: " + operation
					+ "\nNumber of matrix in list: " + matrices.size());
			
//			perform the operation selected on the spinner
			
//			matrix used to iterate over all matrices inside the list and
			resultingMatrix = matrices.getFirst();
			matrices.removeFirst();
//			at this point the first matrix will be the next matrix to evaluate
			
//			helps iterate through all matrices
			Iterator iterator = matrices.iterator();
			
			for(int i = 0; i < matrices.size(); i++){
				
				System.out.println("Performing the calculation...");
				
				if(operation == Operation.ADDITION){
					
//					while the list still has matrices, evaluate
					while(iterator.hasNext()){
						
//						the matrix to be evaluated
						Matrix matrix2 = (Matrix)iterator.next();
						
//						just if the matrix was not canceled
						if(matrix2.isOperable()){
							
							for(int row = 0; row < matrix2.getRows(); row++){
								
								for(int col = 0; col < matrix2.getColumns(); col++){
									
	//								adds the values inside the same index of both matrices and sets that value to the resulting matrix
									resultingMatrix.insertValue(resultingMatrix.getValue(row, col) + matrix2.getValue(row, col), row, col);
									
								}
								
							}
							
						}
						
					}
					
//					reset matrices' id
					Matrix.resetID();
					
//					FOR SUBTRACTION==========================
				}else if(operation == Operation.SUBTRACTION){
					
//					while the list still has matrices, evaluate
					while(iterator.hasNext()){
						
//						the matrix to be evaluated
						Matrix matrix2 = (Matrix)iterator.next();
						
//						just if the matrix has not been canceled
						if(matrix2.isOperable()){
						
							for(int row = 0; row < matrix2.getRows(); row++){
								
								for(int col = 0; col < matrix2.getColumns(); col++){
									
	//								adds the values inside the same index of both matrices and sets that value to the resulting matrix
									resultingMatrix.insertValue(resultingMatrix.getValue(row, col) - matrix2.getValue(row, col), row, col);
									
								}
								
							}
							
						}
						
					}
					
//					reset the id of the matrices
					Matrix.resetID();
					
//					FOR MULTIPLICATION==========================
				}else if(operation == Operation.MULTIPLICATION){
					
					while(iterator.hasNext()){
						
//						the matrix to be evaluated
						Matrix matrix2 = (Matrix)iterator.next();
						
//						just if the matrix has not been canceled
						if(matrix2.isOperable()){
						
							for(int row = 0; row < matrix2.getRows(); row++){
								
								for(int col = 0; col < matrix2.getColumns(); col++){
									
	//								adds the values inside the same index of both matrices and sets that value to the resulting matrix
									resultingMatrix.insertValue(resultingMatrix.getValue(row, col) - matrix2.getValue(row, col), row, col);
									
								}
								
							}
							
						}
						
					}
					
//					FOR INVERSE==========================
				}else if(operation == Operation.INVERSE){
					
					
					
				}
				
			}
			
		}
	
	}

	public void windowClosing(WindowEvent e) {
		
	}

	public void windowDeactivated(WindowEvent e) {
		
	}

	public void windowDeiconified(WindowEvent e) {
		
	}

	public void windowIconified(WindowEvent e) {
		
	}

	public void windowOpened(WindowEvent e) {
		
	}
	
}
