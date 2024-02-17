package com.nexsoft.testng.frontpage;

import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;

public class FrontpageTest {
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
	
	//latihan get data dari class lain yang sudah di-generate sendiri
	@Test(dataProvider = "data-provider",dataProviderClass = com.nexsoft.testng.dataprovider.DataProviderNexsoft.class)
	public void consumeData(String data) {
		System.out.println(data);
	}
	
	@BeforeClass
	public void init() {
		System.setProperty("url","http://localhost/cicool");
		System.setProperty("webdriver.chrome.driver","D:\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(System.getProperty("url"));
		driver.manage().window().maximize();
	}
	
	@Test
	public void login() {
		driver.findElement(By.cssSelector("i.fa.fa-sign-in")).click();
		
		driver.findElement(By.cssSelector("input[placeholder='Email']")).clear();
		driver.findElement(By.cssSelector("input[placeholder='Email']")).sendKeys("ridhotadjudin@gmail.com");
		driver.findElement(By.cssSelector("input[placeholder='Password']")).clear();
		driver.findElement(By.cssSelector("input[placeholder='Password']")).sendKeys("123456");
		
		driver.findElement(By.cssSelector("button[type='submit']")).click();
		
//		screenShoot();
		String file = "<img src='file://"+screenShoot()+"'height=\"400\" width=\"904\"/>";
		Reporter.log(file);
		
		String username = driver.findElement(By.cssSelector("span[class='hidden-xs']")).getText();
		Assert.assertEquals(username, "Ridho");
	}
	
	@Test
	public void setLanguage() {
//		Reporter.log("<img src='https://media.alkhairaat.id/wp-content/uploads/2019/05/Buaya-berkalung-ban-di-Kota-Palu.jpg'/>");
	}

}
