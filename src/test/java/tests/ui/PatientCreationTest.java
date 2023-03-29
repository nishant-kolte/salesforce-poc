package tests.ui;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.*;

import static tests.ui.BaseTest.homePage;

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
        homePage.verifyHomePageTitleIsDisplayed();
        homePage.closeAllTabs();
        homePage.clicknavigationButton();
        homePage.clickAccountOption();
        accountPage.verifyAccountPageTitleIsDisplayed();
        accountPage.clickNewButton();
        accountPage.verifyNewAccountTitleIsDisplayed();
        accountPage.selectPatientOption();
        accountPage.clickNext();
        accountInformationPage.clickNextButton();
        accountInformationPage.enterUserData();
        accountInformationPage.clickSaveButton();
        accountInformationPage.verifySuccessMessage();
    }
}
