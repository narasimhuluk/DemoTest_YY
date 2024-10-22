package SeleniumFramework.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SeleniumFramework.AbstractComponent.AbstractComponent;

public class ProductCatelogPage extends AbstractComponent {

	WebDriver driver;

	public ProductCatelogPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

	@FindBy(css = ".mb-3")
	List<WebElement> products;

	By byProduct = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastMsg = By.cssSelector("#toast-container");
	By animation = By.cssSelector(".ng-animating");

//	WebElement prod = products.stream()
//	.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst()
//	.orElse(null);

	public List<WebElement> getProductList() {
		waitForElementToBeAppear(byProduct);
		return products;
	}

	public WebElement getProductName(String productName) {
		WebElement prod = products.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst()
				.orElse(null);
		return prod;
	}

	public void addProductToCart(String productName) {
		WebElement prod = getProductName(productName);
		prod.findElement(addToCart).click();
		waitForElementToBeAppear(toastMsg);
		waitForElementToBeInVisable(animation);
	}

}
