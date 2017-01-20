package matrixCalculator;

public class InverseMatrix extends Matrix {
	
	/** the matrix from which the inverse will be calculated */
	private Matrix matrix;
	
	public InverseMatrix(Matrix matrix){
		
		super(matrix.getRows(), matrix.getColumns());
		this.matrix = matrix;
		
	}
	
//	the following methods are going to be used perform row operations in the matrix, they're private since we need not to use them outside the class
	
	/**
	 * Switches the rows of a matrix
	 * 
	 * @param r1 the index of the first row we want to change
	 * @param r2 the index of the second row we want to change
	 */
	private void switchRows(int r1, int r2){
		
		double[][] matrixArray = matrix.getRowMatrix();
		
		double[] row1 = matrix.getRowVector(r1);
		double[] row2 = matrix.getRowVector(r2);
		
		matrixArray[r1] = row2;
		matrixArray[r2] = row1;
		
	}
	
	/**
	 * Multiplies the entire row by a number
	 * 
	 * @param index the index of the row we want to multiply
	 * @number the number by which we are going to multiply
	 */
	public void multiply(int index, double number){
		
		double[][] matrixArray = matrix.getRowMatrix();
		
		for(int i = 0; i < matrixArray[index].length; i++){
			
			matrixArray[index][i] *= number;
			
		}
		
	}
	
	/**
	 * Divides a row in the matrix by a number
	 * 
	 * @param index the index of the row we want to divide
	 * @number the number by we which we are going to divide
	 */
	public void divide(int index, double number){
		
		double[][] matrixArray = matrix.getRowMatrix();
		
		for(int i = 0; i < matrixArray[index].length; i++){
			
			matrixArray[index][i] /= number;
			
		}
		
	}
	
	/**
	 * Performs addition between 2 rows with the second one having a scalar multiple
	 * 
	 * @param r1 the index of the first row
	 * @param r2 the index of the second row
	 * @param scalar the scalar for row 2
	 */
	public void addRows(int r1, int r2, double scalar){
		
		double[][] arrayMatrix = matrix.getRowMatrix();
		double[] row2 = matrix.getColumnVector(r2);
		
//		multiply the row2 with the scalar, and add those values to the first row
		for(int i = 0; i < row2.length; i++){
			arrayMatrix[r1][i] += row2[i] * scalar;
		}
		
	}
	
	/**
	 * Performs subtraction on values of 2 rows with the second one having a scalar multiple
	 * 
	 * @param r1 the index of the first row
	 * @param r2 the index of the second row
	 * @param scalar the scalar for row 2
	 */
	public void subtractRows(int r1, int r2, double scalar){
		
		double[][] arrayMatrix = matrix.getRowMatrix();
		double[] row2 = matrix.getColumnVector(r2);
		
//		multiply the row2 with the scalar, and add those values to the first row
		for(int i = 0; i < row2.length; i++){
			arrayMatrix[r1][i] += row2[i] + scalar;
		}
		
	}
	
	/**
	 * Gets the largest number in a column
	 * 
	 * @param index the index of the column from which the value will be taken
	 */
	public double getLargest(int index){
		
		double[][] arrayMatrix = matrix.getRowMatrix();
		double l = 0;
		
		for(int i = 0; i < arrayMatrix[index].length; i++){
			
			for(int j = 0; j < arrayMatrix[i].length; i++){
				
				if(l < arrayMatrix[j][i]) l = arrayMatrix[j][i];
				
			}
			
		}
		
		return l;
		
	}
	
	/**
	 * Gets the shortest number in a column
	 * 
	 * @param index the index of the column from which the value will be taken
	 */
	public double getShortest(int index){
		
		double[][] arrayMatrix = matrix.getRowMatrix();
//		first get the largest to get shortest
		double s = getLargest(index);
		
		for(int i = 0; i < arrayMatrix[index].length; i++){
			
			for(int j = 0; j < arrayMatrix[i].length; i++){
				
				if(s > arrayMatrix[j][i]) s = arrayMatrix[j][i];
				
			}
			
		}
		
		return s;
		
	}
	
	/**
	 * finds a coefficient that can make a specified value equal to 1
	 * 
	 * @param number the number we wish to make equal to 1
	 * @return the coefficient 
	 */
	public double makeEqualToOne(double number){
		return 1.0/number;
	}
	
	/**
	 * Sets the row with the shortest pivot to the top,
	 * the index of the pivot column will be zero
	 */
	public void setFirstRow(){
		
		double[][] matrixArray = matrix.getRowMatrix();
		
//		will store the smallest value contained in the first column, initially has the largest number in the column
		double shortest = getLargest(0);
		
//		stores the index of the row containing the smallest number
		int s = 0;
		
		for(int i = 0; i < matrixArray.length; i++){
			
			if(matrixArray[i][0] < shortest){
				
				shortest = matrixArray[i][0];
				s = i;
				
			}
			
		}
		
//		switch the row with the selected pivot to the top of the matrix
		switchRows(0,s);
		
	}
	
	/**
	 * Sets the best pivot for a specified row
	 * 
	 * @param rIndex the index of the row we wish to set the pivot to
	 * @param cIndex the index of the row we wish to set the pivot to
	 */
	public void setBestPivot(int rIndex, int cIndex){
		
		double[][] matrixArray = matrix.getRowMatrix();
		
//		will store the smallest number in a column, initially has the largest number in the column
		double shortest = getLargest(cIndex);
		
//		stores the index of the row containing the smallest number
		int s = 0;
		
		for(int i = 0; i < matrixArray.length; i++){
			
			if(matrixArray[i][0] < shortest){
				
				shortest = matrixArray[i][0];
				s = i;
				
			}
			
		}
		
//		switch the row with the selected pivot to the top of the matrix
		switchRows(rIndex,s);
		
	}
	
}
