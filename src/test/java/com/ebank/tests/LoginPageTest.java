package com.ebank.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.ebank.base.BaseTest;

public class LoginPageTest extends BaseTest {
	
	
	@Test(priority = 1)
	public void validateTitleTest() {
		boolean titleTest = loginPage.validateTitle();
		Assert.assertTrue(titleTest);
	}
	
	@Test(priority = 2)
	public void validateUrlTest() {
		boolean urlTest = loginPage.validateUrl();
		Assert.assertTrue(urlTest);
	}
	
	@Test(priority = 3)
	public void resetBtnTest() {
		boolean rBtnTest = loginPage.checkResetBtn();
		Assert.assertTrue(rBtnTest);
	}
	
	@Test(priority = 4)
	public void bLogoTest() {
		boolean logo = loginPage.bLogoCheck();
		Assert.assertTrue(logo);
	}
	
	@Test(priority = 5)
	public void doLoginTest() {
		mHomePage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		boolean linkCheck = mHomePage.validateLogOutLinkExist();
		Assert.assertEquals(linkCheck, true);
	}
	
	

}
