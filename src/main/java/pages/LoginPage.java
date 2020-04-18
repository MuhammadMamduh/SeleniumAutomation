package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends PagesBase
{
    private WebDriver driver;
    // ________________________________________ [ Instance Variables ] _________________________________________________
    //
    @FindBy(xpath = "/html/body/div[1]/div[2]/div[4]/div[2]/form/div[1]/div[1]/div[1]/input")
    public WebElement email_txtField;

    @FindBy(xpath = "//input[@type=\"password\" and @name=\"password\" and @class=\"form-control\"]")
    public WebElement password_txtField;

    @FindBy(css = "input[name=\"remember\"]")
    public WebElement rememberMe_checkBox;

    @FindBy(linkText = "Forgot Password")
    public WebElement forgotPassword_link;

    @FindBy(css = "button.btn.create-account")
    public WebElement login_btn;

    @FindBy(linkText = "Register Now")
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

    public RegistrationPage goToRegistrationPage()
    {
        click(registrationPage_link);

        return new RegistrationPage(driver);
    }
}