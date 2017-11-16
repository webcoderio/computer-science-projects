package org.bioapi;

import org.bioapi.BioAPIException;
import org.bioapi.data.Event;
/**
 * The application implements the EventHandler interface to handle events reported by the biometric service provider. The application should avoid any calls to BioAPI while the body of the event handler is executed, otherwise a deadlock condition can occur. One possible use case of the event handler is the synchronyzation of execution of the application with the state of the biometric unit. For example, the concrete implementation of the event handler can provide the waitForSample() method that will block the execution of the calling thread until the SOURCE_PRESENT event is received for the particular unit. 
 * @author Ashwin Mohan
 */

public interface EventHandler
{
	/**
	* handle public void handle(Event event) throws BioAPIException This method is invoked by the Framework when a change in the unit status is detected. See the documentation for the Event interface for details. The application can put an arbitrary code in the body of this method. The only limitation is that any calls to BioAPI should be avoided until the method returns to the caller. Calling the BioAPI from the event handler can lead to infinite loops or cause the deadlock condition.
	* @param event - identifies the source unit of the event and the reason of the event. 
	* @throws BioAPIException - if any error occurs while the event is processed.
	**/

	public void handle(Event event) throws BioAPIException;
}

