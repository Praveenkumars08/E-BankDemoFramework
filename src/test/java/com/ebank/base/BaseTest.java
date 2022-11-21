package com.ebank.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.ebank.factory.DriverFactory;
import com.ebank.pages.LoginPage;
import com.ebank.pages.ManagerHomePage;

public class BaseTest {
	
	public WebDriver driver;
	public DriverFactory df;
	public Properties prop;
	public LoginPage loginPage;
	public ManagerHomePage mHomePage;
	
	
	@BeforeTest
	public void setUP() {
		df = new DriverFactory();
		prop = df.initializeProp();
		driver = df.initializeDriver(prop);
		loginPage = new LoginPage(driver);
		
	}
	
	@AfterTest
	public void tearDown() {
		//driver.close();
	}

}
