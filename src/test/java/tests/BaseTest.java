package tests;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.net.MalformedURLException;
import java.net.URL;

public class BaseTest {

    AppiumDriver appiumDriver;
    DesiredCapabilities desiredCapabilities = new DesiredCapabilities();

    @BeforeMethod
    public void setUp() throws MalformedURLException {
        desiredCapabilities.setCapability("deviceName", "iphone simulator");
        desiredCapabilities.setCapability("platformName", "iOS");
        desiredCapabilities.setCapability("platformVersion", "11.3");
        desiredCapabilities.setCapability("automationName", "XCUITest");
        desiredCapabilities.setCapability("app", "/Users/nhanngo/Downloads/ChallengeTwitterApp.app");

        appiumDriver = new AppiumDriver(new URL("http://127.0.0.1:4723/wd/hub"), desiredCapabilities);

    }

    @AfterMethod
    public void tearDown() {
        appiumDriver.quit();
    }
}
