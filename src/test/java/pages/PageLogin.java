package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class PageLogin extends PageBase{
    private AppiumDriver appiumDriver;
    private WebDriverWait wait;

    @FindBy(xpath = "//XCUIElementTypeTextField")
    MobileElement userName;

    @FindBy(xpath = "//XCUIElementTypeSecureTextField")
    MobileElement password;

    @FindBy(id = "Login")
    MobileElement loginBtn;

    public PageLogin(AppiumDriver appiumDriver) {
        this.appiumDriver = appiumDriver;
        PageFactory.initElements(new AppiumFieldDecorator(appiumDriver, TIMEOUT_30S, TimeUnit.SECONDS), this);
        this.wait = new WebDriverWait(appiumDriver, TIMEOUT_30S);
        wait.until(ExpectedConditions.visibilityOf(userName));
    }

    public PageHome login(String usrName, String pwd){
        this.userName.sendKeys(usrName);
        this.password.sendKeys(pwd);
        this.loginBtn.click();

        return new PageHome(appiumDriver);
    }
}
