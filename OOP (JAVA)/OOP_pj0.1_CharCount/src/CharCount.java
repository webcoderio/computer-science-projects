/**
 * This Program prompt the user an input dialog
 * to input string.
 * It will shows how many uppercase and lowercase
 * char 'e' that were
 * contained in the string that user has typed.
 * */

/**
 *
 * @author  Andy Ng <admin@webcoder.io>
 * Webcoder.io All Rights Reserved
 * Please do not delete the credit above!
 *
 * This e-mail address is being protected from spam bots, you need JavaScript enabled to view it
 * @category CS212
 * @since 2006/02/25
 * @version 1.0
 */

import javax.swing.JOptionPane;//Impoty swing class GUI

public class CharCount  {

   /**
    * Main method.
    */
   public static void main(String[] args)
   {
      //Definitions
      String userInput, resultMsg;
      int sumLowerE=0, sumUpperE=0;
     
      //Prompt Input
      userInput = JOptionPane.showInputDialog(null, "Please enter a sentence.");
     
      //Determine How many 'e'
      for(int i=0; i<userInput.length();i++ )
      {
         if(userInput.charAt(i)=='e')
            sumLowerE++;
         if(userInput.charAt(i)=='E')
            sumUpperE++;
      }
     
      //Output Result
      resultMsg = "Number of lower case e's: " + sumLowerE + "\n"//line break
                + "Number of upper case e's: " + sumUpperE;
      JOptionPane.showMessageDialog(null, resultMsg);
     
      System.exit(0);     
   }
}  // method main(String[]) 