package SeleniumFramework.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SeleniumFramework.AbstractComponent.AbstractComponent;

public class CheckOutPage extends AbstractComponent {

	WebDriver driver;
	String scroll = "window.scrollBy(0,800)";

	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "[placeholder='Select Country']")
	WebElement Country;

	@FindBy(css = ".list-group-item span")
	List<WebElement> ListCountries;

	// driver.findElement(By.cssSelector(".action__submit")).click();

	@FindBy(css = ".action__submit")
	WebElement placeOrder;

	public void countryName(String country) {
		Country.sendKeys(country);
	}

	public void SelectCountry(String country) {
		countryName(country);
		WebElement element = ListCountries.stream().filter(s -> s.getText().equals("India")).findAny().orElse(null);
		element.click();
	}

	public ConfirmationOrder clickOnplaceOrder() {
		scrollPage("window.scrollBy(0,2000)");
		waitForWebElementToBeAppear(placeOrder);
		placeOrder.click();
		ConfirmationOrder confirmOrder = new ConfirmationOrder(driver);
		return confirmOrder;
	}

}
