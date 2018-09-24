package com.automation.object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class LoginPage{
	
	private static WebElement element;
	
	public static WebElement tbox_Email(WebDriver driver)
	{
		element = driver.findElement(By.name("username"));
		return element;
	}
	
	public static WebElement tbox_Passwd(WebDriver driver)
	{
		element = driver.findElement(By.name("password"));
		return element;
	}

	public static WebElement btn_Login(WebDriver driver)
	{
		element = driver.findElement(By.xpath(".//button[@class='btn btn-action btn-lg btn-block loginbtn']"));
		return element;
	}
	
	public static WebElement label_LoginError(WebDriver driver)
	{
		element = driver.findElement(By.xpath(".//div[@class='alert alert-danger']"));
		return element;
	}
}
