package com.first.demo;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Search {

	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver(); //定义一个谷歌浏览器驱动对象
		driver.manage().window().maximize(); //浏览器最大化
		driver.manage().timeouts()
				.implicitlyWait(10, TimeUnit.SECONDS);//元素识别等待默认10秒
		driver.get("https://www.baidu.com"); //打开网址：https://www.baidu.com
		driver.findElement(By.cssSelector("#kw")).sendKeys("新梦想软件测试");//搜索框输入 新梦想软件测试
		driver.findElement(By.cssSelector("#su")).click();//点击 百度一下 按钮进行搜素
		driver.close(); //关闭当前标签页面
	}
}
