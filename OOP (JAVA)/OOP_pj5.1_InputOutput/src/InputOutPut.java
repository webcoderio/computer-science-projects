/**
 * @author  Andy Ng <admin@webcoder.io>
 * Webcoder.io All Rights Reserved
 * Please do not delete the credit above!
 */
import java.io.*;

public class InputOutPut {

   public static void main(String args[]) throws IOException {
         BufferedReader br = null; // initialization
         
         String filename = args[0];
           try  {
              br = new BufferedReader(
                       new InputStreamReader(
                           new FileInputStream(filename)));
           } catch ( IOException ioe )  {
             System.out.print("Error! Unable to read file");         
           }  // catch
   
         PrintWriter pw = null; // initialization
         filename = args[1];
           try  {
              pw = new PrintWriter(
                       new BufferedWriter(
                           new OutputStreamWriter(
                               new FileOutputStream(filename))));
           } catch ( IOException ioe )  {
              System.out.print("Error! Unable to write file");     
           }  // catch
          
         String line = br.readLine();
         while (line != null) {
         try  {
             pw.println(line);
           }
       finally {
            line = br.readLine();
       }
     } // while
         pw.flush();
         pw.close();
   } // main

   public void AlltoUpperCase(String filename)
   {
      
      
   } // StringtoUpperCase
   
   public void FirstLettertoUpperCase(String filename)
   {
      
   } // StringtoUpperCase
   
   public void GuiUpperCase(String filename)
   {
      
   } // StringtoUpperCase

} // class
   