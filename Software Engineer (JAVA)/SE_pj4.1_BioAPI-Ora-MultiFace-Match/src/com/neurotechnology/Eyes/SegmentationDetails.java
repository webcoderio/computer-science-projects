package com.neurotechnology.Eyes;

import java.awt.Point;

public class SegmentationDetails {
	private boolean isAvailable;
	private Point [] points;
	
	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
	public boolean isAvailable() {
		return isAvailable;
	}
	public void setPoints(Point [] points) {
		this.points = points;
	}
	public Point [] getPoints() {
		return points;
	}
}
