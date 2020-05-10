package tests;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.SearchResultsPage;

public class Search_Tests extends TestsBase
{
    HomePage homePage;
    // _________________________________________________________________________________________________________________
    // _________________________________________________________________________________________________________________

    @DataProvider(name= "searchData")
    public Object[][] searchData()
    {
        properties = loadDataRepoFile();

        Object[][] credentialsData= new Object[3][2];

        credentialsData[0][0]= properties.getProperty("searchToken_certainProduct_valid_present"); credentialsData[0][1]= true;
        credentialsData[1][0]= properties.getProperty("searchToken_manyProducts_valid_present"); credentialsData[1][1]= true;
        credentialsData[2][0]= properties.getProperty("searchToken_product_valid_absent"); credentialsData[2][1]= false;
        // credentialsData[3][0]= "clarin\'); adfasf ----"; credentialsData[2][1]= false;
        // credentialsData[4][0]= "%"; credentialsData[2][1]= false;
        // credentialsData[5][0]= "&"; credentialsData[2][1]= false;

        return credentialsData;
    }
    @Test(dataProvider = "searchData")
    public void search_postive_Test(String searchToken, boolean flag) throws InterruptedException
    {
        homePage = new HomePage(driver);
        wait = new WebDriverWait(driver, 9);
        boolean resultsExist;

        SearchResultsPage searchResultsPage= new SearchResultsPage(driver);

        homePage.search(searchToken);
        try
        {
            wait.until(ExpectedConditions.visibilityOf(searchResultsPage.resultingProductsLink_list.get(0)));
            resultsExist = true;
        }
        catch(Exception e)
        {
            resultsExist = false;
        }
        

        if(flag)
        {
            Assert.assertTrue(resultsExist, "A result or more should have appeared here.");
        }
        else
        {
            Assert.assertFalse(resultsExist, "Not a single result should appear here.");
        }
        
    }
}
