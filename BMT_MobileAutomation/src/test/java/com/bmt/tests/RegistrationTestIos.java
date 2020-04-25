package com.bmt.tests;

import java.net.MalformedURLException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.bmt.commons.Library;

public class RegistrationTestIos {

	public Library lib;

	@BeforeClass
	public void setup() throws MalformedURLException {
		lib = new Library("Test Started");
		lib.launchDriver();
	}

	@Test
	public void registrationTest() throws Exception {

		lib.saveScreenshot();
 
		lib.clickMobileElement("xpath://XCUIElementTypeButton[contains(@name,'Allow')]");

//		lib.clickMobileElement("xpath://XCUIElementTypeButton[@name='Allow']");

		lib.clickMobileElement("xpath://XCUIElementTypeButton[@name='Sign Up']");

		lib.sleep(2000);

		lib.enterMobileText("xpath://XCUIElementTypeTextField[contains(@value,'Mobile / Email')]",
				"neo" + lib.getFDate() + "@mailinator.com");

		lib.sendKeys("xpath://XCUIElementTypeSecureTextField[@value='Password']", "test123");

		lib.findMobileElement("xpath://XCUIElementTypeButton[@name='Return']").click();

		lib.sleep(500);

		lib.enterMobileText("xpath://XCUIElementTypeSecureTextField[@value='Confirm Password']", "test123");

		lib.clickMobileElement("xpath://XCUIElementTypeButton[@name='Done']");

		lib.clickMobileElement("xpath://XCUIElementTypeButton[@name='icons8 unchecked checkbox 100']");

		lib.saveScreenshot();

		lib.clickMobileElement("name:Submit");
		lib.sleep(5000);

		lib.clickMobileElement("xpath://XCUIElementTypeButton[@name='Remind me later']");

		lib.clickMobileElement("name:Favorites");

		lib.clickMobileElement("xpath://XCUIElementTypeButton[@name='SKIP']");

		lib.saveScreenshot();

		lib.clickMobileElement("xpath://XCUIElementTypeButton[@name='Favorites']");

		lib.saveScreenshot();

		lib.clickMobileElement("xpath://XCUIElementTypeButton[@name='Apps']");

		lib.saveScreenshot();
		lib.clickMobileElement("xpath://XCUIElementTypeButton[@name='GRID']");
		Thread.sleep(500);
		lib.saveScreenshot();
		lib.clickMobileElement("xpath://XCUIElementTypeButton[@name='sort alphabetically z to a']");
		lib.saveScreenshot();
		lib.clickMobileElement("xpath://XCUIElementTypeButton[@name='Notes']");
		lib.clickMobileElement("xpath://XCUIElementTypeButton[@name='Notebooks']");
		Thread.sleep(500);
		lib.clickMobileElement("xpath://XCUIElementTypeButton[@name='All Notes']");
		lib.saveScreenshot();
		lib.clickMobileElement("xpath://XCUIElementTypeButton[@name='Folders']");
		lib.saveScreenshot();
		lib.clickMobileElement("xpath://XCUIElementTypeButton[@name='App Catalog']");
		lib.saveScreenshot();

	}

	@AfterClass
	public void afterClass() {
		lib.quitBrowser();
	}

}