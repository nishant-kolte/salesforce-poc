package utilities;

import org.testng.ITestListener;
import org.testng.ITestResult;
import tests.ui.BaseTest;

public class ListenerUtils implements ITestListener {
    @Override
    public void onTestFailure(ITestResult result) {
        if (result.getTestClass().getName().split("\\.")[1].equalsIgnoreCase("ui")){
            BaseTest.log.info("saving screenshot to allure report");
            CommonUtils.saveFailureScreenShot();
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
    }
}
