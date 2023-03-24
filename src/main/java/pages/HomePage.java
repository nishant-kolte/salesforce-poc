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
	@FindBy (xpath="//a[@class='btn btn-black navbar-btn']")
	public static WebElement logout;

	@Step("get navigation button")
	public WebElement getNavigationButton()
	{
		return navigationButton;
	}

	public void clicknavigationButton() {
		navigationButton.click();
	}

	public void clickLogout(){
		this.logout.click();
	}
}
