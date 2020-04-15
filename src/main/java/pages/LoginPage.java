package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends PagesBase {
    // ________________________________________ [ Instance Variables ] _________________________________________________
    //
    @FindBy(xpath = "/html/body/div[1]/div[2]/div[4]/div[2]/form/div[1]/div[1]/div[1]/input")
    public WebElement email_txtField;

    @FindBy(xpath = "//input[@type=\"password\" and @name=\"password\" and @class=\"form-control\"]")
    protected WebElement password_txtField;

    //
    @FindBy(css = "input[name=\"remember\"]")
    protected WebElement rememberMe_checkBox;

    @FindBy(linkText = "Forgot Password")
    protected WebElement forgotPassword_link;

    //
    @FindBy(css = "button.btn.create-account")
    protected WebElement login_btn;
    // _________________________________________________________________________________________________________________

    // ___________________________________________ [ Constructor ] _____________________________________________________
    public LoginPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }
    // _________________________________________________________________________________________________________________

    // _______________________________________________ [ Core ] ________________________________________________________
    // Actions
    public void login(String email, String password)
    {
        sendText(email_txtField, email);
        sendText(password_txtField, password);
        clickButton(login_btn);
    }
}