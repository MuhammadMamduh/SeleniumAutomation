package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.RecoverPasswordPage;
import pages.RegistrationPage;
import pages.LoginPage;
public class Registration_Tests extends TestsBase
{
    HomePage homePage;
    RegistrationPage registrationPage;
    RecoverPasswordPage recoverPasswordPage;


    // _________________________________________________________________________________________________________________
    // _________________________________________________________________________________________________________________


    @Test
    public void registration_positive_Test() throws InterruptedException
    {
        homePage = new HomePage(driver);
        recoverPasswordPage = new RecoverPasswordPage(driver);
        wait = new WebDriverWait(driver, 9);
        properties = loadDataRepoFile();

        String[] mainAndAuxilary_handleIds = openLinkInNewTab("https://www.mohmal.com/en/inbox"); // https://tempmailo.com/
        String mainApp_handleId = mainAndAuxilary_handleIds[0];
        String auxilaryApp_handleId = mainAndAuxilary_handleIds[1];

        String newEmail = driver.findElement(By.className("email")).getText();

        driver.switchTo().window(mainApp_handleId);
        registrationPage = homePage.goToLoginPage().goToRegistrationPage();
        wait.until(ExpectedConditions.visibilityOf(registrationPage.name_txtField)); // Assure Page Load ▌ [Registeration] Page ▌ Chosen Flag-> [registerationPage.name_txtField]
        registrationPage.registerWithEmail(newEmail, "anyName", getDataByKey("password_valid"), getDataByKey("password_valid"), true);
        
        wait.until(ExpectedConditions.visibilityOf(homePage.alert));
        Assert.assertEquals(homePage.alert.getText(), "Your account has been created successfully, check your mail.");
        wait.until(ExpectedConditions.invisibilityOf(homePage.alert));
// //        registrationPage.closeAlert_btn.click();

        driver.switchTo().window(auxilaryApp_handleId);
        driver.findElement(By.id("refresh")).click();
        driver.findElement(By.cssSelector("tbody > tr")).click();
        driver.findElement(By.cssSelector("h1.subject")).click();

        driver.switchTo().frame(driver.findElement(By.cssSelector("div > iframe")));
        driver.findElement(By.linkText("VERIFY YOUR EMAIL ADDRESS")).click();
        String[] windowsIDs = driver.getWindowHandles().toArray(new String[0]);
        driver.switchTo().window(windowsIDs[windowsIDs.length-1]);
        wait.until(ExpectedConditions.visibilityOf(homePage.alert));
        wait.until(ExpectedConditions.invisibilityOf(homePage.alert));
        // String recoverPasswordPage_handleId = getWindowIDByItsName("https://develop.nasnav.org/password_recovery?token=");
        // wait.until(ExpectedConditions.visibilityOf(recoverPasswordPage.recoverPassword_btn)); // Assure Page Load ▌ [RecoverPassword] Page ▌ Chosen Flag-> [recoverPasswordPage.recoverPassword_btn];
        // driver.switchTo().window(recoverPasswordPage_handleId);

        // recoverPasswordPage.recoverPassword("123456", true);
        // // Assertion
        // wait.until(ExpectedConditions.visibilityOf(recoverPasswordPage.alert));
        // Assert.assertEquals(recoverPasswordPage.alert.getText(), "Your password has been set successfully");

        // driver.switchTo().window(mainApp_handleId);
        // Rolling back to checkpoint.
        homePage.logout();
        wait.until(ExpectedConditions.visibilityOf(homePage.alert));
        Assert.assertEquals(homePage.alert.getText(), "You've been logged out successfully");
        homePage.closeAlert_btn.click();

        wait.until(ExpectedConditions.visibilityOf(homePage.loginAndAccount_btn)); // Assure Page Load ▌ [Home] Page ▌ Chosen Flag-> [homePage.loginAndAccount_btn]
        LoginPage loginPage = homePage.goToLoginPage();

        wait.until(ExpectedConditions.visibilityOf(loginPage.email_txtField)); // Assure Page Load ▌ [Login] Page ▌ Chosen Flag-> [loginPage.email_txtField]
        loginPage.login(newEmail, getDataByKey("password_valid"));
        // Assertion
        wait.until(ExpectedConditions.visibilityOf(homePage.alert)); // Assure Page Load | [Home] Page
        Assert.assertEquals(homePage.alert.getText(), "Welcome to Fortune");

        Thread.sleep(3000);
    }

//    @Test
    public void registration_negative_Test() throws InterruptedException
    {
    }
}
