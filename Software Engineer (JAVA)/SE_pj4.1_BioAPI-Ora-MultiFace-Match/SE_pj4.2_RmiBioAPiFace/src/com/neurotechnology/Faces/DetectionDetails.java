package com.neurotechnology.Faces;


public class DetectionDetails {
	
	private boolean eyesAvailable;
	private boolean faceAvailable;
	private Face face;
	private Eyes eyes;
	//protected static int count;
	
	public DetectionDetails(){
		//count++;
	}
	
	public void setEyesAvailable(boolean eyesAvailable) {
		this.eyesAvailable = eyesAvailable;
	}
	
	public boolean isEyesAvailable() {
		return eyesAvailable;
	}
	
	public void setFaceAvailable(boolean faceAvailable) {
		this.faceAvailable = faceAvailable;
	}
	
	public boolean isFaceAvailable() {
		return faceAvailable;
	}
	
	public void setFace(Face face) {
		this.face = face;
	}
	
	public Face getFace() {
		return face;
	}
	
	public void setEyes(Eyes eyes) {
		this.eyes = eyes;
	}
	
	public Eyes getEyes() {
		return eyes;
	}
	
	protected void setFace(int width, int height, int x, int y, double confidence){
		face = new Face();
		face.setConfidence(confidence);
		face.setHeight(height);
		face.setWidth(width);
		face.setULPoint(x, y);
	}
	
	protected void setEyes(double firstConfidence, double secondConfidence, int x1, int y1, int x2, int y2){
		eyes = new Eyes();
		eyes.setFirst(x1, y1);
		eyes.setSecond(x2, y2);
		eyes.setFirstConfidence(firstConfidence);
		eyes.setSecondConfidence(secondConfidence);
	}
	
	protected void setRotation(int yaw, int pitch, int roll){
		face.setRotation(new Rotation(yaw, pitch, roll));
	}
	
	
}
