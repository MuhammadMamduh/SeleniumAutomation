package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.awt.*;
import java.awt.event.KeyEvent;

public class HomePage extends PagesBase
{
    // ________________________________________ [ Instance Variables ] _________________________________________________
    // Instance Variables that are ONLY visible BEFORE Logging in
    @FindBy(xpath = "//*[@id=\"root\"]/div[2]/div[3]/div[2]/div[3]/div/div[2]/button")
    protected WebElement login_btn;

    // Instance Variables that are ONLY visible AFTER Logging in
    @FindBy(css = "button.btn.account-btn")
    protected WebElement account_btn;

    @FindBy(css = "div.logged-in-menu :nth-child(3)")
    protected WebElement logout_btn;

    // Common Before & After Logging in
    @FindBy(xpath = "//img[@class=\"logo\"]")
    protected WebElement logo;

    @FindBy(xpath = "//div[@class=\"d-flex flex-wrap banner\"]")
    protected WebElement mainSlidingBanner;

    @FindBy(xpath = "//*[@id=\"root\"]/div[2]/div[3]/div[2]/div[3]/div/div[1]/form/input[1]")
    protected WebElement searchBox;
    // _________________________________________________________________________________________________________________

    // ___________________________________________ [ Constructor ] _____________________________________________________
    public HomePage(WebDriver driver)
    {
        super(driver);
        PageFactory.initElements(driver, this);
        jse = (JavascriptExecutor) driver;
        action = new Actions(driver);
    }
    // _________________________________________________________________________________________________________________

    // _____________________________________________ [ Functions ] _____________________________________________________
    // Actions
    public void goToLoginPage()
    {
        clickButton(login_btn);
    }

    public void logout()
    {
        clickButton(account_btn);
        clickButton(logout_btn);
    }

    public void search(String searchToken)
    {
        clearText(searchBox);
        sendText(searchBox, searchToken);

        Robot robot= null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        robot.keyPress(KeyEvent.VK_ENTER);
    }
}