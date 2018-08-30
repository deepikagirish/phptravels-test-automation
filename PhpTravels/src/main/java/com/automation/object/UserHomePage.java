package com.automation.object;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class UserHomePage {

private static WebElement element;
private static List<WebElement> elements;


	public static WebElement link_Hotels(WebDriver driver)
	{
		element = driver.findElement(By.xpath(".//a[@href='#HOTELS']"));
		return element;
	}
	
	public static WebElement drpdn_Hotel(WebDriver driver)
	{
		element = driver.findElement(By.xpath(".//*[@id='s2id_autogen8']/a"));
		return element;
	}
	
	public static List<WebElement> list_City(WebDriver driver)
	{
		elements = driver.findElements(By.xpath(".//*[@id='select2-drop']/ul/li/ul/li/div"));
		return elements;
	}
		
	public static WebElement cal_CheckIn(WebDriver driver)
	{
		element = driver.findElement(By.name("checkin"));
		return element;
	}
	
	public static WebElement cal_CheckOut(WebDriver driver)
	{
		element = driver.findElement(By.name("checkout"));
		return element;
	}
	
	public static WebElement tbox_Travellers(WebDriver driver)
	{
		element = driver.findElement(By.id("travellersInput"));
		return element;
	}
	
	public static WebElement btn_Search(WebDriver driver)
	{
		element = driver.findElement(By.xpath(".//*[@id='HOTELS']/form/div[5]/button"));
		return element;
	}
	

}


