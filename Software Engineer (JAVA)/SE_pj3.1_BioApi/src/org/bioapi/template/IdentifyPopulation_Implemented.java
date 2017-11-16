/*
 * IdentifyPopulation_Implemented.java
 *
 * Created on April 26, 2007, 8:29 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.bioapi.template;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import org.bioapi.*;
import org.bioapi.data.*;

/**
 *
 * @author Ashwin Mohan
 */
public class IdentifyPopulation_Implemented<T extends BIR> implements IdentifyPopulation, org.bioapi.BIRDatabase.Marker {
    
    /** Creates a new instance of IdentifyPopulation_Implemented */
    boolean bound;
    Collection members;

    public IdentifyPopulation_Implemented()
    {
         members = new ArrayList<T>();
    }

    public IdentifyPopulation_Implemented(Collection<T> idp) throws BioAPIException
    {
        int i =0;
        members = new ArrayList<T>();
        while(i < idp.toArray().length)
        {
          members.add(idp.toArray()[i]);
            i++;
        }
    }
    
    public boolean isBound() throws BioAPIException
    {
        bound= !members.isEmpty();
        return bound;
    }
    
    public void unbind() throws BioAPIException
    {
        bound = false;
    }
    
    public void destroy() throws BioAPIException
    {
         members = new ArrayList<T>();
    }
    
    public java.util.Enumeration<T> getMembers() throws BioAPIException
    {
        return  Collections.enumeration( members);
    }
    
    public void terminate() throws BioAPIException
    {

    }
    public int getLength(){
    return members.size();
    }
    
    public void remove()
    {
        members.iterator().remove();
    }
    public org.bioapi.BIRDatabase.Record next()
    {
       return (org.bioapi.BIRDatabase.Record)(members.iterator().next());
    }
    public boolean hasNext()
    {
        return members.iterator().hasNext();
    }

    
    
    
}
