package SeleniumFramework.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SeleniumFramework.AbstractComponent.AbstractComponent;

public class LandingPage extends AbstractComponent {

	WebDriver driver;

	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "userEmail")
	WebElement userName;

	@FindBy(id = "userPassword")
	WebElement Password;

	@FindBy(id = "login")
	WebElement Login;

	@FindBy(css = ".toast-error")
	WebElement errorMsg;

	public ProductCatelogPage LoginApplication(String username, String password) {
		userName.sendKeys(username);
		Password.sendKeys(password);
		Login.click();
		ProductCatelogPage prodcat = new ProductCatelogPage(driver);
		return prodcat;
	}

	public String errorMsg() {
		waitForWebElementToBeAppear(errorMsg);
		return errorMsg.getText();
	}

	public void GoTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}

}
