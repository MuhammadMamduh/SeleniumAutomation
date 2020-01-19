package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage
{
    // ________________________________________ [ Instance Variables ] _________________________________________________
    private WebDriver driver;

    // Instance Variables that are ONLY visible BEFORE Logging in
    private By interfacingLogin_btn= By.xpath("//*[@id=\"root\"]/div[2]/div[3]/div[2]/div[3]/div/div[2]/button");

    // Instance Variables that are ONLY visible AFTER Logging in
    private By account_btn= By.cssSelector("button.btn.account-btn");
    private By logout_btn= By.cssSelector("div.logged-in-menu :nth-child(3)");

    // Common Before & After Logging in
    private By logo= By.xpath("//img[@class=\"logo\"]");
    private By mainSlidingBanner= By.xpath("//div[@class=\"d-flex flex-wrap banner\"]");
    // _________________________________________________________________________________________________________________

    // ___________________________________________ [ Constructor ] _____________________________________________________
    public HomePage(WebDriver driver)
    {
        this.driver= driver;
    }
    // _________________________________________________________________________________________________________________

    // ____________________________________________ [ Getter(s) ] ______________________________________________________
    public WebElement getInterfacingLogin_btn()
    {
        return driver.findElement(interfacingLogin_btn);
    }
    public WebElement getAccount_btn()
    {
        return driver.findElement(account_btn);
    }
    public WebElement getLogout_btn()
    {
        return driver.findElement(logout_btn);
    }
    // _________________________________________________________________________________________________________________

    // _______________________________________________ [ Core ] ________________________________________________________
    public LoginPage clickGoToLoginPage() throws InterruptedException
    {
        // Explicit Wait
        WebDriverWait wait= new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.visibilityOfElementLocated(logo));
        wait.until(ExpectedConditions.visibilityOfElementLocated(mainSlidingBanner));

        Thread.sleep(2000);

        System.out.println("Clicking to go to the Login page");
        getInterfacingLogin_btn().click();

        return new LoginPage(driver);
    }

    public HomePage clickGoToLogout() throws InterruptedException
    {
        // Explicit Wait
        WebDriverWait wait= new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.visibilityOfElementLocated(logo));
        wait.until(ExpectedConditions.visibilityOfElementLocated(account_btn));

        Thread.sleep(2000);

        System.out.println(getAccount_btn().getText());
        getAccount_btn().click();

        return new HomePage(driver);
    }

    public HomePage clickLogout() throws InterruptedException
    {
        getLogout_btn().click();
        System.out.println(getInterfacingLogin_btn().getText());

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