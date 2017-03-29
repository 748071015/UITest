package com.first.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.first.utils.AssertUtils;

public class NewTest {
	AssertUtils assertutils;
	@BeforeTest
	public void setUp(){
		assertutils = new AssertUtils();
//		try{
//		  int[] i = {1,2,3,4,5};
//		  int b = i[8];
//		}catch(Exception e){
//			e.printStackTrace();
//			Assert.fail("初始化报错，不执行后期代码");
//		}
	}
  @Test
  public void f1() {
	  assertutils.checkIntNum(2, 1);
//	  Assert.assertEquals(2, 1);
  }
  @Test
  public void f2() {
	  assertutils.checkIntNum(1, 1);	  
  }
  @AfterTest
  public void tearDown(){
  }
}
