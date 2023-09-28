package TestRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

//import io.cucumber.junit.Cucumber;
//import io.cucumber.junit.CucumberOptions;

//@RunWith(Cucumber.class)
@CucumberOptions(
		features ={".//Features/LoginFeature.feature",".//Features/Customers.feature"},
		//features =".//Features/",
		glue="StepDefinition",
		dryRun= false,
		tags="@sanity",
		monochrome=true,
		//plugin= {"pretty","html:target/cucumber-reports/report1.html"}
		plugin= {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}
		)

//plugin= {"pretty","html:target/cucumber-reports/report1.html","json:target/cucumber-reports/report_json.json","junit:target/cucumber-reports/report_xml.xml"}

public class Run extends AbstractTestNGCucumberTests {

	/* this class will be empty*/
	
}
