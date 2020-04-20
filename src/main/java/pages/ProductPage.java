package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class ProductPage extends PagesBase
{
    private WebDriver driver;
    // __________________________________________ [ Page Elements ] ____________________________________________________
    //

    // _________________________________________________________________________________________________________________

    // ___________________________________________ [ Constructor ] _____________________________________________________
    public ProductPage(WebDriver driver)
    {
        super(driver);
        this.driver = driver;

        PageFactory.initElements(driver, this);
    }
}
