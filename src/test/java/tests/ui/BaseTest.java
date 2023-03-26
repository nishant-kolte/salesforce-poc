package tests.ui;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.AccountPage;
import pages.HomePage;
import pages.LoginPage;
import utilities.AppUtils;
import utilities.CommonUtils;
import utilities.InitTestData;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    public static String browser;
    public static String env;
    public static WebDriver driver;
    public static Properties testdata;
    public static Properties config;
    public static Logger log;
    public static LoginPage loginPage;
    public static HomePage homePage;
    public static AccountPage accountPage;

    @BeforeSuite
    @Parameters({"browser","environment"})
    public void testInit(@Optional String browser,@Optional String env) throws IOException {
        PropertyConfigurator.configure("log4j.properties");
        log = Logger.getLogger(Test.class);

        if(browser==null) {
            this.browser = "chrome";
        }
        if(env==null){
            if(getEnvConfig("environment")==null){
                this.env="qa";
            }
            else {
                this.env=getEnvConfig("environment");
            }
        }
//        initBrowser(browser);
//        initAllPages();
        testdata = InitTestData.getTestData(this.env);
        config = InitTestData.readConfig(this.env);
    }

    @Step("initialize {0} browser")
    public static void initBrowser(String browser) {
        if (browser == null || browser.equalsIgnoreCase("chrome")){
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-notifications");
            options.addArguments("--disable-infobars");
            options.addArguments("--incognito");
            driver= new ChromeDriver(options);
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            log.info("chrome browser started");
        }
        else if (browser.equalsIgnoreCase("ie")){
            WebDriverManager.iedriver().setup();
            driver= new InternetExplorerDriver();
            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
            driver.manage().window().maximize();
        }
        else if (browser.equalsIgnoreCase("firefox")){
            WebDriverManager.firefoxdriver().setup();
            driver= new FirefoxDriver();
            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
            driver.manage().window().maximize();

        }
        else{
            log.info("invalid browser selected");
        }
    }
    @Step("open url {0} in web browser")
    public static void openURL(String url){
        initBrowser(browser);
        initAllPages();
        driver.get(url);
    }
    public static void initAllPages() {
        loginPage = new LoginPage();
        homePage = new HomePage();
        accountPage = new AccountPage();
    }
    public void login(String url, String username, String password){
        openURL(url);
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();
    }
    public static String getTestData(String key){
        try{
            return testdata.getProperty(key);
        }
        catch (Exception e){
            return null;
        }
    }
    public static String getConfig(String key){
        try{
            return config.getProperty(key);
        }
        catch (Exception e){
            return null;
        }
    }
    public static String getEnvConfig(String key){
        try{
            return System.getenv(key);
        }
        catch (Exception e){
            return null;
        }

    }
    public static String getProp(String key){
        String ev=getEnvConfig(key);
        return ev!=null?ev:getConfig(key);

    }
}
