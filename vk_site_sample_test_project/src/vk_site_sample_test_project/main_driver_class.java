package vk_site_sample_test_project;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Sample test main class for sample project
 * using selenium with java to test for expected elements (sample set)
 * within the targeted page (tailored for vk.com)
 * Note - this is not meant to be an exhaustive test, and it will not
 * cover a lot of functional areas; rather, it covers simple sanity
 *  and sample set of features
 * Note 2: due to the time constraint, the code is not optimized nor
 * truly abstracted or fully object-oriented.
 * 
 * @author Daniel Morozov
 * 
 */
public class main_driver_class {

	public static final String targetUrl = "https://vk.com/";
	public static final String chromeDriverRef = "src/resources/chromedriver.exe";
	
	public static final String newUser_FirstName = "Bob";
	public static final String newUser_LastName = "Smith";
	public static final String expectedNewRegistrationURI = "";
	
	public static WebDriver getDriver() {
	/**
	 * TODO: support other driver and browser types
	 * I am not about to code for everything within a day for free ...
	 */
	
	ChromeOptions options = new ChromeOptions();
	Map<String, Object> prefs = new HashMap<String, Object>();
	prefs.put("profile.default_content_settings.popups", 0);
	options.setExperimentalOption("prefs", prefs);
	options.addArguments("start-maximized");
	System.setProperty("webdriver.chrome.driver", chromeDriverRef);
	DesiredCapabilities cap = DesiredCapabilities.chrome();
	cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
	cap.setCapability(ChromeOptions.CAPABILITY, options);

	ChromeDriver driver = new ChromeDriver(options);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	
	return driver;
	}


	
	@SuppressWarnings("unchecked")
	public static void main(String [] args) throws InterruptedException {
		
		WebDriver chromeDriver  = getDriver();
		
		navigation navInstance = new navigation(chromeDriver);

		// System.out.println("DEBUG: Final destination url: " + navInstance.getLinkDestination(targetUrl));
		List<String> allLinks = navInstance.getAllHrefs(targetUrl);
		Collections.sort(allLinks);
		
		/**
		 * TODO: use test asserts instead; use test assert framework
		 */
		if(compareStringsTest(allLinks.toString(), selenium_refs.expectedLinksAsString) == false) {
			System.out.println("Test Failed! Page links are not as expected");
		} else {
			System.out.println("Test Passed! All links in the web page are as expected");
		}
		
		
		navInstance.fillInputWithText(selenium_refs.registrationFirstNameInputField_id_Ref, newUser_FirstName);
		Thread.sleep(2000);
		navInstance.fillInputWithText(selenium_refs.registrationlastNameInputField_id_Ref, newUser_LastName);
		Thread.sleep(2000);
		navInstance.clickOnButton(selenium_refs.registrationButton_id_Ref);
		Thread.sleep(2000);
		if(navInstance.getCurrentUrlAsString().contentEquals(targetUrl) == false) {
			System.out.println("Test Failed! new user registration without birth date entered from home page use case scenario did not go as expected!");
		} else {
			System.out.println("Test Passed! new user registration, new user registration without birth date entered use case scenario from home page passed");
		}
		
		
		
		chromeDriver.close();
		
	}
	
	public static boolean compareStringsTest(String aString, String bString) {
		//System.out.println("DEBUG - aString >" + aString + "<");
		//System.out.println("DEBUG - bString >" + bString + "<");
		if(aString.contentEquals(bString)) {
			return true;
		} else {
			return false;
		}
	}
	
}
