package com.neurotechnology.NFEngine;

import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

public class NFEngineImage {
	
	private byte [] imageData;
	private int width;
	private int height;
	private int stride;
	private float horizontalResolution;
	private float verticalResolution;
	
	public void setImageData(byte [] imageData) {
		this.imageData = imageData;
	}
	public byte [] getImageData() {
		return imageData;
	}
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
	public void setStride(int stride) {
		this.stride = stride;
	}
	public int getStride() {
		return stride;
	}
	public void setHorizontalResolution(float horizontalResolution) {
		this.horizontalResolution = horizontalResolution;
	}
	public float getHorizontalResolution() {
		return horizontalResolution;
	}
	public void setVerticalResolution(float verticalResolution) {
		this.verticalResolution = verticalResolution;
	}
	public float getVerticalResolution() {
		return verticalResolution;
	}
	
	public BufferedImage getBufferedImage(){
		BufferedImage bimage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

		int [] pixels = new int[width * height];
			for (int i = 0; i < height; i++)
				for (int j = 0; j < width; j++){
					int p = (i * stride + j * 3); 
					pixels[i * width + j] = imageData[p + 2] + (imageData[p + 1] << 8) + (imageData[p] << 16) + (255 << 24);
				}
		bimage.setRGB(0, 0, width, height, pixels, 0, width);
		
		return bimage;
	}
	
	public ImageIcon getImageIcon(){
		return new ImageIcon(getBufferedImage());
	}
}
