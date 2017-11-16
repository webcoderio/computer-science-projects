import Jama.*;

import java.io.*;
import java.net.*;
import java.util.*;

import PPR_Util.HomomorphicEncryption;
/**
 * @author bon
 *
 */

public class SIPPA_PPR_Server {

	private int userDefinedRowSize = -1, userDefinedColumnSize = -1;
	
	private int rA3, cA3;
	private double k,a,maxEigenValue;
	private Matrix A3, P3, U3, V3t, S3, b3, x, maxEigenVector, de, det;
	private double[][] arrA3, arrb3, arrDE;

	private PrintWriter pw, pwf, pwt;
	private BufferedReader br;	
	private Socket socket;
	private ServerSocket serverSocket = null;
	private int port = 1688;
	
//	public SIPPA_PPR_Server(int p_port, int rowSize, int columnSize){
	public SIPPA_PPR_Server(int p_port){
//		userDefinedRowSize = rowSize;
//		userDefinedColumnSize = columnSize;
		port = p_port;
		initialize_socket();
		socket_listener();
		socket_stream_buffer_close();	
	}
	
	private void readSource(File fileName){
		BufferedReader brf=null;
		//System.out.println("File string="+str);
    	try {
    		brf = new BufferedReader(new InputStreamReader(
    				new FileInputStream(fileName.getAbsolutePath())));
    	
    	    if ( brf == null ){
    			socket_stream_buffer_close ();
    			socket_listener();
    		}
 
     		String line = brf.readLine();

    		while (line.length() == 0) line = brf.readLine();

    		int counter=0;

    			//line = brf.readLine(); //Eat the first line with dimension information
    			arrDE=new double[rA3][1];
    			while ( line != null && counter<rA3){
    				arrDE[counter][0]=Double.parseDouble(line);
    				    System.out.println("rA3="+rA3+ " counter = " + counter + " data: " + arrDE[counter][0]+",");
     				counter++;
    				line = brf.readLine();
    			}
    
    		brf.close();
    	}catch (IOException e) {socket_stream_buffer_close (); 
    							socket_listener();}
    }  // method readSource()
	
	/**
	 * Setup connection
	 * Initial d-number of digits after the decimal
	 * Generate matrix P3=U3*S3*V3t
	 * Initial Matrix A3, b3
	 *
	 */
	
	public void initialize_socket() {
		try {
            serverSocket = new ServerSocket(port);
            String addr=serverSocket.getInetAddress().toString();
            String addrr=serverSocket.getLocalSocketAddress().toString();
            System.out.println("IP address of P3: "+addr);
            System.out.println("socket address(IP address: port) of P3: "+addrr);
        } catch (IOException e) {
            System.err.println("Could not listen on port: "+port);
            System.exit(-1);
        }    
        initialize_socket_stream_buffer();
	}
	
	public void initialize_socket_stream_buffer() {
        try{
        	socket=serverSocket.accept();
        	System.out.println("connected");
        	br=new  BufferedReader(new InputStreamReader(socket.getInputStream()));
        	pw = new PrintWriter(socket.getOutputStream(), true);
        }catch(IOException e) {e.printStackTrace();}
	}
	
	public void socket_stream_buffer_close () {
    	pw.close();
    	try {
    		br.close();
    	} catch(IOException e) {e.printStackTrace();}
	}
	
	public void initialize(String transactionID, int option){
		File A3fileName;
		File arrDVFileName;
		File SRCfileName;

		PrintWriter pwA3=null;
		BufferedReader brf=null;

		int counter;
		int feature_dim=0;
		String line;

		System.out.println("---initialize---");

		SRCfileName = new File("DE-"+transactionID+".txt");

    	try {
    		try {
    			brf = new BufferedReader(new InputStreamReader(
    					new FileInputStream(SRCfileName.getAbsolutePath())));
			} catch (IOException ioe) {
				System.out.println("Error thrown in transact EW file open");
    			socket_stream_buffer_close (); 
    			socket_listener();
    		}
			
    		if ( brf == null ){
    			socket_stream_buffer_close ();
    			socket_listener();
    		}

	     	line = brf.readLine();    		
    		while (line.length() == 0) line = brf.readLine();    		
    		while (line != null) {
    			if (line.length() != 0) feature_dim++;
    			line = brf.readLine();
    		}	     		
    		
    		brf.close();		
    	
    		//Add Mar 31 2010 by Bon Sy to get userDefinedRowSize and userDefinedColumnSize
    		int flag = 1;
    		while (flag == 1) {
    			try {
    				if (br.readLine().equals("Send row and column dim")) {
    					userDefinedRowSize = Integer.parseInt(br.readLine());
    					userDefinedColumnSize = Integer.parseInt(br.readLine());
    					System.out.println("Row diemnsion defined by SIPPA client = " + userDefinedRowSize);
    					System.out.println("Column diemnsion defined by SIPPA client = " + userDefinedColumnSize);    					
    					flag = -1;
    				} else {
    					System.out.println("Waiting for row and column dim ...");
    				}
    			}  catch ( IOException ioe )	{System.out.println("Error thrown in reading row and column dim");}
    		}
    		//End code added Mar 31 2010 by Bon Sy
    		
    		if ((userDefinedRowSize > 0) && (userDefinedColumnSize > 0)) {
    			rA3 = userDefinedRowSize;
    			cA3 = userDefinedColumnSize;
    		} else {
    			rA3 = feature_dim;
    			cA3 = feature_dim;    	
    		}
    		
    		System.out.println("Row size = " + rA3);
    		System.out.println("Column size = " + cA3);
    	/*
    		brf = new BufferedReader(new InputStreamReader(
    				new FileInputStream(SRCfileName.getAbsolutePath())));

    		if ( brf == null ){
    			socket_stream_buffer_close ();
    			socket_listener();
    		}
    		
    		line = brf.readLine();
    		while (line.length() == 0) line = brf.readLine();
    		counter=0;

    		EWi=new double[rA3][1];
    		while ( line != null && counter<rA3){
     			EWi[counter][0]=Double.parseDouble(line);
    			counter++;
    			line = brf.readLine();
    		}
    		brf.close();
    		*/
    		

    	}catch (IOException ioe) {System.out.println("Error thrown in Initialize");
    							  socket_stream_buffer_close (); socket_listener();}

    	
    	//arrDVFileName = new File(transactionID + "-server-vector.txt");	   
	    //readSource(arrDVFileName,"arrDE");

    	/*    	
    	if (option == 0) {
    		arrb3=new double[rA3][1];
    		for (int i=0; i<rA3; i++) arrb3[i][0]=arrDE[i][0];  		
    	}

     	// symmetrically reverse; i.e., client side is arrDV - EWi
    	if (option == 2) {
    		arrb3=new double[rA3][1];
    		for (int i=0; i<rA3; i++) {
    			arrb3[i][0]=arrDE[i][0]+EWi[i][0];
    		}
    	}
    	*/
       	//A3fileName = new File(transactionID+"-server-matrix.txt");
	    readSource(SRCfileName);

		//Random rand=new Random();
		k=/**2.5;rand.nextDouble();**/Math.random()*2;
		a=/**0.5;rand.nextDouble();**/Math.random();

		//initialize_socket();
		try {
			pwt=new PrintWriter(new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(new File(transactionID+"-logP3.txt")))));
		} catch(IOException e) {socket_stream_buffer_close (); socket_listener();}
    	
		//A3=new Matrix(arrA3);		
		//b3=new Matrix(arrb3);
				
		pwt.println("k= "+k);
		pwt.println("a= "+a);
		pwt.println();
		
	    de=new Matrix(arrDE);
		pwt.print("Input Vector de:");
		de.print(pwt);
		
		//pwt.print("matrix A3:");
		//A3.print(pwt);
		
		det=de.transpose();
		pwt.print("print dv.transpose():");
		det.print(pwt);		
		
		A3=de.times(det);
		pwt.print("matrix A3:");
		A3.print(pwt);		

/*********czheng add code below ******/
//		pwt.print("Original b3:");
	//	b3.print(pwt);
		
	       EigenvalueDecomposition EA3 =  new EigenvalueDecomposition(A3);
	       double[] ev3 = EA3.getRealEigenvalues();
	       maxEigenValue=ev3[0];
	       int index=0;
	          System.out.print("Print EigenValue of Matrix de is ");
	       for(int m=0; m<ev3.length; m ++){
	    	  if(ev3[m]<0.00000000001) ev3[m]=0;
	     	  if(ev3[m]>maxEigenValue){
	     		 //maxEigenValue=(double)Math.round(ev3[m]);
	     		 maxEigenValue=(double)ev3[m];
	     		  index=m;  
	     	  }
	     	  System.out.print(ev3[m]+",");
	       }
	       
   		   pwt.println("max eigenvalue of A2:"+maxEigenValue);
   		   pwt.println();
	       
	       Matrix D3=EA3.getD();
	       //System.out.println("Print E3.getD()");
	       //D3.print(5,3);
	       Matrix V3=EA3.getV();
	       //System.out.println("Print E3.getV()");
	       //V3.print(5,3);
	       //System.out.println("Print max eigenvalue="+D3.getArray()[index][index]);
	       //System.out.println("Print max eigenvector is");
	       int size=V3.getRowDimension();
	       double[][] evArr=V3.getArray();
	       double[][] eArr= new double[size][1];

	       for(int i=0; i<size; i++){
	    	   eArr[i][0]=evArr[i][index];
	       }
	       maxEigenVector=new Matrix(eArr);
   		   pwt.print("maxEigenVector of A3:");
   		maxEigenVector.print(pwt);

 		b3=maxEigenVector.times(maxEigenValue);
 
		pwt.print("matrix A3 * maxEigenVector:");
		(A3.times(maxEigenVector)).print(pwt);
 		
/*********czheng add code above *****/		
		pwt.print("matrix b3:");
		b3.print(pwt);
		
		while(true){
			P3=Matrix.random(cA3, rA3);
			if (rA3 <= cA3) {
				if (new LUDecomposition(P3).isNonsingular()) {
					System.out.println();
					System.out.println("P3 is not singular");
					break;
				} else {
						System.out.println("P3 is singular");
				}
			} else {
				break;
			}
		}
		
		
		if (P3.getColumnDimension() > P3.getRowDimension() ) {
			SingularValueDecomposition svdP3=new SingularValueDecomposition(P3.transpose());
			V3t=svdP3.getU().transpose();
			U3=(svdP3.getV());
			S3=svdP3.getS();    			
		} else {
			SingularValueDecomposition svdP3=new SingularValueDecomposition(P3);
			U3=svdP3.getU();
			V3t=(svdP3.getV()).transpose();
			S3=svdP3.getS();
		}
		
		//SingularValueDecomposition svdP3=new SingularValueDecomposition(P3);
		//U3=svdP3.getU();
		//V3t=(svdP3.getV()).transpose();
		//S3=svdP3.getS();
		
		pwt.print("matrix P3:");
		P3.print(pwt);
		
		pwt.print("matrix U3:");
		U3.print(pwt);
		
		pwt.print("matrix S3:");
		S3.print(pwt);
		
		pwt.print("matrix V3t:");
		V3t.print(pwt);
		
		Matrix test=(U3.times(S3)).times(V3t);
		
		pwt.print("matrix U3*S3*V3t:");
		test.print(pwt);
		System.out.println("end of initial()");
		pwt.flush();
	}
	
	
	public void step3(){
		System.out.println("------step3------");
		try{
			pwt.println("------step3------");
			//V2t*A2 from P2
			Matrix p2Ret1=Matrix.read(br);
			pwt.print("V2t*A2 from P2");
			p2Ret1.print(pwt);

			//P3 sends E3[V2t*A2*U3*S3]
			Matrix mx=p2Ret1.times(U3).times(S3);
	        HomomorphicEncryption p3Snd1a=new HomomorphicEncryption(k, a, mx);
	        p3Snd1a.print(pw);
            pwt.print("V2t*A2*U3*S3");
            mx.print(pwt);
	        pwt.print("P3 sends E3[V2t*A2*U3*S3]");
	        p3Snd1a.print(pwt);

			//P3 sends E3[A3*U3*S3]
            Matrix A3U3S3 = A3.times(U3).times(S3);
			HomomorphicEncryption p3Snd1b=new HomomorphicEncryption(k, a, A3U3S3);
	        p3Snd1b.print(pw);
            pwt.print("A3*U3*S3");
            A3U3S3.print(pwt);
	        pwt.print("P3 sends E3[A3*U3*S3]");
			p3Snd1b.print(pwt);
			pwt.flush();

			//[E3(V2t*A2*U3*S3)^(U2*S2)]*[E3(A3*U3*S3)^(P2)]<=>E3[P2*(A2+A3)*U3*S3] from P2
			HomomorphicEncryption p2Ret2=HomomorphicEncryption.read(br, k, a);
	        pwt.print("[E3(V2t*A2*U3*S3)^(U2*S2)]*[E3(A3*U3*S3)^(P2)]<=>E3[P2*(A2+A3)*U3*S3] from P2");
	        p2Ret2.print(pwt);

            pwt.print("P2*(A2+A3)*U3*S3");
            (p2Ret2.getDecryptedMatrix()).print(pwt);
            
	        //P3 computes P2*(A2+A3)*U3*S3]*V3t=P2(A2+A3)P3
	        Matrix P2AP3=(p2Ret2.getDecryptedMatrix()).times(V3t);
	        pwt.print("P3 computes [P2*(A2+A3)*U3*S3]*V3t=P2(A2+A3)P3");
	        P2AP3.print(pwt);

	        //[E2(c2*P2*b2)] from P2
	        Matrix p2Ret3a=Matrix.read(br);
	        pwt.print("[E2(c2*P2*b2)] from P2");
	        p2Ret3a.print(pwt);
	        pwt.flush();

	        //[E2(c2*P2)] from P2
	        Matrix p2Ret3b=Matrix.read(br);
	        pwt.print("[E2(c2*P2)] from P2");
	        p2Ret3b.print(pwt);
	        
	        double c3=Math.random()+0.2;
            pwt.println("c3= " + c3);

	        //P3 sends [E2(c2*P2*b2)^(c3)][E2(c2*P2)^(b3*c3)] <=> E2[c2*P2(b2+b3)*c3]
	        Matrix p3Snd3=(p2Ret3a.homomorphicEncryptedTimes(c3)).arrayTimes(p2Ret3b.rhomomorphicEncryptedTimes(b3.times(c3)));
	        p3Snd3.print(pw);
            pwt.print("[E2(c2*P2*b2)^(c3)]");
            (p2Ret3a.homomorphicEncryptedTimes(c3)).print(pwt);
            pwt.print("[E2(c2*P2)^(b3*c3)]");
            p2Ret3b.rhomomorphicEncryptedTimes(b3.times(c3)).print(pwt);
	        pwt.print("P3 sends [E2(c2*P2*b2)^(c3)][E2(c2*P2)^(b3*c3)] <=> E2[c2*P2(b2+b3)*c3], c3 is a number");
	        p3Snd3.print(pwt);
	        
	        //P2(b2+b3)*c3 from P2
	        Matrix p2Ret4=Matrix.read(br);//P2(b2'+b3')*c3
	        pwt.print("P2(b2+b3)*c3 from P2");
	        p2Ret4.print(pwt);

	        //Below is added by Bon Sy Jan 10, 2010
	        //We want to remove P3 before performing SVD as it works better on cases where matrix is generated by V*V^T
	        
	        P2AP3 = P2AP3.times(P3.inverse());
	        pwt.println("Matrix P2AP3*P3^-1");
	        P2AP3.print(pwt);
	        //End adding code above

System.out.println("Before deriving P3 inverse");	                
	        SingularValueDecomposition svdxx=new SingularValueDecomposition(P2AP3);
System.out.println("After deriving P3 inverse");    
	        
	        Matrix Uxx=svdxx.getU();
    		Matrix Vxxt=(svdxx.getV()).transpose();
    		Matrix Sxx=svdxx.getS();
    		
    		Matrix inverseSxx = Sxx;
        	for (int i = 0; i < Sxx.getColumnDimension(); i++) {
        		//if (Sxx.get(i,i) != 0) inverseSxx.set(i,i, (1/Sxx.get(i,i)));
        		if (Sxx.get(i,i) > 0.00000000001) inverseSxx.set(i,i, (1/Sxx.get(i,i)));
        	}    		    	
        	
        	Matrix xx = (Vxxt.transpose().times(inverseSxx.times(Uxx.transpose()))).times(p2Ret4.times(1/c3));    		
    		pwt.print("xx=[inverse(P2AP3)]*P2*(b2+b3)");
	        xx.print(pwt);
	        x=xx; //De-commissioned after removing P3 before SVD: x=P3.times(xx);
	        
	        //P3 sends x
	        x.print(pw);
	        pwt.print("P3 sends x");
	        // pwt.print("P3 sends x=P3*xx");
	        x.print(pwt);
	        System.out.println("Sending x to client");
	        
	        //check if A3*x=b3	        
	        Matrix A3x=A3.times(x);
	        pwt.print("A3*x");
	        A3x.print(pwt);
	        pwt.print("b3");
	        b3.print(pwt);
            pwt.println("A3*x-b3=");
            (A3x.minus(b3)).print(pwt);
			   pwt.println("wait to read continueRun option from P2");
			   pwt.println();

//Added by Bon Sy at Jan 8 2010   
			//Server side checking the distance between de and x   
	 		Matrix temp=maxEigenVector.minus(x);
	  		pwt.print("maxEigenVector-x");
	  		temp.print(pwt);
	  		        
	  		double[][] tArr=temp.getArray();
	  		double dist=0;
	  		for(int i=0; i<tArr.length; i++){
	  			dist=dist+tArr[i][0]*tArr[i][0];
	  		}
	  		double sigma=Math.sqrt(dist);
	  		pwt.println("||Vde-x||="+sigma);
	  		pwt.println();

	  		double epsilon=51.5;
	  		pwt.println("Print epsilon=" + epsilon);
	  		pwt.println();  		      
	                
	  		pwt.println("Sending continueRun to SIPPA Client P2"); 
	  		pwt.println();
	  		int continueRun = 1;
	  		if(sigma > epsilon){
	  			continueRun = 0; 
	  		}
	  		System.out.println("Sending ContinueRun to client= "+ continueRun);
	  		pw.println(Integer.toString(continueRun));
//End coding added by Bon Sy at Jan 8 2010			   
			   
// czheng add code here
  		    //Below expecting the client side to send in the SIPPA option
			  //String line1 = br.readLine();  // need to take out for linux
			  String continue_buffer = br.readLine();
			  //System.out.println("First read= "+line1);
			  //Reading once more is necessary in Windows system
			  if (!(continue_buffer.equals("0") || continue_buffer.equals("1") ) ) continue_buffer = br.readLine();

			  System.out.println("Initial continue_buffer read from client= "+continue_buffer);
  		      //"0" uses only helper data De^T*x and eigenvalue
  		      //"1" uses the vector    
  		       // Above two lines are replaced by below added by Bon Sy    
  		      if (continueRun == 0) {  		       
  		    	  continue_buffer = "0";
  		      }
  		      
			  if (continueRun == 1) {
  		    	   continue_buffer="1"+continue_buffer;
  		    	   // "10" uses helper data. "11" uses the vector
  		       } 

  		       // End adding code by Bon Sy
  		       
			   pwt.println("continueRun= "+continue_buffer);
			   //pwt.flush();
			   //option_buffer != null
			   if (continue_buffer.equals("0") ) {
				   pwt.println("The server informs rejection because continue_buffer= "+ continue_buffer);
				   pw.println(continue_buffer);
			   }
			   
			   if (continue_buffer.equals("10") ) {
				   // need to send HelperData: eigenvalue of De*De^, 1/detx =1/De^T*x
				   pwt.println("The server informs continuation to run with continue_buffer= "+ continue_buffer);
				   pw.println(continue_buffer);
				   
				   pw.print(Double.toString(maxEigenValue));
				   
				   Matrix detx=de.transpose().times(x);
			       detx=detx.inverse();
			       pwt.print("Sending detx.inverse() to P2");
			       detx.print(pw);
			   }
			   
			   if (continue_buffer.equals("11") ) {
				   // need to send b3, 1/detx =1/De^T*x
				   pwt.println("The server informs continuation to run with continue_buffer= "+ continue_buffer);
				   pw.println(continue_buffer);
				   
				   b3.print(pw);
				   Matrix detx=de.transpose().times(x);
			       detx=detx.inverse();
			       pwt.print("Sending detx.inverse() to P2");
			       detx.print(pw);	        
			   }
			   
			   pwt.flush();
//czheng add code above	        
			
		} catch(IOException e) {e.printStackTrace();}
	}//method step3()
	

	private void saveFile(File fileName){
		try {
			pwf = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(fileName.getAbsolutePath()))));
			pwf.println("X");
	        x.print(pwf);
	        pwf.flush();
	        pwf.close();
	    }catch ( IOException ioe ) {throw new RuntimeException(ioe);}
	}//method saveFile(File fileName)
	

	private static void printUsage()
	{
		System.out.println("SIPPA_PPR_Server");
		System.out.println("Returns 0 if successful, 1 otherwise");
		System.out.println("Usage: java -jar SIPPA_PPR_Server.jar <server service port> <userDefinedRowSize> <userDefinedColumnSize>");
		System.out.println("<userDefinedRowSize> and <userDefinedColumnSize> are ignored if they are -1, and are used only if they are positive integer matching the matrix size");
		System.out.println("SIPPA_PPR_Server expects this two parameters to be passed on from the client:");
		System.out.println("     <Transaction ID> <option>");
		System.out.println("EW filename is in the format: EW-{Transaction ID}.txt ");
		System.out.println("<Transaction ID> is in the format: <ref>-<timestamp as unique_identifier> where the corresponding signature file is <ref>.signature ");
		System.out.println("option: 0 using pre-defined A3 matrix, 1 for generating random A3 at run-time");
		System.out.println("Success returns errorlevel 0. Error return greater than zero.");
		System.exit(0);
	}
	
	public void socket_listener () {
		while (true) {
			try {		
					String line = br.readLine();
					System.out.println("Waiting for Initiate Service Request ...  "+line);
					String transactID_buffer = br.readLine();								
					System.out.println("transaction ID= "+ transactID_buffer);
					String option_buffer = br.readLine();		
					System.out.println("Option= "+option_buffer);
				
					if ((transactID_buffer != null) && (option_buffer != null) ) {
						initialize(transactID_buffer, Integer.parseInt(option_buffer));			
						step3();
											
						//line = br.readLine();	
						//System.out.println("Print signaling message for done or not done "+line);
			
						//if (line.equals("Done")) {	
							socket_stream_buffer_close();
							initialize_socket_stream_buffer();
						//}
				}				
				
			} catch ( IOException ioe )	{System.out.println("Error thrown in socket_listener.");
										 }
//			} catch ( IOException ioe ) {throw new RuntimeException(ioe);}
		}		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if (args.length != 1) {
			printUsage();
		}

        /* Print a copyright. */
        System.out.println("PPR Client requester utility");
        System.out.println("Copyright: Bon Sy");
        System.out.println("Free to use this at your own risk!");
                
		SIPPA_PPR_Server ppp=new SIPPA_PPR_Server(Integer.parseInt(args[0]));
		//Below are moved to the constructor
		//ppp.socket_listener();
		//ppp.socket_stream_buffer_close();	
	}

}
