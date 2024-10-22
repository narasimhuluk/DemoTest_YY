package SeleniumFramework.Tests;

import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import SeleniumFramework.PageObjects.LandingPage;

public class StandaloneTest {

	@Test
	public void standAloneTest() {
		// TODO Auto-generated method stub

		String productName = "IPHONE 13 PRO";
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client");

		LandingPage lp = new LandingPage(driver);
		driver.findElement(By.id("userEmail")).sendKeys("narasimhulu3k@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Narasimha@444");
		driver.findElement(By.id("login")).click();

		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

		WebElement prod = products.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst()
				.orElse(null);

		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));

		driver.findElement(By.cssSelector("[routerlink='/dashboard/cart']")).click();

		List<WebElement> CartList = driver.findElements(By.cssSelector(".cartSection h3"));

		boolean match = CartList.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));

		Assert.assertTrue(match);

		driver.findElement(By.xpath("//li/button[@type='button']")).click();

		driver.findElement(By.cssSelector("[placeholder='Select Country']")).sendKeys("aus");

		List<WebElement> ListCountries = driver.findElements(By.cssSelector(".list-group-item span"));

		WebElement element = ListCountries.stream().filter(s -> s.getText().equals("Australia")).findAny().orElse(null);
		element.click();

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");

		driver.findElement(By.cssSelector(".action__submit")).click();

		String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();

		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

		driver.quit();
	}

}
