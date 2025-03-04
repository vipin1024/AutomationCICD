package vipinlearns.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import vipinlearns.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent{
	
	WebDriver driver;
	public LandingPage(WebDriver driver)
	{
		super(driver);
		//initialization
		this.driver=driver;
		PageFactory.initElements(driver, this);
	} 

	//WebElement userEmail= driver.findElement(By.id("userEmail"));
	//pageFactory
	
	@FindBy(id="userEmail")
	WebElement userEmail;

	@FindBy(id="userPassword")
	WebElement userPassword;
	
	@FindBy(css=".login-btn")
	WebElement submit;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement erorrMessage;
	
	public ProductCatalogue loginApplication (String email, String password)
	{
	userEmail.sendKeys(email);
	userPassword.sendKeys(password);
	submit.click();
    ProductCatalogue productCatalogue= new ProductCatalogue(driver);
    return productCatalogue;
	}
	
	public String getErrorMessage()
	{
		waitForWebElementToAppear(erorrMessage);
		erorrMessage.getText();
		return erorrMessage.getText();
	}

	public void goTo()
	{
	    driver.get("https://rahulshettyacademy.com/client");

	}
	
}
