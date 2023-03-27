package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.DriverInit;
import utilities.Functions;

public class NewAccountPage extends Functions {

    static WebDriver driver= DriverInit.driver;
    public NewAccountPage(){
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath="//div[text() = 'Used for creating a patient account']")
    public static WebElement patientOption;

    @FindBy(xpath="//button[@type = 'button']/span[text()='Next']")
    public static WebElement nextButton;

    public void selectPatientOption() throws InterruptedException{
        Thread.sleep(2000);
        JavascriptExecutor je = ((JavascriptExecutor) driver);
        je.executeScript("arguments[0].scrollIntoView(true);",patientOption);
        waitForElementClickable(patientOption,"60");
        patientOption.click();
    }

    public void clickNextButton(){
        nextButton.click();
    }
}
