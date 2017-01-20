package matrixCalculator;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class MatrixCalculator extends JPanel implements ActionListener {

	/**
	 * the frame in which the matrix calculator interface is going to be
	 * displayed
	 */
	private Window window;

	/**
	 * labels for the rows, columns, operations, and number of matrices of the
	 * calculator
	 */
	private JLabel rowsLabel, columnsLabel, matrixCount, operationLabel;

	/**
	 * spinners that set the number of rows, columns and matrices involved in
	 * the calculation
	 */
	private JSpinner rows, columns, matrixCounter;
	
	/** spinner models for each dimension spinner */
	SpinnerNumberModel rowModel, columnModel;

	/**
	 * number of matrices to be evaluated, initially 2 since addition is
	 * selected
	 */
	private int matrixNumber = 2;

	/**
	 * button that creates the window interfaces used to input values into the
	 * matrices
	 */
	private JButton createMatrices;

	/**
	 * combo box used to set which operation will be performed by the calculator
	 */
	private JComboBox<String> operations;

	/**
	 * array of strings that contain the names of the operations of the
	 * calculator
	 */
	private ArrayList<String> operationNames;

	/** default font for the program */
	public static final Font DEFAULT_FONT = new Font("Berlin Sans FB", Font.PLAIN, 20);

	/** the list of matrices being operated */
	private LinkedList<MatrixWindow> matrixWindows;

	/** in charge of distributing the windows of the matrices */
	private WindowDistributor distributor;

	/** list of matrices that result after all values have been entered */
	private LinkedList<Matrix> rMatrices;

	/** object in charge of finding the solution of the calculation */
	private Solution solution;

	/** the operation type selected */
	private Operation operation = Operation.ADDITION;

	/** determines whether an operation can only be done on a single matrix */
	private boolean single = false;

	public MatrixCalculator() {

		// set the grid bag layout for the panel
		setLayout(new GridBagLayout());

		// initiate all instance variables except "window"
		rowsLabel = new JLabel("Rows");
		columnsLabel = new JLabel("Columns");
		matrixCount = new JLabel("Number of Matrices");
		operationLabel = new JLabel("Operation");
		rowModel = new SpinnerNumberModel(2, 2, 20, 1);
		columnModel = new SpinnerNumberModel(2, 2, 20, 1);
		rows = new JSpinner(rowModel);
		columns = new JSpinner(columnModel);
		matrixCounter = new JSpinner(new SpinnerNumberModel(2, 2, 10, 1));
		createMatrices = new JButton("Enter");
		createMatrices.addActionListener(this);

		// add the names of the operations of the calculator
		operationNames = new ArrayList<String>();
		operationNames.add("Addition");
		operationNames.add("Subtraction");
		operationNames.add("Multiplication");
		operationNames.add("Inverse");
		operationNames.add("Transpose");
		String[] tempArray = new String[operationNames.size()];
		operations = new JComboBox<String>(operationNames.toArray(tempArray));
		operations.addActionListener(this);

		// add all components to this panel
		Window.addComponent(this, rowsLabel, 0, 0, 1, 1, Window.none, Window.center, 5);
		Window.addComponent(this, rows, 1, 0, 1, 1, Window.hor, Window.center, 5);
		Window.addComponent(this, columnsLabel, 2, 0, 1, 1, Window.none, Window.center, 5);
		Window.addComponent(this, columns, 3, 0, 1, 1, Window.hor, Window.center, 5);
		Window.addComponent(this, createMatrices, 0, 1, 1, 2, Window.both, Window.center, 5);
		Window.addComponent(this, operationLabel, 1, 1, 1, 1, Window.none, Window.center, 5);
		Window.addComponent(this, operations, 1, 2, 1, 1, Window.none, Window.center, 5);
		Window.addComponent(this, matrixCount, 2, 1, 2, 1, Window.hor, Window.center, 5);
		Window.addComponent(this, matrixCounter, 2, 2, 2, 1, Window.hor, Window.center, 5);

		// set the theme of the calculator interface
		Window.setTheme(this, new Color(0, 40, 0), Color.WHITE, DEFAULT_FONT);

		// create the main frame that will contain the calculator panel
		window = new Window(this, "Matrix Calculator", Window.EXIT_ON_CLOSE, true);

		window.setResizable(false);

	}

	// executes the matrix calculator
	public static void main(String[] args) {
		new MatrixCalculator();
	}

	public void actionPerformed(ActionEvent e) {

		Object source = e.getSource();

		if (source == createMatrices) { // the button

			// declare the value of the matrixWindow list
			matrixWindows = new LinkedList<MatrixWindow>();

			// we want to create the number of matrices specified by the spinner
			// and then store them into the linked list and pass the link
			if(single){
				matrixNumber = 1;
			}else{
				matrixNumber = (int)matrixCounter.getValue();
			}
			
			// number of rows and columns of the matrices
			int rowNumber = (int) rows.getValue();
			int colNumber = (int) columns.getValue();

			// create the list matrix windows
			for (int i = 0; i < matrixNumber; i++) {

				// the corresponding matrix for the window
				Matrix tempMatrix = new Matrix(rowNumber, colNumber);

				matrixWindows.add(new MatrixWindow(tempMatrix));

			}

			// FOR TESTING: print the size of the matriWindows list
			System.out.println("matrices displayed: " + matrixWindows.size());

			// create the windowDistributor object since we now have the
			// matrices added to the list
			distributor = new WindowDistributor(matrixWindows);

			// display all windows in the screen
			distributor.displayWindows();

			// create the solution object and pass the list of displayed windows
			solution = new Solution(matrixWindows, operation);

		} else if (source == operations) {

			// get the operation index
			int operationIndex = operations.getSelectedIndex();

			// determine which operation is going to be executed
			switch (operationIndex) {
			case 0:
				operation = Operation.ADDITION;
				System.out.println("Addition selected");
				// if transpose or inverse was selected
				single = false;
//				set the spinner model back to default for row counter
				rows.setModel(rowModel);
				// set the matrix counter spinner to visible
				matrixCounter.setVisible(true);
				break;
			case 1:
				operation = Operation.SUBTRACTION;
				System.out.println("Subtraction selected");
				// get the number of matrices to be worked on
				single = false;
//				set the spinner model back to default for row counter
				rows.setModel(rowModel);
				// set the matrix counter spinner to visible
				matrixCounter.setVisible(true);
				break;
			case 2:
				operation = Operation.MULTIPLICATION;
				System.out.println("Multiplication selected");
				// get the number of matrices to be worked on
				single = false;
//				make the spinners point to a single spinner model
				rows.setModel(columnModel);
				// set the matrix counter spinner to visible
				matrixCounter.setVisible(true);
				break;
			case 3:
				operation = Operation.INVERSE;
				System.out.println("Inverse selected");
				// inverse only has 1 matrix to work with
				single = true;
				// hide the spinner of matrix counter
				matrixCounter.setVisible(false);
				break;
			case 4:
				operation = Operation.TRANSPOSE;
				System.out.println("Transpose selected");
				// transpose only has 1 matrix to work with
				single = true;
				// hide the spinner of matrix counter
				matrixCounter.setVisible(false);
				break;
			}

		}

	}

}
