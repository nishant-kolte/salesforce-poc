package utilities;

import com.relevantcodes.extentreports.LogStatus;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import io.qameta.allure.model.Status;
import org.openqa.selenium.*;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;

import static tests.ui.BaseTest.driver;


public class CommonUtils {
	public static void takescreenshot(String testname) throws IOException
	{
		File myfile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileHandler.copy(myfile, new File(System.getProperty("user.dir")+"\\html-report\\"+testname+"fail.png"));
	}

	@Attachment
	public static byte[] saveFailureScreenShot()
	{
		return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
	}

	public static void initPageFactory(Class page)
	{
		PageFactory.initElements(driver, page);
	}

	@Step("{0}")
	public static void logStep(String message){
		// intentionally kept empty
	}
	public static WebElement element(By locator){
		return driver.findElement(locator);
	}

	public static void logStepAsPassedInExtentReport(String message){
		ListenerUtils.test.log(LogStatus.PASS, "<font color=green>"+message);
	}
	public static void logStepAsFailedInExtentReport(String message){
		ListenerUtils.test.log(LogStatus.FAIL, "<font color=red>"+message);
	}

	public static void logStepAsInfoInExtentReport(String step, String details){
		ListenerUtils.test.log(LogStatus.INFO, "<b><u>"+step+"</u></b><br />" + "<pre>"+details+"</pre>");
	}

	public static void assertEquals(Object actual, Object expected, String fieldName){
		try {
			Assert.assertEquals(actual,expected);
			Allure.step("successfully validated "+fieldName+" value ", Status.PASSED);
			logStepAsPassedInExtentReport("successfully validated "+fieldName+" value equal to "+expected);
		}
		catch (AssertionError e){
			logStepAsFailedInExtentReport("assertion failed for - " + fieldName + " with ERROR: "+ e.getMessage());
			Allure.step("assertion failed for - " + actual + "ERROR:"+ e.getStackTrace(), Status.FAILED);
			Assert.fail("assertion failed for - " + fieldName + " with ERROR: "+ e.getMessage());
		}
	}

	public static void assertNotNull(Object actual, String fieldName){
		try {
			Assert.assertNotNull(actual);
			Allure.step("successfully validated "+fieldName+" is not null", Status.PASSED);
			logStepAsPassedInExtentReport("successfully validated "+fieldName+" is not null");
		}
		catch (AssertionError e){
			Allure.step("assertion failed for - " + fieldName + " with ERROR: "+ e.getMessage(), Status.FAILED);
			logStepAsFailedInExtentReport("assertion failed for - " + fieldName + " with ERROR: "+ e.getMessage());
			Assert.fail("assertion failed for - " + fieldName + " with ERROR: "+ e.getMessage());

		}
	}

	public static void assertTrue(Boolean actual, String fieldName){
		try {
			Assert.assertTrue(actual);
			Allure.step("successfully validated "+fieldName+" is not null", Status.PASSED);
			logStepAsPassedInExtentReport("successfully validated "+fieldName+" is not null");
		}
		catch (AssertionError e){
			Allure.step("assertion failed for - " + fieldName + " with ERROR: "+ e.getMessage(), Status.FAILED);
			logStepAsFailedInExtentReport("assertion failed for - " + fieldName + " with ERROR: "+ e.getMessage());
			Assert.fail("assertion failed for - " + fieldName + " with ERROR: "+ e.getMessage());

		}
	}

	}

