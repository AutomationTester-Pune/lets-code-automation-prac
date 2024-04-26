package com.basetest;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest {

	public WebDriver driver = new ChromeDriver();

	@BeforeClass
	public void setUp() {
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(12));
		driver.get("https://letcode.in/table");
	}

	@AfterClass
	public void terDown() throws InterruptedException {
		
		Thread.sleep(2500);
		driver.close();
	}

}
