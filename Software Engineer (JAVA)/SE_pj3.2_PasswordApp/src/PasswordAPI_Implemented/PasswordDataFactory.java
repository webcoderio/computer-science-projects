/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package PasswordAPI_Implemented;

import java.util.Collection;
import org.bioapi.BioAPIException;
import org.bioapi.data.FMR;
import org.bioapi.data.IdentifyPopulation;
import org.bioapi.template.DataFactory_Implemented;

/**
 *
 * @author UPEK
 */
class PasswordDataFactory extends DataFactory_Implemented implements FMR{

    int fmr;


    public int getFalseMatchRate() {
        return fmr;
    }

    @Override
    public FMR newFMR(int fmr){
    this.fmr= fmr;
    return this;
    }


}
