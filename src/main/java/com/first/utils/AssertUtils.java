package com.first.utils;

import org.testng.Assert;

public class AssertUtils {
	
	//整型做比较 、字符串做比较、字符串包含、非空、实型断言
	
	public void checkIntNum(int actual, int expected,String... com){
		try{
			Assert.assertEquals(actual, expected);
		}catch(AssertionError e){
			
			Assert.fail("断言失败");
		}
	}
	
	public void checkString(String actual, String expected,String... com){
		Assert.assertEquals(actual, expected);
	}
	
	public void checkStringContains(String actual, String expected,String... com){
		Assert.assertEquals(true, actual.contains(expected));
	}
	
	public void checkNotNull(Object actual,String... com){
		Assert.assertNotNull(actual);
	}

}
