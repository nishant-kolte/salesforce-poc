package utilities;

import com.fasterxml.jackson.databind.ser.Serializers;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import tests.ui.BaseTest;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import static tests.ui.BaseTest.*;


public class AppUtils extends CommonUtils {
	public static WebDriverWait wait;
	public static JavascriptExecutor jse = (JavascriptExecutor)driver;

	@Step("click on element - {0}")
	public static void click(WebElement element) throws InterruptedException{
		Thread.sleep(1000);
		wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();
	}

	@Step("click on element - {0}")
	public static void jseClick(WebElement element) throws InterruptedException{
		Thread.sleep(1000);
//		setImplicitWait(0);
		waitForElementClickable(element);
//		setImplicitWait(Integer.parseInt(config.getProperty("default_timeout")));

		jse.executeScript("arguments[0].click();", element);
	}

	@Step("click on element - {0} with retry")
	public void retryClick(WebElement ele, ExpectedCondition expectedCondition, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		ExpectedCondition elementIsClickable = arg0 -> {
			try {
				ele.click();
				if (expectedCondition != null) {
					wait.until(expectedCondition);
				}
				return true;
			} catch (Exception e) {
				return false;
			}
		};
		wait.until(elementIsClickable);
	}

	@Step("enter text '{0}' in '{1}'")
	public static void setText(WebElement locator, String value) throws InterruptedException{
		Thread.sleep(1000);
		locator.clear();
		locator.sendKeys(value);
	}

	@Step("mousehover on element - {0}")
	public static void mousehover(WebElement element) throws InterruptedException{
//		Thread.sleep(1000);
//		wait = new WebDriverWait(driver,30);
//		wait.until(ExpectedConditions.elementToBeClickable(element));
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();
	}

	@Step("double click on element - {0}")
	public static void doubleClick(WebElement element) throws InterruptedException{
		Thread.sleep(1000);
		wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		Actions action = new Actions(driver);
		action.doubleClick(element).build().perform();
	}


	@Step("verify element displayed")
	public static void verifyElementDisplayed(WebElement element) throws InterruptedException{
		Thread.sleep(1000);
		try{
			Assert.assertTrue(element.isDisplayed());
		}
		catch (Exception e){
			Assert.fail("element is not displayed");
		}
	}

	@Step("check if element is displayed")
	public static boolean isElementDisplayed(WebElement element){
		try{
			return element.isDisplayed();
		}
		catch (Exception e){
			return false;
		}
	}

	@Step("verify text in {0} field")
	public static void verifyText(WebElement element, String text) throws InterruptedException{
		Thread.sleep(1000);
		Assert.assertEquals(element.getText(), text);
	}

	@Step("wait for {0} element to load within {1} secs")
	public static void waitForElementLoad(WebElement element, String timeout){
//		setImplicitWait(0);
		wait = new WebDriverWait(driver, Integer.parseInt(timeout));
		wait.until(ExpectedConditions.visibilityOf(element));
//		setImplicitWait(Integer.parseInt(config.getProperty("default_timeout")));
	}

	@Step("wait for {0} element to load with default timeout")
	public static void waitForElementLoad(WebElement element){
//		setImplicitWait(0);
		wait = new WebDriverWait(driver, 15,2000);
		wait.until(ExpectedConditions.visibilityOf(element));
//		setImplicitWait(Integer.parseInt(config.getProperty("default_timeout")));
	}

	@Step("wait for {0} element to be clickable within {1} seconds")
	public static void waitForElementClickable(WebElement element, String timeout){
//		setImplicitWait(0);
		wait = new WebDriverWait(driver, Integer.parseInt(timeout),1000);
		wait.until(ExpectedConditions.elementToBeClickable(element));
//		setImplicitWait(Integer.parseInt(config.getProperty("default_timeout")));
	}

	public static void setImplicitWait(int timeout){
		driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
	}

	@Step("enter text '{0}' in field '{1}'")
	public static void enterTextInField(String value, String label) throws InterruptedException{
//		setImplicitWait(0);
//		wait = new WebDriverWait(driver,10,1000);
		WebElement inputtext = element(By.xpath("//label[contains(text(),\""+label+"\")]/following-sibling::div/input"));
		waitForElementPresent(inputtext);
//		setImplicitWait(Integer.parseInt(config.getProperty("default_timeout")));
//		mousehover(inputtext);
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", inputtext);
		inputtext.clear();
		inputtext.sendKeys(value);
		CommonUtils.logStepAsPassedInExtentReport(String.format("enter text '%s' in field '%s'",value,label));
	}

	@Step("select text '{0}' from dropdown field '{1}'")
	public static void selectDropDownField(String value, String label) throws InterruptedException{

		WebElement input = element(By.xpath("//label[contains(text(),'"+label+"')]/following-sibling::div/descendant::button"));
		//waitForElementClickable(input);
//		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", input);
		jseClick(input);
		//input.click();

		WebElement inputtext = element(By.xpath("//lightning-base-combobox-item[@data-value=\""+value+"\"]"));
		waitForElementPresent(inputtext);
//		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", inputtext);
		inputtext.click();
//		setImplicitWait(Integer.parseInt(config.getProperty("default_timeout")));
		CommonUtils.logStepAsPassedInExtentReport(String.format("select text '%s' from dropdown field '%s'",value,label));
	}

	@Step("select value '{0}' from input lookup '{1}'")
	public static void selectValueFromLookupField(String label, String inputText){
//		wait = new WebDriverWait(driver,10,1000);
		WebElement input = element(By.xpath("//label[text()='"+label+"']/following-sibling::div/descendant::input"));
		waitForElementPresent(input);
		input.sendKeys(inputText);
//		wait.until(ExpectedConditions.visibilityOfElementLocated()).sendKeys(inputText);
		WebElement option = element(By.xpath("(//lightning-base-combobox-formatted-text[contains(@title,\""+inputText+"\")])[1]"));
		waitForElementPresent(option);
		option.click();
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//lightning-base-combobox-formatted-text[contains(@title,\""+inputText+"\")])[1]"))).click();
		CommonUtils.logStepAsPassedInExtentReport(String.format("select value '%s' from input lookup '%s'",inputText,label));
	}

	public static void waitForElementPresent(WebElement element){
		setImplicitWait(0);
		Wait<WebDriver> gWait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(10))
				.pollingEvery(Duration.ofMillis(600)).ignoring(NoSuchElementException.class, StaleElementReferenceException.class);

				gWait.until(ExpectedConditions.visibilityOf(element));
		setImplicitWait(10);
	}

	public static void waitForElementNotPresent(WebElement element){
		setImplicitWait(0);
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.invisibilityOf(element));
		setImplicitWait(10);
	}

	public static void waitForElementPresent(WebElement element, int timeout, int polling){
		setImplicitWait(0);
		Wait<WebDriver> gWait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(10))
				.pollingEvery(Duration.ofMillis(600)).ignoring(NoSuchElementException.class, StaleElementReferenceException.class);

		gWait.until(ExpectedConditions.visibilityOf(element));
		setImplicitWait(10);
	}


	public static void waitForElementClickable(WebElement element){
		setImplicitWait(0);
		Wait<WebDriver> gWait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(10))
				.pollingEvery(Duration.ofMillis(600)).ignoring(StaleElementReferenceException.class, ElementClickInterceptedException.class);

		gWait.until(ExpectedConditions.elementToBeClickable(element));
		setImplicitWait(10);
	}

}

