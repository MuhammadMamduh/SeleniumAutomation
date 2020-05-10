package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegistrationPage extends PagesBase
{
    private WebDriver driver;

    // __________________________________________ [ Page Elements ] ____________________________________________________
    //
    @FindBy(css = "input[type=\"email\"][name=\"Email\"]")
    public WebElement email_txtField;

    @FindBy(css = "input[type=\"text\"][name=\"name\"]")
    public WebElement name_txtField;

    @FindBy(css = "input[type=\"password\"][name=\"Password\"]")
    public WebElement password_txtField;

    @FindBy(css = "input[type=\"password\"][name=\"confirmPassword\"]")
    public WebElement passwordConfirmation_txtField;
    // @FindBy(id = "autoSizingCheck")
    // public WebElement terms_checkBox;

    @FindBy(css = "button.sallab-submit-button")
    public WebElement signUp_btn;

    @FindBy(css = "button.fb-button")
    public WebElement continueWithFB_btn;

    @FindBy(id = "button.twitter-button")
    public WebElement continueWithTwitter_btn;

    @FindBy(linkText = "Log in Now")
    public WebElement loginPage_link;
    // _________________________________________________________________________________________________________________

    // ___________________________________________ [ Constructor ] _____________________________________________________
    public RegistrationPage(WebDriver driver){
        super(driver);
        this.driver = driver;

        PageFactory.initElements(driver, this);
    }
    // _________________________________________________________________________________________________________________

    // ____________________________________________ [ Functions ] ______________________________________________________
    public void registerWithEmail(String email, String name, String password, String passwordConfirmation, boolean acceptTerms_flag)
    {
        sendText(email_txtField, email);
        sendText(name_txtField, name);
        sendText(password_txtField, password);
        sendText(passwordConfirmation_txtField, passwordConfirmation);

        // if(acceptTerms_flag==true)
        // {
        //     click(terms_checkBox);
        // }
        click(signUp_btn);
    }
    public void registerWithFB()
    {
        click(continueWithFB_btn);
    }
    public void registerWithTwitter()
    {
        click(continueWithTwitter_btn);
    }
}
