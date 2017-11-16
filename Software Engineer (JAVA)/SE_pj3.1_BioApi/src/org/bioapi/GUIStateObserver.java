package org.bioapi;

/**
 * <p>
 * Represents a callback that an application supplies to allow the biometric service provider to pass GUI
 * state information to the application through the Framework, and to receive reponses back.
 * </p>
 * 
 * @author	Ashwin Mohan
 */
public interface GUIStateObserver
{
	/**
	 * <p>
	 * Represents the response from the application to the GUI state change event.
	 * </p>
	 * 
	 * @status	Implemented but untested.
	 */
	public enum Response
	{
		/** User cancelled operation. */
		CANCEL,
		/** Instruction to BSP to capture sample. */
		CAPTURE_SAMPLE,
		/** User or application selects to proceed. */
		CONTINUE,
		/** Invalid sample received. */
		INVALID_SAMPLE,
		/** Valid sample received. */
		VALID_SAMPLE
	}

	/**
	 * <p>
	 * Represents state information that is passed by the BSP to the application.
	 * </p>
	 * 
	 * @status	Implemented but untested.
	 */
	public interface State
	{
		/**
		 * @return	true if the BSP has provided the message the application should display to the user.
		 */
		public boolean hasMessage();

		/**
		 * @return	true if the BSP has provided the progress to display.
		 */
		public boolean hasProgress();

		/**
		 * @return	true if the BSP has provided graphic representation of the sample captured for display.
		 */
		public boolean hasSample();

		/**
		 * @return	the String containing the message for display or null if no message is available.
		 */
		public String getMessage();

		/**
		 * @return	a value that indicates (as a percentage) the amount of progress in the development of a Sample/BIR. The value may be used to display a progress bar. If the value is not available, -1 is returned.
		 */
		public int getProgress();

		/**
		 * @return	The graphic representation of the current sample for the application to display. if the sample is not available, null is returned.
		 */
		public GUIImageObserver.Bitmap getSample();
	}

	/**
	 * <p>
	 * A callback function that an application supplies to allow the biometric service provider to pass GUI
	 * state information to the application through the Framework, and to receive reponses back.
	 * </p>
	 * 
	 * @param	newState	specifies state update information that is passed by the BSP to the application.
	 * 
	 * @return	response from the application back to the biometric service provider.
	 * 
	 * @exception	BioAPIException	in the case the application has encountered error while processing the GUI state update.
	 */
	public Response stateUpdate(State newState) throws BioAPIException;
}