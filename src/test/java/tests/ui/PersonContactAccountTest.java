package tests.ui;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.*;

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
//		driver.close();
	}

	@Severity(SeverityLevel.CRITICAL)
	@Story("story_id: 001 - successful creation of Patient Contact record")
	@Description("successful creation of Patient Contact record")
	@Test (priority=1, groups={"smoke","regression"}, description = "verify successful creation of Patient Contact")
	public void successful_contact_creation_Test() throws InterruptedException {
		log.debug("executing - verifyHomePageTitleIsDisplayed");
		homePage.verifyHomePageTitleIsDisplayed();
		log.debug("executing - closeAllTabs");
		homePage.closeAllTabs();
		log.debug("executing - clicknavigationButton");
		homePage.clicknavigationButton();
		log.debug("executing - clickAccountOption");
		homePage.clickAccountOption();
		log.debug("executing - verifyAccountPageTitleIsDisplayed");
		accountPage.verifyAccountPageTitleIsDisplayed();
		log.debug("executing - clickNewButton");
		accountPage.clickNewButton();
		log.debug("executing - verifyNewAccountTitleIsDisplayed");
		accountPage.verifyNewAccountTitleIsDisplayed();
		log.debug("executing - selectContact");
		accountPage.selectContact();
		log.debug("executing - clickNext");
		accountPage.clickNext();
		log.debug("executing - selectContactInputLookup - person_account");
		accountPage.selectContactInputLookup(getTestData("person_account"));
		log.debug("executing - clickNextButton1");
		accountPage.clickNextButton1();
		log.debug("executing - updateContactDetails");
		accountPage.updateContactDetails();
		log.debug("executing - clickSaveButton");
		accountPage.clickSaveButton();
		log.debug("executing - handleVerifyAddressAlert");
		accountPage.handleVerifyAddressAlert();
		log.debug("executing - validateSuccessMessage");
		accountPage.validateSuccessMessage();
		log.debug("executing - getCreatedContactName");
	}

	@Severity(SeverityLevel.CRITICAL)
	@Story("story_id: 002 - successful deletion of Patient Contact record")
	@Description("successful deletion of Patient Contact record")
	@Test (priority=2, groups="regression", description = "verify successful deletion of Patient Contact")
	public void successful_contact_deletion_Test() throws InterruptedException {
		String expectedContactName=getTestData("contact_FN")+" "+getTestData("contact_LN");
		log.debug("expectedContactName -"+ expectedContactName);
		log.debug("executing - closeAllTabs");
		homePage.closeAllTabs();
		log.debug("executing - clicknavigationButton");
		homePage.clicknavigationButton();
		log.debug("executing - clickAccountOption");
		homePage.clickAccountOption();
		log.debug("executing - verifyAccountPageTitleIsDisplayed");
		accountPage.verifyAccountPageTitleIsDisplayed();
		log.debug("executing - openContact");
		accountPage.openContact(expectedContactName);
		log.debug("executing - getCreatedContactName");
		String contactName = accountPage.getCreatedContactName();
		log.debug("contactName -"+ contactName);
		log.debug("executing - assertEquals");
		Assert.assertEquals(contactName,expectedContactName);
		log.debug("executing - deleteContact");
		accountPage.deleteContact();
		log.debug("executing - validateSuccessfulDeleteMessage");
		accountPage.validateSuccessfulDeleteMessage(contactName);
	}
}


