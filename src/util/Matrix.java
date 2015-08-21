package util;
import java.util.ArrayList;

public class Matrix 
{

	private double[][] elements;
	
	/**
	 * Constructor
	 * @param elements Elements as array of double.
	 */
	public Matrix (double[][] elements)
	{
		this.elements = elements;
	}
	
	/**
	 * Constructor.
	 * @param lines Number of lines.
	 * @param columns Number of columns.
	 */
	public Matrix (int lines, int columns)
	{
		this.elements = new double[lines][columns];
	}
	
	/**
	 * Constructor.
	 * @param matrix A matrix as a Matrix.
	 */
	public Matrix(Matrix matrix)
	{
		elements = new double[matrix.getLines()][matrix.getColumns()];
		for (int i = 0; i < matrix.getLines(); i++)
		{
			for (int j = 0; j < matrix.getColumns(); j++)
			{
				elements[i][j] = matrix.get(i, j);
			}
		}
	}
	
	/**
	 * Return the number of lines.
	 * @return The number of lines as an integer.
	 */
	public int getLines()
	{
		return elements.length;
	}
	
	/**
	 * Return the number of columns.
	 * @return The number of columns as an integer.
	 */
	public int getColumns()
	{
		return elements[0].length;
	}
	
	/**
	 * Set the value at the specified line and column.
	 * @param line The line index starting at 0 as an integer.
	 * @param column The column index starting at 0 as an integer.
	 * @param value The value to be set as a double.
	 */
	public void set(int line, int column, double value)
	{
		elements[line][column] = value;
	}
	
	/**
	 * Return the value at the specified line and column.
	 * @param ligne The line index starting at 0 as an integer.
	 * @param colonne The column index starting at 0 as an integer.
	 * @return The value at the specified position as a double.
	 */
	public double get(int ligne, int colonne)
	{
		return elements[ligne][colonne];
	}
	
	/**
	 * Return an ArrayList of double containing the value of the specified line.
	 * @param line The line index starting at 0 as an integer.
	 * @return An ArrayList of double.
	 */
	public ArrayList<Double> getLineList(int line)
	{
		ArrayList<Double> l = new ArrayList<Double>();
		for (int i = 0; i < this.getColumns(); i++)
		{
			l.add(elements[line][i]);
		}
		return l;
	}
	
	/**
	 * Return an ArrayList of double containing the value of the specified column.
	 * @param column The column index starting at 0 as an integer.
	 * @return An ArrayList of double.
	 */
	public ArrayList<Double> getColumnList(int column)
	{
		ArrayList<Double> c = new ArrayList<Double>();
		for (int i = 0; i < this.getLines(); i++)
		{
			c.add(elements[i][column]);
		}
		return c;
	}
	
	/**
	 * Return the multiplication of two matrixes, if their sizes are not adequate, an exception is raised.
	 * @param matrix The matrix to multiply.
	 * @return The product of the multiplication as a Matrix.
	 * @throws Exception If the matrixes do not have adequate sizes.
	 */
	public Matrix multMatrix(Matrix matrix) throws Exception
	{
		int lines1 = this.getLines();
		int columns1 = this.getColumns();
		int lines2 = matrix.getLines();
		int columns2 = matrix.getColumns();
	
		if (columns1 != lines2)
		{
			throw new Exception("Cannot multiply matrixes! (" + lines1 + "x" + columns1 + " " + lines2 + "x" + columns2 + ")");
		}
		
		Matrix produit = new Matrix(lines1, columns2);
		
		for (int i = 0; i < lines1; i++)
		{
			for (int j = 0; j < columns2; j++)
			{
				double sommeprod = 0.0;
				for (int k = 0; k < columns1; k++)
				{
					sommeprod += matrix.get(k, j) * this.get(i, k);
				}
				produit.set(i, j, sommeprod);
			}
		}
		return produit;
	}
	
	/**
	 * Return the transposed the matrix.
	 * @return The transposed matrix.
	 */
	public Matrix transposeMatrix()
	{
		int lines = this.getLines();
		int columns = this.getColumns();
		Matrix transpose = new Matrix(columns, lines);
		for (int i = 0; i < lines; i++)
		{
			for (int j = 0; j < columns; j++)
			{
				transpose.set(j, i, elements [i][j]);
			}
		}
		return transpose;
	}
	
	/**
	 * Multiply each value of the matrix by a specified double.
	 * @param real The specified double.
	 * @return The multiplied matrix.
	 */
	public Matrix multReal(double real)
	{
		int lines = this.getLines();
		int columns = this.getColumns();
		for (int i = 0; i < lines; i++)
		{
			for (int j = 0; j < columns; j++)
			{
				elements[i][j] = elements [i][j] * real;
			}
		}
		
		return new Matrix(elements);
	}
	
	/**
	 * Sum two matrix, item by item, if possible otherwise an exception is raised.
	 * @param matrix The matrix to sum.
	 * @return The sum of the matrixes as a Matrix.
	 * @throws Exception If the two matrixes do not have the same size.
	 */
	public Matrix sumMatrix(Matrix matrix) throws Exception
	{
		int lines1 = this.getLines();
		int columns1 = this.getColumns();
		int lines2 = matrix.getLines();
		int columns2 = matrix.getColumns();
	
		if (columns1 != columns2 || lines1 != lines2)
		{
			throw new Exception("Cannot sum matrixes! (" + lines1 + "x" + columns1 + " " + lines2 + "x" + columns2 + ")");
		}
		Matrix sum = new Matrix(lines1, columns1);
		for (int i = 0; i < lines1; i++)
		{
			for (int j = 0; j < columns1; j++)
			{
				sum.set(i, j, elements[i][j] + matrix.get(i, j));
			}
		}
		return sum;
	}
	
	/**
	 * Simplify the matrix into a double if possible, otherwise an exception is raised.
	 * @return The value as a double
	 * @throws Exception If the matrix contains more than one value.
	 */
	public double simplifyMatrix() throws Exception
	{
		int lines = this.getLines();
		int columns = this.getColumns();
		if (lines == 1 && columns == 1)
		{
			return elements[0][0];
		}
		else
		{
			throw new Exception("Cannot simplify matrix!");
		}
	}
	
	/**
	 * Print the matrix.
	 */
	public void printMatrix()
	{
		System.out.println("{");
		for (int i = 0; i < elements.length;i++)
		{
			System.out.print("\t");
			for (int j = 0; j < elements[0].length; j++)
			{
				System.out.print(elements[i][j]);
				if (j == elements[0].length - 1)
				{
					System.out.println(";");
				}
				else
				{
					System.out.print(", ");
				}
			}
		}
		System.out.println("}");
	}
}
