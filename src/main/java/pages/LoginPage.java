package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.DriverInit;

public class LoginPage {
	static WebDriver driver= DriverInit.driver;

	public LoginPage(){
		PageFactory.initElements(driver, this);
	}
	//a[text()='Login' and @class="btn btn-link navbar-btn"]

	@FindBy (xpath="//a[text()='Login' and @class=\"btn btn-link navbar-btn\"]")
	public static WebElement loginButton;

	@FindBy (xpath="//input[@type='email']")
	public static WebElement username;
	
	@FindBy (xpath="//input[@type='password']")
	public static WebElement password;
	
	@FindBy (xpath="//input[@id='Login']")
	public static WebElement LoginButton;

	@FindBy (xpath="//div[contains(text(),\"No match for E-Mail and/or Password.\")]")
	public static WebElement errorMessage;

	@FindBy (xpath="//input[@id='input-pin']")
	public static WebElement pin;

	@FindBy (xpath="//button[@class='btn btn-primary btn-lg']")
	public static WebElement continueButton;

	@Step("go to Login page")
	public void goToLogin(){
		this.loginButton.click();
	}

	@Step("enter username {0}")
	public void enterUsername(String username){
		this.username.sendKeys(username);
	}

	@Step("enter password {0}")
	public void enterPassword(String password){
		this.password.sendKeys(password);
	}

	@Step("click login button")
	public void clickLoginButton(){
		this.LoginButton.click();
	}

	@Step("enter pin")
			public void enterPin(String pin){
		this.pin.sendKeys(pin);
			}

	@Step("click on continue")
			public void clickContinueButton()
	{
		this.continueButton.click();
	}

}
