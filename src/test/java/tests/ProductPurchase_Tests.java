package tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.ProductPage;
import pages.SearchResultsPage;

public class ProductPurchase_Tests extends TestsBase
{
    HomePage homePage;
    // _________________________________________________________________________________________________________________
    // _________________________________________________________________________________________________________________
    
    @Test
    public void purchaseSingleProduct_Test() throws InterruptedException
    {
        wait = new WebDriverWait(driver,10);
        homePage = new HomePage(driver);

        SearchResultsPage searchResultsPage= homePage.search("AGENT PROVOCATEUR");
        ProductPage ProductPage = searchResultsPage.chooseRandomProduct();

        wait.until(ExpectedConditions.visibilityOf(ProductPage.productImage));
        Thread.sleep(3000);
    }
}
