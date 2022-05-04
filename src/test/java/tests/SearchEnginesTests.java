package tests;

import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pages.BingHomePage;
import pages.BingSearchResultPage;
import pages.GoogleHomePage;
import pages.SearchResultPage;

public class SearchEnginesTests extends TestBase{
	
	GoogleHomePage googleHome;
	SearchResultPage search;
	BingHomePage bingHome;
	BingSearchResultPage bingSearch;
	String[] googleSearchResults;
	String[] bingSearchResults;
	String expectedGoogleTitle = "Google";
	String expectedBingTitle ="Bing";
	
	@BeforeClass
    public void initPages() {
		googleHome = new GoogleHomePage(driver());
		search = new SearchResultPage(driver());
		bingHome = new BingHomePage(driver());
		bingSearch = new BingSearchResultPage(driver());
    }
	//@Parameters("keyword")
	@Test (priority=1)
	public void googleSearchTest() throws Exception {
		
		try {
			googleHome.launchApp();
			System.out.println("Google Search Engine is launched successfully");
			//Check weather google page is launched
			String actualTitle = googleHome.getTitle();
			System.out.println("Get the page title using driver.getTitle method and put the result of this method in variable");
			System.out.println("Then compare the actual title and expected title of the page using assertEquals methods of TestNG");
			Assert.assertEquals(actualTitle, expectedGoogleTitle, "Google page is not lanched");
			System.out.println("The tile of the page is " + actualTitle);
			googleHome.searchKeyword(keyword());
			System.out.println("Google is searching " + keyword() );
			//googleSearchResults = search.extractResultList(keyword);
			googleSearchResults = search.extractResultList(keyword());
			System.out.println("Invoke extractResultList method of GoogleSearch object and put the result in List");
			System.out.println("Google Search Engine extracted "+ googleSearchResults.length + " items with " + keyword() + " Keyword");
			//System.out.println();
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	//@Parameters("keyword")
	@Test(priority=2)//(dataProvider="getData")
	public void blingSearchTest() throws Exception {
		try {
			//launches bling search engine
			bingHome.launchApp();
			System.out.println("Launching Bling Search Engine...");
			String actualTitle = bingHome.getTitle();
			System.out.println("Get the page title using driver.getTitle method and put the result of this method in variable");
			System.out.println("Then compare the actual title and expected title of the page using assertEquals methods of TestNG");
			Assert.assertEquals(actualTitle, expectedBingTitle, "Bing page is not lanched");
			System.out.println("The title of the page is " + actualTitle);
			//searches the keyword from bling search engine
			bingHome.searchKeyword(keyword());
			System.out.println("Bing is searching " + keyword() );
			bingSearchResults = bingSearch.extractResultList(keyword());
			System.out.println("Invoke extractResultList method of bingSearch object and put the result in List");
				
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	@Test(priority=3)
	public void listPopularSites() {
		//extract the length of each arrays	
		int googleResultCount = googleSearchResults.length;
		int bingSearchResultCount = bingSearchResults.length;
	   //check the smallest count - we use the smallest array in the outer loop and the longest one in the inner loop
		// loop each element and compare the urls in both arrays and find the common site
		if (googleResultCount <= bingSearchResultCount) {
			for (int i = 0; i < googleResultCount; i++) {
				for(int j= 0 ; j < bingSearchResultCount ; j++) {
					//compare the url of the google results with url of the bing search engine
					//if it gets in both engines then the url is popular
					if (googleSearchResults[i].toLowerCase().equalsIgnoreCase(bingSearchResults[j].toLowerCase())) {
						System.out.println(googleSearchResults[i] + " is popular site");
						//if the url is found , in the second array no need to check the rest items so breaking the loop
						break;
					}
				}
			}
		}else 
		{
			for (int i = 0; i < googleResultCount; i++) {
				for(int j= 0 ; j < bingSearchResultCount ; j++) {
					//compare the url of the google results with url of the bing search engine
					//if it gets in both engines then the url is popular
					if (googleSearchResults[i].toLowerCase().equalsIgnoreCase(bingSearchResults[j].toLowerCase())) {
						System.out.println(googleSearchResults[i] + " is popular site");
						//if the url is found , in the second array no need to check the rest items so breaking the loop
						break;
					}
				}
			}
		}
		
		
	}
	
}
