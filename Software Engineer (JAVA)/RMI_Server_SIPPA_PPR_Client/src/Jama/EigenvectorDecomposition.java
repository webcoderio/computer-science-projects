package Jama;

import Jama.EigenvalueDecomposition;
/** Eigenvalues and eigenvectors of a real matrix. 
<P>
    If A is symmetric, then A = V*D*V' where the eigenvalue matrix D is
    diagonal and the eigenvector matrix V is orthogonal.
    I.e. A = V.times(D.times(V.transpose())) and 
    V.times(V.transpose()) equals the identity matrix.
<P>
    If A is not symmetric, then the eigenvalue matrix D is block diagonal
    with the real eigenvalues in 1-by-1 blocks and any complex eigenvalues,
    lambda + i*mu, in 2-by-2 blocks, [lambda, mu; -mu, lambda].  The
    columns of V represent the eigenvectors in the sense that A*V = V*D,
    i.e. A.times(V) equals V.times(D).  The matrix V may be badly
    conditioned, or even singular, so the validity of the equation
    A = V*D*inverse(V) depends upon V.cond().
**/

public class EigenvectorDecomposition implements java.io.Serializable {

/* ------------------------
   Class variables
 * ------------------------ */
	/**
	   * Determines if a de-serialized file is compatible with this class.
	   */
	private static final long serialVersionUID = 7526471155622776147L;

	
   /** Row and column dimension (square matrix).
   @serial matrix dimension.
   */
   private int size;
   
   /** input matrix (square matrix).
   @serial input matrix.
   */
   private Matrix m;
   
   /** Eigenvalue (square matrix).
   @serial maximum Eigenvalue of input Matrix.
   */
   private double maxEigenvalue;

/* ------------------------
   Constructor
 * ------------------------ */

   /** Check for symmetry, then construct the eigenvalue decomposition
   @param A    Square matrix
   @return     Structure to access m, n and maxEigenvalue.
   */
   public EigenvectorDecomposition (Matrix Arg) {
      m=Arg;
      size=Arg.getColumnDimension();
      EigenvalueDecomposition E = new EigenvalueDecomposition(Arg);
      double[] ev = E.getRealEigenvalues();
      maxEigenvalue=ev[0]; 
      for(int m=0; m<ev.length; m ++){
    	  if(ev[m]>maxEigenvalue) 
    		  maxEigenvalue=ev[m];
    	  //System.out.print(ev[m]+",");
      }
   }

/* ------------------------
   Public Methods
 * ------------------------ */

   /** Return the eigenvector matrix
   @return     V
   */

   private Matrix getSubMatrix(){
	   //int size=m.getColumnDimension();
	   
	   double[][] sub=new double[size-1][size-1];
	   double[][] mArray=m.getArray();

	   for(int i=0; i<size-1; i++){
		for(int j=0; j<size-1; j++){
		    if(i==j) mArray[i][j]=mArray[i][j]-maxEigenvalue; 	
                }
	   }

	   for(int i=0; i<size-1; i++){
		for(int j=0; j<size-1; j++){
		    sub[i][j]=mArray[i][j]; 	
                }
	   }
	   Matrix subMatrix=new Matrix(sub);
	   //System.out.println("Matrix subMatrix is -----------");		   
	   //subMatrix.print(15,5);
	   return subMatrix;	
}
   private Matrix getSubVector(){
	//int size=m.getColumnDimension();
	double[][] mArray=m.getArray();
	double[][] sub1=new double[size-1][1];
	for(int i=0; i<size-1; i++){
	   sub1[i][0]=(-1)* mArray[i][size-1];
	}
	return new Matrix(sub1);
}

	public Matrix getEigenVector(double arbVal){
		Matrix subMatrix=getSubMatrix();
		Matrix subVector=getSubVector();
		//System.out.println("Matrix subM, subV are -----------");
		//subMatrix.print(15,5);
		//subVector.print(15,5);
		SingularValueDecomposition svd=new SingularValueDecomposition(subMatrix);
		Matrix s=svd.getS();
		Matrix u=svd.getU();
	    Matrix v=svd.getV().transpose();
		//System.out.println("Matrix s, u, v, v.transpose() are -----------");	
		//s.print(15,5);
		//u.print(15,5);
		//v.print(15,5);
		Matrix subResult = v.times(s.inverse()).times(u.transpose()).times(subVector);
		//System.out.println("print Matrix subResult -----------");	
		//subResult.print(15,5);
		double[][] array=new double[size][1];
		double[][] subResultArray=subResult.getArray();
		for(int i=0; i<size-1; i++){
			array[i][0]=subResultArray[i][0];
		}
		array[size-1][0]=arbVal;
		

		Matrix result=new Matrix(array);
		//System.out.println("print Matrix getEigenVector(arbVal) -----------");	
		//result.print(15,5);		
		double nfactor =0;
		double[][] narray=result.getArray();
		for(int i=0; i<result.getRowDimension(); i++){
			nfactor=nfactor+narray[i][0]*narray[i][0];	
			//System.out.println("i="+i+" nfactor="+nfactor);
		}
		nfactor=Math.sqrt(nfactor);
		//System.out.println("final nfactor="+nfactor);
		for(int i=0; i<result.getRowDimension(); i++)
			narray[i][0]=narray[i][0]/nfactor;
		return new Matrix(narray);
	}
	
	public static void main (String argv[]) {
		//double[][] vals = {{1,2,4},{9,7,5},{8,4,3}};
		double[][] vals = {{1,5,7},{5,25,35},{7,35,49}};
		Matrix m = new Matrix(vals);

		EigenvectorDecomposition E = new EigenvectorDecomposition(m);
		Matrix ev=E.getEigenVector(1.0);
		System.out.println("$$$$$$print normalized eigenVecoter is -----------");	
		ev.print(15,5);
	}
	
}
