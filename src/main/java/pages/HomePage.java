package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class HomePage extends PagesBase
{
    // ________________________________________ [ Instance Variables ] _________________________________________________
    // Instance Variables that are ONLY visible BEFORE Logging in
    @FindBy(xpath = "//*[@id=\"root\"]/div[2]/div[3]/div[2]/div[3]/div/div[2]/button")
    private WebElement login_btn;

    // Instance Variables that are ONLY visible AFTER Logging in
    @FindBy(css = "button.btn.account-btn")
    private WebElement account_btn;

    @FindBy(css = "div.logged-in-menu :nth-child(3)")
    private WebElement logout_btn;

    // Common Before & After Logging in
    @FindBy(xpath = "//img[@class=\"logo\"]")
    private WebElement logo;

    @FindBy(xpath = "//div[@class=\"d-flex flex-wrap banner\"]")
    private WebElement mainSlidingBanner;
    // _________________________________________________________________________________________________________________

    // ___________________________________________ [ Constructor ] _____________________________________________________
    public HomePage(WebDriver driver)
    {
        super(driver);
        jse = (JavascriptExecutor) driver;
        action = new Actions(driver);
    }
    // _________________________________________________________________________________________________________________

    // _____________________________________________ [ Functions ] _____________________________________________________
    // Actions
    public LoginPage clickGoToLoginPage() throws InterruptedException
    {
        clickButton(login_btn);

        return new LoginPage(driver);
    }

    public HomePage clickGoToLogout() throws InterruptedException
    {
        return new HomePage(driver);
    }

//    private void clickLink(String linkText)
//    {
//        driver.findElement(By.linkText(linkText)).click();
//    }

    // _________________________________________________
//    @FindBy(xpath = "//*[@id=\"root\"]/div[2]/div[3]/div[2]/div[3]/div/div[2]/button")
//    private WebElement interfacingLoginButton;
//    interfacingLoginButton.click();
}