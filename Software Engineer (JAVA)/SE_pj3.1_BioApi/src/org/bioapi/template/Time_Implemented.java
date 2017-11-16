/*
 * Time_Implemented.java
 *
 * Created on April 5, 2007, 3:41 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.bioapi.template;

/**
 *
 * @author Ashwin Mohan
 */
public class Time_Implemented {
    
    private int hour;
    private int minute;
    private int second;
    
    /** Creates a new instance of Time_Implemented */

    public int getHour() {
        return hour;
    }

    protected void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    protected void setMinute(int minute) {
        this.minute = minute;
    }

    public int getSecond() {
        return second;
    }

    protected void setSecond(int second) {
        this.second = second;
    }
    
}
