/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bioapi.template;

import java.util.UUID;
import org.bioapi.Unit.Category;
import org.bioapi.data.BFPSchema;
import org.bioapi.data.BIR.BiometricType;
import org.bioapi.data.BIR.Format;

/**
 *
 * @author Ashwin Mohan
 */
public class BFP_Implemented implements BFPSchema{

    public Category getBFPCategory() {
        return null;
    }

    public String getBFPDescription() {
        return null;
    }

    public byte[] getBFPProperty() {
        return null;
    }

    public UUID getBFPPropertyID() {
        return null;
    }

    public Format[] getBFPSupportedFormats() {
        return null;
    }

    public UUID getBFPUuid() {
        return null;
    }

    public BiometricType getFactorsMask() {
        return null;
    }

    public String getPath() {
       return null;
    }

    public String getProductVersion() {
        return null;
    }

    public String getSpecVersion() {
        return null;
    }

    public String getVendor() {
       return null;
    }

}
