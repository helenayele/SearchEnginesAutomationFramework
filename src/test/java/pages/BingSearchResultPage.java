package pages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BingSearchResultPage extends BasePage{

	@FindBy(xpath="//ol[@id='b_results']/li[contains(@class,'b_algo')]")
	private List<WebElement> searchResultList;
	
	@FindBy(xpath="//ol[@id='b_results']/li[contains(@class,'b_algo')]//h2/a")
	private List<WebElement> titlesList;
	
	@FindBy(xpath="//ol[@id='b_results']/li[contains(@class,'b_algo')]//div[@class='b_caption']//cite")
	private List<WebElement> urlList;
	
	@FindBy(xpath="//ol[@id='b_results']/li[contains(@class,'b_algo')]//div[@class='b_caption']//p")
	private List<WebElement> descriptionList;
	
	
	private static int countOfKeywords = 0;
	

	Map<String,List<String>> result ;
	
	public BingSearchResultPage(WebDriver driver) {
		super(driver);
	}
	
	
	
	public String[] extractResultList(String keyword) throws Exception {
		// get the size of the search result here
		int resultCount = searchResultList.size();
		//Based on the size we will create array
		String urlArr[];
		/*
		 * Based on the size of the search result create array check here the size of
		 * the search result if it is between 1 and 10 number of elements create it but
		 * if it is less than 1 , return, and if it is more than 10 create with 10 array
		 * size
		 */
		if (resultCount > 0 && resultCount <= 10) {
			urlArr = new String[resultCount];

		}
		// return empty array if size of the search result is less than 1
		else if (resultCount < 1)
			return new String[0];

		else {
			urlArr = new String[resultCount];

		}
		/*
		 * Retrieve the title url and description attributes from the result set Check
		 * if the keyword appears in the search result and also try to see if the
		 * keyword is shown in url, title and description attributes
		 */
		int index = 0;
		for (WebElement ele : searchResultList) {
			//Extract the title part of the searched item
			String title = waitForElement(10,ele.findElement(By.xpath(".//h2/a"))).getText();
			//Extract the url part of the searched item
			String url = waitForElement(10,ele.findElement(By.xpath(".//div[@class='b_caption']//cite"))).getText();
			String description="";
			//Sometimes we don't get short description for some of the searched item, so we will put it in try catch statement
			try {			
				WebElement e = waitForElement(10, ele.findElement(By.xpath(".//div[@class='b_caption']//p")));
			    description = e.getText();
			 }catch(NoSuchElementException ex) {
				description="It doesn't have short description";
		    }
			// 6. make sure atleast one attribute of each element contains the keyword
			// check it by writing if statmeent here
			// check here if the search result contain the keyword
			if (ele.getText().toLowerCase().contains(keyword.toLowerCase())) {
				// check if the title attribute of the search result contains the keyword
				if (title.toLowerCase().contains(keyword.toLowerCase())) {
					System.out.println("Title : " + title + " contains the keyword " + keyword);

					// check if the url attribute of the search result contains the keyword
				} else if (url.toLowerCase().contains(keyword.toLowerCase())) {
					System.out.println("Url : " + url + " contains the keyword " + keyword);

					// check if the descritption attribute of the search result contains the keyword
				} else if (description.toLowerCase().contains(keyword.toLowerCase())) {
					System.out.println("Description : " + description + " contains the keyword " + keyword);
					// if it is not avaialbe in the above attributes , display this
				} else {
					System.out.println(
							"None of the title , url and description attributes contain the keyword " + keyword);
				}
			} else
				System.out.println("The Search result " + url + " doesn't contain the keyword " + keyword);

			// add the url part to the array so that url comparison will be made with the
			// other search engine to get popular site
			urlArr[index] = url;
			if (index > 9)
				break;
			index++;
		}

		return urlArr;
	}

}
