package com.automation.object;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class UserAccountPage {

	private static WebElement element;
	private static List<WebElement> elements;

	public static List<WebElement> link_MyProfile(WebDriver driver) {
		elements = driver.findElements(By.xpath(".//span[@class='profile-icon']"));
		return elements;
	}

	public static WebElement link_Home(WebDriver driver) {
		element = driver.findElement(By.xpath(".//*[@id='collapse']/ul[1]/li[1]/a"));
		return element;
	}

	public static List<WebElement> link_MyAccount(WebDriver driver) {
		elements = driver.findElements(By.xpath(".//*[@id='collapse']/ul[2]/ul/li[1]/a"));
		return elements;
	}

	public static List<WebElement> link_Logout(WebDriver driver) {
		elements = driver.findElements(By.xpath(".//*[@id='collapse']/ul[2]/ul/li[1]/ul/li[2]/a"));
		return elements;
	}

}
