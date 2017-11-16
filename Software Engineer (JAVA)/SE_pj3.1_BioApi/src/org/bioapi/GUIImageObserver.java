package org.bioapi;

import java.io.InputStream;

/**
 * <p>
 * Represents the callback that a biometric application supplies to allow a BSP to stream data for display,
 * in the form of a sequence of bitmaps, to the application through the Framework.
 * </p>
 * 
 * @author	Ashwin Mohan
 */
public interface GUIImageObserver
{
	/**
	 * Represents the graphic for display by an application.
	 * 
	 *@status	Implemented but untested.
	 */
	public static interface Bitmap
	{
		/**
		 * @return	the height of the graphic in pixels.
		 * 
		 * @status	Implemented but untested.
		 */
		public int getHeight();

		/**
		 *@return	a series of 8-bit grayscale pixels (where 00=black and FF=white), read from left to right, top to bottom).
		 * 
		 * @status	Implemented but untested.
		 */
		public byte[] getPixels();

		/**
		 * @return	a series of 8-bit grayscale pixels (where 00=black and FF=white), read from left to right, top to bottom).
		 * 
		 * @status	Implemented but untested.
		 */
		public InputStream getPixelsAsStream();

		/**
		 * @return	the width of the graphic in pixels.
		 * 
		 * @status	Implemented but untested.
		 */
		public int getWidth();
	}

	/**
	 * A callback function that a biometric application supplies to allow a BSP to stream data for display,
	 * in the form of a sequence of bitmaps, to the application through the Framework.
	 * 
	 * @param	image the bitmap to be displayed.
	 * 
	 * @exception BioAPIException	in the case the application has encountered error while displaying the bitmap.
	 */
	public void imageUpdate(Bitmap image) throws BioAPIException;
}