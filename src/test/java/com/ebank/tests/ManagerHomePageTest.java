package com.ebank.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.ebank.base.BaseTest;
import com.ebank.constants.AppConstants;

public class ManagerHomePageTest extends BaseTest{
	
	@BeforeClass
	public void acctSetUp() {
		mHomePage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test(priority = 1)
	public void validateManagerTitleTest() {
		Assert.assertTrue(mHomePage.validateManagerPageTitle());
	}
	
	@Test(priority = 2)
	public void validateManagerUrlTest() {
		Assert.assertTrue(mHomePage.validateManagerPageUrl());
	}
	
	@Test(priority = 3)
	public void validaManagerLogOutLinkTest() {
		Assert.assertTrue(mHomePage.validateLogOutLinkExist());
	}
	
	@Test(priority = 4)
	public void validateManagerHeaderTest() {
		Assert.assertTrue(mHomePage.validateManagerPageHeader());
	}
	
	@Test(priority = 5)
	public void validateAcctSecHeaderTest() {
		List<String> ar = mHomePage.getAcctSecHeader();
		Assert.assertEquals(ar, AppConstants.EBANK_MHOME_ACCSEC_HEADER);
	}

}
