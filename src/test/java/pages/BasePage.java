package pages;

import java.time.Duration;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
	
	protected WebDriver driver = null;
	
	public BasePage(WebDriver driver) {
		//Initialize the driver
		this.driver = driver;
		//initialize all the web elements located by @FindBy annotation in this class and its subclass
		PageFactory.initElements(driver, this);
	}
	
	public WebElement waitForElement(long time, WebElement element) throws Exception{
		//creating webDriverWait object to wait for an element for specified period - time 
		//But it will be polling every 1 seconds to check the element's state
		System.out.println("Checking wether the element can be clicked every 1 second and this activity will last a maximum of the give time - " + time);

		WebDriverWait wait = new WebDriverWait(driver, time);
		return wait.ignoring(NoSuchElementException.class)
		.pollingEvery(Duration.ofSeconds(1))
		.until(ExpectedConditions.elementToBeClickable(element));
	}

}
