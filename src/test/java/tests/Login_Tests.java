package tests;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.PagesBase;

import java.io.IOException;


public class Login_Tests extends TestsBase
{
    HomePage homePage;
    LoginPage loginPage;
    // _________________________________________________________________________________________________________________
    // _________________________________________________________________________________________________________________


    @DataProvider(name = "loginData_postive")
    public Object[][] loginData_postive()
    {
        properties = loadDataRepoFile();

        Object[][] credentialsData= new Object[2][3];

        credentialsData[0][0]= getDataByKey("email_valid"); credentialsData[0][1]= getDataByKey("password_valid"); credentialsData[0][2]= true;
        credentialsData[1][0]= getDataByKey("email_valid_2"); credentialsData[1][1]= getDataByKey("password_valid_2"); credentialsData[1][2]= true;

        return credentialsData;
    }
    @Test(dataProvider = "loginData_postive", priority = 1)
    public void login_positive_Test(String email, String password, boolean flag) throws InterruptedException, IOException
    {
        homePage = new HomePage(driver);
        wait = new WebDriverWait(driver, 9);

        wait.until(ExpectedConditions.visibilityOf(homePage.loginAndAccount_btn)); // Assure Page Load ▌ [Home] Page ▌ Chosen Flag-> [homePage.loginAndAccount_btn]
        LoginPage loginPage = homePage.goToLoginPage();

        wait.until(ExpectedConditions.visibilityOf(loginPage.email_txtField)); // Assure Page Load ▌ [Login] Page ▌ Chosen Flag-> [loginPage.email_txtField]
        loginPage.login(email, password);

        // Assertions*3
        wait.until(ExpectedConditions.visibilityOf(homePage.alert)); // Assure Page Load | [Home] Page
        Assert.assertEquals(homePage.alert.getText(), "Welcome to Fortune");

        wait.until(ExpectedConditions.invisibilityOf(homePage.alert));
        Assert.assertEquals(homePage.loginAndAccount_btn.getText(), "Account");

        Assert.assertEquals(driver.getCurrentUrl(), "https://develop.nasnav.org/fortune");
        wait.until(ExpectedConditions.invisibilityOf(homePage.alert));

        // Rolling back to checkpoint.
        homePage.logout();
        wait.until(ExpectedConditions.visibilityOf(homePage.alert));
        Assert.assertEquals(homePage.alert.getText(), "You've been logged out successfully");
        homePage.closeAlert_btn.click();
    }
    // _________________________________________________________________________________________________________________

    @DataProvider(name = "loginData_negative")
    public Object[][] loginData_negative()
    {
        properties = loadDataRepoFile();

        Object[][] credentialsData= new Object[2][2];

        credentialsData[0][0]= getDataByKey("email_invalid"); credentialsData[0][1]= getDataByKey("password_valid");
        credentialsData[1][0]= "any"+getDataByKey("email_invalid"); credentialsData[1][1]= getDataByKey("password_valid");
        // credentialsData[2][0]= getDataByKey("email_invalid")+"\'); 123413242 --"; credentialsData[2][1]= getDataByKey("password_valid");
        // credentialsData[3][0]= getDataByKey("email_valid"); credentialsData[3][1]= "";
        // credentialsData[4][0]= ""; credentialsData[4][1]= getDataByKey("password_valid");

        return credentialsData;
    }
    @Test(dataProvider = "loginData_negative", priority = 2)
    public void login_negative_Test(String email, String password) throws InterruptedException, IOException
    {
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        
        wait = new WebDriverWait(driver, 9);

        if(driver.getCurrentUrl().equals("https://develop.nasnav.org/fortune"))
        {
            wait.until(ExpectedConditions.visibilityOf(homePage.loginAndAccount_btn)); // Assure Page Load ▌ [Home] Page ▌ Chosen Flag-> [homePage.loginAndAccount_btn]
            LoginPage loginPage = homePage.goToLoginPage();
            wait.until(ExpectedConditions.visibilityOf(loginPage.email_txtField)); // Assure Page Load ▌ [Login] Page ▌ Chosen Flag-> [loginPage.email_txtField]
        }

        loginPage.login(email, password);

        // Assertions*3
        wait.until(ExpectedConditions.visibilityOf(loginPage.alert));
        loginPage.hoverOnElement(loginPage.alert, driver);
        Assert.assertEquals(loginPage.alert.getText(), "Email or password is incorrect");
        loginPage.closeAlert_btn.click();

        Assert.assertEquals(driver.getCurrentUrl(), "https://develop.nasnav.org/fortune/login");
        Assert.assertEquals(homePage.loginAndAccount_btn.getText(), "LOGIN");

        // Rolling back to checkpoint.
        PagesBase.clearText(loginPage.email_txtField);
        PagesBase.clearText(loginPage.password_txtField);
    }
    // _________________________________________________________________________________________________________________
    // _________________________________________________________________________________________________________________

}
