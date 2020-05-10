package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;

// TODO: Validate the popups.
// _____________________________________________________________________________________________________________________
// TODO: Logging | Yet using printing
// _____________________________________________________________________________________________________________________
// TODO: Registration.
// TODO: Searching.
// TODO:
// TODO:
// TODO:
// TODO:
public class PagesBase
{
    // ________________________________________ [ Instance Variables ] _________________________________________________
    // public WebDriver driver; // TODO: Understand why this corrupted the design & caused a [NullPointerException]

    protected FileInputStream fis;
    protected Properties properties;

    public JavascriptExecutor jse ;
    public Select select ;
    public Actions action ;
    public WebDriverWait wait;

    protected HomePage homePage;

    @FindBy(css = "div.Toastify__toast-container Toastify__toast-container--top-center")
    public List<WebElement> alerts_list;

    @FindBy(css = "div.Toastify__toast-body")
    public WebElement alert;

    @FindBy(css = "div.Toastify__toast-body + button")
    public WebElement closeAlert_btn;

    @FindBy(css = "button[class=\"shopping-cart icon\"]")
    public WebElement cart_btn;

    @FindBy(css = "button[class=\"shopping-cart icon\"] p.badge")
    public WebElement noOfItemsInCart_lbl;

    @FindBy(css = "div.dropCartContainer ul[class=\"cartDropdown mb-0\"]")
    public WebElement cartItems_dropDown;

    @FindBy(css = "a[class=\"cartDropdown__btn bg\"]")
    public WebElement goToCartFromDropDown_btn;

    
    // _________________________________________________________________________________________________________________


    // ___________________________________________ [ Constructor ] _____________________________________________________
    public PagesBase(WebDriver driver)
    {
        PageFactory.initElements(driver, this);
    }
    // _________________________________________________________________________________________________________________


    // ____________________________________________ [ Functions ] ______________________________________________________
    // Common Functions
    public static WebElement click(WebElement element)
    {
        element.click();

        return element;
    }
    public WebElement hoverOnElement(WebElement element, WebDriver driver)
    {
        action = new Actions(driver);

        action.moveToElement(element).build().perform();

        return element;
    }
    public static void scrollToElement(WebDriver driver, WebElement element)
    {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", element);
    }
    public static WebElement sendText(WebElement textElement , String value)
    {
        textElement.sendKeys(value);
        System.out.println("Inputing: " + value);

        return textElement;
    }
    public static WebElement clearText(WebElement element)
    {
        element.clear();

        return element;
    }
    public static void clickEnter()
    {
        Robot robot= null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        robot.keyPress(KeyEvent.VK_ENTER);
    }
    public void scrollToBottom()
    {
        jse.executeScript("scrollBy(0,2500)");
    }
    public static int getRandomIndexFromZeroBasedDs(int dsSize)
    {
        if(dsSize==0)
        {
            return -1;
        }
        else
        {
            double random = Math.random()*10;
            int roundedRandom = (int) Math.round(random);
    
            while(roundedRandom>=dsSize-1) // dsSize-1 = maxIndex
            {
                roundedRandom--;
            }
    
            return roundedRandom;
        }
    }

    public static void clickElementWhenClickable(By locator, int timeout, EventFiringWebDriver driver) {
        WebElement element = null;
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
    }
//    public CookieManager getCookieManager(){
//        return new CookieManager(driver);
//    }
    // _________________________________________________________________________________________________________________
}
