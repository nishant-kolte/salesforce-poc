package tests.ui;

import java.io.IOException;

import io.qameta.allure.*;
import org.testng.annotations.*;

import pages.HomePage;
import pages.LoginPage;
import utilities.AppUtils;
import utilities.CommonUtils;

@Feature("Login feature")
@Listeners(utilities.ListenerUtils.class)
public class LoginTest extends BaseTest {

	@AfterTest
	public void closeBrowser() {
		driver.close();
	}
	@Severity(SeverityLevel.CRITICAL)
	@Story("story_id: 001 - successful login test")
	@Description("verify user able to login to Salesforce app with valid credentials")
	@Test (priority=1, groups="smoke", description = "verify successful login test")
	public void successful_Login_Test() throws InterruptedException {
		openURL(getTestData("url"));
		loginPage.waitForPageLoad();
		loginPage.enterUsername(getTestData("username"));
		loginPage.enterPassword(getTestData("password"));
		loginPage.clickLoginButton();
		homePage.verifyHomePageTitleIsDisplayed();
	}
}


