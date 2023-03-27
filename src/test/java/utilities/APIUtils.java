package utilities;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class APIUtils {
    public static Properties api_config;
    public static RequestSpecification request;
    public static Response response;

    public static void readAPIConfigs() throws IOException {
        api_config = new Properties();
        FileInputStream objfile = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\configs\\api_configs.properties");
        api_config.load(objfile);
    }
    public static void setBaseURL() throws IOException {
        //RestAssured.baseURI = "https://gorest.co.in/public/v2";
       RestAssured.baseURI =api_config.getProperty("base_url");
    }
    public static RequestSpecification initRequest() {
        response = null;
        request = null;
        request = RestAssured.given().filter(new AllureRestAssured());
        return request;
    }
//    @Step("make a post request tests.api call")
    public static Response postRequest(String endpoint, String formdata){
        String token = getToken();
        request = initRequest();
        request.header("Authorization", "Bearer "+token);
        request.header("Content-Type", "application/json");
        request.body(formdata);
        response =request.post(endpoint);
        return response;
    }
    public static Response putRequest(String endpoint, String formdata){
        String token = getToken();
        request = initRequest();
        request.header("Authorization", "Bearer "+token);
        request.header("Content-Type", "application/json");
        request.body(formdata);
        response =request.put(endpoint);
        return response;
    }
    public static Response getRequest(String endpoint){
        String token = getToken();
        request = initRequest();
        request.header("Authorization", "Bearer "+token);
        request.header("Content-Type", "application/json");
      //  request.body(formdata);
        response =request.get(endpoint);
        return response;
    }
    public static Response deleteRequest(String endpoint){
        String token = getToken();
        request = initRequest();
        request.header("Authorization", "Bearer "+token);
        request.header("Content-Type", "application/json");
        //  request.body(formdata);
        response =request.delete(endpoint);
        return response;
    }

    @Step("get access token call")
    private static String getToken() {
        //make an auth API call to get token if available
        String token = APIUtils.api_config.getProperty("access_token");
        return token;
    }
}
