package tests;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.HomePage;

public class productsNavigation_Tests extends TestsBase
{
    @DataProvider(name= "data")
    public Object[] searchData() throws InterruptedException
    {
        wait = new WebDriverWait(driver, 9);
        HomePage homePage = new HomePage(driver);

        Thread.sleep(3000);
         homePage.fragrances_navBarElement.click();
         wait.until(ExpectedConditions.visibilityOf(homePage.fragrancesCategories_list.get(0)));
        Object[] credentialsData= new Object[homePage.fragrancesCategories_list.size()];
//        credentialsData[0] = "Lip Gloss";
//        credentialsData[1] = "Lip Liner";
//        credentialsData[2] = "Lipstick";
         for(int i=0; i<homePage.fragrancesCategories_list.size(); i++)
         {
             credentialsData[i] = homePage.fragrancesCategories_list.get(i).getText();
         }

        return credentialsData;
    }
    
    @Test(dataProvider = "data")
    public void navigateProducts(String data) throws InterruptedException
    {
        wait = new WebDriverWait(driver, 9);
        HomePage homePage = new HomePage(driver);
        wait.until(ExpectedConditions.visibilityOf(homePage.fragrances_navBarElement));
        Thread.sleep(2000);
        homePage.navigateMainProductsCategories(homePage.fragrances_navBarElement, data);
        Thread.sleep(2000);
        driver.navigate().to("https://develop.nasnav.org/fortune");
    }
}