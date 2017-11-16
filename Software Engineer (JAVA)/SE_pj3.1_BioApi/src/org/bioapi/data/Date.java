package org.bioapi.data;

/**
 * Represents calendar date. For security reasons no methods to modify the existing Date object are provided. Instead, an application should use the corresponding method of the DataFactory to create new Date containing modified data.
 * 
 * @author Ashwin Mohan
 */
public interface Date
{
	int getMday();
	
	int getMonth();
	
	int getYear();
}
