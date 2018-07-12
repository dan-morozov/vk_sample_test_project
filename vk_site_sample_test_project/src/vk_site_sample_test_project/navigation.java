package vk_site_sample_test_project;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class navigation {

	private WebDriver wDriver;
	
	public navigation(WebDriver wDriver) {
		this.wDriver = wDriver;
	}
	
	public String getLinkDestination(String uRI) {
		if(uRI == null) {
			System.err.println("ERROR: unable to work with null uRI");
			return null;
		}
		
		//System.out.println("DEBUG: navigating to this url >" + uRI + "<");
		
		this.wDriver.get(uRI);
		this.wDriver.findElement(By.xpath(selenium_refs.topHomeLogo));
		
		String currentUrl = this.wDriver.getCurrentUrl();
		
		if(currentUrl == null) {
			System.err.println("ERROR: navigation failed!");
		}
		
		return currentUrl;
		
	}
	
	public ArrayList<String> getAllHrefs(String uRI) {
		
		if(getLinkDestination(uRI).contains(uRI) == false) {
			System.err.println("ERROR: Failed to navigate successfully!");
			return null;
		}
		
		List<WebElement> listOfLinks =  wDriver.findElements(By.tagName("a"));
		ArrayList<String> hrefsAsStrings = new ArrayList<String>();
		for(WebElement we : listOfLinks) {
			if(we.getAttribute("href") != null) {
				// System.out.println("DEBUG: attribute: " + we.getAttribute("href").toString());
				hrefsAsStrings.add(we.getAttribute("href").toString());
			}
		}
		
		return hrefsAsStrings;
	}
	
	public void clickOnButton(String buttonRef) {
		
		wDriver.findElement(By.id(buttonRef)).click();
		
		
	}
	
	public void fillInputWithText(String InputFieldRef, String textToFillIn) {
		
		wDriver.findElement(By.id(InputFieldRef)).sendKeys(textToFillIn);
		
	}
	
	public String getCurrentUrlAsString() {
		
		String currentUrlString = wDriver.getCurrentUrl();
		// System.out.println("DEBUG: current url >" + currentUrlString);
		return currentUrlString;
		
	}
	
	
}
