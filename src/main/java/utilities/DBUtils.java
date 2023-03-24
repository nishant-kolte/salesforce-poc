package utilities;

import io.qameta.allure.Step;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtils {
    private static Connection connection;
    public static Properties db_config;

    public static void readConfig() throws IOException {
        db_config = new Properties();
        FileInputStream objfile = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\configs\\db_configs.properties");
        db_config.load(objfile);
    }

    @Step("create  connection to the database")
    public static void setConnection() {

        try {
            readConfig();
            connection = DriverManager.getConnection(db_config.getProperty("url"), db_config.getProperty("user"), db_config.getProperty("password"));//Establishing connection
            System.out.println("Connected With the database successfully");

        } catch (SQLException e) {
            System.out.println("Error while connecting to the database");
        } catch (IOException e) {
            System.out.println("Error while reading the db configs");
            e.printStackTrace();
        }
    }

    @Step("run sql query to fetch records from the database")
    public static ResultSet getValues(String sqlQuery) throws SQLException {
        ResultSet resultSet;
        try {
            resultSet=connection.createStatement().executeQuery(sqlQuery);
            System.out.println("Successfully fetched records from the database");
            return resultSet;
        } catch (SQLException e) {
            System.out.println("Error while fetching records from the database");
            return null;
        }
    }

    @Step("close connection to the database")
    public static void closeConnection() throws SQLException {
        connection.close();
    }
}
