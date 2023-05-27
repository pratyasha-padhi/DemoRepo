package BaseClass;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {

	public WebDriver driver;
	public Properties prop;

	public WebDriver init_driver(String browserName)  {

		WebDriverManager.chromedriver().setup();
		if (browserName.equals("chrome") || browserName.equals("ch")) {
			driver = new ChromeDriver();
		} else if (browserName.equals("edge") || browserName.equals("ed")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		
	driver.manage().window().maximize();
	driver.manage().deleteAllCookies();

	driver.manage().timeouts().pageLoadTimeout(Integer.parseInt(prop.getProperty("pageloadtimeout")),
			TimeUnit.SECONDS);
	driver.manage().timeouts().implicitlyWait(Integer.parseInt(prop.getProperty("implicitwait")),
			TimeUnit.SECONDS);
		
	return driver;
	}
	public Properties init_properties() {
		prop = new Properties();
		try {
			FileInputStream ip = new FileInputStream("/Users/praty/OneDrive/Documents/Java Training/TechAssessment/src/main/java/Config/config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return prop;
	} 

}

