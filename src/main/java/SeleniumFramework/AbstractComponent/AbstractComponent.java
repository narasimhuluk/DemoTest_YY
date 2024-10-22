package SeleniumFramework.AbstractComponent;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import SeleniumFramework.PageObjects.CartPage;
import SeleniumFramework.PageObjects.MyOrdersPage;

public class AbstractComponent {

	WebDriver driver;

	public AbstractComponent(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// driver.findElement(By.cssSelector("[routerlink='/dashboard/cart']")).click();

	@FindBy(css = "[routerlink='/dashboard/cart']")
	WebElement gotoCart;

	@FindBy(css = "[routerlink='/dashboard/myorders']")
	WebElement goToOrders;

	public void waitForElementToBeAppear(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}

	public void waitForWebElementToBeAppear(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void waitForElementToBeInVisable(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(findBy));
	}

	public void scrollPage(String scroll) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript(scroll);
	}

	public CartPage goToCart() {
		gotoCart.click();
		CartPage cartPage = new CartPage(driver);
		return cartPage;
	}

	public MyOrdersPage goToOrders() {

		goToOrders.click();
		MyOrdersPage myorders = new MyOrdersPage(driver);
		return myorders;
	}
}
