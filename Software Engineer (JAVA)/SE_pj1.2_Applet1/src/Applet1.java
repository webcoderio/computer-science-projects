// Copyright (c) 2001 by Bon K. Sy

// URL for this applet: http://bonnet18.cs.qc.edu/bon_space/DBApplet.html
// This deployment requires Oracle specific packages: JDevelper Runtime
//   Connection Manager, and Oracle 8.1.6 JDBC (famous classes12.zip)

// At depolyment, get a deployment space and unjar the deployment jar package.
//  For example, at bon_space, jar xvf DBApplet.jar and let it create the appropriate subdir.

import java.applet.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;


// Be aware oracle.jdeveloper.layout.* is NOT a standard JDK package
import oracle.jdeveloper.layout.*;

/**
 * Applet
 * <P>
 * @author Bon K. Sy
 */

@SuppressWarnings("unused")
public class Applet1 extends Applet{
	
  /**
	 * S
	 */
  private static final long serialVersionUID = 1L; //sng: ctrl+1

// SQL Statement. This sets the global variable Statement visbile by different methods.
  Statement stmt;

  boolean isStandalone = false;

  // Note that XYLayout is specific from JDeveloper package and needs to be 
  // included during deployment.
  XYLayout xYLayout1 = new XYLayout();
  JPanel jPanel1 = new JPanel();
  Label label1 = new Label();
  TextField textField1 = new TextField(16);
  Label label2 = new Label();
  JPasswordField jPasswordField1 = new JPasswordField(10);
  Label label3 = new Label();
  TextField textField2 = new TextField();
  Label label4 = new Label();
  TextField textField3 = new TextField();
  Label label5 = new Label();
  TextField textField4 = new TextField();
  FlowLayout flowLayout1 = new FlowLayout();
  TextArea textArea1 = new TextArea();
  Button button1 = new Button();
  Label label6 = new Label();
  Label label7 = new Label();

  /**
   * Constructs a new instance.
   */
  /**
   * getParameter
   * @param key
   * @param def
   * @return java.lang.String
   */
  public String getParameter(String key, String def) {
    if (isStandalone) {
      return System.getProperty(key, def);
    }
    if (getParameter(key) != null) {
      return getParameter(key);
    }
    return def;
  }

  public Applet1() {
  }


  /**
   * Initializes the state of this instance.
   */
  /**
   * init --- This is also the standard entry point if you wish to build a standalone runnable
   */
  @Override
public void init() {
    try  {
      jbInit();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }


  // All the actions appears here about JDBC connection

  private void jbInit() throws Exception {
    xYLayout1.setWidth(419);
    xYLayout1.setHeight(394);
    this.setLayout(flowLayout1);
    label1.setText("Username:");
    label2.setText("Password:");
    label3.setText("Start Date:");

    // Java should do a better job on setting the reference for month and year!
    /*Calendar today = Calendar.getInstance();
    
      textField2.setText((today.getTime().getMonth()+1) +"/"+
                          today.getTime().getDate()+"/"+
                          (today.getTime().getYear()+1900));
    label4.setText("End Date: ");
      textField3.setText((today.getTime().getMonth()+1) +"/"+
                          today.getTime().getDate()+"/"+
                          (today.getTime().getYear()+1900));
                          */

    label5.setText("Community PIN#: ");
    //textField4.setText("82");  // Default database community
    textField4.setText("3494");  // Default database community
    button1.setLabel("Get Data!");
    label6.setFont(new Font("Dialog", 1, 12));
    label6.setText("Applet Demo: E-Community Access Log by Bon K. Sy @ Nov 2001");
    label7.setFont(new Font("Dialog", 1, 11));
    label7.setText("Note: Thin client is NOT capable of handling large data pipe.");
    button1.addActionListener(new java.awt.event.ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          button1_actionPerformed(e);
        }
      }
    );
    textArea1.setText("Waiting for Data Access");
    this.setLayout(xYLayout1);
    this.setBackground(SystemColor.control);
    this.add(jPanel1, new XYConstraints(175, 207, -1, 9));
    this.add(label1, new XYConstraints(5, 34, 68, 24));
    this.add(textField1, new XYConstraints(71, 36, 111, 22));
    this.add(label2, new XYConstraints(188, 35, 67, 25));
    this.add(jPasswordField1, new XYConstraints(254, 35, 108, 22));
    this.add(label3, new XYConstraints(5, 66, 62, -1));
    this.add(textField2, new XYConstraints(63, 66, 64, -1));
    this.add(label4, new XYConstraints(130, 66, 59, 24));
    this.add(textField3, new XYConstraints(185, 66, 68, -1));
    this.add(label5, new XYConstraints(258, 66, 100, 25));
    this.add(textField4, new XYConstraints(363, 66, 29, -1));
    this.add(textArea1, new XYConstraints(7, 99, 387, 253));
    this.add(button1, new XYConstraints(331, 362, -1, -1));
    this.add(label6, new XYConstraints(9, 9, 374, 20));
    this.add(label7, new XYConstraints(1, 363, 330, -1));
  }

  @SuppressWarnings("deprecation")
private void initializeJDBC()
  {
    try
    {
      //Declare Driver and Connection String
      String driver = "oracle.jdbc.driver.OracleDriver";

      // Load the Oracle JDBC Thin driver
      Class.forName("oracle.jdbc.driver.OracleDriver");
      textArea1.append("Driver oracle.jdbc.driver.OracleDriver" + " loaded"+'\n');

      //Connect to the Sample database
      /*
      Connection conn = DriverManager.getConnection
        ("jdbc:oracle:thin:@<host>:<port>:<SID>", textField1.getText(), jPasswordField1.getText());
      textArea1.append("Database jdbc:oracle:thin:<username>/<password>" +
        "@<host>:<port>:<SID>:test connected"+'\n');
       */
      Connection conn = DriverManager.getConnection
      ("jdbc:oracle:thin:@149.4.211.237:1521:sulfur", textField1.getText(), jPasswordField1.getText());
    
      textArea1.append("Database jdbc:oracle:thin:<username>/<password>" +
      "@<host>:<port>:<SID>:test connected"+'\n');
      
      //Create a statement
      stmt = conn.createStatement();
    }
    catch (Exception ex)
    {
      textArea1.append(ex.getMessage() + '\n');
    }
  }

  void button1_actionPerformed(ActionEvent e)
  {
    String query = "SELECT sysdate FROM Dual";
    if (e.getSource() instanceof Button)
    {
      try
      {
        //Bon: Initialized JDBC
        initializeJDBC();
        //Execute the query
        ResultSet rset = stmt.executeQuery(query);
        //Display result
        while (rset.next())
          textArea1.append(rset.getString(1));
        textArea1.append(" " + '\n');

	// YOU NEED TO HAVE YOUR OWN QUERY GOES HERE!
        query = "SELECT sysdate FROM dual";

        //Execute the query
        rset = stmt.executeQuery(query);
        //Display result
        while (rset.next())
          textArea1.append(rset.getString(1) + '\n');
      }
      catch (Exception ex)
      {
        textArea1.append(ex.getMessage() + '\n');
      }
    }
  }

  /**
   * start
   */
  @Override
public void start() {
  }

  /**
   * stop
   */
  @Override
public void stop() {
  }

  /**
   * destroy
   */
  @Override
public void destroy() {
  }

  /**
   * getAppletInfo
   * @return java.lang.String
   */
  @Override
public String getAppletInfo() {
    return "Applet Information";
  }

  /**
   * getParameterInfo
   * @return java.lang.String[][]
   */
  @Override
public String[][] getParameterInfo() {
    return null;
  }


} // class

