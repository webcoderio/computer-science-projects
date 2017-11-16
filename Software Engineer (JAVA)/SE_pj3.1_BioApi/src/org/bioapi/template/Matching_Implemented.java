/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bioapi.template;

import org.bioapi.BioAPIException;
import org.bioapi.Matching;
import org.bioapi.Matching.VerifyResult.Options;
import org.bioapi.data.BIR;
import org.bioapi.data.FMR;
import org.bioapi.data.IdentifyPopulation;

/**
 *
 * @author Ashwin Mohan
 */
public class Matching_Implemented implements Matching{

    public Matching_Implemented() {
    }

    public IdentifyResult identify(FMR maxFMRrequested, BIR processedBIR, int totalNumberOfTemplates, IdentifyPopulation population, boolean binning, int maxResults, int timeout) throws BioAPIException {
        return null;
    }

    public void presetIdentifyPopulation(IdentifyPopulation population) throws BioAPIException {

    }

    public VerifyResult verify(FMR maxFMRrequested, BIR processedBIR, BIR referenceTemplate, Options options) throws BioAPIException {
        return null;
    }

}
