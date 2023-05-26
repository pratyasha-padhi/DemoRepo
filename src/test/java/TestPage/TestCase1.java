package TestPage;

import java.util.Properties;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import BaseClass.BasePage;
import Pages.ContactPage;
import Pages.HomePage;

public class TestCase1 {
	public BasePage basePage;
	public WebDriver driver;
	public Properties prop;
	public HomePage homePage;
	public ContactPage contactPage;
	String forenameerror_expected = "Forename is required";
	String emailerror_expected = "Email is required";
	String msgerror_expected = "Message is required";

	@BeforeClass
	public void setUp() {
		basePage = new BasePage();
		prop = basePage.init_properties();
		driver = basePage.init_driver("chrome");
		driver.get(prop.getProperty("url"));

	}

	@Test(priority = 1)
	public void goto_contactpage() throws InterruptedException {
		homePage = new HomePage(driver);
		homePage.click_contact();
//		Assert.assertTrue(contactPage.page_validation());
		System.out.println("User is in contact page");
	}

	@Test(priority = 2)
	public void click_submit() {
		contactPage = new ContactPage(driver);
		contactPage.clickonsubmit();
		System.out.println("submitted the form");

	}

	@Test(priority = 3)
	public void errormsg_validation() {
		contactPage = new ContactPage(driver);
		Assert.assertEquals(forenameerror_expected, contactPage.ForenameError_gettext());
		Assert.assertEquals(emailerror_expected, contactPage.EmailError_gettext());
		Assert.assertEquals(msgerror_expected, contactPage.MessageError_gettext());
		System.out.println("error messgages are succesfully verified ");

	}

	@Test(priority = 4)
	public void errormsg_gone() throws InterruptedException {

		contactPage = new ContactPage(driver);

		try {
			contactPage.createNewContact("Pratyasha", "pratyasha.padhi@gmail.com", "Test Message");
			System.out.println("Populated mandatory fields");

			Thread.sleep(5000);
			Assert.assertFalse(contactPage.forenameerror_display());
			Assert.assertFalse(contactPage.emailerror_display());
			Assert.assertFalse(contactPage.msgerror_display());
		} catch (NoSuchElementException e) {
			System.out.println("Error msgs are gone");
		}

	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}

}
