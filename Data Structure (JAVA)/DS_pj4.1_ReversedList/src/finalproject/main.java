/*******************************************
Name: Shing Ng
Assignment id: ds4_01

@author  Andy Ng <andy@pcinvent.com>
PCinvent.com All Rights Reserved
Please do not delete the credit above!
********************************************/

package finalproject;

/**
 * @author Shing Ng
 */
import java.util.ArrayList;

public class main {
    // This class is used to test the implementation of
    // Reversed Index List that is done in ReversedList.java
    @SuppressWarnings("unchecked")
	public static void Main(String args[]) {
        
        // Create empty ArrayList of Normal List
        // -----------------------------------------------
        ArrayList List1 = new ArrayList();
        
        // Generate Data
        List1.add(1);
        List1.add(2);
        List1.add(3);
        List1.add(4);
        List1.add(5);
        List1.add(6);
        List1.add(7);
        List1.add(8);
        List1.add(9);
        
        // Show Result of List 1 ( Normal List )
        System.out.println("--- List1 ( Result of Normal List ) ---");
        for(int i=0;i<List1.size();i++)
        {
            System.out.println(i+"th Element: "+List1.get(i));
        }

        // Create empty ArrayList of Reversed List
        // -----------------------------------------------
        ArrayList List2 = new ArrayList();
        ReversedList ReversedList = new ReversedList(List2);
        
        // Generate Data
        ReversedList.add(1);
        ReversedList.add(2);
        ReversedList.add(3);
        ReversedList.add(4);
        ReversedList.add(5);
        ReversedList.add(6);
        ReversedList.add(7);
        ReversedList.add(8);
        ReversedList.add(9);
        
        // Show Result of ReversList ( Reversed List )
        System.out.println("--- ReversedList ( Result of Implimented Reversed Index List ) ---");
        for(int i=0;i<ReversedList.size();i++)
        {
            System.out.println(i+"th Element: "+ReversedList.get(i));
        }

        // Test all methods of the Reversed List
        // -----------------------------------------------

        System.out.println("--- Testing of using methods ---");
        System.out.println("Use add(index) method");
        ReversedList.add(10);  // method of add(index)
        ReversedList.add(11);  // method of add(index)
        System.out.println("Use remove(index) method");
        ReversedList.remove(1);  // method of add(index)
        System.out.println("Use get(index) method");
        ReversedList.get(5);  // method of add(index)
        System.out.println("Use set(index,element) method");
        ReversedList.set(0,99);  // method of set(index,element)
        ReversedList.set(1,98);  // method of set(index,element)
        System.out.println("Use size() method");
        ReversedList.size();  // method of size()
        System.out.println("Use currentIndex() method");
        ReversedList.currentIndex();  // method of currentIndex()

        // Show Result of the ReversList after using all methods
        System.out.println("--- ReversedList ( Result after using all methods ) ---");
        for(int i=0;i<ReversedList.size();i++)
        {
            System.out.println(i+"th Element: "+ReversedList.get(i));
        }


    } // main()
} // class main