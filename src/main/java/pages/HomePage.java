package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
    // Instance Variables
    private WebDriver driver;


    private By interfacingLoginButton= By.xpath("//*[@id=\"root\"]/div[2]/div[3]/div[2]/div[3]/div/div[2]/button");
    private By logo= By.xpath("//img[@class=\"logo\"]");
    private By mainSlidingBanner= By.xpath("//div[@class=\"d-flex flex-wrap banner\"]");

    // Constructor
    public HomePage(WebDriver driver)
    {
        this.driver= driver;
    }

    // Getter(s)
    public WebElement getLoginButton()
    {
        return driver.findElement(interfacingLoginButton);
    }

    // Core
    public LoginPage clickGoToLoginPage() throws InterruptedException
    {
        // Explicit Wait
        WebDriverWait wait= new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.visibilityOfElementLocated(logo));
        wait.until(ExpectedConditions.visibilityOfElementLocated(mainSlidingBanner));

        Thread.sleep(2000);

        System.out.println("Clicking to go to the Login page");
        getLoginButton().click();

        return new LoginPage(driver);
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