package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import core.FileConfig;

public class GoogleHomePage extends BasePage{
	
		// locators
		@FindBy(xpath="//input[@name='q']")    
		private WebElement searchInput;
	
			
		public GoogleHomePage(WebDriver driver) {
			super(driver);
		}
	    
		// business methods
		
	    public void launchApp() throws Exception {
	    	//Fetching the bing base url from File Config get property method and launching the app
	    	driver.get(FileConfig.getProperty("baseUrl"));
	    	System.out.println("lanching the Google search engine");
	    }
	    
	    public String getTitle() {
	         return driver.getTitle();
	    }
	    
	    public void searchKeyword(String keyword) {
	    	searchInput.sendKeys(keyword);
	    	searchInput.sendKeys(Keys.ENTER);
	    }
	
		
}
