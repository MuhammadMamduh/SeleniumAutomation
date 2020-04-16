package tests;

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

        Thread.sleep(2000);
        LoginPage loginPage = homePage.goToLoginPage();
        loginPage.login(email, password);

        Thread.sleep(1000);
        Assert.assertEquals(driver.getCurrentUrl(), "https://develop.nasnav.org/fortune");
        Assert.assertEquals(homePage.loginAndAccount_btn.getText(), "Account");

        // This step is just to pave the way to restart the test with different data.
        homePage.logout();
    }
    // _________________________________________________________________________________________________________________

    @DataProvider
    public Object[][] loginData_negative()
    {
        properties = loadDataRepoFile();

        Object[][] credentialsData= new Object[2][3];

        credentialsData[0][0]= getDataByKey("email_invalid"); credentialsData[0][1]= getDataByKey("password_valid"); credentialsData[0][2]= false;
        credentialsData[1][0]= "any"+getDataByKey("email_invalid"); credentialsData[1][1]= getDataByKey("password_valid"); credentialsData[1][2]= false;

        return credentialsData;
    }
    @Test(dataProvider = "loginData_negative", priority = 2)
    public void login_negative_Test(String email, String password, boolean flag) throws InterruptedException, IOException
    {
        homePage = new HomePage(driver);

        Thread.sleep(2000);
        LoginPage loginPage = homePage.goToLoginPage();
        loginPage.login(email, password);

        Assert.assertEquals(driver.getCurrentUrl(), "https://develop.nasnav.org/fortune/login");
        Assert.assertEquals(homePage.loginAndAccount_btn.getText(), "LOGIN");

        // These steps is just to pave the way to restart the test with different data.
        PagesBase.clearText(loginPage.email_txtField);
        PagesBase.clearText(loginPage.password_txtField);
    }
    // _________________________________________________________________________________________________________________
    // _________________________________________________________________________________________________________________

}
