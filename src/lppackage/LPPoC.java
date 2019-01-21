package lppackage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions; //~for explicit waits
import org.openqa.selenium.support.ui.WebDriverWait;      //~for explicit waits
import java.util.concurrent.TimeUnit;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;


public class LPPoC {

	String baseUrl = "https://staging.clueylearning.com.au";
	WebDriver driver;
	WebDriverWait myWaitVar;
	String pageTitle = "";
	
	
	@BeforeTest
	public void beforeTest() {
		System.setProperty("webdriver.chrome.driver","C:\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(baseUrl);
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS); //~setting Implicit Wait
		myWaitVar = new WebDriverWait(driver, 10);                      //~Setting explicit Wait
	}
	
	@Test(priority = 0)
	public void verifySessionLandingPage() {
		//Verify home page title
		String expectedTitle = "Online Tutoring | Find Expert Private Tutors in Australia | Cluey Learning";
		
		//explicit wait for page to load by waiting for the pricing link to appear
		myWaitVar.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Pricing")));
		pageTitle = driver.getTitle();
		System.out.println(pageTitle);
		Assert.assertEquals(expectedTitle, pageTitle);
				
	}
	
	@Test(priority = 1)
	public void verifyMath7Selected() {
		driver.findElement(By.linkText("Subjects")).click();
		driver.findElement(By.xpath("//a[contains(@href, '/programs/?discipline=mathematics&yearlevel=year-7')]")).click();
		
	}
	
	@AfterTest
	public void afterTest() {
	  //driver.close();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	<input id="Email" type="email" <span class="html-attribute-name">value</span>="" <span class="html-attribute-name">spellcheck</span>="<span class="html-attribute-value">false</span>" class="emailClass" 
	autofocus="" <span class="html-attribute-name">name</span>="<span class="html-attribute-value">Email</span>" placeholder="Enter your email"/>
	*/
	
	
}
