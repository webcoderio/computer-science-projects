package org.bioapi;

/**
 * Represents generic unit selected for an attach session.
 * 
 * @author	Ashwin Mohan
 */
public interface Unit
{
	/**
	 * Defines possible unit categories.
	 */
	public enum Category
	{
		/** The unit manages BSP's BIR database. */
		ARCHIVE,
		/** The unit is the collection of matching algorithms. */
		MATCHING,
		/** The unit is the collection of processing algorithms. */
		PROCESSING,
		/** The unit manages hardware sensor. */
		SENSOR
	}

	/**
	 * Defines possible values of indicator status.
	 */
	public enum IndicatorStatus
	{
		ACCEPT,
		BUSY,
		FAILURE,
		READY,
		REJECT
	}

	/**
	 * Defines unit power modes.
	 */
	public enum PowerMode
	{
		/** The mode when the unit is able to detect insertion/finger on/person present type of events. */
		DETECT,
		/** The mode when all functions are available. */
		NORMAL,
		/** Minimum mode. All functions off. */
		SLEEP
	}

	/**
	 * Sends control data from the application to the BioAPI Unit and receives status or operationdata 
	 * from that unit. The content of the 'opcode', the send (input) data, and the receive (output) data 
	 * will be specified in the related interface specification for this BioAPI Unit (or associated FPI, if 
	 * present).
	 * 
	 * @param	opcode	the function code in the BioAPI Unit to be called.
	 * @param	argument	the input argument for the function in the BioAPI Unit to be called.
	 * 
	 * @return	the data received from the BioAPI Unit after processing the function indicated by the 'opcode'.
	 */
	public byte[] control(int opcode, byte argument);

	/**
	 * 
	 * 
	 * @return	the indicator status of the BioAPI Unit if that BioAPI Unit supports it.
	 */
	public Unit.IndicatorStatus getIndicatorStatus();

	/**
	 * Sets the selected BioAPI Unit to the requested indicator status if the BioAPI Unit supports it. After 
	 * INDICATOR_ACCEPT or INDICATOR_REJECT is set in the 'IndicatorStatus' parameter, the status will 
	 * not be changed until the application sets another value.
	 * 
	 * @param	indicatorStatus	the setting for the indicator status of the BioAPI Unit.
	 */
	public void setIndicatorStatus(Unit.IndicatorStatus indicatorStatus);

	/**
	 * Sets the currently attached BioAPI Unit of the BSP attach session to the requested power mode if 
	 * the BioAPI Unit supports it.
	 * 
	 * @param	powerMode	the power mode to set the BioAPI Unit to.
	 */
	public void setPowerMode(Unit.PowerMode powerMode);
}