package tests.db;

import io.qameta.allure.Step;
import org.testng.Assert;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Employee {
    static String getEmployeeSQL = "Select * from Employee";

    @Step("validate employee id")
    public static void verifyEmpId(ResultSet resultSet, int expected) throws SQLException {
        Assert.assertEquals(resultSet.getInt("emp_id"),expected,"employee id mismatch");
    }
    @Step("validate employee name")
    public static void verifyEmpName(ResultSet resultSet, String expected) throws SQLException {
        Assert.assertEquals(resultSet.getString("emp_name"),expected,"employee name mismatch");
    }
    @Step("validate employee salary")
    public static void verifyEmpSalary(ResultSet resultSet, int expected) throws SQLException {
        Assert.assertEquals(resultSet.getInt("emp_salary"),expected,"employee salary mismatch");
    }
    @Step("validate employee assigned projected")
    public static void verifyEmpProject(ResultSet resultSet, String expected) throws SQLException {
        Assert.assertEquals(resultSet.getString("assigned_project"),expected,"employee assigned project mismatch");
    }
    @Step("validate employee contact")
    public static void verifyEmpContact(ResultSet resultSet, int expected) throws SQLException {
        Assert.assertEquals(resultSet.getInt("emp_contact"),expected,"employee contact mismatch");
    }
}
