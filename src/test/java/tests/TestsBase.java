package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utilities.Helper;
import utils.CookieManager;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TestsBase
{
    // ________________________________________ [ Instance Variables ] _________________________________________________
    public static WebDriver driver ;
    public static String downloadPath = System.getProperty("user.dir") + "\\Downloads";
    protected FileInputStream fis;
    protected Properties properties;

    // ________________________________________ [ Instance Variables ] _________________________________________________
    public static FirefoxOptions firefoxOption()
    {
        FirefoxOptions option = new FirefoxOptions();
        option.addPreference("browser.download.folderList", 2);
        option.addPreference("browser.download.dir", downloadPath);
        option.addPreference("browser.helperApps.neverAsk.saveToDisk", "application/pdf");
        option.addPreference("browser.download.manager.showWhenStarting", false);
        option.addPreference("pdfjs.disabled", true);
        return option;
    }

    public static ChromeOptions chromeOption()
    {
        ChromeOptions options = new ChromeOptions();
        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("profile.default.content_settings.popups", 0);
        chromePrefs.put("download.default_directory", downloadPath);
        options.setExperimentalOption("prefs", chromePrefs);
        options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        return options;
    }

    @BeforeSuite
    @Parameters({"browser"})
    public void startDriver(@Optional("chrome") String browserName)
    {
        if (browserName.equalsIgnoreCase("chrome"))
        {
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/Drivers/chromedriver.exe");
            driver = new ChromeDriver(chromeOption());
        }
        else if(browserName.equalsIgnoreCase("firefox"))
        {
            System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"/Drivers/geckodriver.exe");
            driver = new FirefoxDriver(firefoxOption());
        }
        else if (browserName.equalsIgnoreCase("ie")) //TODO
        {
            System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"/Drivers/absent.exe");
            driver = new InternetExplorerDriver();
        }
        else if (browserName.equalsIgnoreCase("safari")) //TODO
        {
            System.setProperty("webdriver.safari.driver", System.getProperty("user.dir")+"/Drivers/absent.exe");
            driver = new SafariDriver();
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);


        driver.navigate().to("https://develop.nasnav.org/fortune");
    }

    @AfterSuite
    public void stopDriver()
    {
        driver.quit();
    }

    // take screenshot when test case fail and add it in the Screenshot folder
    @AfterMethod
    public void screenshotOnFailure(ITestResult result)
    {
        if (result.getStatus() == ITestResult.FAILURE)
        {
            System.out.println("Failed!");
            System.out.println("Taking Screenshot....");
            Helper.captureScreenshot(driver, result.getName());
        }
    }

    public CookieManager getCookieManager(){
        return new CookieManager(driver);
    }
}
