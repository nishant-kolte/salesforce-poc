package pages;

import io.qameta.allure.Step;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import tests.ui.BaseTest;
import utilities.AppUtils;
import utilities.CommonUtils;

import static tests.ui.BaseTest.driver;
import static utilities.CommonUtils.element;

public class LoginPage {
	By username = By.xpath("//input[@type='email']");
	By password = By.xpath("//input[@type='password']");
	By LoginButton = By.xpath("//input[@id='Login']");

	@Step("wait for login page element to load")
	public void waitForPageLoad(){
		AppUtils.waitForElementLoad(element(username));
	}

	@Step("enter username {0}")
	public void enterUsername(String username){
		element(this.username).clear();
		element(this.username).sendKeys(username);
//		CommonUtils.logStepAsPassedInExtentReport(String.format("enter username %s",username));
	}

	@Step("enter password {0}")
	public void enterPassword(String password){
		element(this.password).clear();
		element(this.password).sendKeys(password);
//		CommonUtils.logStepAsPassedInExtentReport(String.format("enter password %s",password));
	}

	@Step("click login button")
	public void clickLoginButton(){
		element(this.LoginButton).click();
//		CommonUtils.logStepAsPassedInExtentReport(String.format("click login button"));
	}
}
