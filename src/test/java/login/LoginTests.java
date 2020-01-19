package login;

import base.Base;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;

import java.io.IOException;

public class LoginTests extends Base
{
    @DataProvider(name= "loginCredentialsData")
    public Object[][] loginData()
    {
        Object[][] credentialsData= new Object[3][3];

        credentialsData[0][0]= "test2@nasnav.com"; credentialsData[0][1]= "123456"; credentialsData[0][2]= true;
        credentialsData[1][0]= "test2@nasnav.com"; credentialsData[1][1]= "123"; credentialsData[1][2]= false;
        credentialsData[2][0]= "test2@nasnav"; credentialsData[2][1]= "123456"; credentialsData[2][2]= false;

        return credentialsData;
    }

    public void loginModule(String email, String password, int flag) throws InterruptedException, IOException
    {
        LoginPage loginPage= homePage.clickGoToLoginPage();

        if(flag==1)
        {
            System.out.println("Getting credentials directly");
            loginPage.enterEmail(email);
            loginPage.enterPassword(password);
        }
        else if(flag==2)
        {
            System.out.println("Reading from file");
            loginPage.enterEmail(properties.getProperty(email));
            loginPage.enterPassword(properties.getProperty(password));
        }
        else if(flag==3)
        {
            // Kept in case the need to read from an excel data source
        }

        Thread.sleep(2000);

        homePage = loginPage.clickLogin();

        Thread.sleep(2000);
    }

    @Test
    public void testSuccessfulLogin() throws InterruptedException, IOException
    {
        loginModule("test2@nasnav.com", "123456", 1);
        Assert.assertEquals(homePage.getAccount_btn().getText(), "Account");
    }

    @Test(dependsOnMethods = "testSuccessfulLogin")
    public void logout() throws InterruptedException, IOException
    {
        homePage.clickGoToLogout().clickLogout();
        Thread.sleep(3000);
        Assert.assertEquals(homePage.getInterfacingLogin_btn().getText(), "LOGIN");
    }
    //

    // _____________________________________________ [ Draft ] _________________________________________________________
//    if (charge==true)
//    {
//        Assert.assertTrue(homePage.getAccount_btn().isDisplayed(), "FAIL: it did not log in");
//    }
//            else
//    {
//        Assert.assertFalse(homePage.getAccount_btn().isDisplayed(), "Fail: it was able to log in");
//    }

//    try
//    {
//        loginModule(email, password, 1);
//        Assert.assertEquals(homePage.getAccount_btn().getText(), "Account");
//    }
//        catch (NoSuchElementException e)
//    {
//        homePage.clickLogout();
//    }

//    (dataProvider= "loginCredentialsData")
}
