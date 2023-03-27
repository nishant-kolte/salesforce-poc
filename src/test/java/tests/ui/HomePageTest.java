package tests.ui;

import io.qameta.allure.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pages.AccountPage;
import pages.HomePage;

import java.io.IOException;
import java.util.Properties;

@Feature("HomePage feature")
@Listeners(utilities.ListenerUtils.class)
public class HomePageTest extends BaseTest {
	@BeforeMethod
	public void setup() {
		login(getTestData("url"),getTestData("username"),getTestData("password"));
	}

	@AfterMethod
	public void closeBrowser() {
//		driver.quit();
	}

	@Severity(SeverityLevel.CRITICAL)
	@Story("story_id: 001 - successful login and create a patient account")
	@Description("verify user able to create a new patient account")
	@Test (priority=1, groups="smoke", description = "verify successful login test")
	public void successful_Login_Test() throws InterruptedException {
		homePage.verifyHomePageTitleIsDisplayed();
		homePage.closeAllTabs();
		homePage.clicknavigationButton();
		homePage.clickAccountOption();
		accountPage.verifyAccountPageTitleIsDisplayed();
		accountPage.clickNewButton();
		accountPage.verifyNewAccountTitleIsDisplayed();
		accountPage.selectContact();
		accountPage.clickNext();
		accountPage.selectContactInputLookup(getTestData("person_account"));
		accountPage.clickNextButton1();
		accountPage.updateContactDetails();
		accountPage.validateSuccessMessage();

	}


}


