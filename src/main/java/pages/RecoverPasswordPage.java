package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RecoverPasswordPage extends PagesBase
{
    private WebDriver driver;
    // ________________________________________ [ Instance Variables ] _________________________________________________
    // Password doesn't match confirmed password
    // Your password has been set successfully
    @FindBy(css = "input[name=\"password\"]")
    public WebElement password_txtField;

    @FindBy(css = "input[name=\"passwordConfirm\"]")
    public WebElement passwordConfirmation_txtField;

    @FindBy(css = "button[class=\"btn btn-submit-recover bg\"]")
    public WebElement recoverPassword_btn;
    // _________________________________________________________________________________________________________________

    // ___________________________________________ [ Constructor ] _____________________________________________________
    public RecoverPasswordPage(WebDriver driver){
        super(driver);
        this.driver = driver;

        PageFactory.initElements(driver, this);
    }
    // _________________________________________________________________________________________________________________

    // ____________________________________________ [ Functions ] ______________________________________________________
    // Actions
    public void recoverPassword(String password, boolean rightfulness_flag)
    {
        sendText(password_txtField, password);
        if(!rightfulness_flag)
        {
            password = password + "anythingToMakeThemMismatch";
        }
        sendText(passwordConfirmation_txtField, password);
        click(recoverPassword_btn);
    }
}
