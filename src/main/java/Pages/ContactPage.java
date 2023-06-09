package Pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import BaseClass.BasePage;

public class ContactPage extends BasePage {

	@FindBy(xpath = "//a[contains(@class,'btn-contact')]")
	WebElement SubmitBtn;

	@FindBy(xpath = "//span[text()='Forename is required']")
	WebElement ForenameError;

	@FindBy(xpath = "//span[text()='Email is required']")
	WebElement EmailError;

	@FindBy(xpath = "//span[text()='Message is required']")
	WebElement MessageError;

	@FindBy(id = "forename")
	WebElement ForenameTextbox;

	@FindBy(name = "email")
	WebElement EmailTextbox;

	@FindBy(name = "message")
	WebElement MessageTextbox;

	@FindBy(xpath = "//strong[text()='Thanks Pratyasha']")
	WebElement SuccessMsg;

	public ContactPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}
	

	public void clickonsubmit() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(SubmitBtn));
		SubmitBtn.click();

	}

	

	public String ForenameError_gettext() {
		String Forename = ForenameError.getText();
		return Forename;
	}

	public String EmailError_gettext() {
		String Email = EmailError.getText();
		return Email;
	}

	public String MessageError_gettext() {
		String Message = MessageError.getText();
		return Message;
	}

	public void createNewContact(String Forename, String Email, String Message) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		wait.until(ExpectedConditions.visibilityOf(ForenameTextbox));
		ForenameTextbox.sendKeys(Forename);

		wait.until(ExpectedConditions.visibilityOf(EmailTextbox));
		EmailTextbox.sendKeys(Email);

		wait.until(ExpectedConditions.visibilityOf(MessageTextbox));
		MessageTextbox.sendKeys(Message);

	}

	public boolean forenameerror_display() {
		boolean forenameerror = ForenameError.isDisplayed();
		return forenameerror;

	}

	public boolean emailerror_display() {
		boolean emailerror = EmailError.isDisplayed();
		return emailerror;
	}

	public boolean msgerror_display() {
		boolean msgerror = MessageError.isDisplayed();
		return msgerror;
	}

	public String SuccessMsg_actualtext() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(SuccessMsg));
		String ThanksMsg = SuccessMsg.getText();
		System.out.println(ThanksMsg);
		return ThanksMsg;

	}

}