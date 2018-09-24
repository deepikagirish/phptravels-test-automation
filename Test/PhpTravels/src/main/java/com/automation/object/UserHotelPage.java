package com.automation.object;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class UserHotelPage {
private static List<WebElement> elements;
private static WebElement element;
	
	public static List<WebElement> link_Resultlocation(WebDriver driver)
	{
		elements = driver.findElements(By.xpath(".//a[@class='go-right ellipsisFIX go-text-right mob-fs14']"));
		return elements;
	}
	public static WebElement input_Travellers(WebDriver driver)
	{
		element = driver.findElement(By.id("travellersInput"));
		return element;
	}
}
