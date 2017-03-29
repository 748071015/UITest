package com.first.testcases;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.dom4j.DocumentException;
import org.openqa.selenium.WebDriver;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.first.actions.LoginOperate;
import com.first.uiframework.DriverFactory;
import com.first.utils.AssertUtils;
import com.first.utils.ExeclUtils;
import com.first.utils.TestCase;

public class LoginTest {
	
	WebDriver driver;
	AssertUtils assertUtils; 
	
	@BeforeTest
	public void setUp(){
		try{
			assertUtils = new AssertUtils();
			driver = DriverFactory.getChromeDriver();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}catch(Exception e){
			Assert.fail("测试初始化报错");
		}
	}
	
	//登录成功测试
    @Test
    public void loginTest01()  throws DocumentException{
    	List<TestCase> testcases = ExeclUtils.getUITestData("UITestData.xlsx");
    	
    	HashMap<String,String> testdata = (HashMap<String, String>) testcases.get(0).getTestdata();
    	
    	System.out.println("测试用例"+testdata);
    	LoginOperate.setDriver(driver);
    	
		String value = LoginOperate.getLoginName(testdata.get("username"), testdata.get("password"));
		Assert.assertEquals(value.contains(testcases.get(0).getExpectedResult()), true,"登录测试失败");
    }
    
    
//    @Test
//    public void loginTest02() throws InterruptedException, DocumentException{
//    	LoginPage lp = new LoginPage(driver);
//    	String value = lp.loginFailOperate("admin", "123");    	
//    	assertUtils.checkStringContains(value, "登录失败", "登录测试失败");
////    	Assert.assertEquals(value.contains("登录失败"), true,"登录测试失败");
//    }

    @AfterTest
    public void tearDown() throws InterruptedException{
    	Thread.sleep(3000);
    	driver.close();
    }
}
