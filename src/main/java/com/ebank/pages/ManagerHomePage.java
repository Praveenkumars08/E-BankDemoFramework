package com.ebank.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.ebank.constants.AppConstants;

public class ManagerHomePage {
	
	private WebDriver driver;
	
	public ManagerHomePage(WebDriver driver) {
		this.driver=driver;
	}
	
	private By logOutLink = By.xpath("//div//ul[@class='menusubnav']/li/a[text()='Log out']");
	private By header = By.xpath("//tbody//tr//marquee");
	private By newCust = By.xpath("//div//ul[@class='menusubnav']/li/a[text()='New Customer']");
	private By acctSecHeaders = By.xpath("//ul[@class='menusubnav']/li");

	
	
	public boolean validateManagerPageTitle() {
		String title = driver.getTitle();
		System.out.println("Manager page title is :"+title);
		if(title.equals(AppConstants.MANAGER_PAGE_TITLE)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean validateManagerPageUrl() {
		String url = driver.getCurrentUrl();
		System.out.println("Manager page url is :"+url);
		if(url.contains(AppConstants.MANAGER_PAGE_URL_PARAM)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean validateManagerPageHeader() {
		String txtHeader = driver.findElement(header).getText();
		System.out.println("The manager page header is :"+txtHeader);
		if(txtHeader.equals(AppConstants.MANAGER_PAGE_HEADER)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean validateLogOutLinkExist() {
		return driver.findElement(logOutLink).isDisplayed();
	}
	
	public List<String> getAcctSecHeader() {
		List<String> ar = new ArrayList<>();
		List<WebElement> ele = driver.findElements(acctSecHeaders);
		for(WebElement e : ele) {
			String txt = e.getText();
			ar.add(txt);
		}
		System.out.println("Header list is :"+ar);
		return ar;
	}

}
