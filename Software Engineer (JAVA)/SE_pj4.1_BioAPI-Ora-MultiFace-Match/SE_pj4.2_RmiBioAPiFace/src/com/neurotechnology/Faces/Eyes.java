package com.neurotechnology.Faces;

import java.awt.Point;

public class Eyes {
	private Point first;
	private Point second;
	private double firstConfidence;
	private double secondConfidence;
	
	protected void setFirst(int x, int y){
		first = new Point(x, y);
	}
	
	protected void setSecond(int x, int y){
		second = new Point(x, y);
	}

	public void setFirstConfidence(double firstConfidence) {
		this.firstConfidence = firstConfidence;
	}

	public double getFirstConfidence() {
		return firstConfidence;
	}

	public void setSecondConfidence(double secondConfidence) {
		this.secondConfidence = secondConfidence;
	}

	public double getSecondConfidence() {
		return secondConfidence;
	}

	public void setFirst(Point first) {
		this.first = first;
	}

	public Point getFirst() {
		return first;
	}

	public void setSecond(Point second) {
		this.second = second;
	}

	public Point getSecond() {
		return second;
	}
}
