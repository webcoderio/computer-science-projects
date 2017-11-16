package org.bioapi.data;

/**
 * <p>
 * Represents <i>False Match Rate</i> - a 32-but integer value (N) that indicates a probable False Match Rate of 
 * N/(231-1). The larger the value, the worse the result. Negative values are used to signal exceptional 
 * conditions. The only negative value currently defined is minus one.
 * </p>
 * <p>
 * For security reasons no methods to modify the exisitng FMR object are procided. Instead, an application 
 * should use the corresponding method of the DataFactory to create new FMR containing modified data.
 * </p>
 * 
 * @author	Ashwin Mohan
 */
public interface FMR
{
	public int getFalseMatchRate();
}