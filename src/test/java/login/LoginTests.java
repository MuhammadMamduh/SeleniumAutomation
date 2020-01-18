package login;

import base.Base;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTests extends Base {
    @Test
    public void testSuccessfulLogin() throws InterruptedException {
        LoginPage loginPage = homePage.clickGoToLoginPage();

        loginPage.setEmail("test2@nasnav.com");
        loginPage.setPassword("123456");
        Thread.sleep(2000);

        homePage = loginPage.clickLogin();
        Thread.sleep(2000);

//        assertTrue(secureAreaPage.getAlertText()
//                        .contains("You logged into a secure area!"),
//                "Alert text is incorrect");
    }
}
