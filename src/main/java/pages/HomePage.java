package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.DriverInit;

public class HomePage {
	static WebDriver driver=DriverInit.driver;
	public HomePage(){
		PageFactory.initElements(driver, this);
	}

	@FindBy (xpath="//button[@title=\"Show Navigation Menu\"]")
	public static WebElement navigationButton;
	@FindBy (xpath="//*[text()= 'Log Out']")
	public static WebElement logout;

	@FindBy (xpath="//*[contains(@class,'profileTrigger')]")
	public static WebElement viewProfile;

	@FindBy (xpath="//*[@href='/lightning/o/Account/home']")
	public static WebElement accountOption;

	@FindBy (xpath="//a[@title = 'New']")
	public static WebElement newButton;

	@Step("get navigation button")
	public WebElement getNavigationButton()
	{
		return navigationButton;
	}

	public void clickNavigationButton() throws InterruptedException {
		Thread.sleep(1000);
		navigationButton.click();

	}
	public void clickAccountOption(){
		accountOption.click();
	}

	public void clickNewButton() throws InterruptedException{
		Thread.sleep(3000);
		newButton.click();
	}

	public void clickLogout(){
		viewProfile.click();
		logout.click();
	}
}
