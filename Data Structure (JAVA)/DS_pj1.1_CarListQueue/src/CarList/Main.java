/*******************************************
Name: Shing Ng
Assignment id: ds1_01

Author: Andy Ng <andy@pcinvent.com>
PCinvent.com All Rights Reserved
Please do not delete the credit above!
********************************************/

package CarList;

/**
 * @description:
 * This class file is the main class that call the methods of CarList object
 * @author Shing Ng
 */
public class Main {

    /**This is the main class file
     * Program start from here
     * @param args empty
     */

    public static void main(String[] args) {
        // Greeting
        System.out.println("Welcome to Automobile Showroom!");
        System.out.println("Please select from one of the following:");

        // Create car instance
        CarList NewCar = new CarList();

        // Enter Selections
        NewCar.setMake();
        NewCar.setModel( NewCar.GetMake() );
        
        // Result
        NewCar.showCar(NewCar.GetMake(), NewCar.GetModel());
    }
    
}
