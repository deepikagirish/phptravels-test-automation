package com.automation.test;

import com.automation.base.PhpTravelsBase;
import com.automation.object.GuestHomePage;
import com.automation.object.LoginPage;
import com.automation.object.UserAccountPage;
import com.automation.object.UserHomePage;
import com.automation.object.UserHotelPage;
import com.relevantcodes.extentreports.LogStatus;

public class PhpTravelsMethods extends PhpTravelsBase {

	public static void login() {
		try {

			test = report.startTest(excelLoginData.getCellValue(1, 0));

			driver.navigate().to(prop.getProperty("url"));

			String userName = excelLoginData.getCellValue(1, 1);
			String passWd = excelLoginData.getCellValue(1, 2);

			GuestHomePage.drpd_Account(driver).get(1).click();
			GuestHomePage.link_Login(driver).get(0).click();
			
			LoginPage.tbox_Email(driver).sendKeys(userName);
			LoginPage.tbox_Passwd(driver).sendKeys(passWd);

			test.log(LogStatus.INFO, "Entered username and password");

			LoginPage.btn_Login(driver).click();

			int iconSize = UserAccountPage.link_MyProfile(driver).size();

			if (iconSize > 0) {

				test.log(LogStatus.PASS, "Login Passed. Expected Result - User navigated to Account Page. Actual Result - User navigated to Account Page.");
				PhpTravelsBase.takeScreenshot("Login", test);
			}

			else {
				test.log(LogStatus.FAIL, "Login Failed");
				PhpTravelsBase.takeScreenshot("Login", test);
			}

			report.endTest(test);

		} catch (Exception e) {
			test.log(LogStatus.ERROR, e);
			report.endTest(test);
			PhpTravelsTest.closeBrowser();
		}

	}

	public static void loginBadPassword() {
		try {
			test = report.startTest(excelLoginData.getCellValue(2, 0));

			driver.navigate().to(prop.getProperty("url"));

			String userName = excelLoginData.getCellValue(2, 1);
			String passWd = excelLoginData.getCellValue(2, 2);
			String expectedLabel = prop.getProperty("errorlabel");

			GuestHomePage.drpd_Account(driver).get(1).click();
			GuestHomePage.link_Login(driver).get(0).click();

			LoginPage.tbox_Email(driver).sendKeys(userName);
			LoginPage.tbox_Passwd(driver).sendKeys(passWd);

			test.log(LogStatus.INFO, "Entered username and incorrect password");

			LoginPage.btn_Login(driver).click();

			String actualLabel = LoginPage.label_LoginError(driver).getText();

			if (actualLabel.equalsIgnoreCase(expectedLabel)) {
				test.log(LogStatus.PASS, "Login Failed. Expected Result - " +expectedLabel+ ". Actual Result - "+actualLabel);
				PhpTravelsBase.takeScreenshot("BadLogin", test);
			} else {
				test.log(LogStatus.FAIL, "Login Passed. No error message found.");
				PhpTravelsBase.takeScreenshot("BadLogin", test);
			}

			report.endTest(test);

		} catch (Exception e) {
			test.log(LogStatus.ERROR, e);
			report.endTest(test);
			PhpTravelsTest.closeBrowser();
		}
	}

	public static void search() {
		try {

			int rowscount = excelSearchData.getUsedRange();

			for (int i = 1; i < rowscount; i++) {

				test = report.startTest(excelSearchData.getCellValue(i, 0));

				driver.navigate().to(prop.getProperty("accounturl"));

				String cityInput = excelSearchData.getCellValue(i, 1);
				String checkinDate = excelSearchData.getCellValue(i, 2);
				String checkoutDate = excelSearchData.getCellValue(i, 3);
				String adultInput = excelSearchData.getCellValue(i, 4);
				String childInput = excelSearchData.getCellValue(i, 5);
				String travellersInput = adultInput + " Adult " + childInput + " Child";

				UserAccountPage.link_Home(driver).click();

				UserHomePage.link_Hotels(driver).click();
				UserHomePage.drpdn_Hotel(driver).click();
				UserHomePage.drpdn_Hotel(driver).sendKeys(cityInput + "\n");
				UserHomePage.list_City(driver).get(0).click();
				UserHomePage.cal_CheckIn(driver).clear();
				UserHomePage.cal_CheckIn(driver).sendKeys(checkinDate);
				UserHomePage.cal_CheckOut(driver).clear();
				UserHomePage.cal_CheckOut(driver).sendKeys(checkoutDate);
				UserHomePage.tbox_Travellers(driver).clear();
				UserHomePage.tbox_Travellers(driver).sendKeys(travellersInput);

				test.log(LogStatus.INFO, "Entered hotel search inputs");

				UserHomePage.btn_Search(driver).click();
				int resultSize = UserHotelPage.link_Resultlocation(driver).size();
				if (resultSize == 1) {
					String resultCity = UserHotelPage.link_Resultlocation(driver).get(0).getText().toUpperCase();
					if (!resultCity.equalsIgnoreCase(cityInput)) {
						test.log(LogStatus.FAIL,
								" Search Failed. Expected Result - "  + cityInput + ". Actual Result - " + resultCity);
						PhpTravelsBase.takeScreenshot("Failed Output", test);
					} else {
						test.log(LogStatus.PASS, " Search Successful. Result location is " + cityInput);
						PhpTravelsBase.takeScreenshot("Pass Output", test);
					}
				} else {
					if (resultSize > 1) {
						for (int k = 0; k < resultSize - 1; k++) {
							String resultsCity = UserHotelPage.link_Resultlocation(driver).get(k).getText()
									.toUpperCase();
							if (!resultsCity.equalsIgnoreCase(cityInput)) {
								test.log(LogStatus.FAIL,
										"Search Failed. Expected Result - "  + cityInput + ". Actual Result - " + resultsCity);
								PhpTravelsBase.takeScreenshot("Failed Output" + k, test);
								break;
							} else {
								test.log(LogStatus.PASS, " Search Successful. Result location is " + cityInput);
								PhpTravelsBase.takeScreenshot("Pass Output" + k, test);
							}
						}
					} else {
						test.log(LogStatus.FAIL,
								"Expected Result - Error message for invalid input search criteria . Actual Result - No Results Found");
						PhpTravelsBase.takeScreenshot("Search Output - no error", test);
					}
				}
				report.endTest(test);
			}
		} catch (Exception e) {
			test.log(LogStatus.ERROR, e);
			report.endTest(test);
			PhpTravelsTest.closeBrowser();
		}
	}
}
