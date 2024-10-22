package SeleniumFramework.PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SeleniumFramework.AbstractComponent.AbstractComponent;

public class MyOrdersPage extends AbstractComponent {

	WebDriver driver;

	public MyOrdersPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}


	@FindBy(xpath = "//tr/td[2]")
	List<WebElement> myOrders;

	public Boolean myOrders(String productName) {

		Boolean product = myOrders.stream().anyMatch(s -> s.getText().equalsIgnoreCase(productName));
		return product;
	}

}
