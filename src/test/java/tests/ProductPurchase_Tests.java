package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.HomePage;
import pages.ProductPage;
import pages.ResultingProductsPage;

import static pages.PagesBase.clickElementWhenClickable;

public class ProductPurchase_Tests extends TestsBase
{
    HomePage homePage;
    // _________________________________________________________________________________________________________________
    // _________________________________________________________________________________________________________________

    @Test
    public void addingToBasket_Test() throws InterruptedException {
        wait = new WebDriverWait(driver, 15);
        homePage = new HomePage(driver);
        ProductPage productPage = new ProductPage(driver);
        CartPage cartPage = new CartPage(driver);

        for (int i = 1; i <= 2; i++) {
            ResultingProductsPage resultingProductsPage = homePage.search("AGENT PROVOCATEUR");
            productPage = resultingProductsPage.chooseRandomProduct();
            wait.until(ExpectedConditions.visibilityOf(resultingProductsPage.resultingProductsLink_list.get(0)));
            productPage.addItemsToCart("1");
            wait.until(ExpectedConditions.visibilityOf(resultingProductsPage.alert));
            Thread.sleep(3000);
        }
        productPage.cart_btn.click();

        JavascriptExecutor js;
        for (int x = 1; x <= 10; x++) {
            try {
                clickElementWhenClickable(By.cssSelector("a[class=\"cartDropdown__btn bg\"]"), 2, driver);
                break;
            } catch (TimeoutException e) {
                js = (JavascriptExecutor) driver;
                js.executeScript("document.getElementsByClassName(\"artDropdown mb-0\").animate({ scrollTop: \"" + 100 * x + "px\" })");
            }
        }
        wait.until(ExpectedConditions.visibilityOf(cartPage.checkout_btn));
        Thread.sleep(5000);
    }
}
