package tests;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.ProductPage;
import pages.ResultingProductsPage;

public class ProductPurchase_Tests extends TestsBase
{
    HomePage homePage;
    // _________________________________________________________________________________________________________________
    // _________________________________________________________________________________________________________________
    
    @Test
    public void addingToBasket_Test() throws InterruptedException
    {
        wait = new WebDriverWait(driver,10);
        homePage = new HomePage(driver);

        ResultingProductsPage resultingProductsPage = homePage.search("AGENT PROVOCATEUR");
        ProductPage ProductPage = resultingProductsPage.chooseRandomProduct();

        wait.until(ExpectedConditions.visibilityOf(ProductPage.productImage));
        Thread.sleep(3000);
    }
}
