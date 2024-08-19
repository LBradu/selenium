package three;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;

import org.testng.annotations.Test;

public class TakingScreenshot {
	WebDriver driver;
	
	// Taking a screenshot
	@Test
	public void methodOne() throws InterruptedException, IOException {
	
		driver = new ChromeDriver();		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://maximum.md/ro/");
		File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(file, new File("screenshots//screenshot1.png"));		

	}

	@AfterMethod
	public void closure() {
		driver.close();

	}
}
