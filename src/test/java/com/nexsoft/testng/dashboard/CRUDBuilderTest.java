package com.nexsoft.testng.dashboard;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class CRUDBuilderTest {

	WebDriver driver;
	
	public String screenShoot() {
		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String waktu = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		String namaFile = "D:\\TestScreenshoot\\"+waktu+".png";
		File screenshoot = new File(namaFile);
		try {
			FileUtils.copyFile(srcFile, screenshoot);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return namaFile;
	}
	
	@BeforeClass
	public void init() {
		System.setProperty("url", "http://localhost/cicool");
		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(System.getProperty("url"));
		driver.manage().window().maximize();
	}
	
	@Test(priority=2,dataProvider="getNexsoftData",dataProviderClass = com.nexsoft.testng.dataprovider.DataProviderNexsoft.class)
	public void cobaDulu(String param1, String param2, String param3) {
//		System.out.println(param1+param2+param3);
		driver.get("http://localhost/cicool/administrator/crud");
		driver.findElement(By.cssSelector("a.label-default")).click();
		driver.findElement(By.cssSelector("#btn_add_new")).click();

		driver.findElement(By.xpath("//input[@placeholder='Username']")).clear();
		driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys(param1);

		driver.findElement(By.xpath("//input[@placeholder='Email']")).clear();
		driver.findElement(By.xpath("//input[@placeholder='Email']")).sendKeys(param2);

		driver.findElement(By.xpath("//input[@placeholder='Location']")).clear();
		driver.findElement(By.xpath("//input[@placeholder='Location']")).sendKeys(param3);
		
//		Reporter.log("Nama: "+param1+", Email: "+param2+", Alamat: "+param3);
		driver.findElement(By.xpath("/html/body/div[1]/div[1]/section[2]/div/div/div/div/div/form/div[5]/a[1]")).click();

		driver.get("http://localhost/cicool/administrator/absensi");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Test(priority=0)
	public void login() {
		driver.findElement(By.cssSelector("i.fa.fa-sign-in")).click();
		
		driver.findElement(By.cssSelector("input[placeholder='Email']")).clear();
		driver.findElement(By.cssSelector("input[placeholder='Email']")).sendKeys("ridhotadjudin@gmail.com");
		driver.findElement(By.cssSelector("input[placeholder='Password']")).clear();
		driver.findElement(By.cssSelector("input[placeholder='Password']")).sendKeys("123456");
		
		driver.findElement(By.cssSelector("button[type='submit']")).click();
		
		String username = driver.findElement(By.cssSelector("span[class='hidden-xs']")).getText();
		Assert.assertEquals(username, "Ridho");
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Test(priority=1)
	public void createData() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
		System.out.println("create data --->" + sdf.format(new Date().getTime()));
		
		driver.get("http://localhost/cicool/administrator/crud");
		driver.findElement(By.cssSelector("a.label-default")).click();
		driver.findElement(By.cssSelector("#btn_add_new")).click();

		driver.findElement(By.xpath("//input[@placeholder='Username']")).clear();
		driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys("kamu");

		driver.findElement(By.xpath("//input[@placeholder='Email']")).clear();
		driver.findElement(By.xpath("//input[@placeholder='Email']")).sendKeys("kamu@gmail.com");

		driver.findElement(By.xpath("//input[@placeholder='Location']")).clear();
		driver.findElement(By.xpath("//input[@placeholder='Location']")).sendKeys("jakarta");
		
		String file = "<img src='file://"+screenShoot()+"'/>";
		Reporter.log(file);
		
		driver.findElement(By.xpath("/html/body/div[1]/div[1]/section[2]/div/div/div/div/div/form/div[5]/a[1]")).click();

		driver.get("http://localhost/cicool/administrator/absensi");
		
		String file2 = "<img src='file://"+screenShoot()+"'/>";
		Reporter.log(file2);
//		
//		List<WebElement> lstUsername = driver.findElements(By.xpath("/html[1]/body[1]/div[1]/div[1]/section[2]/div[1]/div[1]/div[1]/div[1]/div[1]/form[1]/div[2]/table[1]/tbody[1]/tr/td/span[@class='list_group-username']"));
//
//		for (WebElement element : lstUsername) {
//			System.out.println(element.getText());
//		}
//		System.out.println("Nama terakhir : "+lstUsername.get(lstUsername.size()-1).getText());
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Test(priority=3,enabled=false)
	public void getUsername(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
		System.out.println(driver.findElement(By.linkText("Ridho")).getText()+sdf.format(new Date().getTime()));
	}
	
	@Test(priority=4,enabled=false)
	public void getUsername2(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
		System.out.println(driver.findElement(By.linkText("Ridho")).getText()+sdf.format(new Date().getTime()));
	}
}
