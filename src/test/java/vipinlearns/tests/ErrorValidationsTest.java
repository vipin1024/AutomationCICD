package vipinlearns.tests;

import org.testng.annotations.Test;

import vipinlearns.TestComponents.Retry;

import org.testng.AssertJUnit;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import vipinlearns.TestComponents.BaseTest;
import vipinlearns.pageobjects.CartPage;
import vipinlearns.pageobjects.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest {
	

    @Test(groups= {"ErrorHandling"}, retryAnalyzer= Retry.class)
	public void loginErrorValidation() throws IOException, InterruptedException
    {
    	String productName = "ZARA COAT 3";		
		landingPage.loginApplication("vipin1@gmail.com", "hgcfvbjk");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());

	}
    
    @Test
	public void productErrorValidation() throws IOException, InterruptedException
    {
    	String productName = "ZARA COAT 3";		
		ProductCatalogue productCatalogue = landingPage.loginApplication("vipin2@gmail.com", "Vipin@1024");

		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.verifyProductDisplay("ZARA COAT 32");
		Assert.assertFalse(match);
	}

}