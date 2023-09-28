package PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AddNewCustomerPage {

	WebDriver ldriver;     //Local Driver
	
	

		//Constructor
	
		public AddNewCustomerPage(WebDriver rDriver) {   //Remote Driver
			
			ldriver=rDriver;
			PageFactory.initElements(ldriver, this);
		}
		
		///Find Elements on the web page
		
		@FindBy(xpath="//a[@href='#'] //p[contains(text(),'Customers')]")
		WebElement custMenu;
		
		@FindBy(xpath="//a[@href='/Admin/Customer/List']//p[contains(text(),' Customers')]")
		WebElement custMenuItem;
		
		@FindBy(xpath="//a[@class='btn btn-primary']")
		WebElement addNewBtn;
		
		@FindBy(xpath="//*[@id=\"Email\"]")
		WebElement email;
		
		@FindBy(xpath="//*[@id=\"Password\"]")
		WebElement password;
		
		@FindBy(xpath="//*[@id=\"FirstName\"]")
		WebElement firstName;
		
		@FindBy(xpath="//*[@id=\"LastName\"]")
		WebElement lastName;
		
		@FindBy(xpath="//*[@id=\"Gender_Male\"]")
		WebElement maleGender;
		
		@FindBy(xpath="//*[@id=\"Gender_Female\"]")
		WebElement femaleGender;
		
		@FindBy(xpath="//*[@id=\"DateOfBirth\"]")
		WebElement txtDob;
		
		@FindBy(xpath="//*[@id=\"Company\"]")
		WebElement companyName;
		
		@FindBy(xpath="//*[@id=\"IsTaxExempt\"]")
		WebElement isTaxExempt;
		
		@FindBy(xpath="//div[@class='k-multiselect-wrap k-floatwrap']")
		WebElement txtCustomerRoles;
		
		@FindBy(xpath="//option[text()='Administrators']")
		WebElement listItemAdministrators;
		
		@FindBy(xpath="//option[text()='Registered']")
		WebElement listIttemRegistered;
		
		@FindBy(xpath="//option[text()='Guests']")
		WebElement listIttemGuest;
		
		@FindBy(xpath="//option[text()='Vendors']")
		WebElement listIttemVendors;
		
		@FindBy(xpath="//*[@id=\"VendorId\"]")
		WebElement dropdownVendorMgr;
		
		@FindBy(xpath="//*[@id=\"AdminComment\"]")
		WebElement txtAdminComment;
				
		@FindBy(xpath="//*[@name=\"save\"]")
		WebElement btnSave;
	
		/// Actions Methods for Web Elements
		

		public String getPageTitle()
		{
			return ldriver.getTitle();
		}
		
		public void clickOnCustomerMenu()
		{
			custMenu.click();
		}
		
		public void clickOnCustomerMenuItem()
		{
			custMenuItem.click();
		}
		
		public void clickOnAddNewButton() {
			addNewBtn.click();
		}
		
		public void enterEmail(String addemail) {
			email.sendKeys(addemail);
		}
		
		public void enterPassword(String pwd) {
			password.sendKeys(pwd);
		}
		
		public void enterFirstName(String fName) {
			firstName.sendKeys(fName);
		}
		
		public void enterLastName(String lName) {
			
			lastName.sendKeys(lName);
		}
		
		public void enterGender(String gender) {
			
			if(gender.equals("male")) {
				maleGender.click();
			}else if(gender.equals("female")){
				femaleGender.click();
			}else {
				maleGender.click();
			}			
		}
		
		public void enterDOB(String dob) {
			txtDob.sendKeys(dob);
		}
		
		public void enterCompanyName(String cName) {
			companyName.sendKeys(cName);
		}
		
		public void selectTaxExempt() {
			isTaxExempt.click();
		}
		
		public void enterManagerOfVendor(String value) {
			
			Select drp=new Select(dropdownVendorMgr);
			drp.selectByVisibleText(value);
			
		}
		
		public void enterAdminContent(String content) {
			txtAdminComment.sendKeys(content);
			
		}
		
		public void clickOnSave() {
			btnSave.click();
		}	
}

