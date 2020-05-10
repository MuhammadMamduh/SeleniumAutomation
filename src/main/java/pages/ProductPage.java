package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage extends PagesBase
{
    private WebDriver driver;
    // __________________________________________ [ Page Elements ] ____________________________________________________
    //
    @FindBy (css = "div[role=\"tabpanel\"] > img")
    public WebElement productImage;

    @FindBy (css = "div.details > h4")
    public WebElement productName_main;

    @FindBy (css = "div.details > p")
    public WebElement productName_minor;

    @FindBy (css = "div.details > h5")
    public WebElement productPrice;

    @FindBy (css = "div.filters div.dropdown")
    public WebElement size_dropDown;

    @FindBy (css = "div.filters div.dropdown")
    public WebElement quantity_txtField;

    @FindBy (css = "button[class=\"btn cart\"]")
    public WebElement addToCart_btn;

    @FindBy (css = "button[class=\"btn availability\"]")
    public WebElement checkAtStore_btn;

    @FindBy (css = "ul.all-shops")
    public WebElement availableShops_list;

    @FindBy (css = "div.grid-data")
    List<WebElement> resultingProductsBulk_list;

    @FindBy (css = "div.grid-data > a")
    List<WebElement> resultingProductsLink_list;
    // _________________________________________________________________________________________________________________

    // ___________________________________________ [ Constructor ] _____________________________________________________
    public ProductPage(WebDriver driver)
    {
        super(driver);
        this.driver = driver;

        PageFactory.initElements(driver, this);
    }    
    // _________________________________________________________________________________________________________________

    // ____________________________________________ [ Functions ] ______________________________________________________
    // Actions
    public boolean addItemToCart()
    {
        click(addToCart_btn);

        if(alert.getText().equals("anObject"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
