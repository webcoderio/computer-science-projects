import java.net.*;
import java.io.*;

import Jama.*;
import PPR_Util.HomomorphicEncryption;

import java.rmi.RemoteException;
import java.util.StringTokenizer;

/**
 * @author bon
 *
 *       java -jar PPR_Client_V2.jar 9174551349-200808061234 149.4.211.232 1693 2 10 20    
 */
class Requester_SMC_Component {
//	private String addr;
//	private int port, rA2, cA2;
//	private Socket soc;
//	private PrintWriter pw=null, pwf=null, pwt=null;
//	private BufferedReader br = null;
//	private Matrix A2, P2, U2, V2t, S2, b2, x, EWi, Pi, dv, dvt;
//	private double[][] arrA2, arrEWi;
//	private double k, a;
	
    private final class RequestThread extends Thread {
    	private String transaction_id; 
    	private int feature_dim;
    	private String server_ip;
    	private int server_port;
    	private int option;
    	private String rcd;
    	private int rowSize;
    	private int columnSize;
    	private String option_HelperData;
    	
    	private String addr;
    	private int port, rA2, cA2;
    	private Socket soc;
    	private PrintWriter pw=null, pwf=null, pwt=null;
    	private BufferedReader br = null;
    	private Matrix A2, P2, U2, V2t, S2, b2, x, EWi, Pi, maxEigenVector, dv, dvt;
    	private double[][] arrA2, arrDV;
    	private double k, a, maxEigenValue;
    	
    	
        RequestThread(final String transaction_id, final int feature_dim, final String server_ip, final int server_port, final int option, final String rcd, final int rowSize, final int columnSize, final String option_HelperData) {
            this.transaction_id = transaction_id;
            this.feature_dim = feature_dim;
            this.server_ip = server_ip;
            this.server_port = server_port;
            this.option = option;
            this.rcd = rcd;
            this.rowSize = rowSize;
            this.columnSize = columnSize;
            this.option_HelperData = option_HelperData;
            this.start();
        }
        
        public void run() {
        	int repeat_flag = 1; //repeat_flag = 0 implies NO repeat, repeat_flag =1 means repeat
    		int repeat_counter = 0;
 
            while (repeat_flag == 1) {
            	System.out.println("Stage for step 3 ....");
            	repeat_flag = 0;

            	initialize(transaction_id, "DV-"+transaction_id+".txt", server_ip, server_port, option);
            	final long start=System.currentTimeMillis();
            	step3();
        	
            	long age =(System.currentTimeMillis()-start)/1000;
            	System.out.println("age= "+age+" seconds.");
            	saveFile(new File(rcd+"-requester-result"+".txt"));
            	
            	try {   		
            		File rfile = new File(rcd+"-requester-result"+".txt");	   
            		BufferedReader local_brf = new BufferedReader(new InputStreamReader(
        				new FileInputStream(rfile.getAbsolutePath())));
    		
            		File wfile = new File(rcd+"-requester-result-verified"+".txt");
        		
            		PrintWriter pwf1 = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
        				new FileOutputStream(wfile.getAbsolutePath()))));

            		String local_line = local_brf.readLine();
//            		while (!(local_line.contains("Pi")) ) local_line = local_brf.readLine();
               		while (!(local_line.contains("X")) ) local_line = local_brf.readLine();
               		
            		local_line = local_brf.readLine(); 		
            		
//            		pwf1.println("Number of items:"+feature_dim);            		
            		pwf1.println("Number of items:"+cA2);
        		
            		for (int ii = 0; ii < cA2; ii++) {
            			local_line = local_brf.readLine();
            			// System.out.println("buffer output: "+ local_line);
            			if (local_line.contains("NaN") ) repeat_flag = 1;
            			pwf1.println(local_line);
            			pwf1.flush();
            		}
            		if (repeat_flag == 1) {
            			repeat_counter++;
            		} 
            		pwf1.close();		    
            		local_brf.close();
            	}catch ( IOException ioe ) {throw new RuntimeException(ioe);}
        	
            }
            //pw.flush();
            //pw.close();
            System.out.println("Transaction ID: " + transaction_id + " Part: " + " where NaN causes to repeat: " + repeat_counter + " time(s)");		
        }   

    	/*
    	 *  Initialize should start the thread that does: open socket connection, 
    	 *  perform steps 3 and 4, and write file
    	 */
    	private void initialize(String transaction_id, String DV_filename, String server_IP, int server_port, int option){
    		
    		/* String s, sizeA2=null; */
    	
    		k=/**2.5;rand.nextDouble();**/Math.random()*2;
    		a=/**0.5;rand.nextDouble();**/Math.random();

    		/*
    		StringTokenizer st = new StringTokenizer(sizeA2, ", ");
    		rA2=Integer.parseInt(st.nextToken());
    		cA2=Integer.parseInt(st.nextToken());
    		*/
    		
    		File SRCfileName = new File("DV-"+transaction_id+".txt");

        	try {
        		BufferedReader brf = new BufferedReader(new InputStreamReader(
        				new FileInputStream(SRCfileName.getAbsolutePath())));
        	    String line = brf.readLine();    		
            	while (line.length() == 0) line = brf.readLine();    		
            	while (line != null) {
            		if (line.length() != 0) feature_dim++;
            		line = brf.readLine();
            	}
            	brf.close();	
    		} catch (IOException ioe) { System.out.println("Error thrown in transact EW file open");}
    		
    		if ((rowSize > 0) && (columnSize > 0)) {
    			rA2 = rowSize;
    			cA2 = columnSize;
    		} else {
    			rA2 = feature_dim;
    			cA2 = feature_dim;
    		}
    		
    		addr= server_IP;
    		port= server_port; /**1688;**/
    		readFile(DV_filename);

    		
    		try{
    			pwt=new PrintWriter(new BufferedWriter(new OutputStreamWriter(
    					new FileOutputStream(new File(transaction_id+"-requester-log.txt")))));
    			}catch(IOException e) {e.printStackTrace();}
    		pwt.println("k= "+k);
    		pwt.println("a= "+a);
    		System.out.println("k= "+k);
    		System.out.println("a= "+a); 		
    		pwt.println();
    		
    		dv=new Matrix(arrDV);
      		pwt.print("matrix dv:");
      		dv.print(pwt);
      		dvt=dv.transpose();
      		pwt.print("matrix dv.transpose():");
      		dvt.print(pwt);
    		System.out.println("-------------------------");
    		
    		A2=dv.times(dvt);
    		pwt.println();
    		pwt.print("matrix A2:");
    		A2.print(pwt);

 /********* czheng add code ************/

 	       EigenvalueDecomposition EA2 =  new EigenvalueDecomposition(A2);
	       double[] ev2 = EA2.getRealEigenvalues();
	       maxEigenValue=ev2[0];
	       int index=0;

	       pwt.println("Print all the eigenvalue of A2:");
	       for(int m=0; m<ev2.length; m ++){
	    	  if(ev2[m]<0.00000000001) ev2[m]=0;
	     	  if(ev2[m]>maxEigenValue){
	     		 if(ev2[m]<0.00000000001) ev2[m]=0;
	     		 	//maxEigenValue=(double)Math.round(ev2[m]);
	     		 	maxEigenValue=(double)ev2[m];
	     		 	index=m;  
	     	  }
	     	 pwt.print(ev2[m]+",");
	       }
	       pwt.println();
	       pwt.println();
   		   pwt.println("max eigenvalue of A2:"+maxEigenValue);
   		   pwt.println();
   		
	       Matrix D2=EA2.getD();
	       Matrix V2=EA2.getV();
	       int size=V2.getRowDimension();
	       double[][] evArr=V2.getArray();
	       double[][] eArr= new double[size][1];

	       for(int i=0; i<size; i++){
	    	   eArr[i][0]=evArr[i][index];
	       }
	       Matrix eigenVector=new Matrix(eArr);
	       maxEigenVector=eigenVector;
   		   pwt.print("maxEigenVector of A2:");
   		   maxEigenVector.print(pwt);
   		
 		b2=eigenVector.times(maxEigenValue);
/********czheng code above*******/ 		
    		
    		pwt.print("b2:");
    		b2.print(pwt);
    		
    		while(true){
    			P2=Matrix.random(cA2, rA2);
    			if (rA2 <= cA2) {
    				if (new LUDecomposition(P2).isNonsingular()) break;
    			} else {
    				break;
    			}
    		}

    		pwt.print("matrix P2:");
    		P2.print(pwt);
    		
    		if (P2.getColumnDimension() > P2.getRowDimension() ) {
    			SingularValueDecomposition svdP2=new SingularValueDecomposition(P2.transpose());
    			V2t=svdP2.getU().transpose();
    			U2=(svdP2.getV());
    			S2=svdP2.getS();    			
    		} else {
    			SingularValueDecomposition svdP2=new SingularValueDecomposition(P2);
    			U2=svdP2.getU();
    			V2t=(svdP2.getV()).transpose();
    			S2=svdP2.getS();
    		}
    		
    		pwt.print("matrix U2:");
    		U2.print(pwt);
    		
    		pwt.print("matrix S2:");
    		S2.print(pwt);
    		
    		pwt.print("matrix V2t:");
    		V2t.print(pwt);
    		
    		//System.out.println("U2*S2*V2t:");
    		Matrix test=(U2.times(S2)).times(V2t);
    		
    		pwt.print("U2*S2*V2t:");
    		test.print(pwt);
    		
    		try {
//    			System.out.println("Server address connected to is "+addr+" and port is "+port);
    			soc = new Socket(addr, port);

    			pw=new PrintWriter(soc.getOutputStream(), true);
    			br = new BufferedReader(new InputStreamReader(
                        soc.getInputStream()));

    		} catch (UnknownHostException e) {
    			System.err.println("Don't know about host.");
               System.exit(1);
    			e.printStackTrace();
    		} catch (IOException e) {
    			System.err.println("Couldn't get I/O for the connection to server.");
    			System.exit(1);
    			e.printStackTrace();
    		}

    		// Connecting to PPR data custodian server
    		pw.println("Initiate Service Request");
    		    		
    		pw.println(transaction_id);
    		pw.println(option);  //pass option 1 if and only if you want server side to use random matrix
    	
    		//Add Mar 31 2010 by Bon Sy to send rA2 for userDefinedRowSize and cA2 for userDefinedColumnSize
    		pw.println("Send row and column dim");
    		pw.println(rA2);
    		pw.println(cA2);
    		//End code added Mar 31 2010 by Bon Sy

    		System.out.println("end of initialize()");
    		pwt.flush();
    	}//method initialize()
    	
        
    	/**
    	 * Read matrix A2 and EWi from the file 
    	 *
    	 */
    	private void readFile(String in_file){
    		readSource(new File(in_file));
    	}
    	
    	private void readSource(File fileName){
    		BufferedReader brf=null;
        	try {
        		brf = new BufferedReader(new InputStreamReader(
        				new FileInputStream(fileName.getAbsolutePath())));
        	}catch ( IOException ioe ) {throw new RuntimeException(ioe);}
        	
        	try {
        		if ( brf == null )
        			throw new RuntimeException("Cannot read from closed file "
    					                       + fileName.getAbsolutePath() + ".");
        		
        		String line = brf.readLine();
        		while (line.length() == 0) line = brf.readLine();
        		int counter=0;
        		 
        			arrDV=new double[rA2][1];
        			while ( line != null && counter<rA2){    				
        				arrDV[counter][0]=Double.parseDouble(line);
        				counter++;
        				line = brf.readLine();
        			}
        		
        		
        		brf.close();
        	}catch (IOException ioe) {throw new RuntimeException(ioe);}
        }  // method readSource()
    
    	/**
    	 * Setup P3's Inet address, port#
    	 * Initialize d-number of digits after the decimal
    	 * Generate matrix P2=U2*S2*V2t, encryption key k & a
    	 * Initialize Matrix A2, b2, EWi
    	 *
    	 */
    	

    	public void step3(){
    		System.out.println("------step3------");
    		pwt.println("------step3------");
    		//P2 sends V2t*A2
    		Matrix p2Snd1=V2t.times(A2);
    		p2Snd1.print(pw);
    		pwt.print("P2 sends V2t*A2");
    		p2Snd1.print(pwt);
    		
    		try{
     			//E3[V2t*A2*U3*S3] from P3
    			Matrix p3Ret1a=Matrix.read(br);
    			pwt.print("E3[V2t*A2*U3*S3] from P3");
    			p3Ret1a.print(pwt);
    			
    			//E3[A3*U3*S3] from P3
    			Matrix p3Ret1b=Matrix.read(br);
    			pwt.print("E3[A3*U3*S3] from P3");
    			p3Ret1b.print(pwt);

    			//P2 sends[E3(V2t*A2*U3*S3)^(U2*S2)]*[E3(A3*U3*S3)^(P2)]<=>E3[P2*(A2+A3)*U3*S3]
                Matrix U2S2 = U2.times(S2);
                pwt.print("U2*S2:");
                U2S2.print(pwt);
                Matrix LE1=p3Ret1a.lhomomorphicEncryptedTimes(U2S2);
                Matrix LE2 = p3Ret1b.lhomomorphicEncryptedTimes(P2);
    			Matrix p2Snd2=(LE1).arrayTimes(LE2);
    			p2Snd2.print(pw);
                pwt.print("E3(V2t*A2*U3*S3)^(U2*S2)");
                LE1.print(pwt);
                pwt.print("E3(A3*U3*S3)^(P2)");
                LE2.print(pwt);
    			pwt.print("P2 sends [E3(V2t*A2*U3*S3)^(U2*S2)]*[E3(A3*U3*S3)^(P2)]<=>E3[P2*(A2+A3)*U3*S3]");
    			p2Snd2.print(pwt);
    			pwt.flush();

    			double c2=Math.random()+0.3;
                pwt.println("c2: " + c2);
    			Matrix c2p2b2=P2.times(b2).times(c2);
    			Matrix c2p2=P2.times(c2);
    			pwt.print("c2*P2*b2:");
    			c2p2b2.print(pwt);
                pwt.print("c2*P2:");
                c2p2.print(pwt);
    			HomomorphicEncryption p2Snd3a=new HomomorphicEncryption(k, a, c2p2b2);
    			HomomorphicEncryption p2Snd3b=new HomomorphicEncryption(k, a, c2p2);

    			//P2 sends [E2(c2*P2*b2)]
    			p2Snd3a.print(pw);
    			pwt.print("P2 sends [E2(c2*P2*b2)], c2 is a random number");
    			p2Snd3a.print(pwt);
    			
    			//P2 sends [E2(c2*P2)]
    			p2Snd3b.print(pw);
    			pwt.print("P2 sends [E2(c2*P2)]");
    			p2Snd3b.print(pwt);

    			//[E2(c2*P2*b2)^(c3)][E2(c2*P2)^(b3*c3)] <=> E2[c2*P2(b2+b3)*c3] from P3
    			HomomorphicEncryption p3Ret3=HomomorphicEncryption.read(br, k, a);
    			pwt.print("[E2(c2*P2*b2)^(c3)][E2(c2*P2)^(b3*c3)] <=> E2[c2*P2(b2+b3)*c3] from P3");
    			p3Ret3.print(pwt);
    			
    			//P2 sends P2(b2+b3)*(c3)
                Matrix p3Ret3Decrypted = p3Ret3.getDecryptedMatrix();
    			Matrix p2Snd4=p3Ret3Decrypted.times(1/c2);
    			p2Snd4.print(pw);

                pwt.print("c2*P2(b2+b3)*(c3)");
                p3Ret3Decrypted.print(pwt);
    			pwt.print("P2 sends P2(b2+b3)*(c3)");
    			p2Snd4.print(pwt);	
   			
    			// x from P3
    			x=Matrix.read(br);
    			
    			pwt.print("x from P3");
    	        x.print(pwt);
   	        
    	        //check if A2*x=b2
    	        Matrix A2x=A2.times(x);
    	        pwt.print("A2*x");
    	        A2x.print(pwt);
    	        pwt.print("b2");
    	        b2.print(pwt);
                pwt.println("A2*x-b2=");
                (A2x.minus(b2)).print(pwt);
 
 /********* czheng add code here *******/
                //Comment block added by Bon Sy Jan 8, 2010
                //Client side computes the distance between x and Vdv
  		      	Matrix temp=maxEigenVector.minus(x);
  		        pwt.print("maxEigenVector-x");
  		        temp.print(pwt);
  		        
  		        double[][] tArr=temp.getArray();
  		        double dist=0;
  		        for(int i=0; i<tArr.length; i++){
  		        	dist=dist+tArr[i][0]*tArr[i][0];
  		        }
  		        double sigma=Math.sqrt(dist);
  		        pwt.println("||Vdv-x||="+sigma);
  		        pwt.println();

  		        double epsilon=51.5;
  		        pwt.println("Print epsilon=" + epsilon);
  		        pwt.println();  		      
             //Self comparison between x and Vdv ends here  		        
  		     
  		        String line1 = br.readLine();
  		        String continueRun = br.readLine();
  		        System.out.println("continueRun received from server side= " + continueRun);

  		        pwt.println("Sending option_HelperData to p3 with option_HelperData= "+ option_HelperData); 
  		        pwt.println();
  		        pw.println(option_HelperData);

  		        String continue_buffer = br.readLine();
  		        System.out.println("continue_buffer received from server side= " + continue_buffer);
  		        
    		    if (continue_buffer.equals("0")) {  		       
      		    	  pwt.println("Distance discrepancy too large. Server side refuses to continue");
      		      }
      		      
    			  if (continue_buffer.equals("10")) {
      		    	   // "10" uses helper data eigenvalue and 1/De^T*x
    				  double de_maxEigenValue = Double.parseDouble(br.readLine());
    				  pwt.print("Receive de_maxEigenValue from P3: ");
    				  pwt.println(de_maxEigenValue);
    				  pwt.flush();
    				  
    				  Matrix detx_inverse=Matrix.read(br);				   
    				  pwt.print("Receive detx_inverse from P3: ");
    				  detx_inverse.print(pwt);
    				  pwt.println();
    				  pwt.flush();
    				  
    				  Matrix offset_dv_EigenVector = x.minus(maxEigenVector);
    				  
    				  //double [][] offset_Array = offset_dv_EigenVector.getArray();
    				  //double tem_norm1 = 0;
    				  //for (int i = 0; i < offset_dv_EigenVector.getRowDimension()-1; i++) {
    					//  tem_norm1 += offset_Array[i][0];
    				  //}
    				  //if (tem_norm1 < 0) {
    					//offset_dv_EigenVector = offset_dv_EigenVector.uminus();  
    				  //}
    				  
    				  Matrix estimated_de_EigenVector = offset_dv_EigenVector.times(2).plus(maxEigenVector);
    				  Matrix result = estimated_de_EigenVector.times(de_maxEigenValue).times(detx_inverse); 
    				  pwt.print("Estimated de:");
    				  result.print(pwt);	
      		       } 
    			  
    			  if (continue_buffer.equals("11")) {
     		    	   // "11" uses the vector b3 and 1/detx
   				   Matrix b3=Matrix.read(br);				   
			        pwt.print("Receive b3 from P3");
			        b3.print(pwt);
			        pwt.flush();

				    Matrix detx_inverse=Matrix.read(br);				   
			        pwt.print("Receive detx_inverse from P3");
			        detx_inverse.print(pwt);
			        pwt.flush();	
			     
			        //Double.parseDouble(line);
			        
			        Matrix NegA2x=A2x.times(-1);
			        pwt.print("-A2*x");
			        NegA2x.print(pwt);	
			        
			        Matrix numo=b2.plus(b3).minus(A2x);
			        pwt.print("b1+b2-A3*x");
			        numo.print(pwt);
			        Matrix  result = numo.times(detx_inverse);
			        pwt.print("de:");
			        result.print(pwt);			       	
     		       }
 /********* czheng add code above *******/  		       
    		}  catch (IOException e) {
    			System.err.println("Couldn't get I/O for the connection to server.");
    			System.exit(1);
    			e.printStackTrace();
    		}
    		pwt.flush();
    		
    	}//method step3()

    	public void saveFile(File fileName){		
    		try {
    			pwf = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
    					new FileOutputStream(fileName.getAbsolutePath()))));
    	        pwf.println("X");
    			x.print(pwf);
    			
     	        pwf.flush();
    	        pwf.close();
    	    }catch ( IOException ioe ) {throw new RuntimeException(ioe);}
    	}//method saveFile(File fileName)

    }
	    
	public Requester_SMC_Component(String transaction_id, String server_ip, int server_port, int option, String rcd, int rowSize, int columnSize, String option_HelperData){
//    	new RequestThread(transaction_id, <feature dimenion initialized as 0>, EW_initial+".txt", server_ip, server_port, option, rcd, rowSize, columnSize, opton_HelperData);
		new RequestThread(transaction_id, 0, server_ip, server_port, option, rcd, rowSize, columnSize, option_HelperData);	       
	}
	
}//class Requester_SMC_Component

/**
 * You must start the server program(P3) first.
 * Next, run the client program(P2).
 * You can run the client on any machine on your network;
 * it does not have to run on the same machine as the server.
 *  
 */

public class SIPPA_PPR_Client {

	public SIPPA_PPR_Client (String transaction_ID, String rmi_client_ip_of_SIPPA_server, int SIPPA_Server_socket_port, int option, int userDefinedRowSize, int userDefinedColumnSize, String optionHelperData) {
    	new Requester_SMC_Component(transaction_ID, rmi_client_ip_of_SIPPA_server, SIPPA_Server_socket_port, option, transaction_ID.substring(0,transaction_ID.indexOf("-")), userDefinedRowSize, userDefinedColumnSize, optionHelperData);
	}
	
	private static void printUsage()
	{
		System.out.println("SIPPA_PPR_Client");
		System.out.println("Returns 0 if successful, 1 otherwise");
		System.out.println("Usage: java -jar SIPPA_PPR_Client.jar <Transaction ID> <DB_Agent server IP> <Server Port> <option> <userDefinedRowSize> <userDefinedColumnSize> <optionHelperData>");
		System.out.println("   <userDefinedRowSize> and <userDefinedColumnSize> are ignored if they are -1, and are used only if they are positive integer matching the matrix size");
		System.out.println("   <optionHelperData> should be 0 for using only helper data 1/De^T*x and eigenvalue, and 1 for using vector b3 and 1/De^T*x");
		System.out.println("Requester matrix and vector default filenames are <Transaction ID>-Requester-matrix.txt and <Transaction ID>-Requester-vector.txt");
		System.out.println("EW filename is assumed to be <EW_initial>.txt");
		System.out.println("option: 0");
		//System.out.println("option: 1 for generating random requester matrix and vector at run-time");
		//System.out.println("option: 2 for using pre-defined requester matrix and b vector= vector - EW");
		System.out.println("Success returns errorlevel 0. Error return greater than zero.");
		System.exit(0);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try
		{
			if (args.length != 7) {
				printUsage();
			}

	        /* Print a copyright. */
	        System.out.println("PPR Client requester utility");
	        System.out.println("Copyright: Bon Sy");
	        System.out.println("Free to use this at your own risk!");
	        
        	new Requester_SMC_Component(args[0], args[1], Integer.parseInt(args[2]), Integer.parseInt(args[3]), args[0].substring(0,args[0].indexOf("-")), Integer.parseInt(args[4]), Integer.parseInt(args[5]), args[6]);

		} 
		catch (Exception e){
			// probably error in input
			printUsage();
		}
	} // End main

}
