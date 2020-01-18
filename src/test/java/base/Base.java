package base;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import pages.HomePage;
import utils.CookieManager;

import java.util.concurrent.TimeUnit;

public class Base {
    private EventFiringWebDriver driver;
    protected HomePage homePage;

    @BeforeClass
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new EventFiringWebDriver(new ChromeDriver(getChromeOptions()));

        driver.manage().timeouts().pageLoadTimeout(1, TimeUnit.MINUTES);
        driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
//        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

//        driver.register(new EventReporter());
        goHome();
    }

    @BeforeMethod
    public void goHome(){
        driver.get("https://develop.nasnav.com/fortune");
        homePage = new HomePage(driver);
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }

    @AfterMethod
    public void recordFailure(ITestResult result){
        if(ITestResult.FAILURE == result.getStatus())
        {
//            var camera = (TakesScreenshot)driver;
//            File screenshot = camera.getScreenshotAs(OutputType.FILE);
//            try{
//                Files.move(screenshot, new File("resources/screenshots/" + result.getName() + ".png"));
//            }catch(IOException e){
//                e.printStackTrace();
//            }
        }
    }

    private ChromeOptions getChromeOptions(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("disable-infobars");
        //options.setHeadless(true);
        return options;
    }

    public CookieManager getCookieManager(){
        return new CookieManager(driver);
    }

}
