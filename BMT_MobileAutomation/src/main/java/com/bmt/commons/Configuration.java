package com.bmt.commons;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Configuration {

	public String appDirectory;
	public String url;
	public String appname;
	public String platformVersion;
	public String deviceID;
	public String deviceName;
	public String screenShotFolder;
	public String platformName;

	public Configuration() {

		final Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(new File("config.properties")));
		} catch (IOException e) {
			e.printStackTrace();
		}

		appDirectory = prop.getProperty("getAppDirectory");
		appname = prop.getProperty("getAppname");
		platformVersion = prop.getProperty("getPlatformVersion");
		deviceID = prop.getProperty("getDeviceID");
		deviceName = prop.getProperty("getDeviceName");
		url = prop.getProperty("url");
		screenShotFolder = prop.getProperty("screenShotFolder");
		platformName =prop.getProperty("platform");
		
	}


}