package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ResultingProductsPage extends PagesBase
{
    private WebDriver driver;

    // __________________________________________ [ Page Elements ] ____________________________________________________
    //
    @FindBy (css = "div.grid-data")
    public List<WebElement> resultingProductsBulk_list;

    @FindBy (css = "div.grid-data > a")
    public List<WebElement> resultingProductsLink_list;

    String shopNowBtnCss_query = "a";

    // _________________________________________________________________________________________________________________

    // ___________________________________________ [ Constructor ] _____________________________________________________
    public ResultingProductsPage(WebDriver driver) {
        super(driver);
        this.driver = driver;

        PageFactory.initElements(driver, this);
    }
    // _________________________________________________________________________________________________________________

    // ____________________________________________ [ Functions ] ______________________________________________________
    // Actions
    public ProductPage chooseRandomProduct()
    {
        int randomProductIndex = getRandomIndexFromZeroBasedDs(resultingProductsBulk_list.size());
        
        try
        {
            WebElement randomProduct = resultingProductsLink_list.get(randomProductIndex);
        
            click(randomProduct);
        }
        catch(ArrayIndexOutOfBoundsException e)
        {
            e.printStackTrace();
            System.out.println(e.getLocalizedMessage());
        }
        
        return (new ProductPage(driver));
    }
}
