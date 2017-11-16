/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package PasswordAPI_Implemented;

import org.bioapi.Matching.VerifyResult.Options;
import org.bioapi.data.BIR;
import org.bioapi.data.FMR;
import org.bioapi.template.Matching_Implemented;
import org.bioapi.template.VerifyResult_Implemented;

/**
 *
 * @author Administrator
 */
public class PasswordMatchingUnit extends Matching_Implemented{


    @Override
   public org.bioapi.AttachSession.VerifyResult verify(FMR maxFMRrequested, BIR processedBIR,
            BIR referenceTemplate, Options options) {

      if(((PasswordBIR)(processedBIR)).username.equalsIgnoreCase(((PasswordBIR)(referenceTemplate)).username)
              && ((PasswordBIR)(processedBIR)).password.equalsIgnoreCase(((PasswordBIR)(referenceTemplate)).password)){
      return  new VerifyResult_Implemented(1);

      }

       return new VerifyResult_Implemented(0);
      
    }

}
