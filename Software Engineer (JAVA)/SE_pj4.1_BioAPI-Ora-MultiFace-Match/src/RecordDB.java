

// import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// import com.neurotechnology.Library.CCIDL;
// import com.neurotechnology.Library.NativeManager;
import com.neurotechnology.NImages.NImage;
import com.neurotechnology.NImages.NImageFormat;
import com.neurotechnology.Templates.NBiometricType;
import com.neurotechnology.Templates.NFRecord;

import java.io.*;

public class RecordDB {

   private String oracle_driver = "oracle.jdbc.driver.OracleDriver";
   private String dbURL1 = "jdbc:oracle:thin:@5.154.255.136:1521:sulfur";
   private String userName1 = "sng";
   private String userPassword1 = "sng2";

   private Connection conn;
   private PreparedStatement pstmt;
   private ResultSet rs;
   
   private int maxID;
   private int maxImageID;
   private List<Record> recordsList;

   //Below is added by Bon Sy Feb 3, 2010
   String [] subjectCaption;
   int subjectCaptionSize;
   
   public RecordDB() throws Exception {
	   Class.forName(oracle_driver);

       try
       {
    	   recordsList = new ArrayList<Record>();	
    	   ResultSet rs = selectAll();
    	      	   
    	   while (rs.next()) {
    		   Record fr = new Record();
    		   fr.setId(rs.getInt("TemplateId"));
    		   fr.setName(rs.getString("TemplateCaption"));
    		   fr.setTempalte(rs.getBytes("Template"));    		   
    		   recordsList.add(fr);
    	   }
            
           rs.close();
           pstmt.close();
           try
           {
        	   conn.close();
           } 
           catch (SQLException e) {};
            
       }
       catch (SQLException e)
       {
    	   System.out.println(e.getMessage());
       }
       
       System.out.println("recordList size is: " + getRecords().size());
		for (int ii = 0; ii < getRecords().size(); ii++) {
			System.out.println("index " + ii + " has name: " + getRecords().get(ii).getName());
		}
		
   }

	public List<Record> getRecords(){
		return recordsList;
	}
	
	public List<NImage> getImagesById(int Id) throws Exception{	       
		ArrayList<NImage> images = new ArrayList<NImage>();
		NImageFormat pngFormat = null;
		for (NImageFormat format : NImageFormat.getFormats()) 
			if (format.getName().contains("PNG")){
				pngFormat = format;
				break;
			}

		try
		{
			conn = DriverManager.getConnection(dbURL1, userName1, userPassword1);
			String stmtQuery = "SELECT * FROM Images where TemplateId = " + Id;
			pstmt = conn.prepareStatement(stmtQuery);
			rs = pstmt.executeQuery();
	    
			if(rs.next()){
				byte[] png = rs.getBytes("Image");
				images.add(pngFormat.loadNImage(png));
			}

			rs.close();
			pstmt.close();
			
			try
			{
				conn.close();
			} 
			catch (SQLException e) {};   
		}
		catch (SQLException e)
		{
			System.out.println(e.getMessage());
		}
        
		return images;
	}
	
	public ResultSet selectAll() throws Exception {
		ResultSet rs = null;
 	   
		conn = DriverManager.getConnection(dbURL1, userName1, userPassword1);

			// BiometricType = 8 is for fingerprint, and BiometricType = 2 is for face
			//String stmtQuery = "SELECT * FROM Templates";
			String stmtQuery = "select templates.templateid, templates.templateCaption, templates.template,templates.thumbnail from images, templates where images.templateID = templates.templateID and images.BiometricType = 2";
				
            pstmt = conn.prepareStatement(stmtQuery);
            rs = pstmt.executeQuery();

            //cannot close conn or rs is returned empty. .... conn.close();
            
 		return rs;
	}

	public synchronized void append(String name, NImage image, NFRecord template) throws Exception 
	{
		try 
		{
			conn = DriverManager.getConnection(dbURL1, userName1, userPassword1);
			pstmt = conn.prepareStatement("Select decode(MAX(TemplateId), null, 0, MAX(TemplateId)) from Templates");
			rs = pstmt.executeQuery();
			if (rs.next()) {
				maxID = rs.getInt(1);
			}
		
			byte[] pngimage = null;
			for (NImageFormat format : NImageFormat.getFormats()) 
				if (format.getName().contains("PNG"))
					pngimage = format.saveToMemory(image);
			
			pstmt = conn.prepareStatement("INSERT INTO Templates VALUES (?,?,?,?)");
			pstmt.setInt(1, maxID + 1);
			if (name != null)
				pstmt.setString(2, name);
			if (template != null) 
				pstmt.setBytes(3, template.save());
			if (pngimage != null)
				pstmt.setBytes(4, null);
			
			pstmt.execute();

			pstmt = conn.prepareStatement("Select decode(MAX(Id), null, 0, Max(Id)) from Images");
			rs = pstmt.executeQuery();
			if (rs.next()) {
				maxImageID = rs.getInt(1);
			}
	
			pstmt = conn.prepareStatement("INSERT INTO Images VALUES (?,?,?,?,?,?,?)");
			pstmt.setInt(1, maxImageID + 1);
			pstmt.setInt(2, maxID + 1);
			pstmt.setInt(3, NBiometricType.nbtFacialFeatures.eval());
			pstmt.setInt(4, 0);
			pstmt.setInt(5, 0);
			pstmt.setInt(6, 0);
			pstmt.setBytes(7, pngimage);
			pstmt.execute();
		
			Record fr = new Record();
			fr.setId(maxID + 1);
			fr.setName(name);
			fr.setTempalte(template.save());
			recordsList.add(fr);

			try
			{
				conn.close();
			} 
			catch (SQLException e) {};
        
			pstmt.close();
		}
		catch (SQLException e)
		{
			System.out.println(e.getMessage());
		}
	}

	public synchronized void deleteDB() 
	{
		try 
		{
			conn = DriverManager.getConnection(dbURL1, userName1, userPassword1);
			Statement delete = conn.createStatement();
			delete.executeQuery("DELETE FROM Templates");
			delete.executeQuery("DELETE FROM Images");
			recordsList = new ArrayList<Record>();
			
			try
			{
				conn.close();
			} 
			catch (SQLException e) {};
			delete.close();
	              
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
// Additional functionalities added by Bon Sy Jan 31 2010
	public synchronized void append_single_face(String name, NImage image, byte[] template) throws Exception 
	{
		try 
		{
			conn = DriverManager.getConnection(dbURL1, userName1, userPassword1);
			pstmt = conn.prepareStatement("Select decode(MAX(TemplateId), null, 0, MAX(TemplateId)) from Templates");
			rs = pstmt.executeQuery();
			if (rs.next()) {
				maxID = rs.getInt(1);
			}
		
			byte[] pngimage = null;
			for (NImageFormat format : NImageFormat.getFormats()) 
				if (format.getName().contains("PNG"))
					pngimage = format.saveToMemory(image);
			
			pstmt = conn.prepareStatement("INSERT INTO Templates VALUES (?,?,?,?)");
			pstmt.setInt(1, maxID + 1);
			if (name != null)
				pstmt.setString(2, name);
			if (template != null) 
				pstmt.setBytes(3, template);
			if (pngimage != null)
				pstmt.setBytes(4, null);
			
			pstmt.execute();

			pstmt = conn.prepareStatement("Select decode(MAX(Id), null, 0, Max(Id)) from Images");
			rs = pstmt.executeQuery();
			if (rs.next()) {
				maxImageID = rs.getInt(1);
			}
	
			pstmt = conn.prepareStatement("INSERT INTO Images VALUES (?,?,?,?,?,?,?)");
			pstmt.setInt(1, maxImageID + 1);
			pstmt.setInt(2, maxID + 1);
			pstmt.setInt(3, NBiometricType.nbtFacialFeatures.eval());
			pstmt.setInt(4, 0);
			pstmt.setInt(5, 0);
			pstmt.setInt(6, 0);
			pstmt.setBytes(7, pngimage);
			pstmt.execute();
		
			Record fr = new Record();
			fr.setId(maxID + 1);
			fr.setName(name);
			fr.setTempalte(template);
			recordsList.add(fr);

			try
			{
				conn.close();
			} 
			catch (SQLException e) {};
        
			pstmt.close();
		}
		catch (SQLException e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	public List<NImage> getImagesByName(String name) throws Exception{
		ArrayList<NImage> images = new ArrayList<NImage>();

		NImageFormat pngFormat = null;
		for (NImageFormat format : NImageFormat.getFormats()) 
			if (format.getName().contains("PNG")){
				pngFormat = format;
				break;
			}

		try
		{
			conn = DriverManager.getConnection(dbURL1, userName1, userPassword1);
			//String stmtQuery = "SELECT * FROM Images where TemplateId like '" + name + "'";
			String stmtQuery = "select templates.templateCaption, images.id, images.templateID, images.BiometricType,images.RecordIndex,images.FrameIndex, images.image from images, templates where images.templateID = templates.templateID and images.BiometricType = 2 and templates.TemplateCaption like '" + name + "'";

			pstmt = conn.prepareStatement(stmtQuery);
			rs = pstmt.executeQuery();

			int i = 0;
			while (rs.next()){		
				byte[] png = rs.getBytes("Image");
				images.add(pngFormat.loadNImage(png));					
				//FileOutputStream fos = new FileOutputStream("c:\\temp_rs_"+i+".png");
				FileOutputStream fos = new FileOutputStream("temp_rs_"+i+".png");
				fos.write(png);
				fos.close();
				i = i + 1;	
			}
			subjectCaptionSize = i;
			subjectCaption = new String[subjectCaptionSize];

			rs = pstmt.executeQuery();
			
			int j = 0;
			while (rs.next()) {
				subjectCaption[j] = rs.getString(1);
				j = j + 1;	
			}
			
			rs.close();
			pstmt.close();
	        	    		            
			try
			{
				conn.close();
			} 
			catch (SQLException e) {};   
		}
		catch (SQLException e)
		{
			System.out.println(e.getMessage());
		}
		
		return images;
	}	
//	End additional
}



