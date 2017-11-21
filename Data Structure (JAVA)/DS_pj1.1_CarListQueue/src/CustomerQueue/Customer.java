/*******************************************
Name: Shing Ng
Assignment id: ds1_02

Author: Andy Ng <andy@pcinvent.com>
PCinvent.com All Rights Reserved
Please do not delete the credit above!
********************************************/

package CustomerQueue;

/**
 * @description:
 * This class file create the object of the customer information
 * @author Shing Ng
 */
public class Customer {

  // Customer information
  private String CustomerIdNumber;  // The unique number of the customer
  private String ArrivalTime;       // The time the customer arrive
  private String ServiceDuration;   // Expexcte how long it will take


  // Construct
  public Customer( int ArrivalTime ){}
  // Create Customer data in two dimension array
  // Format: { Customer number, Arrival time, Expected service duration }
  String[][] CustomerArry = {
      {"1","9:05","40"},
      {"2","9:30","35"},
      {"3","9:45","45"},
      {"4","10:20","30"},
      {"5","10:30","40"},
      {"6","10:50","15"},
  }; // CustomerArry

/**@description: Get total customer in size
 * @param  String[][] CustomerArry
 * Customer data
 * @return int CustomerArry.length
 * Customer data array length
 */
  public int getTotalCustomers( String[][] CustomerArry )
  {
      return CustomerArry.length;
  }

/**
 * @description: Get Customer Number by Customer Id Number
 * @param  String[][] CustomerArry
 * Customer data
 * @return int CustomerNumbe
 * Unique ordered Customer Id Number
 */
  public String getCustomerNumber( String[][] CustomerArry, int CustomerNumber )
  {
      CustomerIdNumber = CustomerArry[CustomerNumber][0];
      return CustomerIdNumber;
  }

/**
 * @description: Get Customer arrival time by Customer Id Number
 * @param  String[][] CustomerArry
 * Customer data
 * @return int CustomerNumbe
 * Unique ordered Customer Id Number
 */
  public String getArrivalTime( String[][] CustomerArry, int CustomerNumber )
  {
      ArrivalTime = CustomerArry[CustomerNumber][1];
      return ArrivalTime;
  }

/**
 * @description: Get Customer service duration time by Customer Id Number
 * @param  String[][] CustomerArry
 * Customer data
 * @return int CustomerNumbe
 * Unique ordered Customer Id Number
 */
  public String getServiceDuration( String[][] CustomerArry, int CustomerNumber )
  {
      ServiceDuration = CustomerArry[CustomerNumber][2];
      return ServiceDuration;
  }
}