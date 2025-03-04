package vipinlearns.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import org.testng.AssertJUnit;
import io.github.bonigarcia.wdm.WebDriverManager;
import vipinlearns.TestComponents.BaseTest;
import vipinlearns.pageobjects.CartPage;
import vipinlearns.pageobjects.CheckoutPage;
import vipinlearns.pageobjects.ConfirmationPage;
import vipinlearns.pageobjects.LandingPage;
import vipinlearns.pageobjects.OrdersPage;

import vipinlearns.pageobjects.ProductCatalogue;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SubmitOrderTest extends BaseTest {
	String productName = "ZARA COAT 3";		

    @Test(dataProvider= "getData", groups= {"Purchase"})
	public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException
    {
		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password") );

		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("productName"));
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.verifyProductDisplay(input.get("productName"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("india");
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();

		driver.findElement(By.cssSelector(".action__submit")).click();

		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}

    @Test(dependsOnMethods= {"submitOrder"})
    public void orderHistoryTest()
    {
	ProductCatalogue productCatalogue = landingPage.loginApplication("vipin1@gmail.com", "Vipin@1024");
	OrdersPage ordersPage= productCatalogue.goToOrdersPage();
	Assert.assertTrue(ordersPage.verifyOrdersDisplay(productName));

    }
    

    
    
    @DataProvider
    public Object[][] getData() throws IOException {
        List<HashMap<String, String>> data = getJsonDataToMap(
            System.getProperty("user.dir") + "//src//test//java//vipinlearns//data//PurchaseOrder.json"
        );
        System.out.println("Test Data: " + data);  // Debugging statement
        return new Object[][] { { data.get(0) } }; // Ensuring only 1 test runs
    }
    
}



    
    
//    @DataProvider
//    public Object[][] getData()
//    {
//    	return new Object [][] { {"vipin1@gmail.com", "Vipin@1024", "IPHONE 13 PRO" }, {"vipin2@gmail.com", "Vipin@1024", "BANARSI SAREE"} };
//    }
//   
//	HashMap<String,String> map= new HashMap<String,String>();
//	map.put("email", "vipin1@gmail.com");
//	map.put("password", "Vipin@1024");
//	map.put("productName", "IPHONE 13 PRO");
//	
//	HashMap<String,String> map1= new HashMap<String,String>();
//	map1.put("email", "vipin2@gmail.com");
//	map1.put("password", "Vipin@1024");
//	map1.put("productName", "BANARSI SAREE");
