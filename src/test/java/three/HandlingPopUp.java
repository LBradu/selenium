package three;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

@Test
public class HandlingPopUp {
WebDriver driver = new ChromeDriver();
	
	public void method() throws InterruptedException {
	
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	driver.get("https://www.invitro.md/ro");
	
	driver.findElement(By.xpath("//header/div[1]/div[1]/div[1]/div[4]/a[1]")).click();
	//to display pop up
	driver.findElement(By.xpath("//body[1]/div[7]/div[1]/div[1]/div[2]/div[1]/div[3]/div[2]/div[1]/div[1]/div[1]/div[2]/div[2]/button[1]/span[1]")).click();
	
	driver.findElement(By.className("analyzes-add-inner")).click();
	Thread.sleep(3000);
	
	driver.close();
	}
}