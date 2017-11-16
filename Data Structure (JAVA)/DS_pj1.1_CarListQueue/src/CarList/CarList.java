/*******************************************
Name: Shing Ng
Assignment id: ds1_01

Author: Andy Ng <admin@webcoder.io>
Webcoder.io All Rights Reserved
Please do not delete the credit above!
********************************************/

package CarList;

import java.io.*;

/**
 * @description:
 * This class file create the object of the Car List
 * with Makes and Model values
 * @author Shing Ng
 */
public class CarList {
    private String Make;
    private String Model;

    // Contructor
    public CarList(){}
    
    /**
     * @return String Make
     * Car Make value
     */
    public String setMake()
    {
        System.out.println("====================================");
        System.out.println("Please enter your Make Name (Word)!");

        // Show make list
        String[] MakeList = {"chevy","toyota","honda","ford"};
        for( int i=0; i< MakeList.length; i++ )
        {
            System.out.println(i+": "+MakeList[i]);
        }

        // Capture input
        BufferedReader MakeBR = new BufferedReader(new InputStreamReader(System.in));

        try
        {
            Make = MakeBR.readLine();
        }
        catch(IOException e)
        {
            System.out.println("Error!");
        }

        // Show result
        if( Make.equals("honda") )
        {
            System.out.println("You have select the make: "+Make);
        }
        else
        {
            System.out.println("Sorry, we do not have inventory for make "+Make);
            Make = "";
        }

        return Make;
    }

    /**
     * @return String Make
     * Car Make getter
     */
    public String GetMake(){ return Make; }

    /**
     * @param  String Make
     * Car Make value
     * @return String Model
     * Car Model value
     */
    public String setModel( String Make )
    {
        System.out.println("====================================");
        System.out.println("Please enter your Model Name for "+Make+" (Word)");

        if( Make.equals("honda") )  // As we have only honda in list
        {
            // Show model list
            String[] ModelList = {"civic","accord","odessy"};
            for( int i=0; i< ModelList.length; i++ )
            {
                System.out.println(i+": "+ModelList[i]);
            }

            // Capture input
            BufferedReader ModelBR = new BufferedReader(new InputStreamReader(System.in));
            try
            {
                Model = ModelBR.readLine();
            }
            catch(IOException e)
            {
                System.out.println("Error!");
            }

            // Show reuslt
            System.out.println("You have select the model: "+Model+" for "+Make);
        } // if
        else    // If the make is not honda
        {
            System.out.println("I am sorry, no model inventory availiable for "+Make);
            Model = "";
        }

        return Model;
    }

    /**
     * @return String Model
     * Car Model value
     */
    public String GetModel(){ return Model; }

    /**
     * @param String Make
     * Car Make value
     * @param String Model
     * Car Model Value
     * @return void
     */
    public void showCar(String Make, String Model)
    {
        System.out.println("===================================="); // for easier reading

        // Make description
        if( Make.equals("honda") )
            System.out.println("Honda is a Japanese Car maker");
        else
            System.out.println("No Description availiable for make "+Make);

        // Model description
        if( Model.equals("civic") )
            System.out.println("Civic is a Econmoical model");
        else
            System.out.println("No Description availiable for model "+Model);
    }
}