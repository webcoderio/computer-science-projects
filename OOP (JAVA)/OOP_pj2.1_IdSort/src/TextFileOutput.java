import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

/**
 * Simplified text file output writer
 * with methods similar to 
 * <code>java.io.PrintWriter</code>.
 * If the text file cannot be created,
 * a RuntimeException is thrown,
 * which by default results an an
 * error message being printed to
 * the standard error stream.
 *
 * @author D. Nixon
 */
public class TextFileOutput  {

   /**  Name of text file  */
   @SuppressWarnings("unused")
   private String filename;

   /**  Character output stream for file */
   private PrintWriter pw;

   /**
    * Creates a buffered character output
    * stream for the specified text file.
    *
    * @param filename the output text file.
    * @exception RuntimeException if an
    *            IOException is thrown when
    *            attempting to create the file.
    */
   public TextFileOutput(String filename)
   {
      this.filename = filename;
      try  {
         // Using inherited protected variable out:
         pw = new PrintWriter(
                  new BufferedWriter(
                      new OutputStreamWriter(
                          new FileOutputStream(filename))));
      } catch ( IOException ioe )  {
         throw new RuntimeException(ioe);
      }  // catch
   }  // constructor

    /**
     * Flushes the output character stream.
     * Moves characters out of all intermediate
     * memory buffers and into the file itself.
     * This method should be called after all
     * writing is completed, but before the
     * file is closed.
     */
    public void flush() { pw.flush(); }

    /**
     * Closes the file.
     * No more characters can be written
     * to the file after it is closed.
     */
    public void close() { pw.close(); }

    /**
     * Flushes the output character stream and checks whether an error has occurred.
     * Errors are cumulative; once an error is encountered, this routine will
     * return true on all successive calls.
     *
     * @return True if the print stream has encountered an error, either on the
     * underlying output stream or during a format conversion.
     */
    public boolean checkError() { return pw.checkError(); }

    /**
     * Prints a boolean value (in the form of the string
     * "true" or "false") without terminating the line.
     *
     * @param      b   The <code>boolean</code> to be printed
     */
    public void print(boolean b) { pw.print(b); }

    /**
     * Prints a character.
     *
     * @param      c   The <code>char</code> to be printed
     */
    public void print(char c) { pw.print(c); }

    /**
     * Prints an integer without terminating the line.
     *
     * @param      i   The <code>int</code> to be printed
     * @see        java.lang.Integer#toString(int)
     */
    public void print(int i) { pw.print(i); }

    /**
     * Prints a long integer without terminating the line.
     *
     * @param      l   The <code>long</code> to be printed
     * @see        java.lang.Long#toString(long)
     */
    public void print(long l) { pw.print(l); }

    /**
     * Prints a floating-point number without terminating the line.
     *
     * @param      f   The <code>float</code> to be printed
     * @see        java.lang.Float#toString(float)
     */
    public void print(float f) { pw.print(f); }

    /**
     * Prints a double-precision floating-point number
     * without terminating the line.
     *
     * @param      d   The <code>double</code> to be printed
     * @see        java.lang.Double#toString(double)
     */
    public void print(double d) { pw.print(d); }

    /**
     * Prints an array of characters without terminating the line.
     *
     * @param      s   The array of chars to be printed
     * @exception  NullPointerException  if <code>s</code> is <code>null</code>
     */
    public void print(char[] s) { pw.print(s); }

    /**
     * Prints a string without appending a line separator.
     *
     * @param      s   The <code>String</code> to be printed
     */
    public void print(String s) { pw.print(s); }

    /**
     * Prints an object without terminating the line.
     * Prints the <code>String</code> returned by
     * the object's <code>toString</code> method.
     *
     * @param      obj   The <code>Object</code> to be printed
     * @see        java.lang.Object#toString()
     */
    public void print(Object obj) { pw.print(obj); }

    /**
     * Terminates the current line by writing the line separator string.  The
     * line separator string is defined by the system property
     * <code>line.separator</code>, and is not necessarily a single newline
     * character (<code>'\n'</code>).
     */
    public void println() { pw.println(); }

    /**
     * Prints a boolean value and then terminates the line.
     */
    public void println(boolean x) { pw.println(x); }

    /**
     * Prints a character and then terminates the line.
     */
    public void println(char x) { pw.println(x); }

    /**
     * Prints an integer and then terminates the line.
     */
    public void println(int x) { pw.println(x); }

    /**
     * Prints a long integer and then terminates the line.
     */
    public void println(long x) { pw.println(x); }

    /**
     * Prints a floating-point number and then terminates the line.
     */
    public void println(float x) { pw.println(x); }

    /**
     * Prints a double-precision floating-point number and then terminates the
     * line.
     */
    public void println(double x) { pw.println(x); }

    /**
     * Prints an array of characters and then terminates the line.
     */
    public void println(char x[]) { pw.println(x); }

    /**
     * Prints a String and then terminates the line.
     */
    public void println(String x) { pw.println(x); }

    /**
     * Prints an Object and then terminates the line.
     * Prints the <code>String</code> returned by
     * the object's <code>toString</code> method,
     * followed by a line separator.
     */
    public void println(Object x) { pw.println(x); }
}  // class TextFileOutput
