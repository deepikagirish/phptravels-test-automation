package com.automation.object;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GuestHomePage {
	
private static List <WebElement> elements;
	
	public static List<WebElement> drpd_Account(WebDriver driver)
	{
		elements = driver.findElements(By.id("li_myaccount"));
		return elements;
	}
	
		
	public static List<WebElement> link_Login(WebDriver driver)
	{
		elements = driver.findElements(By.linkText("Login"));
		return elements;
	}

}
