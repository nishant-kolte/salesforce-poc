package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import utilities.AppUtils;

import static utilities.CommonUtils.actionObject;
import static utilities.CommonUtils.element;

public class AccountInformationPage {
    private By nextButton= By.xpath("//button[@name = 'next']");
    private By firstNameField= By.xpath("//input[@name='PatientConnect__PC_First_Name__c']");
    private By lastNameField= By.xpath("//input[@name='PatientConnect__PC_Last_Name__c']");
    private By insuranceStatusField= By.xpath("//button[@name='PHX_Insurance_Status__c']");
    private By saveRecordButton= By.xpath("//button[@name='saveRecord']");
    private By successMessage= By.xpath("//div[@data-key='success']/div/div/div/span");

//    @FindBy(xpath="//button[@name='Delete']")
//    public static WebElement deleteAccountButton;
//    @FindBy(xpath="//button[@name='Delete']")
//    public static WebElement deleteButtonOnDialogBox;
//    @FindBy(xpath="//span[@class='toastMessage slds-text-heading--small forceActionsText']")
//    public static WebElement deleteAccountMessage;



    public void clickNextButton(){
        AppUtils.waitForElementPresent(element(nextButton));
        element(nextButton).click();
    }
    public void enterUserData(){
       element(firstNameField).sendKeys("Abc");
        element(lastNameField).sendKeys("Xyz");
        //Actions actions = new Actions(driver);
        actionObject().click(element(insuranceStatusField)).sendKeys(Keys.ARROW_DOWN,Keys.ARROW_DOWN,Keys.ENTER).build().perform();
    }
    public void clickSaveButton(){
        element(saveRecordButton).click();
    }
    public void verifySuccessMessage(){
        AppUtils.waitForElementPresent(element(successMessage));
        //waitForElementLoad(successMessage,"60");
        System.out.println(element(successMessage).getText());
        Assert.assertEquals(element(successMessage).getText(), "Record was saved.");
    }
/*
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
*/


}
