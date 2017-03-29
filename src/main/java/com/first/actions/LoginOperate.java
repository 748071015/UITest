package com.first.actions;

import org.openqa.selenium.WebDriver;

import com.first.pages.LoginPage;
import com.first.pages.MainPage;

public class LoginOperate {
	
	private static WebDriver driver;
	
	public static WebDriver getDriver() {
		return driver;
	}

	public static void setDriver(WebDriver driver) {
		LoginOperate.driver = driver;
	}
	
	static LoginPage loginPage;
	static MainPage mainPage;
	
	public static MainPage LoginSuess(String name,String password){
		loginPage = new LoginPage(getDriver());
		loginPage.inputUserName(name);
		loginPage.inputPassword(password);
		loginPage.clickLoginButton();
		return new MainPage(getDriver());		
	}
	
	public static String getLoginName(String name,String password){
		mainPage = LoginSuess(name,password);
		return mainPage.getLoginUserName();
	}

}
