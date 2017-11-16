package com.neurotechnology.Library;

import java.io.File;
import javax.swing.filechooser.*;

public class TemplateFileFilter extends FileFilter {
    public boolean accept(File f) 
    {
    	//allow other directories  besides the current one
        if (f.isDirectory()) 
        {
            return true;
        }

        String extension = getFileExtension(f);
        if (extension != null) 
        {
            if (extension.equals("data"))
	        {
	            return true;
	        } 
	        else 
	        {
	            return false;
	        }
	    }

	    return false;
	}

	public String getDescription() 
	{
	    return "Template files: *.data";
	}
	    
	/**
	 * Get the extension of a file.
	 */  
	 public static String getFileExtension(File f) 
	 {
	     String ext = null;
	     String s = f.getName();
	     int i = s.lastIndexOf('.');
	        
	     if (i > 0 &&  i < s.length() - 1) 
	     {
	        ext = s.substring(i + 1).toLowerCase();
	     }
	     return ext;
	 }
}
