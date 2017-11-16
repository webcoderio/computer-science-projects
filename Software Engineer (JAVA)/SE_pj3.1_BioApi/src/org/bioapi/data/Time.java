package org.bioapi.data;

/**
 * Represents time of the day. For security reasons, no methods to modify the existing Time object are 
 * provided. Instead, an application should use the corresponding method of the DataFactory to create new
 * Time containing modified data.
 * 
 * @author	Ashwin Mohan
 */
public interface Time
{
	public int getHour();
	public int getMinute();
	public int getSecond();
}