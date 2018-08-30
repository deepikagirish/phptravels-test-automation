package com.automation.test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.automation.base.PhpTravelsBaseClass;

import bsh.org.objectweb.asm.Constants;

public class PhpTravelsTest extends PhpTravelsBaseClass {

	@BeforeMethod
	public static void open_FirefoxBrowser() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.get(prop.getProperty("url"));
	}

	@Test(priority = 0, enabled = true)
	public static void valid_LoginTest() {
		PhpTravelsMethods.login();
	}

	@Test(priority = 1, enabled = true)
	public static void bad_LoginPasswordTest() {
		PhpTravelsMethods.login_BadPassword();
	}

	@Test(priority = 2, enabled = true)
	public static void hotel_SearchTest() {
		PhpTravelsMethods.login();
		PhpTravelsMethods.search();
	}

	@AfterMethod
	public static void close_Browser() {
		driver.quit();
	}
}
