package com.ebank.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.ebank.constants.AppConstants;

public class LoginPage {
	
	private WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		this.driver=driver;
	}
	
	private By uName = By.name("uid");
	private By pwd = By.name("password");
	private By lgBtn = By.name("btnLogin");
	private By rBtn = By.name("btnReset");
	private By logoCheck = By.xpath("//div/h2");
	
	public boolean validateTitle() {
		String title = driver.getTitle();
		System.out.println("The title of the page is :"+title);
		if(title.equals(AppConstants.LOGIN_PAGE_TITLE)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean validateUrl() {
		String url = driver.getCurrentUrl();
		System.out.println("The URL of the page is :"+url);
		if(url.contains(AppConstants.LOGIN_PAGE_URL_PARAM)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean checkResetBtn() {
		return driver.findElement(rBtn).isEnabled();
	}
	
	public ManagerHomePage doLogin(String userName, String password) {
		driver.findElement(uName).sendKeys(userName);
		driver.findElement(pwd).sendKeys(password);
		driver.findElement(lgBtn).click();
		
		return new ManagerHomePage(driver);
	}
	
	public boolean bLogoCheck() {
		String txt = driver.findElement(logoCheck).getText();
		if(txt.equals(AppConstants.BANK_LOGO)) {
			return true;
		}
		else {
			return false;
		}
	}

}
