/*
 * BIR.java
 *
 * Created on April 5, 2007, 12:27 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.bioapi.template;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.UUID;
import org.bioapi.BioAPIException;
import org.bioapi.data.BIR;
import org.bioapi.data.BIR.Subtype.Instance;
import org.bioapi.data.Date;

/**
 *
 * @author Ashwin Mohan
 */
public class BIR_Implemented implements BIR{
    
    
    byte[] biometricData;
    byte[] securityBlock;
    boolean isBound;
    String headerVersion;
    BIR.ProcessedLevel processedLevel;
    UUID index;
    BIR.Purpose purpose;
    Date expirationDate;
    String name, password;
    /** Creates a new instance of BIR */

    public BIR_Implemented(){

    }



    public BIR_Implemented(
        byte[] _biometricData,
        byte[] _securityBlock,
        boolean _isBound,
        String _headerVersion,
        BIR.ProcessedLevel _processedLevel,
        UUID _index,
        BIR.Purpose _purpose,
        Date _expirationDate)
        {
            biometricData = _biometricData;
            securityBlock= _securityBlock;
            isBound = _isBound;
            headerVersion = _headerVersion;
            processedLevel = _processedLevel;
            index = _index;
            purpose = _purpose;
            expirationDate = _expirationDate;
        }
    
    
    
    public static class BiometricType implements org.bioapi.data.BIR.BiometricType {
        private ArrayList<org.bioapi.data.BIR.BiometricType.Type> contains;
        
        BiometricType() {
            
        }
        
        
        
        public BiometricType(org.bioapi.data.BIR.BiometricType.Type[] _contains) {
            contains = new ArrayList<org.bioapi.data.BIR.BiometricType.Type>();
            for(int i=0; i<_contains.length; i++) {
                contains.add(_contains[i]);
            }
        }
        
        
        public boolean containsMultipleFactors(){return contains.contains(org.bioapi.data.BIR.BiometricType.Type.MULTIPLE);}
        public boolean containsFacialFeatures(){return contains.contains(org.bioapi.data.BIR.BiometricType.Type.FACIAL_FEATURES);}
        public boolean containsVoice(){return contains.contains(org.bioapi.data.BIR.BiometricType.Type.VOICE);}
        public boolean containsFingerprint(){return contains.contains(org.bioapi.data.BIR.BiometricType.Type.FINGERPRINT);}
        public boolean containsIris(){return contains.contains(org.bioapi.data.BIR.BiometricType.Type.IRIS);}
        public boolean containsRetina(){return contains.contains(org.bioapi.data.BIR.BiometricType.Type.RETINA);}
        public boolean containsHandGeometry(){return contains.contains(org.bioapi.data.BIR.BiometricType.Type.HAND_GEOMETRY);}
        public boolean containsKeystrokeDynamics(){return contains.contains(org.bioapi.data.BIR.BiometricType.Type.KEYSTROKE_DYNAMICS);}
        public boolean containsLipMovement(){return contains.contains(org.bioapi.data.BIR.BiometricType.Type.LIP_MOVEMENT);}
        public boolean containsThermalFaceImage(){return contains.contains(org.bioapi.data.BIR.BiometricType.Type.THERMAL_FACE_IMAGE);}
        public boolean containsThermalHandImage(){return contains.contains(org.bioapi.data.BIR.BiometricType.Type.THERMAL_HAND_IMAGE);}
        public boolean containsGait(){return contains.contains(org.bioapi.data.BIR.BiometricType.Type.GAIT);}
        public boolean containsOther(){return contains.contains(org.bioapi.data.BIR.BiometricType.Type.OTHER);}
        public boolean containsPassword(){return contains.contains(org.bioapi.data.BIR.BiometricType.Type.PASSWORD);}
        public boolean contains(Type type){return contains.contains(type.valueOf(type.name()));}
        
        public org.bioapi.data.BIR.BiometricType.Type[] getContains() {
            org.bioapi.data.BIR.BiometricType.Type[] ret = new org.bioapi.data.BIR.BiometricType.Type[contains.size()];
            for(int i=0; i<contains.size(); i++) {
                ret[i] = contains.get(i);
            }
            return ret;
        }
        
    }
    
    /*
    static class DTG implements org.bioapi.data.BIR.DTG
    {
        public Date getDate()
        {
            throw new exception("getdate not implemented");
        }
        public Time getTime()
        {
            throw new exception("gettime not implemented");
        }
    }
     **/
    
    public static class Format extends OwnerTypePair implements org.bioapi.data.BIR.Format {
        
        static Hashtable formats=null;
        public Format(Format f) {
        }
        
        public Format(org.bioapi.data.BIR.Format f) {
            
        }
        public Format(OwnerTypePair o, UUID index) 
        {
            super(o.getOwner(), o.getType());
            if(formats==null)
            {
                formats = new Hashtable();
            }
            formats.put(index, o);
            
            
        }
        
        public static Format getFormat(UUID toGet)
        {
            if(formats.containsKey(toGet))
            {
                //this is not right.
                return (Format)formats.get(toGet);
            }
            return null;
        }
    }
    
    public static class OwnerTypePair implements org.bioapi.data.BIR.OwnerTypePair {
        private short owner;
        private short type;
        
        public OwnerTypePair() {
            
        }
        
        public OwnerTypePair(short _owner, short _type) {
            setOwner(_owner);
            setType(_type);
        }
        
        public short getOwner() {
            return owner;
        }
        
        public void setOwner(short owner) {
            this.owner = owner;
        }
        
        public short getType() {
            return type;
        }
        
        public void setType(short type) {
            this.type = type;
        }
        
        
        
    }
    
    public static class ProductID extends OwnerTypePair implements org.bioapi.data.BIR.ProductID {
        
    }
    
    public static class SecurityBlockFormat extends OwnerTypePair implements org.bioapi.data.BIR.SecurityBlockFormat {
        //there should probably be a security block format hashtable
        Hashtable formats = null;
        SecurityBlockFormat(OwnerTypePair o, UUID index)
        {
            if(formats == null)
            {
                formats = new Hashtable();
            }
            formats.put(index,o);            
        }
        
        SecurityBlockFormat getSecurityBlockFormat(UUID index)
        {
            if(formats.contains(index))
            {
                return (SecurityBlockFormat)formats.get(index);
            }
            return null;
        }
        
        
    }
    
    public static class Quality implements org.bioapi.data.BIR.Quality {
        //private int quality;
        static Hashtable qualities=null;
        Quality(){}
        Quality(int _quality, UUID index)
        {
            if(qualities == null)
            {
                qualities = new Hashtable();
            }
            qualities.put(index,_quality);
        }
        
        public static int getQuality(UUID index) {
            if(qualities.contains(index))
            {
                return (Integer)qualities.get(index);
            }
            return -1;
        }
        
        public int getQuality()
        {
            return -1;
        }
    }
    
    static class Subtype implements org.bioapi.data.BIR.Subtype {
        ArrayList<org.bioapi.data.BIR.Subtype.Instance> has;
        Subtype(){}
        
        
        public Subtype(Instance[] instances) {
            
            for(int i=0; i<instances.length; i++) {
                has.add(instances[i]);
            }
        }
        
        
        public Instance[] getInstances() {
            Instance[] ret = new Instance[has.size()];
            for(int i=0; i<has.size(); i++) {
                ret[i] = has.get(i);
            }
            return ret;
        }
        public boolean hasLeftThumb(){return has.contains(org.bioapi.data.BIR.Subtype.Instance.LEFT_THUMB);}
        public boolean hasLeftPointerFinger(){return has.contains(org.bioapi.data.BIR.Subtype.Instance.LEFT_POINTERFINGER);}
        public boolean hasLeftMiddleFinger(){return has.contains(org.bioapi.data.BIR.Subtype.Instance.LEFT_MIDDLEFINGER);}
        public boolean hasLeftRingFinger(){return has.contains(org.bioapi.data.BIR.Subtype.Instance.LEFT_RINGFINGER);}
        public boolean hasLeftLittleFinger(){return has.contains(org.bioapi.data.BIR.Subtype.Instance.LEFT_LITTLEFINGER);}
        public boolean hasRightThumb(){return has.contains(org.bioapi.data.BIR.Subtype.Instance.RIGHT_THUMB);}
        public boolean hasRightPointerFinger(){return has.contains(org.bioapi.data.BIR.Subtype.Instance.RIGHT_POINTERFINGER);}
        public boolean hasRightMiddleFinger(){return has.contains(org.bioapi.data.BIR.Subtype.Instance.RIGHT_MIDDLEFINGER);}
        public boolean hasRightRingFinger(){return has.contains(org.bioapi.data.BIR.Subtype.Instance.RIGHT_RINGFINGER);}
        public boolean hasRightLittleFinger(){return has.contains(org.bioapi.data.BIR.Subtype.Instance.RIGHT_LITTLEFINGER);}
        public boolean contains(Instance instance){return has.contains(instance);}
    }
    
    public boolean isBound() throws BioAPIException
    {
        return isBound;
    }
    
    public void unbind() throws BioAPIException
    {
        isBound = false;
        //TODO: remove from bsp?
    }
    
    public void destroy() throws BioAPIException
    {
        biometricData= new byte[]{'e','m','p','t','y'};
        securityBlock= new byte[]{'e','m','p','t','y'};
        unbind();
    }
    
    public String getHeaderVersion() throws BioAPIException
    {
        return headerVersion;
    }
    
    public BIR.ProcessedLevel getProcessedLevel() throws BioAPIException
    {
        return processedLevel;
    }
    public BIR.Format getFormat() throws BioAPIException
    {
        return Format.getFormat(index);
    }
    public BIR.Quality getQuality() throws BioAPIException
    {
        //this is bad, horrible, and can't stay this way'
        return new Quality(Quality.getQuality(index), index);
    }
    
    public BIR.Purpose getPurpose() throws BioAPIException
    {
        return purpose;
    }
    public BIR.BiometricType getFactorsMask() throws BioAPIException
    {
        return null;
    }
    
    public BIR.ProductID getProductID() throws BioAPIException
    {
        return null;
    }
    public BIR.DTG getCreationDTG() throws BioAPIException
    {
        return null;
    }
    public BIR.Subtype getSubtype() throws BioAPIException
    {
        return null;
    }
    public Date getExpirationDate() throws BioAPIException
    {
        return expirationDate;
    }
    public boolean isBDBEncrypted() throws BioAPIException
    {
        return false;
    }
    public boolean isBIRSigned() throws BioAPIException
    {
        return false;
    }
    public BIR.SecurityBlockFormat getSBFormat() throws BioAPIException
    {
        return null;
    }
    public boolean isBIRIndexed() throws BioAPIException
    {
        if(index != null)
        {
            return false;
        }
        return true;
    }
    public UUID getIndex() throws BioAPIException
    {
        return index;
    }
    public byte[] getBiometricData() throws BioAPIException
    {
        return biometricData;
    }


    public byte[] getSecurityBlock() throws BioAPIException
    {
        return securityBlock;
    }

    
}
