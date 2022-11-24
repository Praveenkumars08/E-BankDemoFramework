package com.ebank.factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
	
	public WebDriver driver;
	public Properties prop;
	public OptionsManager optionsManager;
	
	public static ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();
	
	public WebDriver initializeDriver(Properties prop) {
		
		String browserName = prop.getProperty("browser");
		optionsManager = new OptionsManager(prop);
		if(browserName.equalsIgnoreCase("chrome")) {
			
			if(Boolean.parseBoolean(prop.getProperty("remote"))) {
				initRemoteDriver("chrome");
			}
			else {
			WebDriverManager.chromedriver().setup();
			//driver= new ChromeDriver();
			threadLocalDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			}
		}
		else if(browserName.equalsIgnoreCase("firefox")) {
			
			if(Boolean.parseBoolean(prop.getProperty("remote"))) {
				initRemoteDriver("firefox");
			}
			else {
			WebDriverManager.firefoxdriver().setup();
			//driver = new FirefoxDriver();
			threadLocalDriver.set(new FirefoxDriver());
			}
			
		}
		else if(browserName.equalsIgnoreCase("edge")) {
			
			if(Boolean.parseBoolean(prop.getProperty("remote"))) {
				initRemoteDriver("edge");
			}
			else {
			WebDriverManager.edgedriver().setup();
			//driver = new EdgeDriver();
			threadLocalDriver.set(new EdgeDriver());
			}
		}
		else {
			System.out.println("Please pass the right browser"+browserName);
		}
		
		getDriver().get(prop.getProperty("url"));
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		return getDriver();
	}
	
	public static synchronized WebDriver getDriver() {
		return threadLocalDriver.get();
	}
	
	private void initRemoteDriver(String browser) {
		
		if(browser.equals("chrome")) {
			try {
				threadLocalDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionsManager.getChromeOptions()));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}
		
		else if(browser.equals("firefox")) {
			try {
				threadLocalDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionsManager.getFireFoxOptions()));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}
		
		else if(browser.equals("edge")) {
			try {
				threadLocalDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionsManager.getEdgeOptions()));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}
		else {
			System.out.println("Please pass the right browser");
		}
		
		
	}
	
	public Properties initializeProp() {
		
		prop = new Properties();
		FileInputStream file = null;
		//String envName = System.getenv("env");
		String envName = System.getProperty("env");
		System.out.println("Running test cases on "+envName);
		
		if(envName==null) {
			System.out.println("No environment given hence running on QA environment");
			try {
				file = new FileInputStream("./src/test/resources/config/qa.config.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		
		else {
			try {
			if(envName.equals("dev")) {
				System.out.println("Running on Dev Environment");
				file = new FileInputStream("./src/test/resources/config/dev.config.properties");
			}
			
			if(envName.equals("prod")) {
				System.out.println("Running on Prod Environment");
				file = new FileInputStream("./src/test/resources/config/prod.config.properties");
			}
			}catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			
		}
		
		try {
			prop.load(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return prop;
		
	}

}
