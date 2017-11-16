package com.neurotechnology.Faces;

import java.awt.Point;

public class Face {
	
	private Point upperLeftPoint;
	private int width;
	private int height;
	private double confidence;
	private Rotation rotation; 
	
	protected void setWidth(int width) {
		this.width = width;
	}
	
	public int getWidth() {
		return width;
	}
	
	protected void setHeight(int height) {
		this.height = height;
	}
	
	public int getHeight() {
		return height;
	}
	
	protected void setUperLeftPoint(Point upperLeftPoint) {
		this.upperLeftPoint = upperLeftPoint;
	}
	
	public Point getUpperLeftPoint() {
		return upperLeftPoint;
	}
	
	protected void setRotation(int yaw, int pitch, int roll){
		this.rotation = new Rotation(yaw, pitch, roll);
	}
	
	protected void setULPoint(int x, int y){
		this.upperLeftPoint = new Point(x, y);
	}
	
	protected void setConfidence(double confidence) {
		this.confidence = confidence;
	}
	
	public double getConfidence() {
		return confidence;
	}

	public void setRotation(Rotation rotation) {
		this.rotation = rotation;
	}

	public Rotation getRotation() {
		return rotation;
	}

}
