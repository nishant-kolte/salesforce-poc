package tests.ui;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.*;
import utilities.CommonUtils;

@Feature("Person Contact Account feature")
@Listeners(utilities.ListenerUtils.class)
public class PersonContactAccountTest extends BaseTest {
	@BeforeClass
	public void setup() {
		log.debug("executing - login");
		login(getTestData("url"),getTestData("username"),getTestData("password"));
	}

	@AfterClass
	public void closeBrowser() {
		log.debug("executing - closing browser");
		driver.close();
	}

	@Severity(SeverityLevel.CRITICAL)
	@Story("story_id: 001 - successful creation of Patient Contact record")
	@Description("successful creation of Patient Contact record")
	@Test (priority=1, groups={"smoke","regression"}, description = "verify successful creation of Patient Contact")
	public void successful_contact_creation_Test() throws InterruptedException {
		CommonUtils.logStepAsPassedInExtentReport(String.format("successful login with creds %s/%s",getTestData("username"),getTestData("password")));
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
		accountPage.clickSaveButton();
		accountPage.handleVerifyAddressAlert();
		accountPage.validateSuccessMessage();
	}

	@Severity(SeverityLevel.CRITICAL)
	@Story("story_id: 002 - successful deletion of Patient Contact record")
	@Description("successful deletion of Patient Contact record")
	@Test (priority=2, groups="regression", description = "verify successful deletion of Patient Contact")
	public void successful_contact_deletion_Test() throws InterruptedException {

		String expectedContactName=getTestData("contact_FN")+" "+getTestData("contact_LN");
		homePage.closeAllTabs();
		homePage.clicknavigationButton();
		homePage.clickAccountOption();
		accountPage.verifyAccountPageTitleIsDisplayed();
		accountPage.openContact(expectedContactName);
		String contactName = accountPage.getCreatedContactName();
		Assert.assertEquals(contactName,expectedContactName);
		accountPage.deleteContact();
		accountPage.validateSuccessfulDeleteMessage(contactName);
	}
}


