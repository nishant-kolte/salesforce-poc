package utilities;
import io.qameta.allure.Step;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class InitTestData {
        @Step("read test data file")
        public static Properties getTestData(String env) throws IOException {
            Properties testdata = new Properties();
            //FileInputStream objfile = new FileInputStream(System.getProperty("C:\\Users\\Ashwini\\Downloads\\IdeaProjects\\IdeaProjects\\Intelli Docs\\IntelliDocs\\Object_Repository.properties"));
            if (env.equalsIgnoreCase("qa")){
                FileInputStream objfile = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\test_data\\QA_testdata.properties");
                testdata.load(objfile);
            }
            else if (env.equalsIgnoreCase("dev")){
                FileInputStream objfile = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\test_data\\DEV_testdata.properties");
                testdata.load(objfile);
            }
            else if (env.equalsIgnoreCase("prod")){
                FileInputStream objfile = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\test_data\\PROD_testdata.properties");
                testdata.load(objfile);
            }
            return testdata;
        }
    }


