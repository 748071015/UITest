package com.first.demo;

import com.first.uiframework.DriverFactory;
import com.first.utils.Log;

public class shuzu {

	public static void main(String[] args) {
		Log log = new Log(DriverFactory.class);
		String a[]={"1"};
		for(String i:a){
		log.info(i);
		}		

	}

}
