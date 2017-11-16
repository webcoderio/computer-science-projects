package com.neurotechnology.Templates;

public class NERecordInfo {
	private int MajorVersion; ///< Major version
	private int MinorVersion; ///< Minor version
	private int Size;         ///< Size
	private int HeaderSize;   ///< Header size
	
	public void setMajorVersion(int majorVersion) {
		MajorVersion = majorVersion;
	}
	public int getMajorVersion() {
		return MajorVersion;
	}
	public void setMinorVersion(int minorVersion) {
		MinorVersion = minorVersion;
	}
	public int getMinorVersion() {
		return MinorVersion;
	}
	public void setSize(int size) {
		Size = size;
	}
	public int getSize() {
		return Size;
	}
	public void setHeaderSize(int headerSize) {
		HeaderSize = headerSize;
	}
	public int getHeaderSize() {
		return HeaderSize;
	}
	
	protected void setValues(int MajorVersion, int MinorVersion, int Size, int HeaderSize){
		this.HeaderSize = HeaderSize;
		this.MajorVersion = MajorVersion;
		this.MinorVersion = MinorVersion;
		this.Size = Size;
	}
}
