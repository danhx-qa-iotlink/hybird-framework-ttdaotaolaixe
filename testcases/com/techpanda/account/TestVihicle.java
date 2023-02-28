package com.techpanda.account;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import common.BaseTest;
import pageObject.user.CreateNewMotoPageObject;
import pageObject.user.LoginPageObject;
import pageObject.user.MotoListPageObject;
import pageObject.user.MyDashboardPageObject;
import pageObject.user.PageGeneratorManager;

public class TestVihicle extends BaseTest {
	WebDriver driver;
	LoginPageObject loginPage;
	MyDashboardPageObject myDashboardPage;
	MotoListPageObject motoListPage;
	CreateNewMotoPageObject createNewMotoPage;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		loginPage = PageGeneratorManager.GetLoginPage(driver);
		
	}
	@Test
	public void TC_01_LoginCorrectEmailAndPassword() {

		loginPage.inputToEmailAddressTextbox("admin@ttlx.com");
		loginPage.inputToPasswordTextbox("123123");
		myDashboardPage = loginPage.clickToLoginButton();
		
		
		Assert.assertEquals("Dashboard", myDashboardPage.getTitleMyDashboardPage());
	}

	@Test
	public void TC_02_CheckShowMotoList() {
		myDashboardPage.clickToMenuVihicle(driver);
		motoListPage = myDashboardPage.clickToMotoIcon(driver);
		Assert.assertEquals("Danh sách xe", motoListPage.getTitlePage());
	}

	@Test
	public void TC_03_CheckCreateNewMoto() {
		Assert.assertEquals("Danh sách xe", motoListPage.getTitlePage());
		createNewMotoPage= motoListPage.clickToCreateNewVihicle();
		motoListPage.inputLicensePlates("");
		motoListPage.chooseRank("A4");
		motoListPage.chooseYear("2022");
		motoListPage.inputOwner("Xuân Dần");
		motoListPage.inputModel("MD013");
		motoListPage.inputGPLX("GPLX");
		motoListPage.chooseDeviceLocation("aa");
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
