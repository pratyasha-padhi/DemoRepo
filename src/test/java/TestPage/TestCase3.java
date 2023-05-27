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
import Pages.ShopPage;

public class TestCase3 {

	public BasePage basePage;
	public WebDriver driver;
	public Properties prop;
	public HomePage homePage;
	public ContactPage contactPage;
	public ShopPage shopPage;

	@BeforeClass
	public void setUp() {
		basePage = new BasePage();
		prop = basePage.init_properties();
		driver = basePage.init_driver("chrome");
		driver.get(prop.getProperty("url"));
	}

	@Test(priority = 1)
	public void addtocart() {
		shopPage = new ShopPage(driver);
		shopPage.click_Shop();
		String title = driver.getTitle();
		Assert.assertEquals(title, "Jupiter Toys");
		System.out.println("User is in Shop page");
		shopPage.buystuffs();
		System.out.println("products are added to cart");
		shopPage.click_Cart();
		shopPage.updateQty("2", "5", "3");
		System.out.println("Quantities are updated in cart");

	}

	@Test(priority = 2)
	public void subtotal() throws InterruptedException {
		shopPage = new ShopPage(driver);
		shopPage.SFSubtotalVerification();
		shopPage.VBSubtotalVerification();
		shopPage.FBSubtotalVerification();
		Thread.sleep(2000);

	}

	@Test(priority = 3)
	public void price_verification() throws InterruptedException {
		shopPage = new ShopPage(driver);
		shopPage.VerifyEachProductPrice();

	}
	
	@Test(priority = 4)
	public void totalprice_verification() throws InterruptedException {
		shopPage = new ShopPage(driver);
		shopPage.VerifyTotalPrice();

	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}

}
