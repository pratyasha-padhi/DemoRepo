package Pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import BaseClass.BasePage;

public class ShopPage extends BasePage {

	@FindBy(xpath = "//*[text()='Shop']")
	WebElement ShopPage;

	@FindBy(xpath = "//h4[text()='Stuffed Frog']/parent::div/p/a[text()='Buy']")
	WebElement StuffedFrog;

	@FindBy(xpath = "//h4[text()='Fluffy Bunny']/parent::div/p/a[text()='Buy']")
	WebElement FluffyBunny;

	@FindBy(xpath = "//h4[text()='Valentine Bear']/parent::div/p/a[text()='Buy']")
	WebElement ValentineBear;

	@FindBy(xpath = "//*[contains(text(),'Cart')]")
	WebElement Cart;

	@FindBy(xpath = "//*[text()=' Stuffed Frog']/following-sibling::td[@class='ng-binding']/following-sibling::td/input[@name='quantity']")
	WebElement StuffedFrogQty;

	@FindBy(xpath = "//*[text()=' Fluffy Bunny']//following-sibling::td[@class='ng-binding']/following-sibling::td/input[@name='quantity']")
	WebElement FluffyBunnyQty;

	@FindBy(xpath = "//*[text()=' Valentine Bear']//following-sibling::td[@class='ng-binding']/following-sibling::td/input[@name='quantity']")
	WebElement ValentineBearQty;

	@FindBy(xpath = "//*[text()=' Stuffed Frog']/following-sibling::td[@class='ng-binding']/following-sibling::td[@class='ng-binding']")
	WebElement StuffedFrogSubtotal;

	@FindBy(xpath = "//*[text()=' Fluffy Bunny']/following-sibling::td[@class='ng-binding']/following-sibling::td[@class='ng-binding']")
	WebElement FluffyBunnySubtotal;

	@FindBy(xpath = "//*[text()=' Valentine Bear']/following-sibling::td[@class='ng-binding']/following-sibling::td[@class='ng-binding']")
	WebElement ValentineBearSubtotal;

	@FindBy(xpath = "//*[text()=' Stuffed Frog']/following-sibling::td")
	WebElement StuffedFrogCartPrice;

	@FindBy(xpath = "//*[text()=' Fluffy Bunny']/following-sibling::td")
	WebElement FluffyBunnyCartPrice;

	@FindBy(xpath = "//*[text()=' Valentine Bear']/following-sibling::td")
	WebElement ValentineBearCartPrice;

	@FindBy(xpath = "//h4[text()='Stuffed Frog']/parent::div/p/span")
	WebElement StuffedFrogShopPrice;

	@FindBy(xpath = "//h4[text()='Fluffy Bunny']/parent::div/p/span")
	WebElement FluffyBunnyShopPrice;

	@FindBy(xpath = "//h4[text()='Valentine Bear']/parent::div/p/span")
	WebElement ValentineBearShopPrice;

	@FindBy(xpath = "//strong[@class='total ng-binding']")
	WebElement TotalPrice;

	public ShopPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void click_Shop() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(ShopPage));
		ShopPage.click();
	}

	public void click_Cart() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(Cart));
		Cart.click();
	}

	public void buystuffs() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(StuffedFrog));
		StuffedFrog.click();
		wait.until(ExpectedConditions.elementToBeClickable(FluffyBunny));
		FluffyBunny.click();
		wait.until(ExpectedConditions.elementToBeClickable(ValentineBear));
		ValentineBear.click();
		wait.until(ExpectedConditions.elementToBeClickable(Cart));
		Cart.click();

	}

	public void updateQty(String SF, String FB, String VB) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(StuffedFrogQty));
		StuffedFrogQty.clear();
		FluffyBunnyQty.clear();
		ValentineBearQty.clear();

		StuffedFrogQty.sendKeys(SF);
		FluffyBunnyQty.sendKeys(FB);
		ValentineBearQty.sendKeys(VB);

	}

	public void FBSubtotalVerification() throws InterruptedException {
		Thread.sleep(2000);

		String FB_Price = FluffyBunnyCartPrice.getText();
		System.out.println("Cart price of FB is " + FB_Price);
		String FB_qty = FluffyBunnyQty.getAttribute("value");

		System.out.println("Quantity of FB is " + FB_qty);

		String FB_subtotal = FluffyBunnySubtotal.getText();
		System.out.println("Subtotal amount of FB is " + FB_subtotal);

		String FBpriceWithoutDollar = FB_Price.replaceAll("\\$", "");
		String FBsubtotalWithoutDollar = FB_subtotal.replaceAll("\\$", "");

		double FB_actualsubtotal = Double.parseDouble(FBsubtotalWithoutDollar);
		double FB_actualprice = Double.parseDouble(FBpriceWithoutDollar);
		double FB_actualqty = Double.parseDouble(FB_qty);

		double FB_expectedsubtotal = FB_actualqty * FB_actualprice;
		if (FB_actualsubtotal == FB_expectedsubtotal) {
			System.out.println("Subtotal verification passed for Fluffy Bunny");
		} else {
			System.out.println("Subtotal verification failed for Fluffy Bunny");

		}

	}

	public void SFSubtotalVerification() throws InterruptedException {
		Thread.sleep(2000);
		String SF_Price = StuffedFrogCartPrice.getText();
		System.out.println("Cart price of SF is " + SF_Price);

		String SF_qty = StuffedFrogQty.getAttribute("value");
		System.out.println("Quantity of SF is " + SF_qty);

		String SF_subtotal = StuffedFrogSubtotal.getText();
		System.out.println("Subtotal amount of SF is " + SF_subtotal);

		String SFpriceWithoutDollar = SF_Price.replaceAll("\\$", "");
		String SFsubtotalWithoutDollar = SF_subtotal.replaceAll("\\$", "");

		double SF_actualsubtotal = Double.parseDouble(SFsubtotalWithoutDollar);
		double SF_actualprice = Double.parseDouble(SFpriceWithoutDollar);
		double SF_actualqty = Double.parseDouble(SF_qty);

		double SF_expectedsubtotal = SF_actualqty * SF_actualprice;
		if (SF_actualsubtotal == SF_expectedsubtotal) {
			System.out.println("Subtotal verification passed for Stuffed Frog");
		} else {
			System.out.println("Subtotal verification failed for Stuffed Frog");

		}

	}

	public void VBSubtotalVerification() throws InterruptedException {
		Thread.sleep(2000);

		String VB_Price = ValentineBearCartPrice.getText();
		System.out.println("Cart price of VB is " + VB_Price);

		String VB_qty = ValentineBearQty.getAttribute("value");
		System.out.println("Quantity of VB is " + VB_qty);

		String VB_subtotal = ValentineBearSubtotal.getText();
		System.out.println("Subtotal amount of VB is " + VB_subtotal);

		String VB_actualpriceWithoutDollar = VB_Price.replaceAll("\\$", "");
		String VBsubtotalWithoutDollar = VB_subtotal.replaceAll("\\$", "");

		double VB_actualsubtotal = Double.parseDouble(VBsubtotalWithoutDollar);
		double VB_actualprice = Double.parseDouble(VB_actualpriceWithoutDollar);
		double VB_actualqty = Double.parseDouble(VB_qty);

		double VB_expectedsubtotal = VB_actualqty * VB_actualprice;

		if (VB_actualsubtotal == VB_expectedsubtotal) {
			System.out.println("Subtotal verification passed for Valentime Bear");
		} else {
			System.out.println("Subtotal verification failed for Valentime Bear");

		}

	}

	public void VerifyEachProductPrice() throws InterruptedException {
		Thread.sleep(3000);
		ShopPage.click();
		String FB_ShopPrice = FluffyBunnyShopPrice.getText();
		System.out.println("Shop Price of FB is " + FB_ShopPrice);

		String SF_ShopPrice = StuffedFrogShopPrice.getText();
		System.out.println("Shop Price of SF is " + SF_ShopPrice);

		String VB_ShopPrice = ValentineBearShopPrice.getText();
		System.out.println("Shop Price of VB is " + VB_ShopPrice);

		Cart.click();
		String FB_CartPrice = FluffyBunnyCartPrice.getText();
		System.out.println("Cart Price of FB is " + FB_CartPrice);

		String SF_CartPrice = StuffedFrogCartPrice.getText();
		System.out.println("Cart Price of SF is " + SF_CartPrice);

		String VB_CartPrice = ValentineBearCartPrice.getText();
		System.out.println("Cart Price of VB is " + VB_CartPrice);
		if (FB_ShopPrice.equalsIgnoreCase(FB_CartPrice) && SF_ShopPrice.equalsIgnoreCase(SF_CartPrice) && 
				VB_ShopPrice.equalsIgnoreCase(VB_CartPrice)) {
			System.out.println("Verified the price of each product");

		} else {
			System.out.println("Price verification of each product is failed ");

		}

	}

	public void VerifyTotalPrice() throws InterruptedException {
		Thread.sleep(3000);
		String totalprice = TotalPrice.getText();
		System.out.println("Total Price is " + totalprice);
		String VBsubtotalWithoutTotalText = totalprice.replaceAll("Total:", "");

		double actual_TotalPrice = Double.parseDouble(VBsubtotalWithoutTotalText);


		String FB_subtotal = FluffyBunnySubtotal.getText();
		String FBsubtotalWithoutDollar = FB_subtotal.replaceAll("\\$", "");
		double FB_actualsubtotal = Double.parseDouble(FBsubtotalWithoutDollar);

		String SF_subtotal = StuffedFrogSubtotal.getText();
		String SFsubtotalWithoutDollar = SF_subtotal.replaceAll("\\$", "");
		double SF_actualsubtotal = Double.parseDouble(SFsubtotalWithoutDollar);

		String VB_subtotal = ValentineBearSubtotal.getText();
		String VBsubtotalWithoutDollar = VB_subtotal.replaceAll("\\$", "");
		double VB_actualsubtotal = Double.parseDouble(VBsubtotalWithoutDollar);

		double Expected_TotalPrice = FB_actualsubtotal + SF_actualsubtotal + VB_actualsubtotal;
		if (actual_TotalPrice == Expected_TotalPrice) {
			System.out.println("Total price verification is passed");
		} else {
			System.out.println("Total price verification is failed");
		}
	}

}
