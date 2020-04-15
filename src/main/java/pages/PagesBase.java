package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.io.FileInputStream;
import java.util.Properties;

public class PagesBase
{
    // ________________________________________ [ Instance Variables ] _________________________________________________
    protected WebDriver driver;

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
    protected  WebElement clickButton(WebElement button)
    {
        button.click();

        return button;
    }

    protected  WebElement sendText(WebElement textElement , String value)
    {
//        WebDriverWait wait = new WebDriverWait(driver,30);
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(textElement.getAttribute("xpath"))));
        textElement.sendKeys(value);

        return textElement;
    }
    public WebElement clearText(WebElement element)
    {
        element.clear();

        return element;
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
