package com.first.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;


public class TestNGListener extends TestListenerAdapter{
	
	Log log = new Log(TestNGListener.class);
	
	//testng初始化
	@Override
	public void onStart(ITestContext testContext) {
		log.info("测试初始化");
		super.onStart(testContext);
	}
	//case开始
	@Override
	public void onTestStart(ITestResult tr) {
		log.info("【"+tr.getName()+"】测试用例开始执行");
		super.onTestStart(tr);
	}
	//case执行成功
	@Override
	public void onTestSuccess(ITestResult tr) {
		log.info("【"+tr.getName()+"】测试用例执行成功");
		super.onTestSuccess(tr);
	}
	//case执行失败
	@Override
	public void onTestFailure(ITestResult tr) {
		log.error("【"+tr.getName()+"】测试用例执行失败");
		super.onTestFailure(tr);
//		takeScreenShot(tr);
	}	
    //case被跳过
    @Override
    public void onTestSkipped(ITestResult tr) {
    	log.error("【"+tr.getName()+"】测试用例被跳过");
        super.onTestSkipped(tr);
//        takeScreenShot(tr);
    }    
    //所有case执行完成
/*    @Override
    public void onFinish(ITestContext testContext) {
    	log.info("测试用例执行完成");
        super.onFinish(testContext);
        // List of test results which we will delete later
        ArrayList<ITestResult> testsToBeRemoved = new ArrayList<ITestResult>();
        // collect all id's from passed test
        Set<Integer> passedTestIds = new HashSet<Integer>();
        for (ITestResult passedTest : testContext.getPassedTests()
                .getAllResults()) {
            log.info("通过的测试用例 ：" + passedTest.getName());
            passedTestIds.add(getId(passedTest));
        }
        Set<Integer> failedTestIds = new HashSet<Integer>();
        for (ITestResult failedTest : testContext.getFailedTests()
                .getAllResults()) {
            log.info("失败的测试用例：" + failedTest.getName());
            int failedTestId = getId(failedTest);
            // if we saw this test as a failed test before we mark as to be
            // deleted
            // or delete this failed test if there is at least one passed
            // version
            if (failedTestIds.contains(failedTestId)
                    || passedTestIds.contains(failedTestId)) {
                testsToBeRemoved.add(failedTest);
            } else {
                failedTestIds.add(failedTestId);
            }
        }
        // finally delete all tests that are marked
        for (Iterator<ITestResult> iterator = testContext.getFailedTests()
                .getAllResults().iterator(); iterator.hasNext();) {
            ITestResult testResult = iterator.next();
            if (testsToBeRemoved.contains(testResult)) {
                log.info("移除重复的失败测试用例： " + testResult.getName());
                iterator.remove();
            }
        }
    }
    
    private int getId(ITestResult result) {
        int id = result.getTestClass().getName().hashCode();
        id = id + result.getMethod().getMethodName().hashCode();
        id = id
                + (result.getParameters() != null ? Arrays.hashCode(result
                        .getParameters()) : 0);
        return id;
    }*/
    
    @Override
    public void onFinish(ITestContext testContext) {
        Iterator<ITestResult> listOfFailedTests = testContext.getFailedTests().getAllResults().iterator();
        while (listOfFailedTests.hasNext()) {
            ITestResult failedTest = listOfFailedTests.next();
            ITestNGMethod method = failedTest.getMethod();
            if (testContext.getFailedTests().getResults(method).size() > 1) {
                listOfFailedTests.remove();
            } else {
                if (testContext.getPassedTests().getResults(method).size() > 0) {
                    listOfFailedTests.remove();
                }
            }
        }
    }
    
//	private void takeScreenShot(ITestResult tr) {
//		UITest b = (UITest) tr.getInstance();
//		WebDriver currentDirver = b.getDriver();
//		System.out.println(currentDirver.getTitle());
//		b.takeScreenShot();
//	}
}
