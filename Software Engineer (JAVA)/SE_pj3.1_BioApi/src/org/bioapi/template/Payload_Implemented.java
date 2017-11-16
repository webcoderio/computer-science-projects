/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bioapi.template;

/**
 *
 * @author Ashwin Mohan
 */
public class Payload_Implemented<T> implements org.bioapi.data.Payload{

    T data;
    protected void setData(T _data)
    {
    data= _data;
    }


    public T getData()
    {
        return data;
    }

}
