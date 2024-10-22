package SeleniumFramework.Tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import SeleniumFramework.PageObjects.CartPage;
import SeleniumFramework.PageObjects.CheckOutPage;
import SeleniumFramework.PageObjects.ConfirmationOrder;
import SeleniumFramework.PageObjects.LandingPage;
import SeleniumFramework.PageObjects.MyOrdersPage;
import SeleniumFramework.PageObjects.ProductCatelogPage;
import SeleniumFramework.TestComponent.BaseTest;

public class SubmitOrderTest extends BaseTest {

	// String userName = "narasimhulu3k@gmail.com";
	// String password = "Narasimha@444";
	String productName = "ZARA COAT 3";
	String country = "India";

	@Test(dataProvider = "getData", groups = { "purchaseOrder" })
	public void SubmitOrder(HashMap<String, String> map) throws IOException {
		// TODO Auto-generated method stub

		ProductCatelogPage prodcat = LP.LoginApplication(map.get("username"), map.get("password"));
		List<WebElement> products = prodcat.getProductList();
		prodcat.addProductToCart(map.get("productname"));
		CartPage cartPage = prodcat.goToCart();
		boolean match = cartPage.verifyProductDisplay(map.get("productname"));
		Assert.assertTrue(match);
		CheckOutPage chekoutpage = cartPage.goToCheckOutPage();
		chekoutpage.SelectCountry(country);
		ConfirmationOrder confirmOrder = chekoutpage.clickOnplaceOrder();
		String confirmMessage = confirmOrder.confirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}

	@Test(dependsOnMethods = { "SubmitOrder" })
	public void verifyOrderPageDisplayed() {
		LP.LoginApplication("narasimhulu3k@gmail.com", "Narasimha@444");
		MyOrdersPage orders = LP.goToOrders();
		Boolean orderedProduct = orders.myOrders(productName);
		Assert.assertTrue(orderedProduct);

	}

	// **Dataprovider normal method **
//	@DataProvider
//	public Object[][] getData() {
//		return new Object[][] { { "narasimhulu3k@gmail.com", "Narasimha@444", "ZARA COAT 3" },
//				{ "narasimhulu3k@gmail.com", "Narasimha@444", "ADIDAS ORIGINAL" } };
//	}

	@DataProvider
	public Object[][] getData() throws IOException {

//		HashMap<String, String> map1 = new HashMap<String, String>();
//		map1.put("username", "narasimhulu3k@gmail.com");
//		map1.put("password", "Narasimha@444");
//		map1.put("productname", "ZARA COAT 3");
//
//		HashMap<String, String> hmap1 = new HashMap<String, String>();
//		hmap1.put("username", "narasimhulu3k@gmail.com");
//		hmap1.put("password", "Narasimha@444");
//		hmap1.put("productname", "ADIDAS ORIGINAL");
		// return new Object[][] { { map1 }, { hmap1 } };

		List<HashMap<String, String>> data = getJosDataToMap(
				System.getProperty("user.dir") + "\\src\\test\\java\\SeleniumFramework\\Data\\PurchaseOrder.json");
		return new Object[][] { { data.get(0) }, { data.get(1) } };
	}

}
