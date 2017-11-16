package org.bioapi.data;

/**
 * Represents the event that is sent to the application when the state of a unit changes. For security reasons no methods to modify the existing Event object are provided.
 * 
 * @author James Kupke
 */
public interface Event
{
	public Event.Kind getEventKind();
	
	public java.util.UUID getSource();
	
	public int getSourceUnit();
	
	public UnitSchema getSourceUnitProperties();
	
	public enum Kind
	{
		FAULT,
		
		INSERT,
		
		REMOVE,
		
		SOURCE_PRESENT,
		
		SOURCE_REMOVED;
	}
	
}
