package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private WebDriver driver;

    private By email_txtField= By.xpath("//input[@type=\"email\" and @name=\"email\" and @class=\"form-control\" and @placeholder=\"E-mail\"]");
    private By password_txtField= By.xpath("//input[@type=\"password\" and @name=\"password\" and @class=\"form-control\"]");

    private By rememberMe_checkBox= By.cssSelector("input[name=\"remember\"]");
    private By forgotPassword_link= By.linkText("Forgot Password");

    private By loginButton = By.cssSelector("button.btn.create-account");

    public LoginPage(WebDriver driver){
        this.driver = driver;
    }

    public void setEmail(String email)
    {
        System.out.println("Entering the Email");
        driver.findElement(email_txtField).sendKeys(email);
    }

    public void setPassword(String password)
    {
        System.out.println("Entering the Password");
        driver.findElement(password_txtField).sendKeys(password);
    }

    public HomePage clickLogin()
    {
        System.out.println("Logging in");
        driver.findElement(loginButton).click();

        return new HomePage(driver);
    }
}