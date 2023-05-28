package TestPage;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import BaseClass.BasePage;
import Pages.ContactPage;
import Pages.HomePage;

public class TestCase2 {

	public BasePage basePage;
	public WebDriver driver;
	public Properties prop;
	public HomePage homePage;
	public ContactPage contactPage;

	@BeforeClass
	public void setUp() {
		basePage = new BasePage();
		prop = basePage.init_properties();
		driver = basePage.init_driver("chrome");
		driver.get(prop.getProperty("url"));

	}

	@Test (retryAnalyzer = Analyzer.RetryAnalyzer.class)
	public void verify_successmsg() throws InterruptedException {
		homePage = new HomePage(driver);
		homePage.click_contact();
//	Assert.assertTrue(contactPage.page_validation());
		System.out.println("User is in contact page");
		Thread.sleep(5000);

		contactPage = new ContactPage(driver);
		contactPage.createNewContact("Pratyasha", "pratyasha.padhi@gmail.com", "Test Message");
		System.out.println("Populated mandatory fields");
		Thread.sleep(3000);
		contactPage.clickonsubmit();
		Thread.sleep(10000);
		String successmsg_expected = "Thanks Pratyasha";
		Assert.assertEquals(successmsg_expected, contactPage.SuccessMsg_actualtext());
		System.out.println("Success messgage is verified ");
		homePage.click_contact();


	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}

}
