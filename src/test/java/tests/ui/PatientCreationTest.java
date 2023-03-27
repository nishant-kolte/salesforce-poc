package tests.ui;



import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.*;
@Feature("Patient Account feature")
@Listeners(utilities.ListenerUtils.class)
public class PatientCreationTest extends BaseTest {

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
    @Story("story_id: 003l - successful creation of Patient Account record")
    @Description("successful creation of Patient Account record")
    @Test (priority=1, groups={"smoke","regression"}, description = "verify successful creation of Patient Account")
        public void createPatient() throws InterruptedException {
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
            log.debug("executing - selectPatient");
            accountPage.selectPatientOption();
            log.debug("executing - clickNext");
            accountPage.clickNext();
            log.debug("executing - clickNext");
            accountInformationPage.clickNextButton();
            log.debug("executing - enterUserData");
            accountInformationPage.enterUserData();
            log.debug("executing - clickSaveButton");
            accountInformationPage.clickSaveButton();
            log.debug("executing - verifySuccessMessage");
            accountInformationPage.verifySuccessMessage();
        }
}
