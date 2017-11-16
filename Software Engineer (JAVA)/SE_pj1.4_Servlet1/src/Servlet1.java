// URL location: http://bonnet18.cs.qc.edu:8080/bon_apps/servlet/Servlet1
//		Additional example: http://bonnet18.cs.qc.edu:8080/
//
// Copyright (c) Oct. 2001 by Bon K. Sy
// Title: Servlet Access to Oracle Database Example
//
// Servlet environment: Tomcat 3.2.3
// Deployment information: 
//  1. Servlet1.jar includes javax.sql.* and oracle.jdbc.driver.* packages
//       and locates at the lib of the "bon_apps" realm
//  2. Servlet1.class locates at the WEB-INF/classes of the "bon_apps" realm
//
// Server side environment setup for deployment to bonnetXX machine:
//  1. URL-pattern to servlet-name mapping is defined in web.xml in WEB-INF of "bon_apps" realm
//  2. Virtual path dir mapping and bon_apps realm definition are done through server.xml
//        and server.xml lives in /conf/ of $TOMCAT_HOME
//  3. Alias and virtual path information should also be in apache-tomcat.conf file. 
//
// Side note: 
//  1. The afamous classes111.zip for oracle connection has been explicitly included 
//     in the CLASSPATH just in case the driver packages are not included in the lib
//  2. Though JDeveloper 3.1 amd JBuilder3.5 should be both adequate for building servlet,
//     the deployment utility of JBuilder is more pleasant in my personal experience. Also,
//     JDeveloper 3.1 is virtually an early version of JBuilder when Borland licensed it 
//     out to Oracle.

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
//import java.util.*;
import java.sql.*;

public class Servlet1 extends HttpServlet {

  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

//Initialize global variables
  public void init(ServletConfig config) throws ServletException {
    super.init(config);
  }

  public void init() {
  }

// The place where all the servlet actions start!

  public void service(ServletRequest req, ServletResponse res)
    throws ServletException, IOException {
    getData(req, res);
  };

  private void getData(ServletRequest req, ServletResponse res)
    throws IOException {
      res.setContentType("Text/html");
      PrintWriter out = res.getWriter();

      // As one can see here, the html code is just hard coded for print to browser 
      out.print("<HEAD><TITLE>");
      out.print("Print information on NAME, EMAIL, URL");
      out.print("</TITLE></HEAD>");
      out.print("<BODY>");

      // &#34 is the famous double-quote " equivalent for html tag to get around 
      //   the semantic meaning of " in Java.
      out.print("<TABLE  BORDER=2 VALIGN= &#34 TOP &#34>");
	    out.print("<TR>");
	    out.print("<td align= &#34 left &#34>");
	    out.print("<font size= &#34 2 &#34 face= &#34 Arial,Helvetica &#34>");
	    out.print("Name");
	    out.print("</font></td>");
	    out.print("<td align= &#34 left &#34>");
	    out.print("<font size= &#34 2 &#34 face= &#34 Arial,Helvetica &#34>");
    	    out.print("Email Address");
	    out.print("</font></td>");
	    out.print("<td align= &#34 left &#34>");
	    out.print("<font size= &#34 2 &#34 face= &#34 Arial,Helvetica &#34>");
	    out.print("URL");
	    out.print("</font></td>");
	    out.print("<td align= &#34 left &#34>");
	    out.print("<font size= &#34 2 &#34 face= &#34 Arial,Helvetica &#34>");
	    out.print("Community Name");
	    out.print("</font></td>");
	    out.print("</TR>");

      try
      {
        //Declare Driver and Connection String
        // String driver = "oracle.jdbc.driver.OracleDriver";

        // Load the Oracle JDBC Thin driver
        Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();

        //Connect to the Sample database
        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@149.4.211.237:1521:sulfur", "sng", "sng2");

        //Create a statement
        Statement stmt = conn.createStatement();

        //Create your SQL query here!
        String query = "select sysdate from dual";


        //Write Connection success message; Uncomment below if you want to get a status message
//        out.println("Connection success!");
//        out.println("<P>");

        //Execute the query
        ResultSet rset = stmt.executeQuery(query);

        //Display result
        while (rset.next()) {
	        out.print("<TR>");
      	    out.print("<td align= &#34 left &#34>");
	          out.print("<font size= &#34 2 &#34 face= &#34 Arial,Helvetica &#34>");
              out.println(rset.getString(1 ));
	          out.print("</font></td>");

      	    out.print("<td align= &#34 left &#34>");
	          out.print("<font size=&#34 2 &#34 face=&#34 Arial,Helvetica &#34>");
              out.println(rset.getString(2 ));
	          out.print("</font></td>");

      	    out.print("<td align= &#34 left &#34>");
	          out.print("<font size=&#34 2 &#34 face=&#34 Arial,Helvetica &#34>");
              out.println(rset.getString(3 ));
	          out.print("</font></td>");

   	        out.print("<td align= &#34 left &#34>");
	          out.print("<font size=&#34 2 &#34 face=&#34 Arial,Helvetica &#34>");
              out.println(rset.getString(4 ));
	          out.print("</font></td>");
          out.println("</TR>");
        }
      }
      catch (Exception ex)
      {
        out.println(ex.getMessage() + '\n');
      }

      out.print("</TABLE>");
      out.print("<HR>");
      out.print("<P>");
      out.print("<FONT SIZE= &#34 1 &#34>");
        out.print("Copyright @copyright 2001, Bon K. Sy");
      out.print("</FONT>");
      out.print("</P>");

      out.print("</BODY>");
      out.close();
  }

//  public String getServletInfo() {
//  };

  public void destroy(){
  };

/* Servlet basic interface */

  //Process the HTTP Get request
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    PrintWriter out = new PrintWriter (response.getOutputStream());
    response.setContentType("text/html");
    out.println("<FONT COLOR=GREEN>");
    out.println("The servlet has received a GET. This is the reply.");
    out.println("</FONT>");

    try {
      String passphrase = String.valueOf(request.getParameter("passphrase"));
      if (passphrase == "<PUT PASSPHRASE HERE if you want authentication>")
        {out.println("Your passphrase is: "+passphrase);
         getData(request, response);}
      else {out.println("Bad passphrase!");}
      } catch (Exception ex)
      {
        out.println(ex.getMessage() + '\n');
      }

    out.close();
  }
}
