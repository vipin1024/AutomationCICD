package vipinlearns.stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import vipinlearns.TestComponents.BaseTest;
import vipinlearns.pageobjects.CartPage;
import vipinlearns.pageobjects.CheckoutPage;
import vipinlearns.pageobjects.ConfirmationPage;
import vipinlearns.pageobjects.LandingPage;
import vipinlearns.pageobjects.ProductCatalogue;

import java.io.IOException;
import java.util.List;

public class StepDefintionImpl extends BaseTest {

    public LandingPage landingPage;
    public ProductCatalogue productCatalogue;
    public ConfirmationPage confirmationPage;

    @Given("I landed on Ecommerece page")
    public void I_landed_on_Ecommerece_page() throws IOException {
        landingPage = launchApplication(); // Ensure this is called
    }

    @Given("^Logged in with username (.+) and password (.+)$")
    public void logged_in_username_and_password(String username, String password) {
        productCatalogue = landingPage.loginApplication(username, password);
    }

    @When("^I add prdouct (.+) to cart$")
    public void I_add_prdouct_to_cart(String productName) throws InterruptedException {
        List<WebElement> products = productCatalogue.getProductList();
        productCatalogue.addProductToCart(productName);
    }

    @When("^Checkout (.+) and submit the order$")
    public void Checkout_and_submit_the_order(String productName) {
        CartPage cartPage = productCatalogue.goToCartPage();
        Boolean match = cartPage.verifyProductDisplay(productName);
        Assert.assertTrue(match);
        CheckoutPage checkoutPage = cartPage.goToCheckout();
        checkoutPage.selectCountry("india");
        confirmationPage = checkoutPage.submitOrder();
    }

    @Then("{string} message is displayed on ConfirmationPage")
    public void message_is_displayed_ConfirmationPage(String string) {
        String confirmMessage = confirmationPage.getConfirmationMessage();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
        driver.close();
    }
    
    @Then ("^\"([^\"]*)\" message is displayed$")
    public void something_message_is_displayed(String strArg1) throws Throwable{
    	Assert.assertEquals(strArg1, landingPage.getErrorMessage());
    	driver.close();
    }
}



