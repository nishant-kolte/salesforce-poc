package pages;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import utilities.AppUtils;
import utilities.CommonUtils;

import static utilities.CommonUtils.actionObject;
import static utilities.CommonUtils.element;
public class AccountInformationPage {
    private By nextButton = By.xpath("//button[@name = 'next']");
    private By firstNameField = By.xpath("//input[@name='PatientConnect__PC_First_Name__c']");
    private By lastNameField = By.xpath("//input[@name='PatientConnect__PC_Last_Name__c']");
    private By insuranceStatusField = By.xpath("//button[@name='PHX_Insurance_Status__c']");
    private By saveRecordButton = By.xpath("//button[@name='saveRecord']");
    private By successMessage = By.xpath("//div[@data-key='success']/div/div/div/span");
    private By dob = By.xpath("//input[@name='PatientConnect__PC_Date_of_Birth__c']");

//    @FindBy(xpath="//button[@name='Delete']")
//    public static WebElement deleteAccountButton;
//    @FindBy(xpath="//button[@name='Delete']")
//    public static WebElement deleteButtonOnDialogBox;
//    @FindBy(xpath="//span[@class='toastMessage slds-text-heading--small forceActionsText']")
//    public static WebElement deleteAccountMessage;


    @Step("click on Next button")
    public void clickNextButton() {
        AppUtils.waitForElementPresent(element(nextButton));
        element(nextButton).click();
        CommonUtils.logStepAsPassedInExtentReport(String.format("click on Next button"));
    }

    @Step("enter user data")
    public void enterUserData() {
        element(firstNameField).sendKeys("Abc");
        element(lastNameField).sendKeys("Xyz");
        element(dob).sendKeys(enterDOB("04/03/2001"));
        //Actions actions = new Actions(driver);
        actionObject().click(element(insuranceStatusField)).sendKeys(Keys.ARROW_DOWN, Keys.ARROW_DOWN, Keys.ENTER).build().perform();
        CommonUtils.logStepAsPassedInExtentReport(String.format("enter user data"));
    }

    @Step("click Save Button")
    public void clickSaveButton() {
        element(saveRecordButton).click();
        CommonUtils.logStepAsPassedInExtentReport(String.format("click Save Button"));
    }

    @Step("verify success message once record is created created")
    public void verifySuccessMessage() {
        AppUtils.waitForElementPresent(element(successMessage));
        //waitForElementLoad(successMessage,"60");
        System.out.println(element(successMessage).getText());
        Assert.assertEquals(element(successMessage).getText(), "Record was saved.");
        CommonUtils.logStepAsPassedInExtentReport(String.format("verify success message once record is created created"));
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
    public String enterDOB(String dateOfBirth) { //dateOfBirth should be in dd/mm/yyyy
        String mon = "";
        String[] dateSplit = dateOfBirth.split("/",0);
        switch (dateSplit[1]) {

            case "01": mon = "Jan";
            break;
            case "02": mon = "Feb";
            break;
            case "03": mon = "Mar";
            break;
            case "04": mon = "Apr";
            break;
            case "05": mon = "May";
            break;
            case "06": mon = "Jun";
            break;
            case "07": mon = "Jul";
            break;
            case "08": mon = "Aug";
            break;
            case "09": mon = "Sep";
            break;
            case "10": mon = "Oct";
            break;
            case "11": mon = "Nov";
            break;
            case "12": mon = "Dec";
            break;
            default : System.out.println("Invalid Month!");

        }
        return mon+" "+dateSplit[0]+", "+dateSplit[2];



    }
}


