package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import utilities.DriverInit;
import utilities.Functions;

import java.util.Random;

public class AccountInformationPage extends Functions {
    static WebDriver driver= DriverInit.driver;
    public AccountInformationPage(){
        PageFactory.initElements(driver, this);
    }
    @FindBy(xpath="//button[@name = 'next']")
    public static WebElement nextButton;
    @FindBy(xpath="//input[@name='PatientConnect__PC_First_Name__c']")
    public static WebElement firstNameField;
    @FindBy(xpath="//input[@name='PatientConnect__PC_Last_Name__c']")
    public static WebElement lastNameField;
    @FindBy(xpath="//button[@name='PHX_Insurance_Status__c']")
    public static WebElement insuranceStatusField;
    @FindBy(xpath="//button[@name='saveRecord']")
    public static WebElement saveRecordButton;
    @FindBy(xpath="//div[@data-key='success']/div/div/div/span")
    public static WebElement successMessage;
    @FindBy(xpath="//button[@name='Delete']")
    public static WebElement deleteAccountButton;
    @FindBy(xpath="//button[@name='Delete']")
    public static WebElement deleteButtonOnDialogBox;
    @FindBy(xpath="//span[@class='toastMessage slds-text-heading--small forceActionsText']")
    public static WebElement deleteAccountMessage;



    public void clickNextButton(){
        waitForElementClickable(nextButton,"60");
        nextButton.click();
    }
    public void enterUserData(){
        firstNameField.sendKeys("Abc");
        lastNameField.sendKeys("Xyz");
        Actions actions = new Actions(driver);
        actions.click(insuranceStatusField).sendKeys(Keys.ARROW_DOWN,Keys.ARROW_DOWN,Keys.ENTER).build().perform();
    }
    public void clickSaveButton(){
        saveRecordButton.click();
    }
    public void verifySuccessMessage(){
        waitForElementLoad(successMessage,"60");
        System.out.println(successMessage.getText());
        Assert.assertEquals(successMessage.getText(), "Record was saved.");
    }
    public void deleteAccount(){
        waitForElementLoad(deleteAccountButton,"60");
        deleteAccountButton.click();
        waitForElementClickable(deleteButtonOnDialogBox,"60");
        Actions actions = new Actions(driver);
        actions.moveToElement(deleteButtonOnDialogBox).click().build().perform();
        //deleteButtonOnDialogBox.click();
    }
    public void verifyDeleteMessage(){
        waitForElementLoad(deleteAccountMessage,"60");
        System.out.println(deleteAccountMessage.getText());
        Assert.assertEquals(deleteAccountMessage.getText(),"Account \"Abc Xyz\" was deleted. ");
    }


}
