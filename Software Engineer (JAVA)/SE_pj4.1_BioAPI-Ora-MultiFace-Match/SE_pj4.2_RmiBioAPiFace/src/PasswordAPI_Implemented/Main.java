/*
 * Main.java
 *
 * Created on April 3, 2007, 9:05 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package PasswordAPI_Implemented;

import org.bioapi.Unit;
import org.bioapi.data.Event;
import org.bioapi.template.UnitClass_Implemented;

 /**
 * @author rosshinkley
 */
public class Main
{
	PasswordBSP bsp;
	UnitClass_Implemented SensorUnitSchema;
	UnitClass_Implemented ProcessUnitSchema;
	UnitClass_Implemented MatchingUnitSchema;

	//Arguments
	org.bioapi.data.BIR.BiometricType.Type[] FactorsMask =
    {org.bioapi.data.BIR.BiometricType.Type.PASSWORD};
	short owner = 555;
	short type = 777;


	java.util.UUID bspUUID = new java.util.UUID(0x94,0xd9);
	org.bioapi.data.BSPSchema.Operations.Operation[] operations =
    {org.bioapi.data.BSPSchema.Operations.Operation.CAPTURE,
	org.bioapi.data.BSPSchema.Operations.Operation.IDENTIFY,
    org.bioapi.data.BSPSchema.Operations.Operation.IDENTIFYMATCH,
	org.bioapi.data.BSPSchema.Operations.Operation.VERIFYMATCH,
    org.bioapi.data.BSPSchema.Operations.Operation.ENROLL,
    org.bioapi.data.BSPSchema.Operations.Operation.VERIFY,
    org.bioapi.data.BSPSchema.Operations.Operation.UTILITIES};
	org.bioapi.data.BSPSchema.Options.Option[] options = {
        org.bioapi.data.BSPSchema.Options.Option.RAW};
	org.bioapi.template.FMR fmr = new org.bioapi.template.FMR();
	Event.Kind[] eventKinds = null;
	java.util.UUID procID = new java.util.UUID(0x0,0x0);
    org.bioapi.template.BIR_Implemented.Format[] bspSupFormat = {
            new org.bioapi.template.BIR_Implemented.Format(
                new org.bioapi.template.BIR_Implemented.OwnerTypePair(
                owner, type),bspUUID) };

	/** Creates a new instance of Main */
	public Main()
	{
		createBSP();
		createSensorUnitSchema();
		createMatchingUnitSchema();
	}

	public void createMatchingUnitSchema()
	{
		MatchingUnitSchema =  new UnitClass_Implemented(bspUUID,
				"",
				"",
				"",
				0,
				0,
				"2.0.1",
				eventKinds,
				Unit.Category.MATCHING,
				3,
				bspUUID,
				procID,
				null,
				procID,
				"Password Matching Unit",
				false);
	}
	
	public void createSensorUnitSchema()
	{
		//defines UnitClass_Implemented SensorUnitSchema
		SensorUnitSchema = new UnitClass_Implemented(bspUUID,
				"",
				"",
				"",
				0,
				0,
				"2.0.1",
				eventKinds,
				Unit.Category.SENSOR,
				1,
				bspUUID,
				procID,
				null,
				procID,
				"Password Sensor Unit",
				false);

	}

	public void createBSP()
	{
		//defines password BSP Schema
		bsp = new PasswordBSP("Sample Password BSP",
				bspSupFormat,
				bspUUID,
				0x7FFFFFFF,
				0x7FFFFFFF,
				0x7FFFFFFF,
				0x7FFFFFFF,
				0x7FFFFFFF,
				FactorsMask,
				0,
				0,
				0,
				operations,
				options,
				null,
				fmr,
				"2.0.1",
				"2.0",
				"BioAPI 2.0 Reference Implementation");
	}

    public PasswordBSP getBSP() {
        return bsp;
    }
	
}

