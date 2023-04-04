
package org.orangeHrm;

import static org.testng.Assert.assertEquals;

import java.io.FileInputStream;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class LoginProvider {
	WebDriver driver;

	@DataProvider(name = "loginDataProviders")
	// it will get the data from excel sheet
	public String[][] loginDataProvider() throws BiffException, IOException {
		String[][] data = getExcelSheet();
		return data;

	}

	// Now we have to take the data from the above rows and columns
	// and it will store the data in the loginDataProvider()

	public String[][] getExcelSheet() throws BiffException, IOException {
		FileInputStream file = new FileInputStream("C:\\Users\\BCP\\Desktop\\OrangeTestData.xls");
		Workbook workbook = Workbook.getWorkbook(file);
		Sheet sheet = workbook.getSheet(0);
		int rows = sheet.getRows();
		int columns = sheet.getColumns();

		// Now we have to read and get the values from the excel //so create we need
		// to create 2X2 matrix

		String[][] testData = new String[rows - 1][columns];
		// using the for loop to get the data
		// to iterate the row
		for (int i = 1; i < rows; i++) { // to iterate the column
			for (int j = 0; j < columns; j++) {
				testData[i - 1][j] = sheet.getCell(j, i).getContents();

			}
		}

		return testData;
	}

	@BeforeTest
	public void launchBrowser() {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\BCP\\eclipse-workspace\\SauceLabs\\Driver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		driver.manage().window().maximize();

	}

	@Test(dataProvider = "loginDataProvider")

	public void userLogin(String username, String pword) throws InterruptedException {

		driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(pword);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(3000);
		String actualvalue = "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index";
		assertEquals(actualvalue, "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index");
		System.out.println("Logged in successfully");
	}

	@AfterTest
	public void closeBrowser() {
		driver.quit();

	}

}
