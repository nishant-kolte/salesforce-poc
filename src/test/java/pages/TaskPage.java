package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import tests.ui.BaseTest;
import utilities.AppUtils;

import static utilities.CommonUtils.element;

public class TaskPage {

    By moreActionsIcon = By.xpath("//a[@title='Show 7 more actions']/lightning-icon");
    By changePriorityOption = By.xpath("//div[text()='Change Priority']");
            //div[@title='Change Priority']");
    By priorityDropdown = By.cssSelector("a.select");
    By priorityOptionToSelect = By.xpath("//div[@class='select-options']//a[text()='High']");
    By saveButtonInChangePriorityWindow = By.xpath("//button[@data-aura-class='uiButton']/span[text()='Save']");
    By saveTaskSuccessMessageLabel = By.xpath("//span[@data-aura-class= 'forceActionsText']");
    @Step("click on more actions icon")
    public void clickActionsIcon() throws InterruptedException {
        AppUtils.waitForElementClickable(element(moreActionsIcon));
        BaseTest.log.info("clicking the actions icon");
        element(moreActionsIcon).click();
        Thread.sleep(2000);
    }
    @Step("click on Change Priority action")
    public void clickChangePriorityOption() throws InterruptedException {
        AppUtils.waitForElementClickable(element(changePriorityOption));
        BaseTest.log.info("clicking the Change Priority Action");
        AppUtils.jseClick(element(changePriorityOption));
        //element(changePriorityOption).click();
        Thread.sleep(2000);
    }

    @Step("click Priority dropdown")
    public void selectPriorityFromDropdown() throws InterruptedException {
        AppUtils.waitForElementClickable(element(priorityDropdown));
        BaseTest.log.info("click Priority dropdown");
        element(priorityDropdown).click();
        Thread.sleep(2000);
        AppUtils.waitForElementClickable(element(priorityOptionToSelect));
        element(priorityOptionToSelect).click();
    }

    @Step("click Save button in change priority window")
    public void clickSaveButtonInChangePriorityWindow() throws InterruptedException {
        AppUtils.waitForElementClickable(element(saveButtonInChangePriorityWindow));
        BaseTest.log.info("click Save Button in the Change Priority window");
        element(saveButtonInChangePriorityWindow).click();
    }

    @Step("")
    public String getTaskSavedSuccessMessage() throws InterruptedException {
        Thread.sleep(2000);
        AppUtils.waitForElementLoad(element(saveTaskSuccessMessageLabel));
        return element(saveTaskSuccessMessageLabel).getText();
    }




}
