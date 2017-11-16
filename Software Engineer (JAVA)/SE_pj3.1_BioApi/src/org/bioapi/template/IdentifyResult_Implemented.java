/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bioapi.template;

import org.bioapi.Matching;
import org.bioapi.data.BIR;
import org.bioapi.data.Candidates;

/**
 *
 * @author Ashwin Mohan
 */
public class IdentifyResult_Implemented implements Matching.IdentifyResult, org.bioapi.AttachSession.IdentifyResult{

    

    public Candidates getCandidates() {
        return null;
    }

    public BIR getAuditData() {
        return null;
    }

    public boolean hasAuditData() {
        return false;
    }

}
