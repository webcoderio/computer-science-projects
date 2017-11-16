package PasswordAPI_Implemented;


import java.util.UUID;
import org.bioapi.AttachSession;
import org.bioapi.data.FMR;
import org.bioapi.data.BIR.BiometricType.Type;
import org.bioapi.data.BSPSchema.Operations.Operation;
import org.bioapi.data.BSPSchema.Options.Option;
import org.bioapi.template.BSP_Schema;
import org.bioapi.template.BIR_Implemented.Format;

public class PasswordBSP extends BSP_Schema
{

	public PasswordBSP(String _BSPDescription, Format[] _BSPSupportedFormats, 
            UUID _BSPUuid, int _DefaultCalibrateTimeout, int _DefaultCaptureTimeout,
            int _DefaultEnrollTimeout, int _DefaultIdentifyTimeout,
            int _DefaultVerifyTimeout, Type[] _FactorsMask, long _MaxBSPDbSize,
            long _MaxIdentify, long _MaxPayloadSize, Operation[] _Operations,
            Option[] _Options, String _Path, FMR _PayloadPolicy, String _ProductVersion,
            String _SpecVersion, String _Vendor)
	{
		super(_BSPDescription, _BSPSupportedFormats, _BSPUuid,
				_DefaultCalibrateTimeout, _DefaultCaptureTimeout,
				_DefaultEnrollTimeout, _DefaultIdentifyTimeout, _DefaultVerifyTimeout,
				_FactorsMask, _MaxBSPDbSize, _MaxIdentify, _MaxPayloadSize,
				_Operations, _Options, _Path, _PayloadPolicy, _ProductVersion,
				_SpecVersion, _Vendor);
	}
	
    @Override
	public AttachSession newAttachSession()
	{
		return new PasswordAttachSession();
	}
}
