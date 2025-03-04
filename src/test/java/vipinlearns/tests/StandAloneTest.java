package vipinlearns.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import vipinlearns.pageobjects.LandingPage;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class StandAloneTest {

	public static void main(String[] args) {
		String productName= "ADIDAS ORIGINAL";
    WebDriverManager.chromedriver().setup();
    WebDriver driver= new ChromeDriver();
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    driver.manage().window().maximize();
    LandingPage landingpage= new LandingPage(driver);
    landingpage.goTo();
    landingpage.loginApplication("vipin1@gmail.com", "Vipin@1024");
   
    WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(5));
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
    List<WebElement>  products= driver.findElements(By.cssSelector(".mb-3"));
    
    WebElement prod= products.stream().filter(product->
    product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
    prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();

    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
    wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
    
//  wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ngx-spinner-overlay")));
// Wait for the cart button to be clickable
//  WebElement cartButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[routerlink*='cart']")));

    driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
    
    List<WebElement> cartProducts= driver.findElements(By.cssSelector(".cartSection h3"));
    Boolean match = cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
    Assert.assertTrue(match);
    driver.findElement(By.cssSelector(".totalRow button")).click();
    
    Actions a =new Actions(driver);
    a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
    driver.findElement(By.xpath("(//button[contains(@class, 'ta-item')])[2]")).click();
    driver.findElement(By.cssSelector(".action__submit")).click();
    
    String confirmMessage= driver.findElement(By.cssSelector(".hero-primary")).getText();
    Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
    driver.close();

    
	}

}