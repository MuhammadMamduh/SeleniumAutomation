package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage extends PagesBase
{
    private WebDriver driver;
    // __________________________________________ [ Page Elements ] ____________________________________________________
    //

    // _________________________________________________________________________________________________________________

    // ___________________________________________ [ Constructor ] _____________________________________________________
    public CheckoutPage(WebDriver driver)
    {
        super(driver);
        this.driver = driver;

        PageFactory.initElements(driver, this);
    }
}
