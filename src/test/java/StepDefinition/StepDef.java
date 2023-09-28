package StepDefinition;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.compress.archivers.dump.DumpArchiveEntry.TYPE;
import org.apache.logging.log4j.LogManager;
import org.codehaus.plexus.util.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import PageObject.AddNewCustomerPage;
import PageObject.LoginPage;
import PageObject.SearchCustomerPage;
import Utitlities.ReadConfig;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;

/* Child Class of Base Class */

public class StepDef extends BaseClass{

	@Before
	public void SetUp1() {
		
		readConfig = new ReadConfig();

		log = LogManager.getLogger("StepDef");
		System.out.println("SetUp 1- sanity Method Executed....");
		
		String browser=readConfig.getBrowser();
		
		//launch browser
		switch(browser.toLowerCase())
		{
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;

		case "msedge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;

		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
		default:
			driver = null;
			break;

		}
		log.fatal("SetUp 1 Executed....");
	}	
/*	
	@Before ("@regression")
	public void SetUp2() {
		System.out.println("SetUp2 regression Method Executed....");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		log.info("SetUp 2 Executed....");
	}	*/
	
	@Given("User Launch Chrome browser")
	public void user_launch_chrome_browser() {
	   
		loginPg= new LoginPage(driver);
		addNewCustPg = new AddNewCustomerPage(driver);
		searchCustPg = new SearchCustomerPage(driver);
		
		log.info("Launch Chrome Browser....");
	}

	@When("User opens URL {string}")
	public void user_opens_url(String url) {
	    
		driver.get(url);
		log.info("URL Opned....");
	}

	@When("User enters Email as {string} and Password as {string}")
	public void user_enters_email_as_and_password_as(String emailadd, String password) {
		loginPg.enterEmail(emailadd);
		loginPg.enterPassword(password);
		log.info("email address and password entered....");
	}

	@When("Click on Login")
	public void click_on_login() {
	    loginPg.clickOnLoginButton();
	    log.info("click on login button....");
	}

	////////Login Feature/////////////////////
	
	@Then("Page Title should be {string}")
	public void page_title_should_be(String expectedTitle) {
	    
		String actualTitle =driver.getTitle();
		
		if(actualTitle.equals(expectedTitle))
		{
			log.warn("Test Passed: Login Feature: Page Title Matched");
			Assert.assertTrue(true);  //pass
		}
		else
		{
			Assert.assertTrue(false);   //fail
			log.warn("Test Failed: Login Feature: Page Title not Matched");
		}
	}

	@When("User click on Log out link")
	public void user_click_on_log_out_link() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		loginPg.clickOnLogOutButton();
		 log.info("User click on log out link....");
	}
/*
	@Then("close browser")
	public void close_browser() {
	    
		driver.close();
		 log.info("Browser Closed....");
		//driver.quit();
		
	} */
	//////////////////Add New Customer///////////////////
	
	@Then("User can view Dashboard")
	public void user_can_view_dashboard() {
	    String actualTitle=addNewCustPg.getPageTitle();
	    
	    String expectedTitle="Dashboard / nopCommerce administration";
	    
	    if(actualTitle.equals(expectedTitle)) {
	    	
	    	log.info("User can view dashboard : Test Passed....");
	    	Assert.assertTrue(true);
	    }else {
	    	Assert.assertTrue(false);
	    	log.info("User cannot view dashboard : Test Failed....");
	    }
	}

	@When("User click on Customer Menu")
	public void user_click_on_customer_menu() {
	   addNewCustPg.clickOnCustomerMenu();
	   log.info("Customer Menu Clicked....");
	   
	   try {
			Thread.sleep(2000);
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}

	@When("Click on Customers Menu Item")
	public void click_on_customers_menu_item() {
	    addNewCustPg.clickOnCustomerMenuItem();	
	    log.info("Customer Menu Item Clicked....");
	    
	    try {
			Thread.sleep(2000);
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}

	@When("Click on Add new button")
	public void click_on_add_new_button() {
	    addNewCustPg.clickOnAddNewButton();
	    log.info("Clicked on add new button....");
	}

	@Then("User can view add new Customer Page")
	public void user_can_view_add_new_customer_page() {
	    String actualTitle= addNewCustPg.getPageTitle();
	    String expectedTitle= "Add a new customer / nopCommerce administration";
	    
	    if(actualTitle.equals(expectedTitle)) {
	    	
	    	log.info("User Can view add new customer page: passed....");
	    	Assert.assertTrue(true); //pass
	    }else {
	    	Assert.assertTrue(false); //fail
	    	log.info("User Can view add new customer page: failed....");
	    }    
		
	}

	@When("User enter customer info")
	public void user_enter_customer_info() {
	    //addNewCustPg.enterEmail("cs1234@gmail.com");
	    addNewCustPg.enterEmail(generateEmailId()+"@gmail.com");
		addNewCustPg.enterPassword("test1");
	    addNewCustPg.enterFirstName("Shrikant");
	    addNewCustPg.enterLastName("Sarode");
	    addNewCustPg.enterGender("male");
	    addNewCustPg.enterDOB("11/22/1989");
	    addNewCustPg.enterCompanyName("CodeStudio");
	    addNewCustPg.selectTaxExempt();
	    addNewCustPg.enterManagerOfVendor("Vendor 1");
	    addNewCustPg.enterAdminContent("This is Admin Content");
	    log.info("Customer Information entered....");
	}

	@When("Click on save buttton")
	public void click_on_save_buttton() {
	  addNewCustPg.clickOnSave();
	  log.info("Click on save button....");
	}

	@Then("User can view confirmation message {string}")
	public void user_can_view_confirmation_message(String expectedConfirmationMsg) {
	    String actualBodyTagText=driver.findElement(By.tagName("body")).getText();
	    expectedConfirmationMsg="The new customer has been added successfully.";
	   
	    if(actualBodyTagText.contains(expectedConfirmationMsg)) {
	    	log.info("User Can view confirmation message: passed....");
	    	Assert.assertTrue(true);	//pass
	    }else {
	    	log.info("User Can view confirmation message: failed....");
	    	Assert.assertTrue(false);   //fail
	    }
	}
	
	/////// Search Customer by Email //////////////////////
	
	@When("Enter Customer Email")
	public void enter_customer_email() {
		searchCustPg.enterEmail("victoria_victoria@nopCommerce.com"); 
		log.info("Email address entered....");
	}

	@When("Click On Search button")
	public void click_on_search_button() {
		searchCustPg.clickOnSearchButton();
		log.info("Click on search button....");
		try {
			Thread.sleep(2000);
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}

	@Then("User should found email in Search Table")
	public void user_should_found_email_in_search_table() {
	    
		String expectedEmail="victoria_victoria@nopCommerce.com";
		
		//Assert.assertTrue(searchCustPg.searchCustomerByEmail(expectedEmail));
		
		
		if(searchCustPg.searchCustomerByEmail(expectedEmail)==true){
			log.info("User should found email in the search Table-passed....");
			Assert.assertTrue(true);
		}else {
			log.info("User should found email in the search Table-failed....");
			Assert.assertTrue(false);
		}
	}	
	/// Search Customer by Name ///
	
	@And("Enter Customer FirstName")
	public void enter_customer_first_name(){
		
		searchCustPg.enterFirstName("Victoria");
	}

	@And("Enter Customer LastName")
	public void enter_customer_last_name() {
		
		searchCustPg.enterLastName("Terces");    
	}

	@Then("User should found Name in Search Table")
	public void user_should_found_name_in_search_table() {
		
		String expectedName= "Victoria Terces";
		
		if(searchCustPg.searchCustomerByName(expectedName)== true) {
			Assert.assertTrue(true);
		}else {
			Assert.assertTrue(false);
		}
	}
	
	//@After
	public void tearDown(Scenario sc) {
	
		System.err.println("Tear Down 1 Method Executed *** Browser Closed");
		
		if(sc.isFailed()==true) {
			//convert WebDriver object to Take Screenshot
			
			String fileWithPath="F:\\EclipseProgramWorkspace\\Java2022_09\\CucumberFramework\\ScreenShots\\failedScreenshot.png";
			
			TakesScreenshot srcShot=((TakesScreenshot)driver);
			
			// call getScreenshotAs method to create image file
			File SrcFile=srcShot.getScreenshotAs(OutputType.FILE);
			
			//move image file to new destination
			
			File DestFile = new File(fileWithPath);
			
			// copy file at destination
			try {
				FileUtils.copyFile(SrcFile,DestFile);
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		driver.quit();
	}
	
	@AfterStep
	public void addScreenshot(Scenario scenario) {
			if(scenario.isFailed())  // Add this if loop if we want to capture screenshot of failed scenario and normal scenario as well
			{
				final byte[] screenshot=((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
				
				//attach image file report (data,media type, name of the attachment)
				scenario.attach(screenshot,"img/png",scenario.getName());
			}
	}
	
	/*@After
	public void tearDown2() {
		driver.quit();
		System.err.println("Tear Down 2 Method Executed *** Browser Closed");
	}*/
	
	/*
	@BeforeStep
	public void beforeStepMethodDemo() {
		System.out.println("This before Step .....");
	}
	@AfterStep
	public void afterStepMethodDemo() {
		System.out.println("This after Step .....");
	}*/
}

	
 
	

