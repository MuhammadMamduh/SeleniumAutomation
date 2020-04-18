package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegistrationPage extends PagesBase
{
    private WebDriver driver;
    // ________________________________________ [ Instance Variables ] _________________________________________________
    //
    @FindBy(css = "input[type=\"email\"][name=\"email\"]")
    public WebElement email_txtField;

    @FindBy(css = "input[type=\"text\"][name=\"name\"]")
    public WebElement name_txtField;

    @FindBy(id = "autoSizingCheck")
    public WebElement terms_checkBox;

    @FindBy(css = "button.create-account")
    public WebElement createAccount_btn;

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
    public void registerWithEmail(String email, String name, boolean acceptTerms_flag)
    {
        sendText(email_txtField, email);
        sendText(name_txtField, name);
        if(acceptTerms_flag==true)
        {
            click(terms_checkBox);
        }
        click(createAccount_btn);
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
