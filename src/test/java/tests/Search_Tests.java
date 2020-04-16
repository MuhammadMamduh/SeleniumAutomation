package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.HomePage;

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

        return credentialsData;
    }
    @Test(dataProvider = "searchData")
    public void search_postive_Test(String searchToken, boolean flag)
    {
        homePage = new HomePage(driver);
        homePage.search(searchToken);
    }
}
