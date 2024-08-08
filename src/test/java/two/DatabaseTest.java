package two;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class DatabaseTest {

	WebDriver driver;
	Logger log = LogManager.getLogger(Test.class.getName());

	@Test
	public void methodSix() throws SQLException {

		// Connect to MySQL Database
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/automationtesting", "root",
				"12345");

		if (!connection.isClosed()) {
			System.out.println("We have successfully connected to MySQL Database");
		}

		// Get the data from database
		Statement statement = connection.createStatement();

		ResultSet resultset = statement.executeQuery("SELECT * FROM automationtesting.credentials;");

		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://maximum.md/ro/");
		driver.findElement(By.xpath("//a[@title='Cabinet personal']")).click();
		driver.findElement(By.xpath("//input[@name='login']")).click();

		//input login and password from database
		if (resultset.next()) {

			driver.findElement(By.xpath("//input[@name='login']")).sendKeys(resultset.getString("UserName"));
			driver.findElement(By.xpath("//input[@name='password']")).sendKeys(resultset.getString("Password"));
			resultset.close();
			driver.findElement(By.xpath("//button[@class='login__form__footer__submit']")).click();
		}

		Boolean expectedMessage = driver.findElement(By.xpath("//p[@class='login__form__field__message']"))
				.isDisplayed();
		Assert.assertTrue(expectedMessage);
	}

	@AfterMethod
	public void closure() {
		driver.close();
	}
}
