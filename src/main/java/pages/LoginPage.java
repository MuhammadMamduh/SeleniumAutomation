package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends PagesBase
{
    private WebDriver driver;

    // __________________________________________ [ Page Elements ] ____________________________________________________
    //
    @FindBy(css = "input[name='email'][type='email']")
    public WebElement email_txtField;

    @FindBy(css = "input[name='password'][type='password']")
    public WebElement password_txtField;

    @FindBy(css = "input[name=\"remember\"]")
    public WebElement rememberMe_checkBox;

    @FindBy(linkText = "Forgot Password?")
    public WebElement forgotPassword_link;

    @FindBy(css = "button.sallab-submit-button")
    public WebElement login_btn;

    @FindBy(css = "a.sallab-login--grid__form--column--create")
    public WebElement registrationPage_link;
    // _________________________________________________________________________________________________________________

    // ___________________________________________ [ Constructor ] _____________________________________________________
    public LoginPage(WebDriver driver){
        super(driver);
        this.driver = driver;

        PageFactory.initElements(driver, this);
    }
    // _________________________________________________________________________________________________________________

    // ____________________________________________ [ Functions ] ______________________________________________________
    // Actions
    public void login(String email, String password)
    {
        sendText(email_txtField, email);
        sendText(password_txtField, password);
        click(login_btn);
    }

    public RegistrationPage goToRegistrationPage() throws InterruptedException
    {
        driver.navigate().to("https://develop.nasnav.org/fortune/register");
        // click(registrationPage_link);

        return new RegistrationPage(driver);
    }
}