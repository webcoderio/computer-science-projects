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
 * ReversedList reverses the order of the list index.
 * This will be praticulauly useful when we manage a very long list
 * where we only need to know the current last few items.
 * Hence, Index 0 will give the last one and Index 1 will give the last second one
 * In this case, we do not need to know what is the last item index
 * Simply use List[0], List[1] reversely
 */

import java.util.AbstractList;
import java.util.List;

/* Create object of Reversed index List*/
class ReversedList<E> extends AbstractList<E> {
    private List<E> list;

    /* Constructor of the list
     * @list:   List
     */
    public ReversedList(List<E> list)
    {
        this.list = list;
    }

    /* Method reverseIndex: Reverse the list index
     * @index:  List Index
     * @return: Integer of index number
     */
    private int reverseIndex(int index)
    {
        return ( (size()-1) - index );
    }


    /* Method currentIndex: Get the most rencent index
     * @return: Integer of last index of the list
     */
    public int currentIndex()
    {
        return list.lastIndexOf(list);
    }

    /* Method get: Get the element by the index
     * @index:  List Index
     * @return: List element of the index
     */
    
    @Override
    public E get(int index)
    {
        return list.get(index);
    }

    /* Method add: Add specific element to the last element
     * @index:      List Index
     * @element:    List element
     * @return:     void
     */
    @Override
    public void add(int index, E element)
    {
        list.add( (reverseIndex(index) + 1), element );
    }

    /* Method remove: remove specific list element
     * @index:  List Index
     * @return: list with the specific index element removed
     */
    @Override
    public E remove(int index)
    {
        return list.remove( reverseIndex(index) );
    }

    /* Method set: set the element to the index
     * @index:      List Index
     * @element:    List Element
     * @return:     List with the element been set by index
     */
    @Override
    public E set(int index, E element)
    {
        return list.set( reverseIndex(index), element );
    }

    /* Method size: Get Size of the list
     * @return: Integer of list size
     */
    public int size()
    {
        return list.size();
    }
    
} // class ReversedList