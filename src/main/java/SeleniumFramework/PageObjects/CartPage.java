package SeleniumFramework.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SeleniumFramework.AbstractComponent.AbstractComponent;

public class CartPage extends AbstractComponent {

	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".cartSection h3")
	List<WebElement> cartProducts;

	// driver.findElement(By.xpath("//li/button[@type='button']")).click();

	@FindBy(xpath = "//li/button[@type='button']")
	WebElement checkout;

	public boolean verifyProductDisplay(String productName) {
		boolean match = cartProducts.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public CheckOutPage goToCheckOutPage()
	{
		checkout.click();
		CheckOutPage chekoutpage=new CheckOutPage(driver);
		return chekoutpage;
	}

}
