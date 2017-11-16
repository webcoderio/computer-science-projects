

package org.bioapi;
import java.util.Enumeration;

/**
 * The application provides the class implementing the Query interface to filter enumerations returned by the Framework and other objects. One possible use case of the Query interface is illustrated by the sample code in the documentation for the enumerateBSPs(Query<BSPProperties>) method of the Framework, where the application provides anonymous inner class to filter BSPs that support capture.  
 * 
 * @param T - specifies the type of record to be filtered. 
 * @author Ashwin Mohan
 */
public interface Query <T>
{
    /**
     * The contract for the method is that it returs true when the input object matches the criteria defined by the concrete implementation.
     *
     * @param object the input object for the filter
     * @return true if the object matches the selection criteria
     */
    boolean select(T object);

    /**
     * The contract for the method is that it returns the subset of the input enumeration that matches the criteria defined by the concrete implementation.
     *
     * @param  input enumeration for the filter
     * @return an enumeration of the objects matching the selection criteria
     */
    Enumeration<T> selectObjects(Enumeration<T> objects);
    
  
}
