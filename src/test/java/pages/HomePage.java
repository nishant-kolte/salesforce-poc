package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import tests.ui.BaseTest;
import utilities.AppUtils;
import utilities.CommonUtils;

import java.util.List;

import static tests.ui.BaseTest.driver;
import static utilities.AppUtils.wait;
import static utilities.AppUtils.waitForElementPresent;
import static utilities.CommonUtils.element;

public class HomePage {
	By ACTICStitle = By.xpath("//span[@title=\"ACTICS PRM\"]");
	By envTitle = By.xpath("//span[contains(text(),\"Sandbox: Regression\")]");
	By navigationButton = By.xpath("//span[contains(text(),\"Show Navigation Menu\")]/parent::button");
	By accountOption = By.xpath("//li[@role=\"presentation\"]//descendant::a[@data-itemid=\"Account\"]");

	By navMenuList = By.xpath("//div[@id=\"navMenuList\"]");
	@Step("click on navigation button dropdown")
	public void clicknavigationButton() throws InterruptedException {
		AppUtils.waitForElementClickable(element(navigationButton));
		AppUtils.jseClick(element(navigationButton));

		if(!AppUtils.isElementDisplayed(element(navMenuList))){
			AppUtils.waitForElementClickable(element(navigationButton));
			AppUtils.jseClick(element(navigationButton));
		}
		CommonUtils.logStepAsPassedInExtentReport(String.format("click on navigation button dropdown"));
	}

	@Step("click on account option from navigation dropdown")
	public void clickAccountOption() throws InterruptedException {
		AppUtils.waitForElementClickable(element(accountOption));
		BaseTest.log.info("clicking account option button");
		element(accountOption).click();
		Thread.sleep(3000);
		CommonUtils.logStepAsPassedInExtentReport(String.format("click on account option from navigation dropdown"));
	}

	@Step("verify ACTICS PRM and Env title displayed on the Home Page")
	public void verifyHomePageTitleIsDisplayed() throws InterruptedException {
//		AppUtils.waitForElementLoad(element(ACTICStitle));
		AppUtils.waitForElementPresent(element(ACTICStitle));
		Assert.assertTrue(AppUtils.isElementDisplayed(element(envTitle)),"env title not displayed");
		Assert.assertTrue(AppUtils.isElementDisplayed(element(ACTICStitle)),"ACTICS PRM title not displayed");
//		BaseTest.log.info("title verified");
		CommonUtils.logStepAsPassedInExtentReport(String.format("verify ACTICS PRM and Env title displayed on the Home Page"));
	}

	@Step("close all open tabs")
	public void closeAllTabs() throws InterruptedException {
		wait = new WebDriverWait(driver,5);
		List<WebElement> tabs;
		try{
			tabs = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//button[contains(@title,'Close')]")));
			for (WebElement tab:tabs){
				Thread.sleep(1000);
				AppUtils.jseClick(tab);
				BaseTest.log.info("tab closed!");
			}
		}
		catch (Exception e){
			BaseTest.log.info("no tabs open!");
		}
		CommonUtils.logStepAsPassedInExtentReport(String.format("close all open tabs"));
	}




}
