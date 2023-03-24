package ui;

import io.qameta.allure.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pages.HomePage;
import pages.LoginPage;
import utilities.DriverInit;
import utilities.Functions;

import java.io.IOException;
import java.util.Properties;

@Feature("HomePage feature")
@Listeners(utilities.ListenerUtils.class)
public class HomePageTest extends DriverInit {
	public static WebDriver driver;
	public static Properties testdata;
	@Parameters("environment")
	@BeforeClass (groups="ui")
	public void testStartUp(String env) throws IOException {
		testdata = utilities.InitTestData.getTestData(env);
		driver = DriverInit.driver;
	}
	@AfterTest
	public void closeBrowser() {
//		driver.quit();
	}
	@Severity(SeverityLevel.CRITICAL)
	@Story("story_id: 001 - successful login and navigate to patient account")
	@Description("verify user able to successful login and navigate to patient account")
	@Test (priority=1, groups="smoke", description = "verify successful login test")
	public void successful_Login_Test() throws InterruptedException {
		login(testdata.getProperty("url"),testdata.getProperty("username"),testdata.getProperty("password"));
		HomePage homePage = new HomePage();
		Functions.waitForElementLoad(homePage.getNavigationButton());
		homePage.clicknavigationButton();
	}

//	@Severity(SeverityLevel.NORMAL)
//	@Story("story_id: 002 - invalid login test")
//	@Description("verify user not able to login to OpenKart app with invalid credentials")
//	@Test (priority=2, groups="smoke", description = "verify unsuccessful login test")
//	public void invalid_Login_Test() throws InterruptedException {
//		driver.get(testdata.getProperty("url"));
//		LoginPage loginPage = new LoginPage();
//		loginPage.goToLogin();
//		loginPage.enterUsername(testdata.getProperty("username"));
//		loginPage.enterPassword("invalid");
//		loginPage.clickLoginButton();
//		Functions.waitForElementLoad(loginPage.errorMessage, testdata.getProperty("default_timeout"));
//		Functions.verifyElementDisplayed(loginPage.errorMessage);
//	}



}


