package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.functions.ExpectedCondition;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static utils.TestReport.testReport;


public class PageHome extends PageBase {
    private AppiumDriver appiumDriver;
    private WebDriverWait wait;
    private String selectedTweetDetail;

    @FindBy(id = "Settings")
    MobileElement settingsBtn;

    @FindBy(xpath = "//XCUIElementTypeCell")
    List<MobileElement> tweets;

    public PageHome(AppiumDriver appiumDriver){
        this.appiumDriver = appiumDriver;
        PageFactory.initElements(new AppiumFieldDecorator(appiumDriver, TIMEOUT_30S, TimeUnit.SECONDS), this);
        this.wait = new WebDriverWait(appiumDriver, TIMEOUT_30S);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//XCUIElementTypeCell")));
    }

    public String getBotName(){
        return appiumDriver.findElementByAccessibilityId("Wize Bot").getText();
    }

    public String getBotDescription(){
        return appiumDriver.findElementByAccessibilityId("@wizeservicesbot - Wize Services Challenge Bot").getText();
    }

    public boolean verifyBotNameAndBotDesc(String botName, String botDescription){
        String log = String.format("Expected: %s - %s.\nActual: %s - %s", botName, botDescription, getBotName(), getBotDescription());
        boolean result = getBotName().equals(botName) && getBotDescription().equals(botDescription);
        testReport(appiumDriver, result, log);
        return result;
    }

    private void scrollingDown() {
        try {
            (new TouchAction(this.appiumDriver)).press(320, 600).waitAction(Duration.ofMillis(500)).moveTo(320, 100).release().perform();
        } catch (Exception ex) {

        }
    }

    public PageTweetDetails selectATweet(){
        Random rnd = new Random();
        int scrollTimes = rnd.nextInt(4);

        for (int i=0; i<scrollTimes; i++){
            scrollingDown();
        }

        int tweetCount = tweets.size();
        try{Thread.sleep(5000);}catch(Exception ex){};
        //List<MobileElement> visibleTweets = tweets.stream().filter(t -> t.getAttribute("visible").equals("true")).collect(Collectors.toList());

        int min = scrollTimes*2 +1;
        int max = 0;

        if(min == 1)
            max = min + 5;
        else if(min+7 < tweetCount)
            max = min + 7;
        else
            max = tweetCount;
        //System.out.println(min + "-" + max);

        int selectedTweet = randomInRange(min, max);
        //System.out.println("selectedTweet: " + selectedTweet);

        List<MobileElement> tweetDetailTextes = tweets.get(selectedTweet).findElements(By.xpath("//XCUIElementTypeStaticText"));
        this.selectedTweetDetail = tweetDetailTextes.get(tweetDetailTextes.size()-1).getText();
        //System.out.println("selectedTweetDetail: " + selectedTweetDetail);
        tweets.get(selectedTweet).click();

        return new PageTweetDetails(appiumDriver);
    }

    public String getSelectedTweetDetail(){
        return this.selectedTweetDetail;
    }
}
