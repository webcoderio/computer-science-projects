package com.neurotechnology.CameraMan;

public class VideoFormat {
	private int width;
	private int height;
	private float FPS;
	
	public void setWidth(int width) {
		this.width = width;
	}
	public int getWidth() {
		return width;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getHeight() {
		return height;
	}
	public void setFPS(float fPS) {
		FPS = fPS;
	}
	public float getFPS() {
		return FPS;
	}
}
