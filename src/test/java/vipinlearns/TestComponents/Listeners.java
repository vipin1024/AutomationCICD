package vipinlearns.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import vipinlearns.resources.ExtentReorterNG;

public class Listeners extends BaseTest implements ITestListener {
    ExtentTest test;
    ExtentReports extent = ExtentReorterNG.getReportObject();
    ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>(); // Thread-safe

    @Override
    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName());
        extentTest.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extentTest.get().log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        extentTest.get().fail(result.getThrowable());

        WebDriver testDriver = null;
        try {
            testDriver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (testDriver != null) {
            String filePath = null;
            try {
                filePath = getScreenshot(result.getMethod().getMethodName(), testDriver);
            } catch (IOException e) {
                e.printStackTrace();
            }
            extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
        } else {
            extentTest.get().log(Status.FAIL, "Screenshot could not be taken as driver was null.");
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {}

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {}

    @Override
    public void onStart(ITestContext context) {}

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}
