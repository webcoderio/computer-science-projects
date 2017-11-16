package org.bioapi.data;

import org.bioapi.*;
import java.util.*;

/**
 * Represents the record in the component registry that defines properties of the BFP installed in the 
 * system. For security reasons, no methods to modify the existing BFPProperties object are provided.
 * Instead, an application should use the corresponding method of the DataFactory to create new 
 * BFPProperties containing modified data.
 * 
 * @author	Ashwin Mohan
 */
public interface BFPSchema
{
	public Unit.Category getBFPCategory();

	public String getBFPDescription();

	public byte[] getBFPProperty();

	public UUID getBFPPropertyID();

	public BIR.Format[] getBFPSupportedFormats();

	public UUID getBFPUuid();

	public BIR.BiometricType getFactorsMask();

	public String getPath();

	public String getProductVersion();

	public String getSpecVersion();

	public String getVendor();
}