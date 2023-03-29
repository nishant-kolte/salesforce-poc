package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import tests.ui.BaseTest;
import utilities.AppUtils;
import utilities.CommonUtils;

import java.awt.*;

import static tests.ui.BaseTest.driver;
import static tests.ui.BaseTest.log;
import static utilities.AppUtils.*;
import static utilities.CommonUtils.element;

public class AccountPage {
	private By newButton= By.xpath("//div[text()=\"New\"]/parent::a");
	private String newButtonXpath= "//a[@title='New' and @class='forceActionLink']";
	private By AccountsSection= By.xpath("//h1/span[text()=\"Accounts\"]");
	private By recentlyViewedTitle= By.xpath("//h1/span[text()=\"Recently Viewed\"]");
	private By newAccountTitle= By.xpath("//h2[text()=\"New Account\"]");
	private By contactRadioButton= By.xpath("//input[@type='radio']/following-sibling::span[text()=\"Contact\"]");
	private By patientOption = By.xpath("//div[text() = 'Used for creating a patient account']");
	private By nextButton= By.xpath("//span[text()='Next']/parent::button");
	private By nextButton1= By.xpath("//button[text()='Next']");
	private By contactInputLookup= By.xpath("//label[text()='Person Account']/following-sibling::div/descendant::input");
	private By saveButton= By.xpath("//button[text()='Save']");
	private By verifyAddressModalHeader= By.xpath("//h2[text()='Verify Address']");
	private By deleteButton = By.xpath("//button[@name='Delete']");
	private By modalsaveButton = By.xpath("//footer[@class='slds-modal__footer']/button[text()='Save']");
	private By successMessage= By.xpath("//*[contains(text(),'Record was saved.')]");
	private By contactNameHeader = By.xpath("//div[contains(text(),'Person Account')]/following::lightning-formatted-text");
	private By deletePersonAccountPopup = By.xpath("//h2[text()='Delete Person Account']");
	private By popupDeleteButton = By.xpath("//div[@class=\"modal-footer slds-modal__footer\"]/descendant::button[@title='Delete']");
	private String successDeleteMessage= "//span[contains(@class,'toastMessage') and contains(text(),'Account ')]";

	private String contactLink = "//a[contains(text(),'%s')]";
	@Step("click on new button")
	public void clickNewButton() throws InterruptedException {
		AppUtils.waitForElementPresent(element(By.xpath(newButtonXpath)));
		BaseTest.log.info("clicking new button");
//		AppUtils.mousehover(element(By.xpath(newButtonXpath)));
		AppUtils.jseClick(element(By.xpath(newButtonXpath)));
		CommonUtils.logStepAsPassedInExtentReport(String.format("click on new button"));
	}

	@Step("click next button on Account Information Page")
	public void clickNextButton1() {
		AppUtils.waitForElementPresent(element(nextButton1));
		element(nextButton1).click();
		CommonUtils.logStepAsPassedInExtentReport(String.format("click next button on Account Information Page"));
	}

	@Step("verify Account Page title is displayed")
	public void verifyAccountPageTitleIsDisplayed() throws InterruptedException {
		AppUtils.waitForElementPresent(element(AccountsSection));
		Assert.assertTrue(AppUtils.isElementDisplayed(element(AccountsSection)));
		Assert.assertTrue(AppUtils.isElementDisplayed(element(recentlyViewedTitle)));
		Assert.assertTrue(AppUtils.isElementDisplayed(element(newButton)));
		CommonUtils.logStepAsPassedInExtentReport(String.format("verify Account Page title is displayed"));
	}

	@Step("verify New Account Page title is displayed")
	public void verifyNewAccountTitleIsDisplayed() throws InterruptedException {
		AppUtils.waitForElementPresent(element(newAccountTitle));
		Assert.assertTrue(AppUtils.isElementDisplayed(element(newAccountTitle)));
		CommonUtils.logStepAsPassedInExtentReport(String.format("verify New Account Page title is displayed"));
	}

	@Step("click on Contact radio button")
	public void selectContact() throws InterruptedException {
		AppUtils.waitForElementPresent(element(contactRadioButton));
		AppUtils.mousehover(element(contactRadioButton));
		element(contactRadioButton).click();
		CommonUtils.logStepAsPassedInExtentReport(String.format("click on Contact radio button"));
	}
	@Step("Click on Patient radio button")
	public void selectPatientOption(){
		AppUtils.waitForElementPresent(element(patientOption));
		JavascriptExecutor je = ((JavascriptExecutor) driver);
		je.executeScript("arguments[0].scrollIntoView(true);",element(patientOption));
		element(patientOption).click();
		CommonUtils.logStepAsPassedInExtentReport(String.format("click on Patient radio button"));
	}

	@Step("click on Next button")
	public void clickNext(){
		AppUtils.waitForElementPresent(element(nextButton));
		element(nextButton).click();
		CommonUtils.logStepAsPassedInExtentReport(String.format("click on Next button"));
	}

	@Step("select value from contact input lookup")
	public void selectContactInputLookup(String inputText) throws InterruptedException {
		AppUtils.waitForElementPresent(element(contactInputLookup));
		element(contactInputLookup).sendKeys(inputText);
		WebElement option = element(By.xpath("(//lightning-base-combobox-formatted-text[contains(@title,\""+inputText+"\")])[1]"));
		AppUtils.waitForElementClickable(option);
		option.click();
		CommonUtils.logStepAsPassedInExtentReport(String.format("select value '%s' from contact input lookup",inputText));
	}

	@Step("update the contact details")
	public void updateContactDetails() throws InterruptedException {
		log.info("\"First Name\")");
		AppUtils.enterTextInField(BaseTest.getTestData("contact_FN"),"First Name");
		log.info("\"Middle Name\")");
		AppUtils.enterTextInField(BaseTest.getTestData("contact_MN"),"Middle Name");
		log.info("\"Last Name\")");
		AppUtils.enterTextInField(BaseTest.getTestData("contact_LN"),"Last Name");
		log.info("\"Role\")");
		AppUtils.selectValueFromLookupField("Role",BaseTest.getTestData("role"));
		log.info("\"Home Phone\")");
		AppUtils.enterTextInField(BaseTest.getTestData("home_phone"),"Home Phone");
		log.info("\"Mobile Phone\")");
		AppUtils.enterTextInField(BaseTest.getTestData("mobile_phone"),"Mobile Phone");
		log.info("\"Primary\")");
		AppUtils.selectDropDownField(BaseTest.getTestData("primary"),"Primary");
		log.info("\"Preferred Communication Channel\")");
		AppUtils.selectDropDownField(BaseTest.getTestData("preferred_communication_channel"),"Preferred Communication Channel");
		log.info("\"Preferred Contact Time\")");
		AppUtils.selectDropDownField(BaseTest.getTestData("preferred_contact_time"),"Preferred Contact Time");
		log.info("\"State\")");
		AppUtils.selectDropDownField(BaseTest.getTestData("state"),"State");
		log.info("\"Address 1\")");
		AppUtils.enterTextInField(BaseTest.getTestData("address_1"),"Address 1");
		log.info("\"Address 2\")");
		AppUtils.enterTextInField(BaseTest.getTestData("address_2"),"Address 2");
		log.info("\"Address 3\")");
		AppUtils.enterTextInField(BaseTest.getTestData("address_3"),"Address 3");
		log.info("\"City\")");
		AppUtils.enterTextInField(BaseTest.getTestData("city"),"City");
		log.info("\"Zip Code\")");
		AppUtils.enterTextInField(BaseTest.getTestData("zip_code"),"Zip Code");
	}

	@Step("click on Save button")
	public void clickSaveButton() throws InterruptedException {
		element(saveButton).click();
		CommonUtils.logStepAsPassedInExtentReport(String.format("click on Save button"));
	}

	@Step("click on Save button on Verify Address Popup")
	public void handleVerifyAddressAlert() throws InterruptedException {
		waitForElementPresent(element(verifyAddressModalHeader), 1, 200);
		if(isElementDisplayed(element(verifyAddressModalHeader))){
			jseClick(element(modalsaveButton));
		}
		CommonUtils.logStepAsPassedInExtentReport(String.format("click on Save button on Verify Address Popup"));
	}

	@Step("verify success message once record is created created")
	public void validateSuccessMessage() throws InterruptedException {
		if(isElementDisplayed(element(By.xpath("//lightning-spinner")))){
			waitForElementNotPresent(element(By.xpath("//lightning-spinner")));
		}
		AppUtils.waitForElementPresent(element(successMessage));
		Assert.assertTrue(isElementDisplayed(element(successMessage)));
		CommonUtils.logStepAsPassedInExtentReport(String.format("verify success message once record is created created"));
	}

	@Step("delete Contact record")
	public void deleteContact() throws InterruptedException {
		waitForElementPresent(element(deleteButton));
		jseClick(element(deleteButton));
		waitForElementPresent(element(deletePersonAccountPopup));
		waitForElementClickable(element(popupDeleteButton));
		jseClick(element(popupDeleteButton));
		CommonUtils.logStepAsPassedInExtentReport(String.format("delete Contact record"));
	}

	@Step("verify success message once record is created created")
	public void validateSuccessfulDeleteMessage(String contactname) throws InterruptedException {
//		if(isElementDisplayed(element(By.xpath("//lightning-spinner")))){
//			waitForElementNotPresent(element(By.xpath("//lightning-spinner")));
//		}
		WebElement message = element(By.xpath(successDeleteMessage));
		AppUtils.waitForElementPresent(message);
		Assert.assertEquals(message.getText(),"Account \""+contactname+"\" was deleted. Undo","successful delete message not displayed");
		CommonUtils.logStepAsPassedInExtentReport(String.format("verify success message once record is created created"));
	}
	@Step("get name of the contact person from the Account tab")
	public String getCreatedContactName() {
		waitForElementPresent(element(contactNameHeader));
		return element(contactNameHeader).getText();
	}

	@Step("navigate to the contact account")
	public void openContact(String expectedContactName) throws InterruptedException {
		WebElement contact = element(By.xpath(String.format(contactLink,expectedContactName)));
		waitForElementPresent(contact);
		jseClick(contact);
		CommonUtils.logStepAsPassedInExtentReport(String.format("navigate to the contact account"));
	}
}
