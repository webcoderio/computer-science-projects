/*******************************************
Name: Shing Ng
Assignment id: ds1_02
Due Date: Sept. 30, 2009
********************************************/

package CustomerQueue;
import java.util.Queue;
import java.util.LinkedList;

/**
 * @description:
 * This class file create the object of the customer information
 * @author Shing Ng
 */
public class Main {

    /**
     * @description:
     * This is the main method
     */
     public static void main(String[] args)
    {
        // Create customer instance
        Customer C = new Customer(0);

        // Create Queue implement LinkedList
        Queue<String> Q=new LinkedList<String>();

        // Store      
        for( int i=0; i<C.getTotalCustomers(C.CustomerArry); i++)
           Q.add(Integer.toString(i));  // i as Customer unique number

        // Display during removing, print out all customer info by customer id number
        while(!Q.isEmpty())
        {
          System.out.print( "Time: "+C.getArrivalTime( C.CustomerArry, Integer.valueOf(Q.element()) ) + " | " );

          // String manipulate to give proper format of "st", "nd" or "th"
          if(Q.element().substring(Q.element().length()-1,Q.element().length()).equals("0")) // Shift 1; 1st
              System.out.print( C.getCustomerNumber( C.CustomerArry, Integer.valueOf(Q.element()) ) + "st customer enters | "  );
          else if(Q.element().substring(Q.element().length()-1,Q.element().length()).equals("1")) // Shift 1, 2nd
              System.out.print( C.getCustomerNumber( C.CustomerArry, Integer.valueOf(Q.element()) ) + "nd customer enters | "  );
          else
              System.out.print( C.getCustomerNumber( C.CustomerArry, Integer.valueOf(Q.element()) ) + "th customer enters | "  );

          System.out.print( "Serive duration expected: "+C.getServiceDuration( C.CustomerArry, Integer.valueOf(Q.element()) ) + " | "  );
          System.out.println( "Total "+Q.size()+" Customer in Queue");
          Q.remove(); // remove top
        }
    } // main
} // class