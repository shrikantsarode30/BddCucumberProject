package PageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchCustomerPage {

	WebDriver ldriver;   //local Driver


	public SearchCustomerPage(WebDriver rDriver) {
		ldriver=rDriver;
		PageFactory.initElements(ldriver, this);

	}

	@FindBy(xpath="//*[@id=\"SearchEmail\"]")
	WebElement emailAdd;

	@FindBy(xpath="//*[@id=\"search-customers\"]")
	WebElement searchbtn;

	@FindBy(xpath="	")
	WebElement searchResult;

	@FindBy(xpath="//table[@id='customers-grid']//tbody/tr")
	List <WebElement> tableRows;
	/*
	@FindBy(xpath="//table[@id='customers-grid']//tbody/tr[1]/td")
	List <WebElement> tableColumns;
	 */
	@FindBy(xpath="//*[@id=\"SearchFirstName\"]")
	WebElement firstName;

	@FindBy(xpath="//*[@id=\"SearchLastName\"]")
	WebElement lastName;


	///// Action methods to enter Email Address

	public void enterEmail(String email) {
		emailAdd.sendKeys(email);
	}

	///// Action methods to perform click action on Search Button
	public void clickOnSearchButton() {
		searchbtn.click();
	}

	/// Action method to search customer by email

	public boolean searchCustomerByEmail(String email)
	{
		boolean found= false;

		//total no of rows in grid
		int ttlrows = tableRows.size();

		//total no of columns
		//int ttlColumns= tableColumns.size();

		for(int i=1; i<=ttlrows; i++) {

			System.out.println("Seraching Row:" +i);


			WebElement webElementEmail= ldriver.findElement(By.xpath("//table[@id='customers-grid']//tbody/tr["+i+"]/td[2]"));

			String actualEmailAdd = webElementEmail.getText();
			System.out.println(actualEmailAdd);

			if(actualEmailAdd.equals(email)) {
				found=true;
			}

		}
		return found;
	}

	/// Search Customer By Name ///////
	/// Action method to enter first name ///
	public void enterFirstName(String firstNameText) {
		firstName.sendKeys(firstNameText);
	}

	/// Action method to enter last name ///
	public void enterLastName(String lastNameText) {
		lastName.sendKeys(lastNameText);
	}

	/// Action method to search customer by name
	public boolean searchCustomerByName(String name)
	{
		boolean found= false;

		//total no of rows in grid
		int ttlrows = tableRows.size();

		for(int i=1; i<=ttlrows; i++) {
			
			WebElement webElementName= ldriver.findElement(By.xpath("//table[@id='customers-grid']//tbody/tr["+i+"]/td[3]"));

			String actualName = webElementName.getText();
			if(actualName.equals(name)) {
				found=true;
				break;
			}

		}
		return found;
	}

}
