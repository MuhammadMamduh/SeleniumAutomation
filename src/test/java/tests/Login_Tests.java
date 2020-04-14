package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class Login_Tests extends TestsBase
{
    HomePage homePage;

        @DataProvider(name= "loginCredentialsData")
        public Object[][] loginData()
        {
            try {
                fis= new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\resources\\credentials.properties");
                properties= new Properties();
                properties.load(fis);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Object[][] credentialsData= new Object[3][3];

            credentialsData[0][0]= properties.getProperty("email_valid"); credentialsData[0][1]= properties.getProperty("password_valid"); credentialsData[0][2]= true;
            credentialsData[1][0]= properties.getProperty("email_valid_2"); credentialsData[1][1]= properties.getProperty("password_valid_2"); credentialsData[1][2]= true;
            credentialsData[2][0]= properties.getProperty("email_invalid"); credentialsData[2][1]= properties.getProperty("password_valid"); credentialsData[2][2]= false;

            return credentialsData;
        }

//        @Test
//        public void loginModule(String email, String password, int flag) throws InterruptedException, IOException
//        {
//            homePage = new HomePage(driver);
//
//            LoginPage loginPage= homePage.clickGoToLoginPage();
//
//            if(flag==1)
//            {
//                System.out.println("Getting credentials directly");
////                loginPage.enterEmail(email);
////                loginPage.enterPassword(password);
//            }
//            else if(flag==2)
//            {
//                System.out.println("Reading from file");
////                loginPage.enterEmail(properties.getProperty(email));
////                loginPage.enterPassword(properties.getProperty(password));
//            }
//            else if(flag==3)
//            {
//                // Kept in case the need to read from an excel data source
//            }
//
//            Thread.sleep(2000);
//
////            homePage = loginPage.clickLogin();
//
//            Thread.sleep(2000);
//        }
    @Test(dataProvider = "loginCredentialsData")
    public void login_Test(String email, String password, boolean flag) throws InterruptedException, IOException
    {
        homePage = new HomePage(driver); // TODO

        LoginPage loginPage= homePage.clickGoToLoginPage();
        loginPage.login(email, password);

    }

    //        @Test(dependsOnMethods = "testSuccessfulLogin")
    public void logout() throws InterruptedException, IOException
    {
//            homePage.clickGoToLogout().clickLogout();
        Thread.sleep(3000);
//            Assert.assertEquals(homePage.getInterfacingLogin_btn().getText(), "LOGIN");
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
