package pages;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import core.FileConfig;

public class BingHomePage extends BasePage{

	@FindBy(id="sb_form_q")
	private WebElement searchInput;
	
	//@FindBy(xpath="//ol[@id='b_results']/li[contains(@class,'b_algo')]")
	//private List<WebElement> searchResults;
	
	public BingHomePage(WebDriver driver) {
		super(driver);
		
	}
	
	  public void launchApp() throws Exception {
	     //fetching the bing base url from File Config get property method and launching the app
		  driver.get(FileConfig.getProperty("bingBaseUrl"));
	      System.out.println("lanching the bing search engine");
	    	
	   }
	  
	  public String getTitle() {
	         return driver.getTitle();
	    }
	 
	    public void searchKeyword(String keyword) {
	    	searchInput.sendKeys(keyword);
	    	searchInput.sendKeys(Keys.ENTER);
	    }
	  
}
