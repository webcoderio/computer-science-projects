package com.neurotechnology.NLicensing;

public class NLicenseInfo {
	private boolean obtained;
	private int distributorId;
	private int serialNumber;
	
	void setSerialNumber(int serialNumber) {
		this.serialNumber = serialNumber;
	}
	public int getSerialNumber() {
		return serialNumber;
	}
	void setDistributorId(int distributorId) {
		this.distributorId = distributorId;
	}
	public int getDistributorId() {
		return distributorId;
	}
	void setObtained(boolean obtained) {
		this.obtained = obtained;
	}
	public boolean isObtained() {
		return obtained;
	}
	
	
}
