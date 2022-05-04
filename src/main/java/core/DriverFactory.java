package core;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	private WebDriver driver = null;

	public WebDriver getDriver(String browser) {

		if (browser.equalsIgnoreCase("chrome")) {
			// It will set the Chrome browser in the automation script
			// webdrivermanager checks for the latest version of the specified WebDriver
			// binary.
			// If the binaries are not present on the machine, then it will download the
			// WebDriver binaries.
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("firefox")) {
			// Setting up the firefox browser for automation
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		driver.manage().window().maximize();
		System.out.println("Maximize the browser window using driver.manager().maximize functionalit");
		// sets the time to wait for a page to load completely before throwing an error
		driver.manage().timeouts().pageLoadTimeout(Long.parseLong(FileConfig.getProperty("pageLoadTimeOut")),
				TimeUnit.SECONDS);
		System.out.println("setting the pageLoadtimeout to wait for the page to load before throwing an error");
		// This timeout is used to specify the amount of time the driver should wait
		// while searching for an element if it is not immediately present
		driver.manage().timeouts().implicitlyWait(Long.parseLong(FileConfig.getProperty("implicitWait")),
				TimeUnit.SECONDS);
		System.out.println(
				"The driver is configured to wait for some configured seconds while searching for an element if it is not immediately found");
		// This method will clear Cache before starting to googling
		ClearBrowserCache();
		return driver;
	}

	private void ClearBrowserCache() {
		driver.manage().deleteAllCookies(); // delete all cookies
		System.out.println("Deliting all cookies of the browser");
		try {
			// wait 10 seconds to clear cookies.
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void quitDriver() {
		if (driver != null) {
			driver.quit();
		}
	}

}
