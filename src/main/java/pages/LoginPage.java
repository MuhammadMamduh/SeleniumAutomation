package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends PagesBase {
    // ________________________________________ [ Instance Variables ] _________________________________________________
    //
    @FindBy(xpath = "//input[@type=\"email\" and @name=\"email\" and @class=\"form-control\" and @placeholder=\"E-mail\"]")
    private WebElement email_txtField;

    @FindBy(xpath = "//input[@type=\"password\" and @name=\"password\" and @class=\"form-control\"]")
    private WebElement password_txtField;

    //
    @FindBy(css = "input[name=\"remember\"]")
    private WebElement rememberMe_checkBox;

    @FindBy(linkText = "Forgot Password")
    private WebElement forgotPassword_link;

    //
    @FindBy(css = "button.btn.create-account")
    private WebElement login_btn;
    // _________________________________________________________________________________________________________________

    // ___________________________________________ [ Constructor ] _____________________________________________________
    public LoginPage(WebDriver driver){
        super(driver);
    }
    // _________________________________________________________________________________________________________________

    // _______________________________________________ [ Core ] ________________________________________________________
    // Steps
    public void enterEmail(String email)
    {
        System.out.println("Entering the Email");
        sendText(email_txtField, email);
    }
    public void enterPassword(String password)
    {
        System.out.println("Entering the Password");
        sendText(password_txtField, password);
    }
    public HomePage clickLogin()
    {
        System.out.println("Logging in");
        clickButton(login_btn);

        return new HomePage(driver);
    }
    // Actions
    public void login(String email, String password){
        sendText(email_txtField, email);
        sendText(password_txtField, password);
        clickButton(login_btn);
    }
}