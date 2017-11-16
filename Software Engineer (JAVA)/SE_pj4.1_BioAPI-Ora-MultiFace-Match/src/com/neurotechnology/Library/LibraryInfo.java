package com.neurotechnology.Library;

public class LibraryInfo {

	public static native LibraryInfo get(String libraryName) throws Exception;

	private String company;
	private String copyright;
	private String product;
	private String title;
	private int versionBuild;
	private int versionMajor;
	private int versionMinor;
	private int versionRevision;
	private String activated;

	public LibraryInfo() {
	}

	public LibraryInfo(String title, String product, String company, String copyright, String activated,
			int versionMajor, int versionMinor, int versionBuild, int versionRevision) {
		this.title = title;
		this.product = product;
		this.company = company;
		this.copyright = copyright;
		this.activated = activated;
		this.versionMajor = versionMajor;
		this.versionMinor = versionMinor;
		this.versionBuild = versionBuild;
		this.versionRevision = versionRevision;
	}
	
	void setCompany(String company) {
		this.company = company;
	}
	
	public String getCompany() {
		return company;
	}
	
	void setCopyright(String copyright) {
		this.copyright = copyright;
	}
	
	public String getCopyright() {
		return copyright;
	}
	
	void setProduct(String product) {
		this.product = product;
	}
	
	public String getProduct() {
		return product;
	}
	
	void setTitle(String title) {
		this.title = title;
	}
	
	public String getTitle() {
		return title;
	}
	
	void setVersionBuild(int versionBuild) {
		this.versionBuild = versionBuild;
	}
	
	public int getVersionBuild() {
		return versionBuild;
	}
	
	void setVersionMajor(int versionMajor) {
		this.versionMajor = versionMajor;
	}
	
	public int getVersionMajor() {
		return versionMajor;
	}
	
	void setVersionMinor(int versionMinor) {
		this.versionMinor = versionMinor;
	}
	public int getVersionMinor() {
		return versionMinor;
	}
	void setVersionRevision(int versionRevision) {
		this.versionRevision = versionRevision;
	}
	public int getVersionRevision() {
		return versionRevision;
	}

	void setActivated(String activated) {
		this.activated = activated;
	}

	public String getActivated() {
		if (activated != null) {
			return activated;
		} else {
			return "";
		}
	}
}
