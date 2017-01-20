package matrixCalculator;

import java.util.Arrays;
import java.util.LinkedList;

public class Matrix {
	
	/**the unique ID of the matrix*/
	private int ID;
	private static int idCounter = 0;
	
	/**number of rows of the matrix*/
	private int row;
	
	/**number of columns of the matrix*/
	private int column;
	
	/**2d arrays containing the row and column sets of the matrix*/
	private double[][] rows;
	private double[][] columns;
	
	/** determines whether a matrix is canceled */
	private boolean isCanceled = false;
	
	/** tells if matrix is square matrix */
	private boolean square;
	
	public Matrix(int row, int column){
		
		this.row = row;
		this.column = column;
		ID = ++idCounter;
		
//		set the dimension of the matrix
		rows = new double[row][column];
		columns = new double[column][row];
		
		if(row != column){
			square = false;
		}else{
			square = true;
		}
		
	}
	
	/**
	 * Gets the number of rows of the matrix
	 * 
	 * @return the number of rows
	 */
	public int getRows(){
		return row;
	}
	
	/**
	 * Gets the number of columns of the matrix
	 * 
	 * @return the number of columns
	 */
	public int getColumns(){
		return column;
	}
	
	/**
	 * Gets the id of the matrix
	 * 
	 * @return the unique id of the matrix
	 */
	public int getID(){
		return ID;
	}
	
	/**
	 * inserts values into the matrix from a list of values
	 * return false if the list can't fill the matrix
	 * 
	 * @param list the matrix list used to insert values
	 */
	public boolean insertValues(LinkedList<Double> list){
		
		if(list.size() != row * column){
			return false;
		}else{
			
//			used to cycle through all the values inside the list
			int a = 0;
			
			for(int i = 0; i < rows.length; i++){
				
				for(int j = 0; j < rows[i].length; j++){
					
					rows[i][j] = list.get(a++);
					
				}
				
			}
			
			a = 0;
			
			for(int i = 0; i < columns.length; i++){
				
				for(int j = 0; j < columns[i].length; j++){
					
					try{
						columns[i][j] = rows[j][i];
					}catch(ArrayIndexOutOfBoundsException e){
						System.out.println("Value of a: " + a + ", the size of the matrix: " + list.size());
					}
					
				}
				
			}
			
//			FOR TESTING ONLY: print the values inside the columns array
			System.out.println("\nPrinting the values of the array of columns:\n");
			for(double[] row: columns){
				for(double column: row){
					System.out.print("[" + column + "]");
				}
				System.out.println();
			}
			
			return true;
			
		}
		
	}
	
	/**
	 * Used for testing only
	 * 
	 * @return string containing the values of the matrix in the row and column form
	 */
	public String printMatrix(){
		
		String string = "";
		
		for(double[] row: rows){
			for(double column: row){
				string += "[" + column + "]";
			}
			string += "\n";
		}
		
		string += "\n";
		
		for(double[] row: columns){
			for(double column: row){
				string += "[" + column + "]";
			}
			string += "\n";
		}
		
		return string;
		
	}
	
	/**
	 * Cancels a matrix
	 */
	public void cancelMatrix(){
		isCanceled = true;
	}
	
	/**
	 * Gets the matrix in a row form
	 * 
	 * @return the matrix in a row form
	 */
	public double[][] getRowMatrix(){
		return rows;
	}
	
	/**
	 * Gets the matrix in a column form
	 * 
	 * @return matrix in columns
	 */
	public double[][] getColumnMatrix(){
		return columns;
	}
	
	/**
	 * Retrieves the value inside the specified location
	 * 
	 * @param row the row of the value
	 * @param col the column of the value
	 * @return a double value inside the matrix
	 */
	public double getValue(int row, int col){
		return rows[row][col];
	}
	
	/**
	 * Inserts a value in the specified location
	 * 
	 * @param value the number to input
	 * @param row the row in which the value is going into
	 * @param col the column in which the value is going into
	 */
	public void insertValue(double value, int row, int col){
		rows[row][col] = value;
		columns[col][row] = value;
	}
	
	/**
	 * Helps determine whether a matrix is usable
	 * for a calculation or not
	 * 
	 * @return whether the matrix is canceled or not
	 */
	public boolean isOperable(){
		return !isCanceled;
	}
	
	/**
	 * Used to reset the id of the matrices
	 */
	public static final void resetID(){
		idCounter = 0;
	}
	
	/**
	 * Transposes the double array (matrix)
	 * 
	 * @return the transposed matrix
	 */
	public Matrix transpose(){
		
		Matrix transpose = new Matrix(row, column);
		
		for(int i = 0; i < row; i++){
			
			for(int j = 0; j < column; j++){
				
				transpose.insertValue(getValue(i,j), j, i);
				
			}
			
		}
		
//		return the transposed matrix
		return transpose;
		
	}
	
	/**
	 * Gets a vector (double array) from the matrix in the specified index
	 * 
	 * @param index the index of the vector row of the matrix
	 * @return the array made up of the values of the matrix row
	 */
	public double[] getRowVector(int index){
		
		double[] vector = new double[row];
		
		for(int i = 0; i < row; i++){
			
			vector[i] = rows[index][i];
			
		}
		
		return vector;
		
	}
	
	/**
	 * Gets a vector (double array) from the matrix in the specified index
	 * 
	 * @param index the index of the vector row of the matrix
	 * @return the array made up of the values of the matrix row
	 */
	public double[] getColumnVector(int index){
		
		double[] vector = new double[column];
		
		for(int i = 0; i < column; i++){
			
			vector[i] = rows[i][index];
			
		}
		
		return vector;
		
	}
	
	/**
	 * Creates a copy of the matrix
	 * 
	 * @param matrix the matrix from which values are going to be copied from
	 * @return the matrix containing the specified copy of the matrix
	 */
	public Matrix clone(){
		
		Matrix newMatrix = new Matrix(row, column);
		
		for(int i = 0; i < rows.length; i++){
			
			for(int j = 0; j < rows[i].length; j++){
				
				newMatrix.insertValue(rows[i][j], i, j);
				
			}
			
		}
		
		return newMatrix;
		
	}
	
	/**
	 * Tells whether a matrix is a square matrix
	 * 
	 * @return true if matrix is squared
	 */
	public boolean isSquared(){
		return square;
	}
	
}
