package matrixCalculator;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.HashMap;
import java.util.LinkedList;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Class that is going to be in charge of storing the values entered for every
 * matrix window and then displays the solution matrix of the calculation
 * 
 * @author Mauricio Hernandez
 *
 */

public class Solution implements WindowListener {

	/** list of matrix windows */
	private LinkedList<MatrixWindow> matrixWindows;

	/** tracks the number of windows open */
	private int windowsOpen;

	/** list of matrices taken from the windows */
	private HashMap<Integer, Matrix> matrices;

	/** the operation to be performed */
	private Operation operation;

	/** window that will contain the final matrix */
	private Window solutionWindow;

	/** panel that contains the resulting matrix values */
	private SolutionPanel sPanel;

	/**
	 * matrix used to iterate through all the matrices inside the list of
	 * matrices
	 */
	private Matrix resultingMatrix;

	/** used to link the windows with the matrix window */
	private HashMap<Window, Integer> mw;

	/** determines whether an operation was successful */
	private boolean success = true;

	public Solution(LinkedList<MatrixWindow> matrixWindows, Operation operation) {

		this.matrixWindows = matrixWindows;
		matrices = new HashMap<Integer, Matrix>();
		this.operation = operation;
		windowsOpen = matrixWindows.size();

		System.out.println("Initial windows open: " + windowsOpen);

		// add the window listener to all windows
		for (MatrixWindow window : matrixWindows) {
			window.addWindowListener(this);
		}

		mw = new HashMap<Window, Integer>();

		// pair the matrix windows with their matrix's ID
		for (int i = 0; i < matrixWindows.size(); i++) {
			mw.put(matrixWindows.get(i).getWindow(), matrixWindows.get(i).getMatrix().getID());
		}

	}

	public void windowActivated(WindowEvent e) {

	}

	public void windowClosed(WindowEvent e) {

		// add the matrix from the window just closed to the list of matrices
		Matrix matrix = new Matrix(matrixWindows.getFirst().getMatrix().getRows(),
				matrixWindows.getFirst().getMatrix().getColumns());

		for (int i = 0; i < matrixWindows.size(); i++) {

			if ((int) mw.get((Window) e.getSource()) == matrixWindows.get(i).getMatrix().getID()) {

				System.out.println("Found ID: " + matrixWindows.get(i).getMatrix().getID());

				matrix = matrixWindows.get(i).getMatrix();

				// add the declared matrix to the matrix array
				matrices.put(i, matrix);

				break;

			}

		}

		// decrease the number of windows open
		windowsOpen--;

		// check when all windows are closed
		if (windowsOpen == 0) {

			// FOR TESTING: print the operation to be performed
			System.out.println("0 windows open, operation selected: " + operation + "\nNumber of matrix in list: "
					+ matrices.size());

			// FOR TESTING: print all the matrices entered with their
			// corresponding index in the matrix HashMap
			for (int i = 0; i < matrices.size(); i++) {
				System.out.println("Printing matrix at " + i + ":\n" + matrices.get(i).printMatrix() + "\n");
			}

			int a = 1;

			// find the first matrix in the list that is not canceled
			for (int i = 0; i < matrices.size(); i++) {

				if (matrices.get(i).isOperable()) {
					System.out.println("Found first operable matrix " + i + " value of a = " + a + "\n");
					resultingMatrix = matrices.get(i);
					matrices.remove(i);
					break;
				} else {
					a++;
				}

			}

			if (operation == Operation.ADDITION || operation == Operation.SUBTRACTION
					|| operation == Operation.MULTIPLICATION) {

				System.out.println("Evaluating the matrices...\n");

				int it = a;

				int b = 0;

				// FOR TESTING: print the matrix size plus a
				System.out.println("List of matrices size: " + matrices.size() + "\n ");

				// for loop used for adding, subtracting, multiplying
				while (it < matrices.size() + 1 && success) {

					// FOR TESTING: print the iteration we are in
					System.out.println("Iteration: " + b++);

					// the next matrix to be evaluated
					Matrix nextMatrix = matrices.get(it);

					// evaluate resultingMatrix and next matrix only if not
					// canceled
					if (nextMatrix.isOperable()) {

						// ADDITION===========================
						if (operation == Operation.ADDITION) {

							for (int row = 0; row < nextMatrix.getRows(); row++) {

								for (int col = 0; col < nextMatrix.getColumns(); col++) {

									// FOR TESTING: print the values that are
									// being added
									System.out.println("Adding: " + resultingMatrix.getValue(row, col) + " + "
											+ nextMatrix.getValue(row, col) + " at index (" + row + ", " + col
											+ ") for matrix: " + it + "\n");

									// adds the values inside the same index
									// of both matrices and sets that value
									// to the resulting matrix
									resultingMatrix.insertValue(
											resultingMatrix.getValue(row, col) + nextMatrix.getValue(row, col), row,
											col);

								}

							}

							// END OF ADDITION
						} else if (operation == Operation.SUBTRACTION) {

							if (nextMatrix.isOperable()) {

								for (int row = 0; row < nextMatrix.getRows(); row++) {

									for (int col = 0; col < nextMatrix.getColumns(); col++) {

										// FOR TESTING: print the values that
										// are being added
										System.out.println("Adding: " + resultingMatrix.getValue(row, col) + " - "
												+ nextMatrix.getValue(row, col) + " at index (" + row + ", " + col
												+ ") for matrix: " + it + "\n");

										// adds the values inside the same index
										// of both matrices and sets that value
										// to the resulting matrix
										resultingMatrix.insertValue(
												resultingMatrix.getValue(row, col) - nextMatrix.getValue(row, col), row,
												col);

									}

								}

							}

						} else if (operation == Operation.MULTIPLICATION) {

							// we need the values of the first matrix unchanged
							Matrix first = resultingMatrix.clone();

							// holds the value to input on the resulting matrix
							double sum = 0;

							// just if the matrices are operable and square
							if (nextMatrix.isOperable() && nextMatrix.isSquared()) {

								for (int r = 0; r < first.getRows(); r++) {

									// holds the values of the rows in matrix1
									double[] tempRow = first.getRowVector(r);

									// multiply the corresponding values in the
									// row
									// with the values of the corresponding
									// column
									// of matrix2
									for (int c = 0; c < nextMatrix.getColumns(); c++) {

										double[] tempRow2 = nextMatrix.getColumnVector(c);

										for (int x = 0; x < nextMatrix.getColumns(); x++) {

											sum += tempRow[x] * tempRow2[x];
											System.out.println("Multiplying " + tempRow[x] + " * " + tempRow2[x]);

										}

										resultingMatrix.insertValue(sum, r, c);
										sum = 0;

									}

								}

							} else if (!nextMatrix.isSquared()) {

								// the operation can't be done without squared
								// matrices
								success = false;

							}

						}

					} else if (!resultingMatrix.isOperable()) {

						System.out.println("Got an error");

					}

					it++;

				} // END OF MAIN WHILE LOOP FOR ADD, SUB, MUL

			} else if (operation == Operation.INVERSE) {

			} else if (operation == Operation.TRANSPOSE) {

				resultingMatrix = resultingMatrix.transpose();

			}

			// create a solution panel containing all values
			sPanel = new SolutionPanel(resultingMatrix);

			// display the answer on a window
			solutionWindow = new Window(sPanel, "Solution Matrix", Window.DISPOSE_ON_CLOSE, false);

			// resize the solution window
			solutionWindow.setSize(solutionWindow.getWidth() + (int) (solutionWindow.getHeight() * .5), solutionWindow.getHeight() + (int) (solutionWindow.getHeight() * .6), true);

			solutionWindow.setVisible(true);

		}

		// FOR TESTING: print whether operation was successful
		if (!success)
			System.out.println("Can't perform calculation");

		Matrix.resetID();

	}// END OF WINDOW CLOSED METHOD

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

	private class SolutionPanel extends JPanel {

		// label for the resulting matrix
		JLabel label;

		// stores the values entered in the matrix
		private JLabel[] elements;

		// stores the text fields in a grid layout
		JPanel matrixPanel;

		/**
		 * Constructor method
		 * 
		 * @param matrix
		 *            the matrix containing the solution
		 */
		private SolutionPanel(Matrix matrix) {

			// FOR TESTING: print to ensure the panel is being created
			System.out.println("Creating panel\n\nMatrix dimension: " + matrix.getRows() + " x " + matrix.getColumns());

			// set a grid bag layout to the main panel
			setLayout(new GridBagLayout());

			label = new JLabel("Solution Matrix");
			label.setHorizontalAlignment(JLabel.CENTER);
			Window.addComponent(this, label, 0, 0, 1, 1, Window.none, Window.center, 5);

			matrixPanel = new JPanel(new GridLayout(matrix.getRows(), matrix.getColumns(), 3, 3));

			elements = new JLabel[matrix.getRows() * matrix.getColumns()];

			// will store the width of the largest text field
			int maxWidth = 0;

			// set the value of each label with the value of the corresponding
			// element in the matrix
			for (int i = 0; i < matrix.getRows(); i++) {

				for (int j = 0; j < matrix.getColumns(); j++) {

					elements[i + j] = new JLabel(Double.toString(matrix.getValue(i, j)));
					elements[i + j].setHorizontalAlignment(JLabel.CENTER);
					matrixPanel.add(elements[i + j]);

				}

			}
			
			Window.addComponent(this, matrixPanel, 0, 1, 1, 1, Window.both, Window.center, 5);
			
			// set the theme for the solution window
			Window.setTheme(this, new Color(0, 40, 0), Color.WHITE, MatrixCalculator.DEFAULT_FONT, true, matrixPanel);
			
//			for now, clear the border on the label component
			label.setBorder(null);
			
		}

	}

}
