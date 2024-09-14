package three;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class NavigateBackForward {
	WebDriver driver = new ChromeDriver();
	
	public void method() throws InterruptedException {
	
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	driver.get("https://timpul.md/");
	
	driver.findElement(By.xpath("//a[text()='Politică']")).click();
	Thread.sleep(2000);
	driver.navigate().back();
	Thread.sleep(2000);
	driver.navigate().forward();
	
	//to verify if we get the proper title or not
	String actualTitle = driver.getTitle();
	String expectedTitle = "Politică – Stiri de ultima ora din Moldova – Ultimele stiri Timpul.md";
	
	Assert.assertEquals(actualTitle, expectedTitle );
	driver.close();
	}
}
