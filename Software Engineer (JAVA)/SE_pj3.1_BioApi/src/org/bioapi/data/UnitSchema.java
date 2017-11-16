package org.bioapi.data;

import org.bioapi.Unit;

/**
 * Defines properties of the biometric unit. For security reasons no methods to modify the existing UnitSchema object are provided.
 * @author Ashwin Mohan
 */
public interface UnitSchema
{
	public java.util.UUID getBSPUuid();
	public java.lang.String getFirmwareVersion();
	public java.lang.String getHardwareSerialNumber();
	public java.lang.String getHardwareVersion();
	public long getMaxBSPDbSize();
	public long getMaxIdentify();
	public java.lang.String getSoftwareVersion();
	public Event.Kind[] getSupportedEvents();
	public Unit.Category getUnitCategory();
	public int getUnitId();
	public java.util.UUID getUnitManagerUuid();
	public java.util.UUID getUnitProperties();
	public byte[] getUnitProperty();
	public java.util.UUID getUnitPropertyID();
	public java.lang.String getVendorInformation();
	public boolean isAuthenticatedHardware();
}
