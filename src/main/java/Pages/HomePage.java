package Pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import BaseClass.BasePage;

public class HomePage extends BasePage {
	
	@FindBy(xpath= "//*[text()='Contact']")
	WebElement ContactPage;
	
	@FindBy(xpath= "//img[@src=\"images/logo.png\"]")
	WebElement brandlogo;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		}
	public boolean verifylogo() {
		boolean logo= brandlogo.isDisplayed();
		return logo;
	}
	public void click_contact() {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(ContactPage));
		ContactPage.click();
	
	}
	

	
	

	


	
	
	
	

}
