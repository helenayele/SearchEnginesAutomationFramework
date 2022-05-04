package tests;


import java.lang.reflect.Method;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;

import org.testng.annotations.BeforeClass;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import core.FileConfig;
import core.DriverFactory;

public class TestBase {

	private WebDriver driver;
	private DriverFactory df;
	private String keyword;
	//private TestReporter reporter;
	
	@BeforeSuite
	public void initSuite() {
		//FileConfig.loadConfig(System.getenv("env"));
		//We can different environments like dev, integration, pre-prod and prod, and we can have
		//different setting for baseurl, implicity wait and other configurable variables like keyword
		//but for this assignmen we have only dev environment as example
		FileConfig.loadConfig("dev");
		//the user will enter keyword while launching the test, and we will extract the keyword using system.getenv method
		keyword = System.getenv("keyword");
		System.out.println("Getting the keyword variable from System.getenv method");
	}
	
	//@Parameters("browser")
	@BeforeClass(alwaysRun=true)
	public void initDriver() {
		df = new DriverFactory();
		//pass browser as system environment variable while invoking the test
		driver = df.getDriver(System.getenv("browser"));
		System.out.println("Getting the browser variable from System.getenv method");
		//driver = df.getDriver(browser);
		if (driver == null) {
			System.out.println("The driver is null please initialize it");
		}
	}
	
    @AfterClass(alwaysRun=true)
	public void killDriver() {
    	System.out.println("Getting all jobs done, quiting the driver");
		df.quitDriver();
	}
	
	protected WebDriver driver() {
		return driver;
	}
	
	protected String keyword() {
		return this.keyword;
	}
	
	
}
