package utilities;

import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.PageFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static tests.ui.BaseTest.driver;


public class CommonUtils {
	public static void takescreenshot(String testname) throws IOException
	{
		File myfile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileHandler.copy(myfile, new File("Screenshots\\"+testname+"fail.png"));
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

	//AK- 27/03/2023
	public static List<WebElement> elements(By locator){
		return driver.findElements(locator);
	}

}

