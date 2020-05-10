package tests;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utilities.Helper;
import utils.CookieManager;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TestsBase
{
    // ________________________________________ [ Instance Variables ] _________________________________________________
    protected EventFiringWebDriver driver;
    public static String downloadPath = System.getProperty("user.dir") + "\\Downloads";
    protected FileInputStream fis;
    protected static Properties properties;
    public WebDriverWait wait;
    // ______________________________________ [ Browser Configurations ] _______________________________________________
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

    @BeforeClass
    @Parameters({"browser"})
    public void startDriver(@Optional("chrome") String browserName)
    {
        if (browserName.equalsIgnoreCase("chrome"))
        {
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/Drivers/chromedriver.exe");
            driver = new EventFiringWebDriver(new ChromeDriver(chromeOption()));
        }
        else if(browserName.equalsIgnoreCase("firefox"))
        {
            System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"/Drivers/geckodriver.exe");
            driver = new EventFiringWebDriver(new FirefoxDriver(firefoxOption()));
        }
        else if (browserName.equalsIgnoreCase("ie")) //TODO: Get the driver.
        {
            System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"/Drivers/absent.exe");
            driver = new EventFiringWebDriver(new InternetExplorerDriver());
        }
        else if (browserName.equalsIgnoreCase("safari")) //TODO: Get the driver.
        {
            System.setProperty("webdriver.safari.driver", System.getProperty("user.dir")+"/Drivers/absent.exe");
            driver = new EventFiringWebDriver(new SafariDriver());
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);


        driver.navigate().to("https://develop.nasnav.org/fortune");
    }

    @AfterClass
    public void stopDriver()
    {
        driver.quit();
    }

//    @AfterClass
//    public void rollBackToCheckpoint()
//    {
//        driver.navigate().to("https://develop.nasnav.org/fortune");
//    }
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
    // _________________________________________________________________________________________________________________
    // _________________________________________________________________________________________________________________


    // Tools
    public Properties loadDataRepoFile()
    {
        properties= new Properties();
        try {
            fis= new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\resources\\credentials.properties");
            properties.load(fis);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return properties;
    }

    public String[] openLinkInNewTab(String url) throws InterruptedException
    {
        Robot robot= null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        Thread.sleep(3000);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_T);

        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_T);

        String fortuneTab_handleId = driver.getWindowHandle();
        String[] windowsIDs = driver.getWindowHandles().toArray(new String[0]);
//        ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
        for(String windowID : windowsIDs)
        {
            if(!windowID.equals(fortuneTab_handleId))
            {
                driver.switchTo().window(windowID);
                driver.navigate().to(url);
            }
        }

        return windowsIDs;
    }

    public String getWindowIDByItsName(String windowName)
    {
        String[] windowsIDs = driver.getWindowHandles().toArray(new String[0]);
        String requiredWindowID = null;
        for(String windowID : windowsIDs)
        {
            if(windowName.contains(driver.switchTo().window(windowID).getTitle()))
            {
                requiredWindowID = windowID;
            }
        }

        return requiredWindowID;
    }
    public String getAlphaNumericString(int n)
    {

        // chose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                    = (int)(AlphaNumericString.length()
                    * Math.random());

            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                    .charAt(index));
        }

        return sb.toString();
    }
    public String getDataByKey(String key)
    {
        return properties.getProperty(key);
    }

    public CookieManager getCookieManager(){
        return new CookieManager(driver);
    }
}
