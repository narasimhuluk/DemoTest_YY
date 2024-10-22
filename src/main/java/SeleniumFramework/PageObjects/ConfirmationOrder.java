package SeleniumFramework.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SeleniumFramework.AbstractComponent.AbstractComponent;

public class ConfirmationOrder extends AbstractComponent {

	WebDriver driver;

	public ConfirmationOrder(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	//		String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();

	@FindBy(css = ".hero-primary")
	WebElement confirmMessage;


	public String confirmationMessage() 
	{
		return confirmMessage.getText();
	}

}
