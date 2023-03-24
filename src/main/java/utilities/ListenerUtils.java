package utilities;

import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.Parameters;

import static utilities.Functions.driver;

public class ListenerUtils implements ITestListener {
    @Override
    public void onTestFailure(ITestResult result) {
        if (Functions.test_type !=null && Functions.test_type.equalsIgnoreCase("ui")){
            System.out.println("saving screenshot to allure report");
            Functions.saveFailureScreenShot();
        }

    }

}
