package proBook;
 
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
 
public class NewTest {
	// Déclaration des variables que nous allons utiliser dans ce script.
    String url = "http://probook.progideo.com";
    String expectedTitle1 = "Fil d'actualités - ProBook";
    String actualTitle1 ;
    String username = "aziz.im";
    String password = "Selenium123";
    String expectedTitle2 ;
    String actualTitle2 ;
    WebDriver driver;
    
    @DataProvider (name="Data")
    public static Object[][] data() {
    	return new Object[][] {
    		{"aziz.im", "Selenium123", "Fil d'actualités - ProBook"},   		
    	};
    }
    
 
	@Test(dataProvider = "Data")
	public void test(String username, String password, String expectedTitle2) {

        driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/a[1]")).click();
 
        actualTitle1 = driver.getTitle();

        Assert.assertEquals(actualTitle1, expectedTitle1);

        if (!actualTitle1.contentEquals(expectedTitle1)) {
            System.out.println("Test Failed");
        }
 
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("login_username")));
 

        driver.findElement(By.id("login_username")).sendKeys(username);
        driver.findElement(By.id("login_password")).sendKeys(password);
  
        driver.findElement(By.id("loginBtn")).click();
        
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("account-dropdown-link")));
        

        actualTitle2 = driver.getTitle();

        Assert.assertEquals(actualTitle2, expectedTitle2);
 
        if (!actualTitle2.contentEquals(expectedTitle2)) {
            System.out.println("Test Failed");
        }
	}
	
	@BeforeMethod
		public void beforeMethod() {
			
		       
		        System.setProperty("webdriver.gecko.driver","/home/progideo/Desktop/drivers/geckodriver");
		      
		        driver = new FirefoxDriver();
	
	        // Ouvrir la page "http://probook.progideo.com".
  
        driver.get(url);
	}
	
	@AfterMethod
	public void afterMethod() {

        driver.close();
	}
 
}