package com.neurotechnology.NLicensing;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class LicensingConfig {

	private static final String configFileName = "NLicenses.cfg";
	private String licenseServer;
	private String licensePort;
	private HashMap<String, String> configEntries = new HashMap<String, String>();
	private static LicensingConfig licensingConfig = null;
	private InputStream cfgFile;

	private LicensingConfig() {
	}

	private void parseConfig() {
		Scanner scanner = null;
		try {
			scanner = new Scanner(cfgFile);

			while (scanner.hasNextLine()) {
				try {
					processLine(scanner.nextLine());
				} catch (Exception ex) {
				}
			}
		} catch (Exception ex) {
		} finally {
			//ensure the underlying stream is always closed
			scanner.close();
		}
	}

	private void processLine(String configLine) {
		Scanner scanner = new Scanner(configLine);
		scanner.useDelimiter("=");
		if (scanner.hasNext()) {
			String name = scanner.next().trim();
			String value = scanner.next().trim();
			if (name.equals("Address")) {
				licenseServer = value;
			} else if (name.equals("Port")) {
				licensePort = value;
			} else if (name != null && value != null) {
				configEntries.put(name, value);
			}
		}
		scanner.close();
	}
	
	public static LicensingConfig getInstance() {
		return getInstance(null);
	}

	public static LicensingConfig getInstance(InputStream cfgfile){
		if (licensingConfig == null) {
			licensingConfig = new LicensingConfig();
			try{
				if(cfgfile == null) cfgfile = new FileInputStream(new File(configFileName));
			}catch (Exception e) {
				throw new NullPointerException("InputStream for Licensese.cfg is null");
			}
			licensingConfig.cfgFile = cfgfile;
			licensingConfig.parseConfig();
		}
		return licensingConfig;
	}

	public String getLicenseString(String licName) {
		return configEntries.get(licName);
	}

	public String getLicenseString(String... licNames) {
		HashSet<String> licStrings = new HashSet<String>();
		for (String licName : licNames) {
			licStrings.add(getLicenseString(licName));
		}

		String result = "";
		for (String licenseString : licStrings) {
			if (licenseString.length() == 0) {
				continue;
			}
			if (result.length() > 0) {
				result += ",";
			}
			result += licenseString;
		}

		return result;
	}

	public String[] getLicenseStrings() {
		HashSet<String> licStrings = new HashSet<String>();
		for (String license : configEntries.values()) {
			if (license.length() > 0) {
				String[] parts = license.split(",");
				for (String part : parts) {
					licStrings.add(part);
				}
			}
		}
		return licStrings.toArray(new String[0]);
	}

	public String getServerIP() {
		return licenseServer;
	}

	public String getServerPort() {
		return licensePort;
	}
}
