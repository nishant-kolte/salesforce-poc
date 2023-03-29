package tests.ui;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Feature("Task Priority Change Test")
@Listeners(utilities.ListenerUtils.class)
public class TaskPriorityChangeTest extends BaseTest {

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

        @Severity(SeverityLevel.NORMAL)
        @Story("story_id: 002 - successful update in the priority of a task assigned to a user")
        @Description("successfully change the priority of a task assigned to a user")
        @Test(priority=1, groups={"smoke","regression"}, description = "verify if the user is able to change the priority of a task assigned to it")
        public void task_priority_change_test() throws InterruptedException {
            log.debug("executing - verifyHomePageTitleIsDisplayed");
            homePage.verifyHomePageTitleIsDisplayed();
            log.debug("executing - closeAllTabs");
            homePage.closeAllTabs();
            log.debug("executing - clicknavigationButton");
            homePage.clicknavigationButton();
            log.debug("executing - clickHomeOption");
            homePage.clickHomeOption();
            log.debug("executing - clickTaskIcon");
            homePage.clickTaskIcon();
            log.debug("executing - clickActionsIcon");
            taskPage.clickActionsIcon();
            log.debug("executing - clickChangePriorityOption");
            taskPage.clickChangePriorityOption();
            log.debug("executing - selectPriorityFromDropdown");
            taskPage.selectPriorityFromDropdown();
            log.debug("executing - clickSaveButtonInChangePriorityWindow");
            taskPage.clickSaveButtonInChangePriorityWindow();
            log.debug("executing - verifySaveButtonInChangePriorityWindow");
            Assert.assertEquals("Task \"Letter could not be mailed by vendor.\" was saved.", taskPage.getTaskSavedSuccessMessage(), "Success Message Not Matching");
        }

}
