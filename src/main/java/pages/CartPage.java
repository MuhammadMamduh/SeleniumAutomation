package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends PagesBase
{
    private WebDriver driver;
    // __________________________________________ [ Page Elements ] ____________________________________________________
    //
    @FindBy( css = "div[class='row cart-content']")
    List<WebElement> productsInCart_list;

    @FindBy( css = "product-> div[class='row cart-content'] > div:nth-child(2) div[class='info'] >a")
    List<WebElement> productsNamesInCart_list;

    @FindBy( css = "price-> div[class='row cart-content'] > div:nth-child(2) div[class='info'] h6.price")
    List<WebElement> productsPricesInCart_list;

    @FindBy( css = "div.subtotal p")
    WebElement subTotal_field;

    @FindBy( css = "div.shipping p")
    WebElement shipping_field;

    @FindBy( css = "div.total-price p")
    WebElement total_field;

    @FindBy( css = "button[class='btn checkout']")
    public WebElement checkout_btn;
    // _________________________________________________________________________________________________________________

    // ___________________________________________ [ Constructor ] _____________________________________________________
    public CartPage(WebDriver driver)
    {
        super(driver);
        this.driver = driver;

        PageFactory.initElements(driver, this);
    }
    // _________________________________________________________________________________________________________________

    // ____________________________________________ [ Functions ] ______________________________________________________
    // Actions

    public void calculateTheTotal()
    {

    }
}
