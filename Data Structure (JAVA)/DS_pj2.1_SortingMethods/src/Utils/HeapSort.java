/*******************************************
Name: Shing Ng
Assignment id: ds2_01
Due Date: Nov. 18, 2009
********************************************/

package Utils;

/**
 *
 * @author Shing Ng
 * @desc Heap Sort Implimentation
 */
public class HeapSort {
    /**
     * Heapsort algorithm.
     * @param a an array of Comparable items.
     */
    public static void heapsort( int [ ] a )
    {
        for( int i = a.length / 2; i >= 0; i-- )  /* buildHeap */
            percDown( a, i, a.length );
        for( int i = a.length - 1; i > 0; i-- )
        {
            swapReferences( a, 0, i );            /* deleteMax */
            percDown( a, 0, i );
        }
    }

    /**
     * Internal method for heapsort.
     * @param i the index of an item in the heap.
     * @return the index of the left child.
     */
    private static int leftChild( int i )
    {
        return 2 * i + 1;
    }

    /**
     * Internal method for heapsort that is used in
     * deleteMax and buildHeap.
     * @param a an array of Comparable items.
     * @index i the position from which to percolate down.
     * @int n the logical size of the binary heap.
     */
    private static void percDown( int [ ] a, int i, int n )
    {
        int child;
        int tmp;

        for( tmp = a[ i ]; leftChild( i ) < n; i = child )
        {
            child = leftChild( i );
            if( child != n - 1 && a[ child ] < a[ child + 1 ] )
                child++;
            if( tmp < a[ child ] )
                a[ i ] = a[ child ];
            else
                break;
        }
        a[ i ] = tmp;
    }


    /**
     * Method to swap to elements in an array.
     * @param a an array of objects.
     * @param index1 the index of the first object.
     * @param index2 the index of the second object.
     */
    public static final void swapReferences( int [ ] a, int index1, int index2 )
    {
        int tmp = a[ index1 ];
        a[ index1 ] = a[ index2 ];
        a[ index2 ] = tmp;
    }
}
