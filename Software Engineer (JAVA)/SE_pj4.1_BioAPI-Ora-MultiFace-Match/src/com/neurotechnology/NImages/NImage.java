package com.neurotechnology.NImages;

import javax.swing.*;

import com.neurotechnology.Library.NativeManager;
import com.neurotechnology.Library.NativeObject;

import java.awt.Toolkit;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.MemoryImageSource;
import java.awt.*;


public class NImage extends NativeObject{
	
	//private long hImage;
	//protected static int count;
	
	private byte [] image;
	private int size;
	private int height;
	private int width;
	private int stride;
	private PixelFormat pixelFormat;
	
	protected NImage(long hImage){
		this.setHandle(hImage);
	}
	
	//for internal use only
	public static NImage getEmptyImage(){
		return new NImage(0);
	}
	
	public NImage(byte[] data, PixelFormat pixelFormat,int width, int heihgt, int stride,
			float horizontalResolution, float verticalResolution)throws Exception{
		this.setHandle(imageFromData(data, pixelFormat.eval(), width, heihgt, stride, horizontalResolution, verticalResolution));
	}
	
	public NImage(byte[] data, ImageFormat format)throws Exception{
		switch (format) {
			case bmp :
				this.setHandle(nimageFromBMP(data, data.length));
				break; 
			case jpeg : 
				this.setHandle(nimageFromJPEG(data, data.length));
				break;
			case tiff :
				this.setHandle(nimageFromTIFF(data, data.length));
				break;
		}
	}
	
	public void finalize()throws Throwable{
		//ImageFree(this.getHandle());
		super.finalize();
	}
	
	public float getHorizontalResoliution()throws Exception{
		return getHorizontalResoliution(this.getHandle());
	}
	
	public float getVerticalResoliution() throws Exception{
		return getVerticalResoliution(this.getHandle());
	}
	
	public void setResolution(float horizontal, float vertical)throws Exception{
		this.setHandle(setResoliutions(this.getHandle(), horizontal, vertical));
	}
	
	public ImageIcon getImageIcon() throws Exception{
		return new ImageIcon(getBufferedImage());
	}
	
	public BufferedImage getBufferedImage() throws Exception{
		LoadImage(this.getHandle(), this);
		BufferedImage bimage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

		switch (pixelFormat.eval()){
			case 0x00303003 : {
				int [] pixels = new int[width * height];
				for (int i = 0; i < height; i++)
					for (int j = 0; j < width; j++){
						int p = (i * stride + j * 3); 
						pixels[i * width + j] = image[p + 2] + (image[p + 1] << 8) + (image[p] << 16) + (255 << 24);
					}
				bimage.setRGB(0, 0, width, height, pixels, 0, width);
				break;
			}
			case 0x00301001 : {
				int [] pixels = new int[size];
				for (int i = 0; i < size; i++){
					int pp = image[i] & 0xFF;
					pixels[i] = pp + (pp << 8) + (pp << 16) + (255 << 24); 		
				}
				bimage.setRGB(0, 0, width, height, pixels, 0, stride);
				break;
			}
			case 0 : {
				throw new Exception("Invalid pixel format");
			}
		}
		image = null;
		return bimage;
	}
	
	public ImageIcon makeGCImageIcon(Color minColor, Color maxColor) throws Exception{	
		return new ImageIcon(makeGCImage(minColor, maxColor));
	}
	
	public Image makeGCImage(Color minColor,Color maxColor) throws Exception{	
		LoadImage(this.getHandle(), this);
		int [] pixels = new int[size];
		for (int i = 0; i < size; i++){
			if (image[i] < 0) 
				pixels[i] = minColor.getRGB();
			else 
				pixels[i] = maxColor.getRGB(); 									
		}
		image = null;
		return Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(width, height, pixels, 0, stride));
	}
	
	public void saveToFile(String fname)throws Exception{
		SaveToFile(this.getHandle(),fname);
	}
	public static NImage loadFromFile(String fname)throws Exception{
		return new NImage(LoadFromFile(fname));
	}
	public NImage clone(){
		return new NImage(cloneImage(this.getHandle()));
	}
 
	public static String[] getFileFormats(){
		int count = getFileFormatCount();
		String[] ff = new String[count];
		for (int i = 0; i < count; i++)
			ff[i] = getFileFormat(i);
		return ff;
	}
	public void convertToGreyscale()throws Exception{
		this.setHandle(toGCImage(this.getHandle()));
	}

	private static native void LoadImage(long hImage,NImage jNImage) throws Exception;
	private static native void ImageFree(long hImage);
	private static native void SaveToFile(long hImage, String fname)throws Exception;
	private static native long LoadFromFile(String fname)throws Exception;
	private static native long cloneImage(long hImage);
	private static native float getVerticalResoliution(long hImage);
	private static native float getHorizontalResoliution(long hImage);
	private static native long setResoliutions(long hImage, float horizontal, float vertical);
	private static native int getFileFormatCount();
	private static native String getFileFormat(int index);
	private static native long toGCImage(long hIname) throws Exception;
	private static native long imageFromData(byte[] data, int pixelFormat,int width, int heihgt, int stride,
			float horizontalResolution, float verticalResolution) throws Exception;
	private static native long nimageFromJPEG(byte[] data, int size);
	private static native long nimageFromBMP(byte[] data, int size);
	private static native long nimageFromTIFF(byte[] data, int size);
	
	
	protected void setImage(byte [] image) {
		this.image = image;
	}
    public byte [] getImage()  throws Exception{
		LoadImage(this.getHandle(),this);
		return image;
	}
    protected void setSize(int size) {
		this.size = size;
	}
	public int getSize() {
		return size;
	}
	protected void setHeight(int height) {
		this.height = height;
	}
	public int getHeight() {
		return height;
	}
	protected void setWidth(int width) {
		this.width = width;
	}
	public int getWidth() {
		return width;
	}
	protected void setStride(int stride) {
		this.stride = stride;
	}
	public int getStride() {
		return stride;
	}
	protected void setPixelFormat(int value){
		this.pixelFormat = PixelFormat.getVal(value);
	}
	
	//Loading Library
	static {
    	NativeManager.loadDefault();
    	NativeManager.checkLoad("NImages");
	}
}
