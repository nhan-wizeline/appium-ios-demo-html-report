package utils;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import io.appium.java_client.AppiumDriver;

import java.io.PrintWriter;
import java.io.StringWriter;

import static org.testng.Assert.fail;
import static utils.Common.captureScreenshot;

public class TestReport {

    public static ExtentTest extentTest;

    public static void testReport(AppiumDriver appiumDriver, boolean isPassed, String passMessage, String failMessage, boolean isCaptureScreenshot) {
        String screeshotPath = "";
        if (isCaptureScreenshot) {
            screeshotPath = extentTest.addScreenCapture(captureScreenshot(appiumDriver));
            passMessage += screeshotPath;
            failMessage += screeshotPath;
        }

        testReport(appiumDriver, isPassed, passMessage, failMessage);
    }

    // capture screenshot if result = failed
    public static void testReport(AppiumDriver appiumDriver, boolean isPassed, String testLog) {
        String screeshotPath = "";
        if(isPassed == false) {
            String filePath = captureScreenshot(appiumDriver);
            screeshotPath = extentTest.addScreenCapture(filePath);
            testLog += screeshotPath;
        }
        testReport(appiumDriver, isPassed, testLog, testLog);
    }

    // capture screenshot if isCaptureScreenshot = true
    public static void testReport(AppiumDriver appiumDriver, LogStatus status, String msgDetail, boolean isCaptureScreenshot) {
        String screeshotPath = "";
        if (isCaptureScreenshot) {
            screeshotPath = extentTest.addScreenCapture(captureScreenshot(appiumDriver));
            msgDetail += screeshotPath;
        }

        extentTest.log(status, msgDetail);
    }

    // always capture screenshot
    public static void testReport(AppiumDriver appiumDriver, LogStatus status, String msgDetail) {
        String screeshotPath = "";
        screeshotPath = extentTest.addScreenCapture(captureScreenshot(appiumDriver));
        msgDetail += screeshotPath;

        extentTest.log(status, msgDetail);
    }

    public static void testReport(AppiumDriver appiumDriver, boolean isPassed, String passMessage, String failMessage) {
        if (isPassed) {
            extentTest.log(LogStatus.PASS, passMessage);
        } else {
            extentTest.log(LogStatus.FAIL, failMessage);
        }
    }

    public static void handleExceptionAndMarkFailResult(AppiumDriver appiumDriver, Exception ex) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        String sStackTrace = sw.toString(); // stack trace as a string
        System.out.println(sStackTrace);

        testReport(appiumDriver, LogStatus.FAIL, ex.getMessage() + "\nStackTrace: " + sStackTrace, true);
        System.out.println("Exception: " + ex.getMessage());
        fail();
    }
}
