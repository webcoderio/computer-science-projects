/*
 * FMR.java
 *
 * Created on April 5, 2007, 4:24 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.bioapi.template;
import org.bioapi.data.*;
/**
 *
 * @author Ashwin Mohan
 */
public class FMR implements org.bioapi.data.FMR
{
    private int falseMatchRate;

    public FMR() {
        
    }

    public FMR(int i) {
        
    }
    public int getFalseMatchRate()
    { 
        return falseMatchRate;
    }
    protected void setFalseMatchRate(int _falseMatchRate)
    {
        falseMatchRate = _falseMatchRate;
    }
   
}
