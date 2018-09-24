package com.automation.test;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.automation.base.PhpTravelsBase;


public class PhpTravelsTest extends PhpTravelsBase {

	@BeforeMethod
	public static void openBrowser() {
		selectBrowser(prop.getProperty("browser"));
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.get(prop.getProperty("url"));
	}

	@Test(priority = 0, enabled = true)
	public static void validLoginTest() {
		PhpTravelsMethods.login();
	}

	@Test(priority = 1, enabled = true)
	public static void badLoginPasswordTest() {
		PhpTravelsMethods.loginBadPassword();
	}

	@Test(priority = 2, enabled = true)
	public static void hotelSearchTest() {
		PhpTravelsMethods.login();
		PhpTravelsMethods.search();
	}

	@AfterMethod
	public static void closeBrowser() {
		driver.quit();
	}
}
