/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bioapi.template;

import java.util.Collection;
import java.util.UUID;
import org.bioapi.BioAPIException;
import org.bioapi.Unit.Category;
import org.bioapi.data.BFPSchema;
import org.bioapi.data.BIR;
import org.bioapi.data.BIR.BiometricType;
import org.bioapi.data.BIR.BiometricType.Type;
import org.bioapi.data.BIR.DTG;
import org.bioapi.data.BIR.Format;
import org.bioapi.data.BIR.ProcessedLevel;
import org.bioapi.data.BIR.ProductID;
import org.bioapi.data.BIR.Purpose;
import org.bioapi.data.BIR.Quality;
import org.bioapi.data.BIR.SecurityBlockFormat;
import org.bioapi.data.BIR.Subtype;
import org.bioapi.data.BIR.Subtype.Instance;
import org.bioapi.data.BSPSchema;
import org.bioapi.data.BSPSchema.Operations;
import org.bioapi.data.BSPSchema.Operations.Operation;
import org.bioapi.data.BSPSchema.Options;
import org.bioapi.data.BSPSchema.Options.Option;
import org.bioapi.data.DataFactory;
import org.bioapi.data.Date;
import org.bioapi.data.FMR;
import org.bioapi.data.IdentifyPopulation;
import org.bioapi.data.Payload;
import org.bioapi.data.Time;

/**
 *
 * @author Ashwin Mohan
 */
public class DataFactory_Implemented implements DataFactory{

    public BFPSchema newBFPProperties(UUID BFPUuid, Category BFPCategory, String BFPDescription, String path, String specVersion, String productVersion, String vendor, Format[] BFPSupportedFormats, BiometricType factorsMask, UUID BFPPropertyID, byte BFPProperty) throws BioAPIException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public BIR newBIR(String headerVersion, ProcessedLevel processedLevel, boolean isBDBEncrypted, boolean isBIRSigned, Format format, Quality quality, Purpose purpose, BiometricType factorsMask, ProductID productID, DTG creationDTG, Subtype subtype, Date expirationDate, SecurityBlockFormat SBFormat, UUID index, byte biometricData, byte securityBlock) throws BioAPIException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public BSPSchema newBSPProperties(UUID BSPUuid, String BSPDescription, String path, String specVersion, String productVersion, String vendor, Format[] BSPSupportedFormats, BiometricType factorsMask, Operations supportedOperations, Options supportedOptions, FMR payloadPolicy, long maxPayloadSize, int defaultVerifyTimeout, int defaultIdentifyTimeout, int defaultCaptureTimeout, int defaultEnrollTimeout, int defaultCalibrateTimeout, long maxBSPDbSize, long maxIdentify) throws BioAPIException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Date newDate(int year, int month, int mday) throws BioAPIException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public FMR newFMR(int falseMatchRate) throws BioAPIException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public IdentifyPopulation newIdentifyPopulation(Collection<BIR> members) throws BioAPIException {
        return null;
    }

    public Payload newPayload(byte data) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Payload newPayload(String data) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Payload newPayload(UUID data) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Time newTime(int hour, int minute, int second) throws BioAPIException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public BiometricType newBIR_BiometricType(Type... types) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public DTG newBIR_DTG(Date date, Time time) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Quality newBIR_Quality(int quality) throws BioAPIException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Format newBIR_Format(int owner, int type) throws BioAPIException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public SecurityBlockFormat newBIR_SecurityBlockFormat(int owner, int type) throws BioAPIException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ProductID newBIR_ProductID(int owner, int type) throws BioAPIException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Subtype newBIR_Subtype(Instance... subject) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Operations newBSPProperties_Operations(Operation... operations) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Options newBSPProperties_Options(Option... options) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
