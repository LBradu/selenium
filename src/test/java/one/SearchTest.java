package one;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class SearchTest {
	WebDriver driver;
	ExtentReports extent;
	Logger log = LogManager.getLogger(Test.class.getName());
	
	@BeforeMethod()
	public void opening() {

		String reportPath = System.getProperty("user.dir") + "\\reports\\index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);

		reporter.config().setReportName("Test Report");
		reporter.config().setDocumentTitle("Test Report Title");

		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tested by ", "L.Bradu");

	}
	// Verify searching with an existing Product Name
	@Test(priority = 1)
	public void methodOne() throws InterruptedException {
		log.debug("Test One started");
		ExtentTest etest = extent.createTest("Test One started");
		driver = new ChromeDriver();
		log.debug("Chrome browser got launched");
		etest.info("Chrome Browser Launched");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://maximum.md/ro/");
		log.debug("Access searchbox");
	    WebElement searchbox = driver.findElement(By.xpath("/html/body/div[5]/div/div/div[4]/div/form/div/span[2]/input"));
	    searchbox.click();
		//JavascriptExecutor
		JavascriptExecutor jse=(JavascriptExecutor)driver;
		log.debug("Insert Product Name");
		jse.executeScript("arguments[0].value = arguments[1]", searchbox, "Smartphone Samsung A145F/64 Galaxy A14 Silver" );
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		WebElement element;
		element = driver.findElement(By.xpath("/html/body/div[5]/div/div/div[4]/div/form/div/button/span"));
		executor.executeScript("arguments[0].click();", element);
		
		Thread.sleep(3000);
		log.info("'Rezultatul căutării' is displayed");
	    Boolean result = driver.findElement(By.xpath("//span[text()='Rezultatul căutării']")).isDisplayed();
	    Assert.assertTrue(result, "'Rezultatul cautarii' is displayed");
	   
		driver.findElement(By.xpath("//div[@class='product__item__title']")).click();
				
		extent.flush();

	}


	// Verify searching with a non existing Product Name
	@Test(priority = 2)
	public void methodTwo() throws InterruptedException {

		log.debug("Test Two started");
		ExtentTest etest = extent.createTest("Test Two started");
		log.debug("Chrome browser got launched");
		driver = new ChromeDriver();
		etest.info("Chrome Browser Launched");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://maximum.md/ro/");
		driver.findElement(By.xpath("/html/body/div[5]/div/div/div[4]/div/form/div/span[2]/input")).click();			
		driver.findElement(By.xpath("/html/body/div[5]/div/div/div[4]/div/form/div/span[2]/input")).sendKeys("silper");
		driver.findElement(By.xpath("/html/body/div[5]/div/div/div[4]/div/form/div/button/span")).click();
	
		
		log.info("NU AI GASIT PRODUSUL POTRIVIT? NOI TE AJUTĂM!");
		Boolean expectedresult = driver.findElement(By.xpath("//span[text()=' NU AI GASIT PRODUSUL POTRIVIT? NOI TE AJUTĂM!  ']")).isDisplayed();
		Assert.assertTrue(expectedresult, "NU AI GASIT PRODUSUL POTRIVIT? NOI TE AJUTĂM!");
		
		extent.flush();

	}

	// Verify searching without providing any Product Name

	@Test(priority = 3)
	public void methodThree() {

		log.debug("Test Three started");
		ExtentTest etest = extent.createTest("Test Three started");
		driver = new ChromeDriver();
		etest.info("Chrome Browser Launched");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://maximum.md/ro/");
		driver.findElement(By.xpath("/html/body/div[5]/div/div/div[4]/div/form/div/span[2]/input")).click();
		driver.findElement(By.xpath("/html/body/div[5]/div/div/div[4]/div/form/div/span[2]/input")).sendKeys(" ");
		driver.findElement(By.xpath("/html/body/div[5]/div/div/div[4]/div/form/div/button/span")).click();
		extent.flush();
	}

	// Verify searching by providing two products
	@Test(priority = 4)
	public void methodFour() throws InterruptedException {

		log.debug("Test Four started");
		ExtentTest etest = extent.createTest("Test Four started");
		log.debug("Chrome browser got launched");
		driver = new ChromeDriver();
		etest.info("Chrome Browser Launched");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://maximum.md/ro/");
		driver.findElement(By.xpath("/html/body/div[5]/div/div/div[4]/div/form/div/span[2]/input")).click();
		driver.findElement(By.xpath("/html/body/div[5]/div/div/div[4]/div/form/div/span[2]/input")).click();
		driver.findElement(By.xpath("/html/body/div[5]/div/div/div[4]/div/form/div/span[2]/input")).sendKeys("Masina de spalat Telefon");
		driver.findElement(By.xpath("/html/body/div[5]/div/div/div[4]/div/form/div/button/span")).click();
		
		log.info("NU AI GASIT PRODUSUL POTRIVIT? NOI TE AJUTĂM!");
		Boolean expectedresult = driver.findElement(By.xpath("//span[text()=' NU AI GASIT PRODUSUL POTRIVIT? NOI TE AJUTĂM!  ']")).isDisplayed();
		Assert.assertTrue(expectedresult, "NU AI GASIT PRODUSUL POTRIVIT? NOI TE AJUTĂM!");

		extent.flush();
	}

	// Verify Search using the text from the product description
	@Test(priority = 5)
	public void methodFive() {

		log.debug("Test Five started");
		ExtentTest etest = extent.createTest("Test Five started");
		log.debug("Chrome browser got launched");
		driver = new ChromeDriver();
		etest.info("Chrome Browser Launched");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://maximum.md/ro/");
		driver.findElement(By.xpath("/html/body/div[5]/div/div/div[4]/div/form/div/span[2]/input")).click();
		driver.findElement(By.xpath("/html/body/div[5]/div/div/div[4]/div/form/div/span[2]/input"));
		driver.findElement(By.xpath("/html/body/div[5]/div/div/div[4]/div/form/div/span[2]/input")).sendKeys("8 Gb");
		driver.findElement(By.xpath("/html/body/div[5]/div/div/div[4]/div/form/div/button/span")).click();		
		
		log.info("Rezultatul cautarii' is displayed");
		Boolean result = driver.findElement(By.xpath("//span[text()='Rezultatul căutării']")).isDisplayed();
	    Assert.assertTrue(result, "'Rezultatul cautarii' is displayed");
		extent.flush();
	}

	@AfterMethod
	public void closure() {
		driver.close();
		log.debug("Quitting the driver");

	}
}
