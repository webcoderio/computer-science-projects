package org.bioapi.template;

import org.bioapi.*;
import org.bioapi.data.*;
import java.util.*;

/**
 *
 * @author Ashwin Mohan
 */
public class UnitClass_Implemented implements Unit, UnitSchema {

	/** Creates a new instance of UnitClass_Implemented */

	Unit.IndicatorStatus status;
	Unit.PowerMode mode;
          
	UUID BSPUuid;
	java.lang.String firmwareVersion;
	java.lang.String hardwareSerialNumber;
	java.lang.String hardwareVersion;
	long maxBSPDbSize;
	long maxIdentify;
	java.lang.String softwareVersion;
	Event.Kind[] supportedEvents;
	Unit.Category unitCategory;       
	int unitID;
	java.util.UUID unitManagerUuid;
	java.util.UUID unitProperties;
	byte[] unitProperty;
	java.util.UUID unitPropertyID;
	java.lang.String vendorInformation;
	boolean isAuthenticatedHardware;
        
    public UnitClass_Implemented(){
    }
    

	public UnitClass_Implemented(UUID _BSPUuid,
			java.lang.String _firmwareVersion,
			java.lang.String _hardwareSerialNumber,
			java.lang.String _hardwareVersion,
			long _maxBSPDbSize,
			long _maxIdentify,
			java.lang.String _softwareVersion,
			Event.Kind[] _supportedEvents,
			Unit.Category _unitCategory,       
			int _unitID,
			java.util.UUID _unitManagerUuid,
			java.util.UUID _unitProperties,
			byte[] _unitProperty,
			java.util.UUID _unitPropertyID,
			java.lang.String _vendorInformation,
			boolean _isAuthenticatedHardware
	) 
	{
		BSPUuid = _BSPUuid;
		firmwareVersion = _firmwareVersion;
		hardwareSerialNumber = _hardwareSerialNumber;
		hardwareVersion = _hardwareVersion;
		maxBSPDbSize = _maxBSPDbSize;
		maxIdentify = _maxIdentify;
		softwareVersion = _softwareVersion;
		supportedEvents = _supportedEvents;
		unitCategory = _unitCategory;       
		unitID = _unitID;
		unitManagerUuid = _unitManagerUuid;
		unitProperties = _unitProperties;
		unitProperty = _unitProperty;
		unitPropertyID = _unitPropertyID;
		vendorInformation = _vendorInformation;
		isAuthenticatedHardware = _isAuthenticatedHardware;
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
	public byte[] control(int opcode, byte argument)
	{
		//this is where something really cool will happen where
		//some device will be sent a cool, ultra-neat command
		//and blink some lights.
		return new byte[10];
	}

	/**
	 * 
	 * 
	 * @return	the indicator status of the BioAPI Unit if that BioAPI Unit supports it.
	 */
	public Unit.IndicatorStatus getIndicatorStatus()
	{
		//todo: request status from unit?
		return status;
	}


	/**
	 * Sets the selected BioAPI Unit to the requested indicator status if the BioAPI Unit supports it. After 
	 * INDICATOR_ACCEPT or INDICATOR_REJECT is set in the 'IndicatorStatus' parameter, the status will 
	 * not be changed until the application sets another value.
	 * 
	 * @param	indicatorStatus	the setting for the indicator status of the BioAPI Unit.
	 */
	public void setIndicatorStatus(Unit.IndicatorStatus indicatorStatus)
	{
		//todo: actually get the status from the unit?
		status = indicatorStatus;
	}

	/**
	 * Sets the currently attached BioAPI Unit of the BSP attach session to the requested power mode if 
	 * the BioAPI Unit supports it.
	 * 
	 * @param	powerMode	the power mode to set the BioAPI Unit to.
	 */
	public void setPowerMode(Unit.PowerMode powerMode)
	{
		//todo: actually set powermode?
		mode = powerMode;
	}

	public java.util.UUID getBSPUuid()
	{
		return BSPUuid;
	}
	public java.lang.String getFirmwareVersion()
	{
		return firmwareVersion;
	}
	public java.lang.String getHardwareSerialNumber()
	{
		return hardwareSerialNumber;
	}
	public java.lang.String getHardwareVersion()
	{
		return hardwareVersion;
	}
	public long getMaxBSPDbSize()
	{
		return maxBSPDbSize;
	}
	public long getMaxIdentify()
	{
		return maxIdentify;
	}
	public java.lang.String getSoftwareVersion()
	{
		return softwareVersion;
	}
	public Event.Kind[] getSupportedEvents()
	{
		return supportedEvents;
	}
	public Unit.Category getUnitCategory()
	{
		return unitCategory;
	}
	public int getUnitId()
	{
		return unitID;
	}
	public java.util.UUID getUnitManagerUuid()
	{
		return unitManagerUuid;
	}
	public java.util.UUID getUnitProperties()
	{
		return unitProperties;
	}
	public byte[] getUnitProperty()
	{
		return unitProperty;
	}
	public java.util.UUID getUnitPropertyID()
	{
		return unitPropertyID;
	}
	public java.lang.String getVendorInformation()
	{
		return vendorInformation;
	}
	public boolean isAuthenticatedHardware()
	{
		return isAuthenticatedHardware;
	}
}
