/*******************************************
Name: Shing Ng
Assignment id: ds3

@author  Andy Ng <admin@webcoder.io>
Webcoder.io All Rights Reserved
Please do not delete the credit above!
*******************************************/

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Shing Ng
 */
public class Project3 {

    /**
     * @param args the command line arguments
     */
    @SuppressWarnings("unchecked")
	public static void main(String[] args) {

        String Source = "";
        String Destination = "";
        boolean EdgeFound = false;

        /////////////////////// Data  /////////////////////////////

        // Input String Array
        // after the word "tower", others are random generated which might not be meaningful
        String [] Words = {
        "bleed","fleed","fleet","fleeg","blend","blond","blood","reter","refer","defer","deter","dever",
        "fever","sorry","soort","saurt","worry","poder","poter","power","tower","zouks","zowie","zuzim",
        "aahed","aalii","aargh","abaca","abaci","aback","abaft","abaka","abamp","abase","abash","abate",
        "abaya","abbas","abbes","abbey","abbot","abeam","abele","abets","abhor","abide","abled","abler",
        "ables","abmho","abode","abohm","aboil","aboma","aboon","abort","about","above","abris","abuse",
        "abuts","abuzz","abyes","abysm","abyss","acari","acerb","aceta","ached","aches","achoo","acids",
        "acidy","acing","acini","ackee","acmes","acmic","acned","acnes","acock","acold","acorn","acred",
        "acres","acrid","acted","actin","actor","acute","acyls","adage","adapt","addax","added","adder",
        "addle","adeem","adept","adieu","adios","adits","adman","admen","admit","admix","adobe","adobo",
        "adopt","adore","adorn","adown","adoze","adult","adunc","adust","adyta","adzed","adzes","aecia",
        "aedes","aegis","aeons","aerie","afars","affix","afire","afoot","afore","afoul","afrit","after",
        "again","agama","agape","agars","agate","agave","agaze","agene","agent","agers","agger","aggee"
        };

       // Create Array List Comparison
       ArrayList JoinedList = new ArrayList();

       for(int i=0; i< Words.length-1;i++) // Word compare to next
       {
           // Compare to next
           int WordMatch = 0;
           // PositionCounted used for duplicated chracters in one word
           // Example: dleed and blecd
           int PositionCounted = -1;
           for(int j=0; j<Words[i].length(); j++) // First word Char
           {
               PositionCounted = -1; // Re count when comparison done
               for(int k=0; k<Words[i].length(); k++) // Second word Char
               {
                    if( Words[i].charAt(j) == Words[i+1].charAt(k) && PositionCounted != k )
                    {
                        PositionCounted = k;
                        WordMatch++;
                        break;
                    }
               } // for k
           } // for j

           // Set Relationship for iterator later
           if(WordMatch == 4)
               JoinedList.add("T");
           else
               JoinedList.add("F");
       } // for i

       
       /////////////////////// Presentation  /////////////////////////////

        for(;;) { // infinity for loop

       // Greeting
       System.out.println("********************************************");
       System.out.println("Welcome to word transition game!");
       System.out.println("********************************************");

       // Capture inputs
       System.out.println("Please input the source word:");
       BufferedReader SourceBR = new BufferedReader(new InputStreamReader(System.in));
       try
       {
           Source = SourceBR.readLine();
       }
       catch(IOException e)
       {
           System.out.println("Error!");
       }

       System.out.println("Please input the destination word:");
       BufferedReader DestinationBR = new BufferedReader(new InputStreamReader(System.in));
       try
       {
           Destination= DestinationBR.readLine();
       }
       catch(IOException e)
       {
           System.out.println("Error!");
       }

       
       //Get position of 2 Source and destination

        // init not found values
        int SourcePosition = -1;
        int DestinationPosition = -1;

       for( int i=0; i<Words.length;i++ )
       {
            if(Words[i].equals(Source) && SourcePosition == -1)
            {
               SourcePosition = i;
            }
            else if(Words[i].equals(Destination) && DestinationPosition == -1)
            {
                DestinationPosition = i;
            }
        } // for

         // Compare 2 positions ( Do Left to Right checking )
        if(SourcePosition!= -1 && DestinationPosition != -1)
        {
            if(SourcePosition<DestinationPosition) // Source is on the left
            {
                EdgeFound = true;
                for(int i=SourcePosition; i<DestinationPosition;i++)
                {
                    if(JoinedList.get(i)=="F" )
                    {
                        EdgeFound = false;
                        break;
                    }
                } // for
            }
            else // Destination is on the left
            {
                EdgeFound = true;
                for(int i=DestinationPosition; i<SourcePosition;i++)
                {
                    if(JoinedList.get(i)=="F" )
                    {
                        EdgeFound = false;
                        break;
                    }
                } // for
            }
        }
        else
        {
            if(SourcePosition==-1)
                System.out.println("Sorry. The world "+Source+" does not exist in the dictionary");
            if(DestinationPosition==-1)
                System.out.println("Sorry. The world "+Destination+" does not exist in the dictionary");
        } // else

        // Result

        if(EdgeFound)
        {
            System.out.println("Great! You can change from "+Source+" to "+Destination+".");
            System.out.println("The path is:");
            if(SourcePosition<DestinationPosition) // Source is on the left
            {
                for(int i=SourcePosition; i<DestinationPosition;i++)
                {
                    System.out.println(Words[i]+",");
                }
                System.out.println(Words[DestinationPosition]); // Print last without comma
            }
            else // Destination is on the left
            {
                for(int i=DestinationPosition; i<SourcePosition;i++)
                {
                    System.out.println(Words[i]+",");
                }
                System.out.println(Words[SourcePosition]); // Print last without comma
            }
        }
        else
        {
            System.out.println("Sorry. No way to transit from "+Source+" to "+Destination);
        }

        } // for loop

    } // main

} // class