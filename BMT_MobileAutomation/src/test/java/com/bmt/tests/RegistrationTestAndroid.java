package com.bmt.tests;

import java.net.MalformedURLException;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.bmt.commons.Library;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class RegistrationTestAndroid {

	public Library lib;

	@BeforeClass
	public void setup() throws MalformedURLException {
		lib = new Library("Test Started");
		lib.launchDriver();
	}

	@Test
	public void registrationTestAndroid() throws Exception {
		lib.saveScreenshot();
		lib.clickMobileElement("//android.widget.Button[contains(@resource-id,'button1')]");

		lib.saveScreenshot();
		lib.clickMobileElement("//android.widget.Button[contains(@resource-id,'permission_allow_button')]");

		lib.saveScreenshot();
		lib.findMobileElement("rl_email_layout");
		
		lib.findMobileElement("tv_email");
		lib.saveScreenshot();

		lib.clickMobileElement("tv_sign_up");
		Thread.sleep(5000);

		lib.sendKeys("et_username", "neo" + lib.getFDate() + "@mailinator.com");
		lib.saveScreenshot();
		
		lib.enterMobileText("et_pass", "test123");

		lib.enterMobileText("et_confirm_password", "test123");
		lib.saveScreenshot();
		lib.clickMobileElement("cb_acceptTerms");

//		driver.findElement(By.id("btn_submit")).click();
//		lib.saveScreenshot();

//		Thread.sleep(5000);
		System.out.println("Script Execution Completed");
	}

	@AfterClass
	public void afterClass() {
		lib.quitBrowser();
	}


}