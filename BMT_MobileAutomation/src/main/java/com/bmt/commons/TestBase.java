package com.bmt.commons;

import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;



public class TestBase {
	protected Library  lib;
	Properties usersProperties = null;
	private String suite;
	protected String testName;
	ITestContext testContext;

	@BeforeClass(alwaysRun = true)
	public void aaasetUp(ITestContext context) throws Exception {

		suite = context.getCurrentXmlTest().getSuite().getName();
		suite = ((suite != null && !(suite.equals("Default suite"))) ? suite
				: InetAddress.getLocalHost().getHostName());
		suite = (suite.contains("Neo") ? "TestLocal" : suite);
		testName = this.getClass().getSimpleName();
		testName = ((testName != null && !(testName.equals("Default test"))) ? testName
				: this.getClass().getSimpleName());

		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("MMMddhhmm");
		String currentDate = sdf.format(date);

		if (System.getProperty("Build") == null) {
			System.setProperty("Build", suite + "_" + currentDate);
			System.setProperty("Suite", suite);
		}
		System.setProperty("Test", testName);
		System.out.println("TestName: " + testName);
		testContext = context;
	}
	
	
	@AfterMethod
	public void checkAlerts(ITestResult result) throws Exception {
		String screenshotName = result.getName() + "_" + lib.getPlatformVersion + "_" + Library.randIntDigits(1, 9999999)
				+ ".png";

		if (result.getStatus() == ITestResult.FAILURE) {
			try {
				lib.saveScreenshot();
				Reporter.log("Failed at URL: " + lib.getCurrentDriverInstance().getCurrentUrl(), true);
				int paramsLength = result.getParameters().length;
				Reporter.log("Screenshot Name : " + screenshotName, true);
				Reporter.log("ScreenShot for " + testName + " "
						+ ((paramsLength > 0) ? " with parameter " + result.getParameters()[1] : "") + " saved as "
						+ screenshotName + ".png", true);

			} catch (Exception e) {
				Reporter.log("Failed fetching URL and screenshot due to error:" + e.getMessage(), true);
				e.printStackTrace();
			}

			if (lib.getCurrentSessionID() != null) {
				Reporter.log("Session id for " + testName + " is " + lib.getCurrentSessionID(), true);
				Reporter.log("Session details for " + testName
						+ " can be found at https://www.browserstack.com/automate/sessions/"
						+ lib.getCurrentSessionID() + ".json", true);
			}
		}
	}
}
