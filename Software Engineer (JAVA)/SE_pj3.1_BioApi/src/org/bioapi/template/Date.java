/*
 * Date_implemented.java
 *
 * Created on April 5, 2007, 3:36 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.bioapi.template;

/**
 *
 * @author Ashwin Mohan
 */
public class Date implements org.bioapi.data.Date {
    
    private int mday;
    private int month;
    private int year;

    public Date(int i, int i0, int i1) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
    
    public int getMday() {
        return mday;
    }

    protected void setMday(int mday) {
        this.mday = mday;
    }

    public int getMonth() {
        return month;
    }

    protected void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    protected void setYear(int year) {
        this.year = year;
    }
    
    
    
}
