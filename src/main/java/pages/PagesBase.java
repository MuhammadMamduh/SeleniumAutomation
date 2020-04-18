package pages;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
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
    public WebDriver driver; // TODO: Understand why this corrupted the design & caused a [NullPointerException]

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

    // _________________________________________________________________________________________________________________


    // ___________________________________________ [ Constructor ] _____________________________________________________
    public PagesBase(WebDriver driver)
    {
        PageFactory.initElements(driver, this);
    }
    // _________________________________________________________________________________________________________________


    // ____________________________________________ [ Functions ] ______________________________________________________
    // Common Functions
    public static WebElement click(WebElement button)
    {
        button.click();

        return button;
    }
    public WebElement hoverOnElement(WebElement element, WebDriver driver)
    {
        action = new Actions(driver);

        action.moveToElement(element).build().perform();

        return element;
    }
    public static WebElement sendText(WebElement textElement , String value)
    {
        textElement.sendKeys(value);

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



//    public CookieManager getCookieManager(){
//        return new CookieManager(driver);
//    }
    // _________________________________________________________________________________________________________________
}
