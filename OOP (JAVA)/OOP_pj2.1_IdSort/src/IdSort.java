/**
 * This program use the TextFileInput class
 * provide by Prof. D. Nixon
 * which will then read the input text data
 * and display the message box with sorting
 * with zip code and price.
 *
 * @author  Andy Ng <andy@pcinvent.com>
 * PCinvent.com All Rights Reserved
 * Please do not delete the credit above!
 */

import java.util.StringTokenizer;
import javax.swing.JOptionPane;

public class IdSort 
{
      /**
       * Main method to call others method below
       * It will show 3 dialog boxes
       * @param args
       */
       public static void main(String[] args)
       {
          final int MAX_NUMBER_OF_NUMBERS = 50;
          
          // Check Parameter during executing
          if ( args.length == 0 )
          {
             System.out.println("You need to type with the file name!");
             System.out.println("e.g. java Project1 project1.txt");
             System.exit(0);
          } // end of if ( args.length == 0 )
          
          // Variables
          final String inputFileName = args[0];
          String[] numbersArray = new String[MAX_NUMBER_OF_NUMBERS];
 
          // Using Read File method
          int subArrayLength = readFile(inputFileName, numbersArray);
 
          // Using Sort by Zip Code method and display 1st panel
         sortbyZipCode(numbersArray, subArrayLength);
          // Display sorted by Code to Panel 1
         displayResults(numbersArray, subArrayLength);
               
          // Using Sort by Price Code method 2nd panel
          sortByPrice(numbersArray, subArrayLength);
          // Display sorted by Code to Panel 2
          displayResults(numbersArray, subArrayLength);
        }  // end of method main

 /**
  * This method reads from the file whose filename is given as a parameter,
  * and fills array of strings representing capacities.
  * @param String filename
  * @param String[] capacities
  * @return int lengthFilled
  */
   private static int readFile(String filename, String[] capacities)
   {
           TextFileInput in = new TextFileInput(filename);  // using TextFileInput class
 
           // Read capacities into array:
           int lengthFilled = 0;
           String line = in.readLine();   // read first line in the input file
           while ( lengthFilled < capacities.length && line != null )
           {
             line = in.readLine();       // read next line in file
             lengthFilled++;
           } // end of while
 
           // Use the capacities limit to check if file size was exceed.
           if ( line != null )  // if doesn't reaches EOF
           {
             System.out.println("File contains too much capacity.");
             System.out.println(
                   "This program can process only "
                   + capacities.length
                   + " capacities."
                   );
             System.exit(1);
           }  // end of if( line != null )
           
           if( !isValidHouse(line) ) // call method isValidHouse
           {
              System.out.println("The input file is not valid!");
           }
 
           // closing
           in.close();
 
           return lengthFilled;
       }  // end of method readFile
 
   /**
    * This method sorts the array of strings
    * by zipCode name using Selection Sort.
    * @param String[] lines
    * @param int length
    */
   public static void sortbyZipCode (String[] lines, int length)
   {
      for ( int i = 0; i < length - 1; i++ )
      {
         int Lowestindex = i;
         for ( int j = i + 1; j < length; j++ )
         {
            if ( lines[j].compareTo(lines[Lowestindex]) < 0 )
            Lowestindex = j;
         }  // end of for ( int j = i + 1; j < length; j++ )
      
         if ( lines[Lowestindex] != lines[i] )
         {
            String temp = lines[Lowestindex];
            lines[Lowestindex] = lines[i];
            lines[i] = temp;
         } // end of if ( lines[Lowestindex] != lines[i] )
      } // for i

   }    // end of method sortbyZipCod
   
   /**
    * This method sorts the array in price order using selection sort
    * @param String[] houses
    * @param int length
    */
   public static void sortByPrice (String[] houses, int length)
   {
      for ( int i = 0; i < length - 1; i++ )
      {
         int Lowestindex = i;
         for ( int j = i + 1; j < length; j++ )
         {
            if ( comparePrice(houses[j], houses[Lowestindex]) < 0 )
            Lowestindex = j;
         }  // end of for ( int j = i + 1; j < length; j++ )
         if ( houses[Lowestindex] != houses[i] )
         {
            String temp = houses[Lowestindex];
            houses[Lowestindex] = houses[i];
            houses[i] = temp;
         } // end of if ( houses[Lowestindex] != houses[i] )
      } // end of for i
   }    // end of method sortbyPrice

   /**
    * This method compares two capacities.
    * Returns a negative number if house1's is less than house2's,
    * 0 if the price of the houses is the same, or a positive number
    * if house1's price is greater than house2's.
    * @param String house1
    * @param String house2
    * @return int 0, -1 or 1
    */
   public static int comparePrice(String house1, String house2)
   {
      // Separation 1
      StringTokenizer StrToken1 = new StringTokenizer(house1, "|");
      @SuppressWarnings("unused")
	int zipcode1 = Integer.parseInt(StrToken1.nextToken());
      int price1 = Integer.parseInt(StrToken1.nextToken());
      @SuppressWarnings("unused")
	int extra1 = Integer.parseInt(StrToken1.nextToken());
   
      // Separation 2
      StringTokenizer StrToken2 = new StringTokenizer(house2, "|");
      @SuppressWarnings("unused")
	int zipcode2 = Integer.parseInt(StrToken2.nextToken());
      int price2 = Integer.parseInt(StrToken2.nextToken());
      @SuppressWarnings("unused")
	int extra2 = Integer.parseInt(StrToken2.nextToken());
   
      if(price1 < price2)
         return -1;
      else if(price1 == price2)
         return 0;
      else
         return 1;
   }    // end of method comparePrice

   /**
    * This method prints the contents of the array to a text area on a JOptionPane.
    * @param String[] houses
    * @param int length
    */
   public static void displayResults(String[] houses, int length)
   {
      String result = "";   // initialize result

      for(int i = 0; i < houses.length; i++)
         result += houses[i] + "\n";

      // display the output dialog box:
      JOptionPane.showMessageDialog(null, result);

   }    // end of method displayResults  

   /**
    * This method Returns true if and only if the string representing a house has
    * the proper format (see Error Checking above.
    * @param Strin house
    * @return boolean
    */
   public static boolean isValidHouse(String house)
   {
      StringTokenizer strToken = new StringTokenizer(house, "|");
   
      // validation of line which should have three fields separated by "|"
      if(strToken.countTokens() != 3)
      {
         return false;
      }
      
    // correct number of tokens
      @SuppressWarnings("unused")
	int zipcode = Integer.parseInt(strToken.nextToken());
      @SuppressWarnings("unused")
	int price = Integer.parseInt(strToken.nextToken());
      return true;
   }    // end of method isValidHouse
   
}   // end of class Project1
