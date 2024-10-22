package SeleniumFramework.StepDefinations;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import SeleniumFramework.PageObjects.CartPage;
import SeleniumFramework.PageObjects.CheckOutPage;
import SeleniumFramework.PageObjects.ConfirmationOrder;
import SeleniumFramework.PageObjects.LandingPage;
import SeleniumFramework.PageObjects.ProductCatelogPage;
import SeleniumFramework.TestComponent.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinations extends BaseTest {

	public LandingPage LP;
	public ProductCatelogPage prodcat;
	public List<WebElement> products;
	public ConfirmationOrder confirmOrder;

	@Given("I Landed on Ecommerce page.")
	public void i_landed_on_ecommerce_page() throws IOException {

		LP = launchApplication();

	}

	@Given("^Login with Username (.+) and (.+)$")
	public void login_with_username_and_password(String username, String password) {

		prodcat = LP.LoginApplication(username, password);
	}

	@When("^I add Product (.+) to cart$")
	public void i_add_product_to_cart(String productName) {

		products = prodcat.getProductList();
		prodcat.addProductToCart(productName);
	}

	@And("^Checkout (.+) and submit order$")
	public void checkout_and_submit_order(String productName) {
		CartPage cartPage = prodcat.goToCart();
		boolean match = cartPage.verifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckOutPage chekoutpage = cartPage.goToCheckOutPage();
		chekoutpage.SelectCountry("India");
		confirmOrder = chekoutpage.clickOnplaceOrder();
	}

	// Then "THANKYOU FOR THE ORDER." message is displayed on the ConformationPage.
	@Then("{string} message is displayed on the ConformationPage.")
	public void message_is_displayed_on_the_conformation_page(String string) {
		String confirmMessage = confirmOrder.confirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}

	@Then("{string} message is displayed")
	public void message_is_displayed(String string) {
		String erroeMsg = LP.errorMsg();
		Assert.assertTrue(erroeMsg.equalsIgnoreCase(string));

	}

}
