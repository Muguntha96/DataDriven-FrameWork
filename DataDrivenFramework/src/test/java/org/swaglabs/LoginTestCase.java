
  package org.swaglabs;
  
  
  import org.openqa.selenium.By; 
  import org.openqa.selenium.WebDriver;
  import org.openqa.selenium.chrome.ChromeDriver;
  
  import org.testng.annotations.DataProvider;
  
  import org.testng.annotations.Test;
  
  
  public class LoginTestCase {
  
  String[][] array = { { "standard_user", "secret_sauce" },
		  {"locked_out_user", "secret_sauce" },
   { "problem_user", "secret_sauce" }, 
   {"performance_glitch_user", "secret_sauce" } 
   };
  
  @DataProvider(name = "loginData")
  public String[][] loginDataProvider() {
  return array;
  
  }
  
  @Test(dataProvider = "loginData") 
  public void launchBrowser(String username,String pword) throws InterruptedException {
  System.setProperty("webdriver.chrome.driver",
  "C:\\Users\\BCP\\eclipse-workspace\\SauceLabs\\Driver\\chromedriver.exe");
  WebDriver driver=new ChromeDriver();
  driver.get("https://www.saucedemo.com/");
  driver.manage().window().maximize();
  driver.findElement(By.id("user-name")).sendKeys(username);
  driver.findElement(By.id("password")).sendKeys(pword);
  driver.findElement(By.id("login-button")).click(); Thread.sleep(3000);
  driver.quit(); }
  
  
  
  }
 