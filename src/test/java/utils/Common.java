package utils;

import io.appium.java_client.AppiumDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Common {

    public static String captureScreenshot(AppiumDriver appiumDriver) {
        String filePath = "";

        try {
            String workingDir = System.getProperty("user.dir");
            String jenkinsEnv = System.getProperty("runOnJenkins");

            File scrFile = ((TakesScreenshot) appiumDriver).getScreenshotAs(OutputType.FILE);
            if((jenkinsEnv == null)||(!jenkinsEnv.equals("true"))) {
                filePath = workingDir + "/report/" + generateUniqueString() + ".png";
                FileUtils.copyFile(scrFile, new File(filePath));
            }
            else{
                filePath = generateUniqueString() + ".png";
                FileUtils.copyFile(scrFile, new File(filePath));
            }

        } catch (Exception e) {
            System.out.println("captureScreenshot exception: " + e.getMessage());
        } finally {
            return filePath;
        }
    }

    public static String captureScreenshot1(AppiumDriver appiumDriver) {
        String fileName = "";
        try {

            fileName = "./"+generateUniqueString() + ".png";
            File scrFile = ((TakesScreenshot) appiumDriver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File(fileName));
        } catch (Exception e) {

        } finally {
            return fileName;
        }
    }

    public static String generateUniqueString() {
        String timeStamp = new SimpleDateFormat("ddMMyyyy_HHmmss").format(Calendar.getInstance().getTime());
        return timeStamp;
    }
}
