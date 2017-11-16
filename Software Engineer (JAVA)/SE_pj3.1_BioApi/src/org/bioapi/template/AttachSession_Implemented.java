/*
 * AttachSession_Implemented.java
 *
 * Created on April 17, 2007, 8:37 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.bioapi.template;

import java.util.UUID;

import org.bioapi.Archive;
import org.bioapi.AttachSession;
import org.bioapi.BioAPIException;
import org.bioapi.GUIImageObserver;
import org.bioapi.GUIStateObserver;
import org.bioapi.Matching;
import org.bioapi.Processing;
import org.bioapi.Sensor;
import org.bioapi.Unit;
import org.bioapi.AttachSession.IdentifyResult.Options;
import org.bioapi.Processing.CreateTemplateResult;
import org.bioapi.data.*;
import org.bioapi.data.BIR.Subtype;

/**
 *
 * @author Ashwin Mohan
 */
public class AttachSession_Implemented implements AttachSession
{

public class EnrollResult implements
			CreateTemplateResult,AttachSession.EnrollResult
	{
        public EnrollResult(){
            
        }

		public BIR getAuditData(){

			return null;
		}

		public boolean hasAuditData(){

			return false;
		}

		public BIR getTemplate(){

			return null;
		}

		public UUID getTemplateUUID(){

			return null;
		}

		public boolean hasTemplateUUID(){

			return false;
		}
	}

public class IdentifyResult implements
			Matching.IdentifyResult, org.bioapi.AttachSession.IdentifyResult
	{
       
		public BIR getAuditData(){

			return null;
		}

		public boolean hasAuditData(){

			return false;
		}

		public Candidates getCandidates(){

			return null;
		}

	}

	public void cancel() throws BioAPIException{

	}

	
	public void enableEvents(Event.Kind[] eventKinds) throws BioAPIException {
		
	}

	
	public EnrollResult enroll(BIR.Purpose purpose, BIR.Subtype subtype,
			BIR.Format outputFormat, BIR referenceTemplate,
			Payload payload, int timeout,
			AttachSession.EnrollResult.Options options)
			throws BioAPIException{
        
		return null;
	}

	
	public Archive getArchive() throws BioAPIException{
        
		return null;
	}

	public Matching getMatching() throws BioAPIException{
        
		return null;
	}

	public Processing getProcessing() throws BioAPIException{
        
		return null;
	}

	
	public Sensor getSensor() throws BioAPIException{
        
		return null;
	}

	public Unit getUnit(int unitID) throws BioAPIException{
        
		return null;
	}

	public AttachSession.IdentifyResult identify(FMR[] maxFMRrequested,
			BIR.Subtype[] subtype, int totalNumberOfTemplates,
			IdentifyPopulation[] population, boolean binning, int maxResults,
			int timeout, AttachSession.IdentifyResult.Options[] options)
			throws BioAPIException{
        
		return null;
	}

	public BIR importBIR(byte data, BIR.Format inputFormat,
			BIR.Format outputFormat, BIR.Purpose purpose)
			throws BioAPIException{
        
		return null;
	}

	public void setGUIObservers(GUIImageObserver imageChangedObserver,
			GUIStateObserver stateChangedObserver) throws BioAPIException{

	}

	public void terminate() throws BioAPIException{

	}

	public AttachSession.VerifyResult verify(FMR[] maxFMRrequested,
			BIR[] referenceTemplate, BIR.Subtype[] subtype, int timeout,
			AttachSession.VerifyResult.Options[] options)
			throws BioAPIException{

		return null;
	}

	public AttachSession.IdentifyResult identify(org.bioapi.data.FMR maxFMRrequested, Subtype subtype, int totalNumberOfTemplates, IdentifyPopulation population, boolean binning, int maxResults, int timeout, Options options) throws BioAPIException{

        return null;
	}

	public AttachSession.VerifyResult verify(org.bioapi.data.FMR maxFMRrequested, BIR referenceTemplate, Subtype subtype, int timeout, org.bioapi.AttachSession.VerifyResult.Options options) throws BioAPIException{
        
        return null;
	}
}
