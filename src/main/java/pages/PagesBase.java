package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.logging.Logger;

// TODO: Validate the popups.
// TODO: Registration.
// TODO: Searching.
// TODO: Logging
// TODO:
// TODO:
// TODO:
// TODO:
public class PagesBase
{
    // ________________________________________ [ Instance Variables ] _________________________________________________
//    public WebDriver driver; // TODO: Understand why this corrupted the design & caused a [NullPointerException]

    protected FileInputStream fis;
    protected Properties properties;

    public JavascriptExecutor jse ;
    public Select select ;
    public Actions action ;

    protected HomePage homePage;
    // _________________________________________________________________________________________________________________


    // ___________________________________________ [ Constructor ] _____________________________________________________
    public PagesBase(WebDriver driver)
    {
        PageFactory.initElements(driver, this);
    }
    // _________________________________________________________________________________________________________________


    // ____________________________________________ [ Functions ] ______________________________________________________
    // Common Functions
    public static WebElement clickButton(WebElement button)
    {
        button.click();
        Logger.getLogger("Clicking on Button: ");

        return button;
    }

    public static WebElement sendText(WebElement textElement , String value)
    {
        textElement.sendKeys(value);
        Logger.getLogger("Writing in Textfield: ");

        return textElement;
    }
    public static WebElement clearText(WebElement element)
    {
        element.clear();
        Logger.getLogger("Clearing Textfield: ");

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
        Logger.getLogger("Clicking ENTER");
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
