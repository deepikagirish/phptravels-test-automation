package com.automation.base;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.automation.data.Constants;
import com.automation.utilities.Excel;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class PhpTravelsBase {

	public static WebDriver driver;

	public static Properties prop;

	public static Excel excelLoginData;

	public static Excel excelSearchData;

	public static ExtentReports report;

	public static ExtentTest test;

	@BeforeSuite
	public static void loadProperty() {
		try {
			prop = new Properties();
			FileInputStream fis = new FileInputStream(new File(System.getProperty("user.dir") + Constants.PATH_PROP));
			prop.load(fis);

			excelLoginData = new Excel((System.getProperty("user.dir") + Constants.PATH_EXCEL), "Login");

			excelSearchData = new Excel((System.getProperty("user.dir") + Constants.PATH_EXCEL), "HotelSearch");

			report = new ExtentReports(System.getProperty("user.dir") + Constants.PATH_REPORTS);

		} catch (Exception e) {
			test.log(LogStatus.ERROR, e);
		}
	}

	@AfterSuite
	public static void TearDown() {
		try {
			report.flush();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, e);
		}
	}

	public static void explicitWait(String path, int timeout) {
		WebDriverWait ewait = new WebDriverWait(driver, timeout);
		ewait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(path)));
	}

	public static void takeScreenshot(String fileName, ExtentTest extenttest) {
		try {
			TakesScreenshot scrShot = ((TakesScreenshot) driver);
			File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
			File DestFile = new File(System.getProperty("user.dir") + "\\OutputResults\\" + fileName + ".png");
			FileUtils.copyFile(SrcFile, DestFile);
			extenttest.log(LogStatus.INFO, "Output Snapshot" + extenttest
					.addScreenCapture(System.getProperty("user.dir") + "\\OutputResults\\" + fileName + ".png"));
		} catch (Exception e) {
			test.log(LogStatus.ERROR, e);
		}
	}

	public static void selectBrowser(String browser) {
		try {
			switch (browser.toUpperCase()) {
			case "CH": {
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+Constants.PATH_CHROME);
				driver = new ChromeDriver();
				break;
			}			
			case "FF": {
				driver = new FirefoxDriver();
				break;
			}			
			case "IE": {
				System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+Constants.PATH_IE);
				driver = new InternetExplorerDriver();
				break;
			}
			}
		} catch (Exception e) {
			test.log(LogStatus.ERROR, e);
		}
	}
}
