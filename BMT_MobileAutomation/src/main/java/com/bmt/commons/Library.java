package com.bmt.commons;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.Reporter;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;

public class Library extends TestBase {

	public static final long GLOBALTIMEOUT = 10;

	public AppiumDriver<MobileElement> driver;
	private Configuration config;

	public DesiredCapabilities caps;
	public String getAppDirectory;
	public String getAppname;
	public String getPlatformVersion;
	public String getDeviceID;
	public String getDeviceName;
	public String url;
	public String screenShotFolder;

	public String currentTestName;
	private String currentSessionID;

	public String getCurrentTestName() {
		return currentTestName;
	}

	public void setCurrentTestName(String currentTestName) {
		this.currentTestName = currentTestName;
	}

	public Library(String testName) {
		this.currentTestName = testName;
		this.config = new Configuration();
	}

	public Library() {
		// do nothign
	}

	public AppiumDriver<MobileElement> launchDriver() throws MalformedURLException {

		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("platformName", config.platformName);
		caps.setCapability("platformVersion", config.platformVersion);
		caps.setCapability("deviceName", config.deviceName);
		File appDir = new File(config.appDirectory);
		File app = new File(appDir, config.appname);
		caps.setCapability("app", app.getAbsolutePath());
		caps.setCapability("udid", config.deviceID);

		if (config.platformName.equalsIgnoreCase("ios")) {
			caps.setCapability("automationName", "XCUITest");
			caps.setCapability("xcodeSigningId", "iPhone Developer");
			caps.setCapability("session-override", "true");
			caps.setCapability("sendKeyStrategy", "setValue");
			caps.setCapability("unicodeKeyboard", true);
			caps.setCapability("resetKeyboard", true);
			caps.setCapability("noResetValue", "false");

			driver = new AppiumDriver<MobileElement>(new URL("http://" + config.url + "/wd/hub"), caps);
		}

		else {
			caps.setCapability("sendKeyStrategy", "setValue");
			caps.setCapability("unicodeKeyboard", true);
			caps.setCapability("resetKeyboard", true);
			caps.setCapability("noResetValue", "false");

			driver = new AndroidDriver<MobileElement>(new URL("http://" + config.url + "/wd/hub"), caps);

		}
		driver.manage().timeouts().implicitlyWait(75, TimeUnit.SECONDS);
		return driver;
	}

	public MobileElement findMobileElement(String locator) {
		return driver.findElement(getlocator(locator));
	}

	public String getCurrentSessionID() {
		return currentSessionID;
	}

	public By getlocator(String locator) {

		String string = locator;
		String[] parts = string.split(":");
		String type = parts[0];
		String object = parts[1];
		By byVal = null;
		Reporter.log("Finding element with logic: " + locator, true);
		try {
			if (type.equals("id")) {
				byVal = By.id(object);
			} else if (type.equals("name")) {
				byVal = By.name(object);
			} else if (type.equals("class")) {
				byVal = By.className(object);
			} else if (type.equals("link")) {
				byVal = By.linkText(object);
			} else if (type.equals("partiallink")) {
				byVal = By.partialLinkText(object);
			} else if (type.equals("css")) {
				byVal = By.cssSelector(object);
			} else if (type.equals("xpath")) {
				byVal = By.xpath(object);
			} else
				throw new RuntimeException("Please provide correct element locating strategy");

		} catch (Exception e) {
			throw new RuntimeException("Please provide correct element locating strategy");
		}
		return byVal;
	}

	public List<MobileElement> findMobileElements(String locator) {
		return driver.findElements(getlocator(locator));
	}

	public void clickMobileElement(String locator) throws Exception {

		int i = 0;
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		while (i < 5) {
			try {
				findMobileElement(locator).click();
				break;
			} catch (Exception e) {
				i++;
				Thread.sleep(3000);
			}
		}

		if (i == 5) {
			driver.manage().timeouts().implicitlyWait(GLOBALTIMEOUT, TimeUnit.SECONDS);
			throw new Exception("Element not found:" + locator);
		}

	}

	public Configuration getConfiguration() {
		if (config == null) {
			config = new Configuration();
		}
		return config;
	}

	public AppiumDriver<MobileElement> getCurrentDriverInstance() {
		return driver;
	}

	public static int randIntDigits(int min, int max) {
		Random rand = new Random();
		int randomNum = (rand.nextInt((max - min) + 1) + rand.nextInt((max - min) + 1)) / 2;
		return randomNum;
	}

	public void enterMobileText(String locator, String text) throws Exception {

		int i = 0;
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		while (i < 5) {
			try {
				MobileElement byVal = findMobileElement(locator);
				byVal.click();
				byVal.clear();
				byVal.sendKeys(text);
				break;
			} catch (Exception e) {
				i++;
				Thread.sleep(3000);
			}
		}

		if (i == 5) {
			driver.manage().timeouts().implicitlyWait(GLOBALTIMEOUT, TimeUnit.SECONDS);
			throw new Exception("Element not found:" + locator);
		}

	}

	public void sendKeys(String locator, String text) throws Exception {

		int i = 0;
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		while (i < 5) {
			try {
				MobileElement byVal = findMobileElement(locator);
				byVal.sendKeys(text);
				break;
			} catch (Exception e) {
				i++;
				Thread.sleep(3000);
			}
		}

		if (i == 5) {
			driver.manage().timeouts().implicitlyWait(GLOBALTIMEOUT, TimeUnit.SECONDS);
			throw new Exception("Element not found:" + locator);
		}

	}

	public String getFDate() {
		return getDate().replaceAll("/", "_").replaceAll(":", "_").replaceAll(" ", "_");
	}

	public String getDate() {

		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("MM/dd HH:mm:ss");
		System.out.println(dateFormat.format(date));

		return dateFormat.format(date);
	}

	public void saveScreenshot() {
		String path = config.screenShotFolder;
		try {
			Path currentRelativePath = Paths.get(path);
			String s = currentRelativePath.toAbsolutePath().toString();
			File file = new File(s);
			System.out.println("Capturing Screenshot:" + file.getAbsolutePath());
			File srcFile = driver.getScreenshotAs(OutputType.FILE);
			String filename = UUID.randomUUID().toString();
			File targetFile = new File(file.getAbsolutePath() + File.separator + filename + ".jpg");
			FileUtils.copyFile(srcFile, targetFile);
		} catch (Exception e) {
			System.out.println("Failed to take screenshot");
			System.err.println(e.getLocalizedMessage());
		}
	}

	public void sleep(long milliSeconds) {
		try {
			Thread.sleep(milliSeconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void verifyMobileElement(String locator, boolean checkVisibility) {
		boolean isDisplayed = checkVisibility;
		try {

			findMobileElement(locator);
			if (checkVisibility == true) {
				isDisplayed = findMobileElement(locator).isDisplayed();
				Assert.assertTrue(isDisplayed, "Element not found using locator: " + locator);
			}
		} catch (NoSuchElementException nsee) {
		}
	}

	public void verifyMobileElementAbsent(String locator) {
		try {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			MobileElement byVal = findMobileElement(locator);

			Assert.assertTrue(false, "Expected element to be absent, but it was found on the page.=" + byVal);

		} catch (Exception e) {
			// It's good if not found
		}

		finally {
			driver.manage().timeouts().implicitlyWait(GLOBALTIMEOUT, TimeUnit.SECONDS);
		}

	}

	public void tapMobileElement(String locator) {
		MobileElement element = findMobileElement(locator);
		new TouchActions(driver).singleTap(element).perform();

	}

	@SuppressWarnings("rawtypes")
	public void swipeToDown() {
		org.openqa.selenium.Dimension size = driver.manage().window().getSize();
		int startY = (int) (size.height * 0.80);
		int endY = (int) (size.height * 0.30);
		int startX = (size.width / 2);
		new TouchAction(driver).longPress(PointOption.point(startX, startY)).moveTo(PointOption.point(startX, endY))
				.release().perform();

	}

	@SuppressWarnings("rawtypes")
	public void swipeToUp() {
		org.openqa.selenium.Dimension size = driver.manage().window().getSize();
		int endY = (int) (size.height * 0.80);
		int startY = (int) (size.height * 0.30);
		int startX = (size.width / 2);
		new TouchAction(driver).longPress(PointOption.point(startX, startY)).moveTo(PointOption.point(startX, endY))
				.release().perform();
	}

	public void quitBrowser() {
		driver.quit();
		try {
			driver.quit();
		} catch (Exception e) {
		}
		Reporter.log("Closing the Browser Successfully", true);
		System.out.println("Closing the Browser Successfully");
		Reporter.log("</table>");
	}

}