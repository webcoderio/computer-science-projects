/*******************************************
Name: Shing Ng
Assignment id: ds2_01
Due Date: Nov. 18, 2009
********************************************/

package Utils;

import java.util.Random;
/**
 *
 * @author Shing Ng
 * @desc This method is used to generate array with limit items
 */
public class Utils {

  public static int[] random_serial(int limit) {
         int[] result = new int[limit];
         for (int i = 0; i < limit; i++)
             result[i] = i;
        int w;
        Random rand = new Random();
         for (int i = limit - 1; i > 0; i--) {
             w = rand.nextInt(i);
             int t = result[i];
             result[i] = result[w];
             result[w] = t;
         }
         return result;
     }
}