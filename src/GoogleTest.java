import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class GoogleTest {
	WebDriver driver;
	String baseUrl = "http://www.google.com";

	@BeforeClass()
	public void setup() {

		System.setProperty("webdriver.chrome.driver", "/Applications/chromedriver");
		driver = new ChromeDriver();
	}

	@Test(priority = 1)
	public void testHomepagetitle() {

		// creating an object of a selenium webdriver
		SoftAssert sa = new SoftAssert();

		driver.navigate().to(baseUrl);
		String actualTitle = driver.getTitle();
		sa.assertEquals(actualTitle, "Google");

		sa.assertAll();

	}

	@Test(priority = 2)
	public void pagetitleSignIn() throws InterruptedException {
		driver.navigate().to(baseUrl);
		WebElement btn = driver.findElement(By.id("gb_70"));
		btn.click();

		String actualTitle = driver.getTitle();
		Assert.assertEquals(actualTitle, "Sign in - Google Accounts");
		Thread.sleep(2000);
		
		driver.navigate().back();
		Thread.sleep(2000);
		driver.navigate().forward();
		

	}

	@Test(priority = 3)
	public void pagetitleforSearch() throws InterruptedException {
		driver.navigate().to(baseUrl);
		driver.findElement(By.name("q")).sendKeys("cars");
		Thread.sleep(4000);
		driver.findElement(By.name("q")).sendKeys(Keys.ENTER);

		String actualTitle = driver.getTitle();
		Assert.assertTrue(actualTitle.contains("cars"));
		

	}

	@Test(priority=4)
	public void testImage() throws InterruptedException {
		driver.navigate().to(baseUrl);
		driver.findElement(By.name("q")).sendKeys("cars");
		Thread.sleep(4000);
		driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
		driver.findElement(By.xpath("//*[@id='hdtb-msb-vis']/div[2]")).click();
		
		String img = driver.findElement(By.xpath("//*[@id='abar_button_ss']/span[1]")).getText();
		Assert.assertTrue(img.contains("SafeSearch"));
		
		
		
	}
	
	@Test(priority=5)
	public void testJcp() {
		driver.get("http://www.jcpenny.com");
		driver.findElement(By.cssSelector("[data-automation-id='account-signin']")).click();
		driver.findElement(By.cssSelector("[data-automation-id='account-signin']")).click();
		
		
	}
	@AfterTest
	public void tearDown() {
		driver.close();
	}
}