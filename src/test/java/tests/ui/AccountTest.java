package tests.ui;
import io.qameta.allure.*;
import org.sikuli.script.FindFailed;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utilities.SikuliUtils;

@Feature("AccountTest feature")
@Listeners(utilities.ListenerUtils.class)
public class AccountTest extends BaseTest {
//    @BeforeMethod
//    public void setup() {
//        login(getTestData("url"),getTestData("username"),getTestData("password"));
//    }
    @AfterMethod
    public void closeBrowser() {
		//driver.quit();
    }
    @Severity(SeverityLevel.CRITICAL)
    @Story("story_id: 003l - successful creation of Patient Account record")
    @Description("successful creation of Patient Account record")
    @Test(priority=1, groups={"smoke","regression"}, description = "verify successful creation of Patient Account")
    public void createPatientAccount() throws InterruptedException, FindFailed {
        login(getTestData("url"),getTestData("username"),getTestData("password"));
        Thread.sleep(2000);
        SikuliUtils.skClickUntilImageIsVisible("CloseTab","AccountPage");
        Thread.sleep(2000);
        SikuliUtils.skClick("AccountDropDown","AccountPage");
        SikuliUtils.skClick("AccountTab","AccountPage");
    }
}
