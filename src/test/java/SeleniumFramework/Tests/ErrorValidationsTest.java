package SeleniumFramework.Tests;

import java.io.IOException;
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

import SeleniumFramework.PageObjects.CartPage;
import SeleniumFramework.PageObjects.CheckOutPage;
import SeleniumFramework.PageObjects.ConfirmationOrder;
import SeleniumFramework.PageObjects.LandingPage;
import SeleniumFramework.PageObjects.ProductCatelogPage;
import SeleniumFramework.TestComponent.BaseTest;
import SeleniumFramework.TestComponent.Retry;

public class ErrorValidationsTest extends BaseTest {

	String userName = "narasimhulu3k@gmail.com";
	String password = "rsimha@444";
	String password2 = "Narasimha@444";
	String productName = "IPHONE 13 PRO";

	@Test(groups = { "errorvalidation" }, retryAnalyzer = Retry.class)
	public void LoginErrorValidation() throws IOException {

		LP.LoginApplication(userName, password);
		String erroeMsg = LP.errorMsg();
		Assert.assertTrue(erroeMsg.equalsIgnoreCase("Incorrect email or password."));

	}

	@Test
	public void ProductErrorValidation() {

		ProductCatelogPage prodcat = LP.LoginApplication(userName, password2);
		List<WebElement> products = prodcat.getProductList();
		prodcat.addProductToCart(productName);
		CartPage cartPage = prodcat.goToCart();
		boolean match = cartPage.verifyProductDisplay(productName);
		Assert.assertTrue(match);
	}

}
