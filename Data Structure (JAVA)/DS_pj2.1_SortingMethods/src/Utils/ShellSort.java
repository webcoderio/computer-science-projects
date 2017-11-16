/*******************************************
Name: Shing Ng
Assignment id: ds2_01
Due Date: Nov. 18, 2009
********************************************/

package Utils;

/**
 *
 * @author Shing Ng
 * @desc Shell Sort Implimentation
 */
public class ShellSort {
    /**
     * Shellsort algorithm.
     * @param a an array of Comparable items.
     */

    public static void shellsort( int [ ] a )
    {
        for( int gap = a.length / 2; gap > 0;
                     gap = gap == 2 ? 1 : (int) ( gap / 2.2 ) )
            for( int i = gap; i < a.length; i++ )
            {
                int tmp = a[ i ];
                int j = i;

                for( ; j >= gap && (tmp < a[ j - gap ]) ; j -= gap )
                    a[ j ] = a[ j - gap ];
                a[ j ] = tmp;
            }
    }

}
