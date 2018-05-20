package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static utils.TestReport.testReport;

public class PageTweetDetails extends PageBase {
    private AppiumDriver appiumDriver;
    private WebDriverWait wait;

    public PageTweetDetails(AppiumDriver appiumDriver){
        this.appiumDriver = appiumDriver;
        PageFactory.initElements(new AppiumFieldDecorator(appiumDriver, TIMEOUT_30S, TimeUnit.SECONDS), this);
        this.wait = new WebDriverWait(appiumDriver, TIMEOUT_30S);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("Back")));
    }
    public boolean verifyTweetDetails(String expectedDetails){
        List<MobileElement> eles = appiumDriver.findElementsByXPath("//XCUIElementTypeStaticText");
        String text = eles.get(eles.size()-1).getText();
        //System.out.print("text: " + text);
        //System.out.print("expectedDetails: " + expectedDetails);
        boolean result = text.equals(expectedDetails);
        testReport(appiumDriver, result, String.format("Expected: %s.\nActual: %s", expectedDetails, text));
        return result;
    }
}
