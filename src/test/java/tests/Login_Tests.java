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
    LoginPage loginPage;

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

    @DataProvider(name= "searchData")
    public Object[][] searchData()
    {
        try {
            fis= new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\resources\\credentials.properties");
            properties= new Properties();
            properties.load(fis);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Object[][] credentialsData= new Object[3][2];

        credentialsData[0][0]= properties.getProperty("searchToken_certainProduct_valid_present"); credentialsData[0][1]= true;
        credentialsData[1][0]= properties.getProperty("searchToken_manyProducts_valid_present"); credentialsData[1][1]= true;
        credentialsData[2][0]= properties.getProperty("searchToken_product_valid_absent"); credentialsData[2][1]= false;

        return credentialsData;
    }

    @Test(dataProvider = "loginCredentialsData")
    public void login_Test(String email, String password, boolean flag) throws InterruptedException, IOException
    {
        homePage = new HomePage(driver); // TODO
        loginPage = new LoginPage(driver);

        homePage.goToLoginPage();
        loginPage.login(email, password);
        homePage.logout();

        Thread.sleep(6000);
    }
    @Test(dataProvider = "searchData")
    public void search_Test(String searchToken, boolean flag)
    {
        homePage = new HomePage(driver); // TODO
        homePage.search(searchToken);
    }

//    @Test(dependsOnMethods = "login_Test")
    public void logout() throws InterruptedException, IOException
    {
        homePage.logout();
        Thread.sleep(3000);
//        Assert.assertEquals(homePage.getInterfacingLogin_btn().getText(), "LOGIN");
    }


}
