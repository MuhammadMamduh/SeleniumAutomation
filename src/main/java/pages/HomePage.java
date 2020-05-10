package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HomePage extends PagesBase
{
    private WebDriver driver;

    // __________________________________________ [ Page Elements ] ____________________________________________________
    // Instance Variables that are ONLY visible BEFORE Logging in
    @FindBy(className = "login-button-large")
    public WebElement loginAndAccount_btn;

    // Instance Variables that are ONLY visible AFTER Logging in
    @FindBy(css = "button.btn.account-btn")
    public WebElement account_btn;

    @FindBy(css = "div.logged-in-menu :nth-child(3)")
    public WebElement logout_btn;

    // Common Before & After Logging in
    @FindBy(xpath = "//img[@class=\"logo\"]")
    public WebElement logo;

    @FindBy(xpath = "//div[@class=\"d-flex flex-wrap banner\"]")
    public WebElement mainSlidingBanner;

    @FindBy(css = "input[type=\"search\"]")
    public WebElement searchBox;

    @FindBy(css = "span.searchButton")
    public WebElement search_btn;

    @FindBy(css = "div.Navigation_navButtons__2syKV button")
    public List<WebElement> navBarElements_list;

    @FindBy(css = "div.Navigation_navButtons__2syKV :nth-child(1)")
    public WebElement fragrances_navBarElement;

    @FindBy(css = "div.Column_Column__2fQ1B a")
    public List<WebElement> fragrancesCategories_list;

    @FindBy(css = "div.Navigation_navButtons__2syKV :nth-child(2)")
    public WebElement makeUp_navBarElement;

    @FindBy(css = "div.Column_Column__2fQ1B a")
    public List<WebElement> makeUpCategories_list;

    @FindBy(css = "div.Navigation_navButtons__2syKV :nth-child(4)")
    public WebElement skinCare_navBarElement;

    @FindBy(css = "div.Navigation_navButtons__2syKV :nth-child(5)")
    public WebElement newArrivals_navBarElement;

    @FindBy(css = "div.Navigation_navButtons__2syKV :nth-child(6)")
    public WebElement bestSellers_navBarElement;

    // _________________________________________________________________________________________________________________

    // ___________________________________________ [ Constructor ] _____________________________________________________
    public HomePage(WebDriver driver)
    {
        super(driver);
        this.driver = driver;

        PageFactory.initElements(driver, this);
        jse = (JavascriptExecutor) driver;
//        action = new Actions(driver);
    }
    // _________________________________________________________________________________________________________________

    // _____________________________________________ [ Functions ] _____________________________________________________
    // Actions
    public LoginPage goToLoginPage()
    {
        click(loginAndAccount_btn);

        return new LoginPage(driver);
    }

    public void logout()
    {
        click(account_btn);
        click(logout_btn);
    }

    public ResultingProductsPage search(String searchToken)
    {
        clearText(searchBox);
        sendText(searchBox, searchToken);
        click(search_btn);
        // clickEnter();

        return (new ResultingProductsPage(driver));
    }

    public void navigateMainProductsCategories(WebElement mainCategoryElement, String subCategoryElementLinkText) throws InterruptedException
    {
        // int randomElementIndex = getRandomIndexFromZeroBasedDs(navBarElements_list.size());
        // hoverOnElement(mainCategoryElement, driver);
        click(mainCategoryElement);
        Thread.sleep(3000);
        click(driver.findElement(By.linkText(subCategoryElementLinkText)));
    }
}