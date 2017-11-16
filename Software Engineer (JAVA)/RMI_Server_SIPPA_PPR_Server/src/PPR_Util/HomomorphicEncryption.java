package PPR_Util;
import java.io.*;
import java.text.NumberFormat;
import Jama.*;

public class HomomorphicEncryption {
	private double k, a, ek;
	private Matrix msg;
	
	public HomomorphicEncryption(double k, double a, Matrix m){
		this.k=k;
		this.a=a;
		ek=Math.pow(k, a);		
		double[][] arrM=m.getArray();
		double[][] arrMsg=new double[m.getRowDimension()][m.getColumnDimension()];
		for (int i=0; i< m.getRowDimension(); i++)
			for (int j=0; j< m.getColumnDimension(); j++)
				arrMsg[i][j]=Math.pow(ek, arrM[i][j]);
		msg=new Matrix(arrMsg);
	}
	
	public HomomorphicEncryption(double k, Matrix m){
		this.k=k;
		this.a=1;
		ek=k;
		msg=new Matrix(m.getRowDimension(), m.getColumnDimension(), 0.);
		double[][] arrM=m.getArray();
		double[][] arrMsg=new double[m.getRowDimension()][m.getColumnDimension()];
		for (int i=0; i< m.getRowDimension(); i++)
			for (int j=0; j< m.getColumnDimension(); j++)
				arrMsg[i][j]=Math.pow(ek, arrM[i][j]);
		msg=new Matrix(arrMsg);
	}
	
	/**
	 * Get an encrypted matrix
	 * @return an encrypted matrix
	 */
	public Matrix getEncryptedMatrix(){return msg;}
	
	/**
	 * Get a decrypted matrix
	 * @return a decrypted matrix
	 */
	public Matrix getDecryptedMatrix(){
		double[][] arrMsg=msg.getArray(); 
		double[][] arrX=new double[msg.getRowDimension()][msg.getColumnDimension()];
		for (int i=0; i< msg.getRowDimension(); i++)
			for (int j=0; j< msg.getColumnDimension(); j++)
				arrX[i][j]=Math.log(arrMsg[i][j])/Math.log(ek);
		return new Matrix(arrX);
	}
	
	/**
	 * Print the HomomorphicEncryption matrix to the output stream.
	 * Column width=1
	 * @param output Output stream.
	 * @param d      Number of digits after the decimal.
	 */
	public void print(PrintWriter output){
		msg.print(output);
	}
	
	/** Print the HomomorphicEncryption matrix to stdout.
	 *  Line the elements up in columns
     *  with a Fortran-like 'Fw.d' style format.
     *  @param w    Column width.
     *  @param d    Number of digits after the decimal.
     */
	public void print (int w, int d) {
		msg.print(w, d);
	}
	
	/** Print the HomomorphicEncryption matrix to the output stream.
	 * Line the elements up in columns with a Fortran-like 'Fw.d' style format.
     * @param output Output stream.
     * @param w      Column width.
     * @param d      Number of digits after the decimal.
     */
    public void print (PrintWriter output, int w, int d) {
	    msg.print(output, w, d);
    }
	
    /** Print the HomomorphicEncryption matrix to stdout.
     * Line the elements up in columns.
     * Use the format object, and right justify within columns of width characters.
     * Note that is the matrix is to be read back in, you probably will want
     * to use a NumberFormat that is set to US Locale.
     * @param format A  Formatting object for individual elements.
     * @param width     Field width for each column.
     * @see java.text.DecimalFormat#setDecimalFormatSymbols
     */
    public void print (NumberFormat format, int width) {
    	msg.print(format, width);
    }
    
    /** Print the HomomorphicEncryption matrix to the output stream.
     * Line the elements up in columns.
     * Use the format object, and right justify within columns of width characters.
     * Note that is the matrix is to be read back in, you probably will want
     * to use a NumberFormat that is set to US Locale.
     * @param output the output stream.
     * @param format A formatting object to format the matrix elements 
     * @param width  Column width.
     * @see java.text.DecimalFormat#setDecimalFormatSymbols
     * */
    public void print (PrintWriter output, NumberFormat format, int width) {
    	msg.print(output, format, width);
    }
    
    /** Read a HomomorphicEncryption matrix from a stream.
     * The format is the same the print method,
     * so printed matrices can be read back in (provided they were printed using US Locale).
     * Elements are separated by whitespace,
     * all the elements for each row appear on a single line,
     * the last row is followed by a blank line.
     * @param input the input stream.
     * */
    public static HomomorphicEncryption read (BufferedReader input, double k, double a) throws java.io.IOException {
    	Matrix em=Matrix.read(input);
    	double[][] arrEm=em.getArray();
    	double[][] arrDm=new double[em.getRowDimension()][em.getColumnDimension()];
    	double ek=Math.pow(k, a);
    	for (int i=0; i<em.getRowDimension(); i++)
    		for (int j=0; j<em.getColumnDimension(); j++){
    			arrDm[i][j]=Math.log(arrEm[i][j])/Math.log(ek);
    		}
    	HomomorphicEncryption h=new HomomorphicEncryption(k, a, new Matrix(arrDm));
    	return h;
    }
}//class HomomorphicEncryption
