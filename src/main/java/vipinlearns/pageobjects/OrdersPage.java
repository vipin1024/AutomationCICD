package vipinlearns.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import vipinlearns.AbstractComponents.AbstractComponent;

public class OrdersPage extends AbstractComponent {

	WebDriver driver;

	@FindBy(css = ".totalRow button")
	WebElement checkoutEle;
	
	@FindBy(css = "tr td:nth-child(3)")
	private List<WebElement> productNames;

	
	public OrdersPage(WebDriver driver) {
		// initialization
		super(driver);

		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	public Boolean verifyOrdersDisplay(String productName)
	{
		Boolean match = productNames.stream().anyMatch(product->product.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public CheckoutPage goToCheckout()
	{
		checkoutEle.click();
		return new CheckoutPage(driver);
	}
	
}
