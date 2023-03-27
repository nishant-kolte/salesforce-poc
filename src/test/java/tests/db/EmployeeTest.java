package tests.db;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.DBUtils;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeTest {

    @BeforeClass
    public void startUp(){
        DBUtils.setConnection();
    }

    @AfterClass
    public void tearDown() throws SQLException {
        DBUtils.closeConnection();
    }

    @Feature("Database test")
    @Story("DBT-001: validation of Employee details")
    @Test(description = "to verify the employee records are fetched correctly from DB")
    @Description("Validation of Employee Record")
    public void getEmployeeTest() throws SQLException {
        ResultSet resultSet = DBUtils.getValues(Employee.getEmployeeSQL);
        resultSet.next();
        Employee.verifyEmpId(resultSet,1);
        Employee.verifyEmpName(resultSet,"Nishant Kolte");
        Employee.verifyEmpSalary(resultSet,1000);
        Employee.verifyEmpProject(resultSet,"Healhcare");
        Employee.verifyEmpContact(resultSet,111111);
    }
}
