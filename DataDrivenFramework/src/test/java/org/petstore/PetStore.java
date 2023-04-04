package org.petstore;

import java.io.FileInputStream;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;



public class PetStore {
	  
	 static List<String> userlist = new ArrayList<String>();
	 static List<String> passwordlist = new ArrayList<String>();

	public  void readExcel() {
		try {
			FileInputStream file = new FileInputStream(
					"C:\\Users\\BCP\\eclipse-workspace\\DataDrivenFramework\\excel\\PetStoredata.xlsx");
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheetAt(0);
			int RowCount = sheet.getPhysicalNumberOfRows();
			System.out.println("No of rows" + RowCount);
			Iterator<Row> rowIterator = sheet.iterator();
			while (rowIterator.hasNext()) {
				Row rowValue = (Row) rowIterator.next();
				Iterator<Cell> columnIterator = rowValue.iterator();
				int i = 2;
				while (columnIterator.hasNext()) {
					if (i % 2 == 0) {
						userlist.add(columnIterator.next().getStringCellValue());

					} else {
						passwordlist.add(columnIterator.next().toString());
					}

					i++;
				}
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.getCause();
			e.printStackTrace();

		}

	}
	
public  void execute() {
for (int i = 0; i < userlist.size(); i++) {
	login(userlist.get(i), passwordlist.get(i));
	
}

}
	public  void login(String uname,String pword) {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\BCP\\eclipse-workspace\\DataDrivenFramework\\Driver\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.get("https://petstore.octoperf.com/actions/Catalog.action");
		driver.findElement(By.xpath("//a[text()='Sign In']")).click();
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys(uname);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(pword);
		driver.findElement(By.name("signon")).click();

		driver.close();
	}
public static void main(String[] args) {
	PetStore pet=new PetStore();
	pet.readExcel();
	System.out.println("users :"+userlist);
	System.out.println("passwords :"+passwordlist);
	pet.execute();
}
	

}
