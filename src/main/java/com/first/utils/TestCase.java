package com.first.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestCase {
	
	private String caseName = null ;
	private String isCheck = null;
	private String expectedResult = null;
	private Map<String,String> testdata = new HashMap<String,String>();
	
	public TestCase(String name,String check,String result,Map<String, String> testData2){
		this.caseName = name;
		this.isCheck = check;
		this.expectedResult = result;
		this.testdata = testData2;
	}
	
	public String getCaseName() {
		return caseName;
	}
	public String getIsCheck() {
		return isCheck;
	}
	public String getExpectedResult() {
		return expectedResult;
	}
	public Map<String, String> getTestdata() {
		return testdata;
	}
}
