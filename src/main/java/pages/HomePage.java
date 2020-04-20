package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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

    @FindBy(xpath = "//*[@id=\"root\"]/div[2]/div[3]/div[2]/div[3]/div/div[1]/form/input[1]")
    public WebElement searchBox;


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

    public void search(String searchToken)
    {
        clearText(searchBox);
        sendText(searchBox, searchToken);
        clickEnter();
    }
}